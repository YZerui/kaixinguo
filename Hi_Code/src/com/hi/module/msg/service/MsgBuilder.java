package com.hi.module.msg.service;

import com.hi.common.db.E_DB_MsgSource;
import com.hi.common.db.E_DB_MsgType;
import com.hi.common.http.E_Http_SendState;
import com.hi.dao.model.T_IMsg;
import com.hi.dao.supImpl.Dao_SelfIfo;
import com.hi.receiver.model.IMMessageBean;
import com.hi.utils.FormatUtils;
import com.hi.utils.Utils;

/**
 * User: Zerui
 * Time: 2015/3/10 0:45
 */
public class MsgBuilder {
    T_IMsg msg;
    public MsgBuilder(){
        msg=new T_IMsg();
    }
    public void target(String toId){
        String convid=ChatUtils.convid(Dao_SelfIfo.getInstance().getMid(),toId);
        msg.setConvid(convid);
        msg.setIdentity(E_DB_MsgSource.SELF.value());
        msg.setMsgType(E_DB_MsgType.ACCOST.value());
//        msg.setMid(Dao_SelfIfo.getInstance().getMid());
        msg.setUid(toId);
    }

    public void sendText(String content){
        msg.setMsgFormat(IMMessageBean.Enum_IM_MsgFormat.TEXT.value());
        msg.setMsg(content);
    }

    public void sendImg(String imgUrl,int imgMode){
        msg.setMsgFormat(IMMessageBean.Enum_IM_MsgFormat.IMAGE.value());
        msg.setImageUrlMode(imgMode);
        msg.setMsg(imgUrl);
    }

    /**
     * 设定对方的信息
     * @param name
     * @param headUrl
     */
    public void setFromIdInfo(String name,String headUrl){
        msg.setName(name);
        msg.setHead(headUrl);
    }
    public T_IMsg preBuild(){
        msg.setSendState(E_Http_SendState.START.value());
        msg.setTime(FormatUtils.getCurrentDateValue_long());
        msg.setMid(Dao_SelfIfo.getInstance().getMid());
        if(msg.getObjectId()==null){
            msg.setObjectId(Utils.uuid());
        }
        return msg;
    }
}
