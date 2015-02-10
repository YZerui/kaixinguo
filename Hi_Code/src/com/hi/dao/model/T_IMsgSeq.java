package com.hi.dao.model;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Table;

@Table(name = "T_IMsgSeq")
public class T_IMsgSeq extends EntityBase{
	@Column(column="mid")
	private String mid;
	
	@Column(column="uid")
	private String uid;
	
	@Column(column="head")
	private String head;
	
	@Column(column="name")
	private String name;
	
	@Column(column="msg")
	private String msg;
	
	@Column(column="time")
	private long time;
	
	@Column(column="identity")
	private int identity;
	
	@Column(column="msgType")
	private int msgType;
	
	@Column(column="msgFormat")
	private int msgFormat;
	
	@Column(column="unRead")
	private int unRead;
	
	@Column(column="sendState")
	private int sendState;
	
	@Column(column="msgId")
	private String msgId;

	
	public int getUnRead() {
		return unRead;
	}

	public void setUnRead(int unRead) {
		this.unRead = unRead;
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
