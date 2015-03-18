package com.hi.module.msg.service;


import android.app.Application;

import com.avos.avoscloud.AVMessage;
import com.avos.avoscloud.Session;
import com.format.utils.Md5Util;
import com.hi.common.db.E_DB_ImgUrlMode;
import com.hi.common.db.E_DB_MsgType;
import com.hi.common.http.E_Http_QNType;
import com.hi.common.http.E_Http_SendState;
import com.hi.dao.model.T_IMsg;
import com.hi.dao.supImpl.Dao_IMsg;
import com.hi.module.base.application.AppContextApplication;
import com.hi.module.base.application.AppManager;
import com.hi.receiver.model.IMMessageBean;
import com.hi.service.image.Enum_ImgScaleType;
import com.hi.service.image.UploadImageService;
import com.hi.utils.FormatUtils;
import com.hi.utils.PathUtils;
import com.hi.utils.ViewHandleUtils;
import com.hi.utils.network.NetAsyncTask;

/**
 * User: Zerui
 * Time: 2015/3/9 16:17
 */
public class MsgAgent {
    /**
     * Created by lzw on 14/12/22.
     */
    public interface SendCallback {
        void onError(Exception e);

        void onStart(T_IMsg msg);

        void onSuccess(T_IMsg msg);
    }

    private String toId;

    public MsgAgent(String toId) {
        this.toId = toId;
    }

    public interface MsgBuilderHelper {
        void specifyType(MsgBuilder msgBuilder);

        void setFromUserInfo(MsgBuilder msgBuilder);
    }

    public void createAndSendMsg(MsgBuilderHelper msgBuilderHelper, final SendCallback sendCallback) {
        final MsgBuilder builder = new MsgBuilder();
        builder.target(toId);
        msgBuilderHelper.specifyType(builder);
        msgBuilderHelper.setFromUserInfo(builder);
        final T_IMsg msg = builder.preBuild();
        Dao_IMsg.addMessage(msg, false, false);
        sendCallback.onStart(msg);
        uploadAndSendMsg(msg, sendCallback);
    }

    /**
     * 发送消息
     *
     * @param msg
     * @param callback
     */
    public void uploadAndSendMsg(final T_IMsg msg, final SendCallback callback) {
        //如果消息为图片且为本地形式（需要将图片上传到服务器）
        if ((msg.getMsgFormat()== IMMessageBean.Enum_IM_MsgFormat.IMAGE.value())
                &&(msg.getImageUrlMode() == E_DB_ImgUrlMode.LOCAL.value())) {
            getImageByPath(msg,callback);
            return;
        }
        new NetAsyncTask(AppContextApplication.getInstance(), false) {
//            String uploadUrl;

            @Override
            protected void doInBack() throws Exception {
//                uploadUrl = MsgBuilder.uploadMsg(msg);
            }

            @Override
            protected void onPost(Exception e) {
                if (e != null) {
                    e.printStackTrace();
                    Dao_IMsg.updateMsgState(msg.getObjectId(), E_Http_SendState.FAIL);
                    callback.onError(e);
                } else {
                    sendMsg(msg);
                    Dao_IMsg.updateMsgState(msg.getObjectId(), E_Http_SendState.SUCCESS);
                    callback.onSuccess(msg);
                }
            }
        }.execute();
    }

    private T_IMsg sendMsg(T_IMsg msg) {
        Session session = ChatService.getSession();
        AVMessage avMessage = msg.fromAVMessage();
        session.sendMessage(avMessage);
        return msg;
    }

    /**
     * 重新发送
     *
     * @param msg
     * @param sendCallback
     */
    public static void resendMsg(T_IMsg msg, SendCallback sendCallback) {
        Dao_IMsg.updateMsgState(msg.getObjectId(), E_Http_SendState.START);
        sendCallback.onStart(msg);
        MsgAgent msgAgent = new MsgAgent(msg.getUid());
        msgAgent.uploadAndSendMsg(msg, sendCallback);
    }

    private void getImageByPath(final T_IMsg msg, final SendCallback sendCallBack) {
        String localSelectPath=msg.getMsg();
        // 压缩处理
        final String newPath = PathUtils.getChatFilePath(Md5Util.myUUID());
        ViewHandleUtils.compressImage(localSelectPath, newPath);
        UploadImageService service = new UploadImageService();
        service.setHeight(400).setImageType(Enum_ImgScaleType.TYPE_0)
                .setImagePath(newPath)
                .setUploadType(E_Http_QNType.IMMSG)
                .uploadImage(new UploadImageService.CallBack() {

                    @Override
                    public void onSuccess(String url) {
                        // TODO Auto-generated method stub
//                imgSendMethod(url);
                        msg.setMsg(url);
                        sendMsg(msg);
                        Dao_IMsg.updateMsgState(msg.getObjectId(), E_Http_SendState.SUCCESS);
                        Dao_IMsg.updateImageMode(msg.getObjectId(), url, E_DB_ImgUrlMode.URL);
                        sendCallBack.onSuccess(msg);
                    }

                    @Override
                    public void onStart() {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void onFinally() {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void onFail() {
                        // TODO Auto-generated method stub
                        Dao_IMsg.updateMsgState(msg.getObjectId(), E_Http_SendState.FAIL);
                        sendCallBack.onError(null);
//                T_IMsg iMsg = items.get(items.size() - 1);
//                iMsg.setName("发送失败");
//                iMsg.setTime(FormatUtils.getCurrentDateValue_long());
//                iMsg.setSendState(E_Http_SendState.FAIL.value());
//                adapter.notifyDataSetChanged();
//                MsgSendFail(iMsg);
                    }
                });
    }
}
