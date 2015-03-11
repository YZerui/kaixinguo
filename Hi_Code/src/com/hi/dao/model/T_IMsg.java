package com.hi.dao.model;

import com.avos.avoscloud.AVMessage;
import com.hi.common.db.E_DB_ImgUrlMode;
import com.hi.common.db.E_DB_MsgSource;
import com.hi.common.db.E_DB_MsgType;
import com.hi.dao.supImpl.Dao_SelfIfo;
import com.hi.module.msg.service.ChatUtils;
import com.hi.receiver.model.IMMessageBean;
import com.hi.utils.DeviceUtils;
import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Table;
import com.lidroid.xutils.db.annotation.Transient;

@Table(name = "T_IMsg", execAfterTableCreated = "CREATE INDEX index_eachother ON T_IMsg(mid,uid)")
public class T_IMsg extends EntityBase {
    @Column(column = "objectId")
    private String objectId;

    @Column(column = "mid")
    private String mid;

    @Column(column = "uid")
    private String uid;

    @Column(column = "convid")
    private String convid;

    @Column(column = "head")
    private String head;

    @Column(column = "name")
    private String name;

    @Column(column = "msg")
    private String msg;

    @Column(column = "time")
    private long time;

    @Column(column = "identity")
    private int identity;

    @Column(column = "msgType")
    private int msgType;

    @Column(column = "msgFormat")
    private int msgFormat;

    @Column(column = "wifiMac")
    private String wifiMac;

    @Column(column = "sendState")
    private int sendState;

    @Column(column = "msgId")
    private String msgId;

    @Column(column = "ImageUrlMode")
    private int ImageUrlMode; //图像路径方式 :本地路径或网络url

//    public static T_IMsg fromIMMsg(IMMessageBean.IMMessage imMsg, int identity, int msgType, int imageUrlMode) {
//        T_IMsg msgBean = new T_IMsg();
//        msgBean.setObjectId(imMsg.getObjectId());
//        msgBean.setHead(imMsg.getU_head());
//        msgBean.setIdentity(identity);// 身份为对方
//        msgBean.setMid(Dao_SelfIfo.getInstance().getMid());
//        msgBean.setMsg(imMsg.getM_content());
//        msgBean.setMsgFormat(imMsg.getM_formate());
//        msgBean.setMsgType(msgType);
//        msgBean.setTime(imMsg.getTimestamp());
//        msgBean.setName(imMsg.getU_name());
//        msgBean.setUid(imMsg.getU_sendID());
//        msgBean.setImageUrlMode(imageUrlMode);
//        msgBean.setWifiMac(DeviceUtils.getWifiMac());
//        msgBean.setConvid(ChatUtils.convid(msgBean.getMid(), msgBean.getUid()));
//        return msgBean;
//    }

    public  AVMessage fromAVMessage() {
//        IMMessageBean.IMMessage imMessage=new IMMessageBean.IMMessage();
//        imMessage.setObjectId(msg.getObjectId());
        IMMessageBean imMessageBean = new IMMessageBean();
        imMessageBean.setObjectId(this.getObjectId())
                .setAnimId(this.getUid())
                .setContent(this.getMsg())
                .setFormat(IMMessageBean.Enum_IM_MsgFormat.get(this.getMsgFormat()))
                .setToPeerIds(this.getUid());
        return imMessageBean.makeAVMessage();
    }

    public String getConvid() {
        return convid;
    }

    public void setConvid(String convid) {
        this.convid = convid;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public int getImageUrlMode() {
        return ImageUrlMode;
    }

    public void setImageUrlMode(int imageUrlMode) {
        ImageUrlMode = imageUrlMode;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getIdentity() {
        return identity;
    }

    public void setIdentity(int identity) {
        this.identity = identity;
    }

    public int getMsgType() {
        return msgType;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }

    public int getMsgFormat() {
        return msgFormat;
    }

    public void setMsgFormat(int msgFormat) {
        this.msgFormat = msgFormat;
    }

    public String getWifiMac() {
        return wifiMac;
    }

    public void setWifiMac(String wifiMac) {
        this.wifiMac = wifiMac;
    }

    public int getSendState() {
        return sendState;
    }

    public void setSendState(int sendState) {
        this.sendState = sendState;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }


}
