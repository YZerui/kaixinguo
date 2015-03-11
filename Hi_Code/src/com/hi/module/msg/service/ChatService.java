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
     * ��ȡ��ʱͨѶ�ĻỰ
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
     * ��עĳ�û�
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
     * ȡ����עĳ�û�
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
     * ���յ�����Ϣ���д���
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
        //������յ���Ϣ
        new NetAsyncTask(context,false){

            @Override
            protected void doInBack() throws Exception {

            }

            @Override
            protected void onPost(Exception e) {
                P.v("onPost");
                if(e!=null){
                    ViewHandleUtils.toastNormal("���绷������");
                }

                boolean done = false;
                for (IMessageReceiver.MessageListener listener : listeners) {
                    if (listener.onMessageUpdate(msgBean.getUid(),msgBean)) {
                        done = true;
                        break;
                    }
                }
                //�����Ϣû�б����漴ʱ��������֪ͨ��ʽ�����û�
                if (!done) {
                    Dao_IMsg.addMessage(msgBean,true,true);
                    if (AVUser.getCurrentUser() != null) {
                        //֪ͨ��Ϣ����
                        brocastMethod(msgBean.getMsg(),msgBean.getUid(),msgBean.getName(),msgBean.getHead(),context);
                    }
                }
            }
        }.execute();
    }

    /**
     * �����ѷ��͵���Ϣ
     * @note ������ͳɹ�����������ݿ���Ӧ��¼
     * @param avMsg
     * @param listeners
     */
    public static void onMessageSent(AVMessage avMsg, Set<IMessageReceiver.MessageListener> listeners) {
        IMMessageBean.IMMessage imMsg = IMMessageBean.fromAVMessage(avMsg);
        //���·���״̬
        Dao_IMsg.updateMsgState(E_Http_SendState.SUCCESS,imMsg.getObjectId(),imMsg.getU_animID());
        for (IMessageReceiver.MessageListener msgListener : listeners) {
            if (msgListener.onMessageUpdate(imMsg.getU_animID(),null)) {
                break;
            }
        }
    }


    /**
     * ������ʧ�ܵ���Ϣ
     *
     * @param avMsg
     * @param listeners
     */
    public static void onMessageFail(AVMessage avMsg, Set<IMessageReceiver.MessageListener> listeners) {
        IMMessageBean.IMMessage imMsg = IMMessageBean.fromAVMessage(avMsg);
        //���·���״̬
        Dao_IMsg.updateMsgState(E_Http_SendState.FAIL,imMsg.getObjectId(),imMsg.getU_animID());
        for (IMessageReceiver.MessageListener msgListener : listeners) {
            if (msgListener.onMessageUpdate(imMsg.getU_animID(),null)) {
                break;
            }
        }
    }

    /**
     * ������Ϣ�ɹ������յ���Ϣ
     *
     * @param avMsg
     * @param listeners
     */
    public static void onMessageDelivered(AVMessage avMsg, Set<IMessageReceiver.MessageListener> listeners) {
        IMMessageBean.IMMessage imMsg = IMMessageBean.fromAVMessage(avMsg);
        //���·���״̬
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
         * @tip_1 ����Ӧ�ý���ʱ������ʾ֪ͨ����ֻ��
		 *
		 * @tip_2 ���ڶԻ������ʱ���𶯡�ˢ����Ϣ�б�����ʾ֪ͨ��
		 *
		 * @tip_3 �����ڱ�Ӧ��ʱ���𶯡�����֪ͨ��
		 */
        if (FunUtils.getRunningActivity()
                .equals(TabBarActivity.class.getName())) {
            System.out.println("Receiver TarBarActivity...");
            // ��
            FunUtils.AcquireWakeLock(context);
            BroadcastUtil.sendBroadCast(context, Enum_Page.TAPBAR.name(), sendId);
            BroadcastUtil.sendBroadCast(context, Enum_Page.MSG.name(), sendId);
        } else if (FunUtils.getRunningActivity().equals(
                MsgDetailActivity.class.getName())) {
            System.out.println("Receiver MsgDetailActivity...");
            FunUtils.AcquireWakeLock(context);
            // ����ˢ���б�Ĺ㲥
//			Intent intent2 = new Intent();
//			intent2.setAction(PARAMS.MSG_CHAT_RECEIVE);
//			intent2.putExtra(PARAMS.MSG_CHAT_DATA_EXTRA, sendId);
//			context.sendBroadcast(intent2);

        } else {
            FunUtils.setNotification(context, "��", "���������ڨ��", msg,
                    sendId.getBytes().length, R.drawable.app_icon,
                    Notification.DEFAULT_ALL, sendId, name, head);
        }
    }

}
