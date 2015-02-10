package com.hi.service;

import java.util.ArrayList;
import java.util.List;

import com.avos.avoscloud.Session;
import com.avos.avoscloud.SessionManager;
import com.hi.dao.supImpl.Dao_SelfIfo;
import com.hi.receiver.IMessageReceiver.MessageListener;
import com.hi.receiver.model.IMMessageBean;
import com.hi.receiver.model.IMMessageBean.Enum_IM_MsgFormat;
import com.hi.receiver.model.IMMessageBean.Enum_IM_MsgType;

/**
 * 即时通讯的消息服务
 * @author MM_Zerui
 *
 */
public class IMMessageService {
	private MessageListener messageListener;
	private IMMessageBean iMMessageBean=new IMMessageBean();
	private List<String> peerIds=new ArrayList<String>();
	
	public IMMessageBean getiMMessageBean() {
		return iMMessageBean;
	}
	public void setiMMessageBean(IMMessageBean iMMessageBean) {
		this.iMMessageBean = iMMessageBean;
	}
	private Session getSession(){
		try{
			return SessionManager.getInstance(Dao_SelfIfo.getInstance()
					.getMid());
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	public IMMessageService setMsgListener(MessageListener messageListener){
		this.messageListener=messageListener;
		return this;
	}
	public IMMessageService setContent(String m_content){
		iMMessageBean.setContent(m_content);
		return this;
	}
	public IMMessageService setMessageType(Enum_IM_MsgType enumType){
		iMMessageBean.setMsgType(enumType);
		return this;
	}
	public IMMessageService setMessageFormat(Enum_IM_MsgFormat enumFormat){
		iMMessageBean.setFormat(enumFormat);
		return this;
	}
	
	public IMMessageService setMessagePeerId(String peerId){
		iMMessageBean.setToPeerIds(peerId);
		peerIds.add(peerId);
		return this;
	}
	public IMMessageService setAnimId(String animId){
		iMMessageBean.setAnimId(animId);
		return this;
	}
	public IMMessageService setObjectId(String objectId){
		iMMessageBean.setObjectId(objectId);
		return this;
	}
	public IMMessageService watchPeer(String peerId){
		peerIds.clear();
		peerIds.add(peerId);
		getSession().watchPeers(peerIds);
		return this;
	}
	public IMMessageService unwatchPeer(String peerId){
		peerIds.clear();
		peerIds.add(peerId);
		getSession().unwatchPeers(peerIds);
		return this;
	}
//	public boolean isOnLine(String peerId){
//		getSession().watchPeers(peerIds)
//		return  SessionManager.getInstance(peerId).isOnline(peerId);
//	}
	public void send(){
		Session session=getSession();
		if(session==null){
			return;
		}
//		session.watchPeers(peerIds);
		session.sendMessage(iMMessageBean.makeAVMessage());
	}
	public void closeSession(){
		if(getSession()==null){
			return;
		}
		getSession().close();
	}
}
