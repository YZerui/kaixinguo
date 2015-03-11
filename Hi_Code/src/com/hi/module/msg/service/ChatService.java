package com.hi.module.msg.service;

import android.app.Notification;
import android.content.Context;

import com.android.ruifeng.hi.R;
import com.avos.avoscloud.AVMessage;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.Session;
import com.avos.avoscloud.SessionManager;
import com.exception.utils.P;
import com.hi.common.db.E_DB_ImgUrlMode;
import com.hi.common.db.E_DB_MsgSource;
import com.hi.common.db.E_DB_MsgType;
import com.hi.common.http.E_Http_SendState;
import com.hi.common.param.Enum_Page;
import com.hi.dao.model.T_IMsg;
import com.hi.dao.supImpl.Dao_IMsg;
import com.hi.dao.supImpl.Dao_SelfIfo;
import com.hi.module.base.application.TabBarActivity;
import com.hi.module.msg.ui.MsgDetailActivity;
import com.hi.receiver.IMessageReceiver;
import com.hi.receiver.model.IMMessageBean;
import com.hi.utils.BroadcastUtil;
import com.hi.utils.FunUtils;
import com.hi.utils.ViewHandleUtils;
import com.hi.utils.network.NetAsyncTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * User: Zerui
 * Time: 2015/3/9 16:16
 */
public class ChatService {
    private List<String> peerIds = new ArrayList<String>();

    /**
     * 获取即时通讯的会话
     *
     * @return
     */
    public static Session getSession() {
        try {
            return SessionManager.getInstance(Dao_SelfIfo.getInstance()
                    .getMid());
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }

    public static boolean isSessionPaused() {
        return IMessageReceiver.isSessionPaused();
    }
    /**
     * 关注某用户
     *
     * @param peerId
     * @return
     */
    public ChatService watchPeer(String peerId) {
        peerIds.clear();
        peerIds.add(peerId);
        getSession().watchPeers(peerIds);
        return this;
    }

    /**
     * 取消关注某用户
     *
     * @param peerId
     * @return
     */
    public ChatService unwatchPeer(String peerId) {
        peerIds.clear();
        peerIds.add(peerId);
        getSession().unwatchPeers(peerIds);
        return this;
    }

    /**
     * 对收到的消息进行处理
     *
     * @param context
     * @param avMsg
     * @param listeners
     */
    public static void onMessage(final Context context, AVMessage avMsg, final Set<IMessageReceiver.MessageListener> listeners) {
        IMMessageBean.IMMessage imMsg = IMMessageBean.fromAVMessage(avMsg);
        imMsg.setInternalMessage(avMsg);
        final T_IMsg msgBean = imMsg.toT_IMsg(E_DB_MsgSource.USER.value(),
                E_DB_MsgType.ACCOST.value(), E_DB_ImgUrlMode.URL.value());
        //处理接收的消息
        new NetAsyncTask(context,false){

            @Override
            protected void doInBack() throws Exception {

            }

            @Override
            protected void onPost(Exception e) {
                P.v("onPost");
                if(e!=null){
                    ViewHandleUtils.toastNormal("网络环境不佳");
                }

                boolean done = false;
                for (IMessageReceiver.MessageListener listener : listeners) {
                    if (listener.onMessageUpdate(msgBean.getUid(),msgBean)) {
                        done = true;
                        break;
                    }
                }
                //如果消息没有被界面即时处理，则以通知形式提醒用户
                if (!done) {
                    Dao_IMsg.addMessage(msgBean,true,true);
                    if (AVUser.getCurrentUser() != null) {
                        //通知消息到达
                        brocastMethod(msgBean.getMsg(),msgBean.getUid(),msgBean.getName(),msgBean.getHead(),context);
                    }
                }
            }
        }.execute();
    }

    /**
     * 处理已发送的消息
     * @note 如果发送成功，则更像数据库相应记录
     * @param avMsg
     * @param listeners
     */
    public static void onMessageSent(AVMessage avMsg, Set<IMessageReceiver.MessageListener> listeners) {
        IMMessageBean.IMMessage imMsg = IMMessageBean.fromAVMessage(avMsg);
        //更新发送状态
        Dao_IMsg.updateMsgState(E_Http_SendState.SUCCESS,imMsg.getObjectId(),imMsg.getU_animID());
        for (IMessageReceiver.MessageListener msgListener : listeners) {
            if (msgListener.onMessageUpdate(imMsg.getU_animID(),null)) {
                break;
            }
        }
    }


    /**
     * 处理发送失败的消息
     *
     * @param avMsg
     * @param listeners
     */
    public static void onMessageFail(AVMessage avMsg, Set<IMessageReceiver.MessageListener> listeners) {
        IMMessageBean.IMMessage imMsg = IMMessageBean.fromAVMessage(avMsg);
        //更新发送状态
        Dao_IMsg.updateMsgState(E_Http_SendState.FAIL,imMsg.getObjectId(),imMsg.getU_animID());
        for (IMessageReceiver.MessageListener msgListener : listeners) {
            if (msgListener.onMessageUpdate(imMsg.getU_animID(),null)) {
                break;
            }
        }
    }

    /**
     * 处理消息成功被接收的消息
     *
     * @param avMsg
     * @param listeners
     */
    public static void onMessageDelivered(AVMessage avMsg, Set<IMessageReceiver.MessageListener> listeners) {
        IMMessageBean.IMMessage imMsg = IMMessageBean.fromAVMessage(avMsg);
        //更新发送状态
        Dao_IMsg.updateMsgState(E_Http_SendState.DELIVERED,imMsg.getObjectId(),imMsg.getU_animID());
        for (IMessageReceiver.MessageListener msgListener : listeners) {
            if (msgListener.onMessageUpdate(imMsg.getU_animID(),null)) {
                break;
            }
        }
    }
    private static void brocastMethod(String msg, String sendId, String name, String head, Context context) {
        // TODO Auto-generated method stub
        /*
         * @tip_1 当在应用界面时，不显示通知栏，只震动
		 *
		 * @tip_2 当在对话框界面时，震动、刷新消息列表，不显示通知栏
		 *
		 * @tip_3 当不在本应用时，震动、弹出通知栏
		 */
        if (FunUtils.getRunningActivity()
                .equals(TabBarActivity.class.getName())) {
            System.out.println("Receiver TarBarActivity...");
            // 震动
            FunUtils.AcquireWakeLock(context);
            BroadcastUtil.sendBroadCast(context, Enum_Page.TAPBAR.name(), sendId);
            BroadcastUtil.sendBroadCast(context, Enum_Page.MSG.name(), sendId);
        } else if (FunUtils.getRunningActivity().equals(
                MsgDetailActivity.class.getName())) {
            System.out.println("Receiver MsgDetailActivity...");
            FunUtils.AcquireWakeLock(context);
            // 发送刷新列表的广播
//			Intent intent2 = new Intent();
//			intent2.setAction(PARAMS.MSG_CHAT_RECEIVE);
//			intent2.putExtra(PARAMS.MSG_CHAT_DATA_EXTRA, sendId);
//			context.sendBroadcast(intent2);

        } else {
            FunUtils.setNotification(context, "嗨", "有人向你搭讪了", msg,
                    sendId.getBytes().length, R.drawable.app_icon,
                    Notification.DEFAULT_ALL, sendId, name, head);
        }
    }

}
