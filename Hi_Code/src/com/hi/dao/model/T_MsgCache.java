package com.hi.dao.model;

import com.hi.common.db.E_DB_MsgSource;
import com.hi.dao.supImpl.Dao_SelfIfo;
import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Table;
import com.lidroid.xutils.db.annotation.Unique;

/**
 * 搭讪缓存表
 * @author MM_Zerui
 *
 */
@Table(name="T_MsgCache",execAfterTableCreated = "CREATE INDEX index_msgId ON T_MsgCache(uid)")
public class T_MsgCache extends EntityBase2{
	@Unique
	@Column(column="msgId")
	private long msgId;
	
	@Column(column="msg")
	private String msg;
	
	@Column(column="head")
	private String head;
	
	@Column(column="nickname")
	private String nickname;
	
	@Column(column="time")
	private String time;
	
	@Column(column="uid")
	private String uid; //对方ID
	
	@Column(column="sendID")
	private String sendID; //消息发送方ID
	
	@Column(column="identity")
	private int identity;
	
	
	
	public long getMsgId() {
		return msgId;
	}
	public void setMsgId(long msgId) {
		this.msgId = msgId;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getSendID() {
		return sendID;
	}
	//如果发送ID和用户ID匹配，修改identity字段
	public void setSendID(String sendID) {
		this.sendID = sendID;
		if(sendID.equals(Dao_SelfIfo.getInstance().getMid())){
			setIdentity(E_DB_MsgSource.SELF.value());
		}else {
			setIdentity(E_DB_MsgSource.USER.value());
		}
	}
	public int getIdentity() {
		return identity;
	}
	private void setIdentity(int identity) {
		this.identity = identity;
	}
	
	
	
}
