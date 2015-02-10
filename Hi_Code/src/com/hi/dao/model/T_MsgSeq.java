package com.hi.dao.model;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Table;
import com.lidroid.xutils.db.annotation.Unique;

/**
 * 消息顺序表，用于管理各个消息对话和先后顺序
 * 
 * @author MM_Zerui
 * @tip_1 mid+uid 必须是唯一
 */
@Table(name = "T_MsgSeq", execAfterTableCreated = "CREATE INDEX index_target ON T_MsgSeq(mid,uid)")
public class T_MsgSeq extends EntityBase{

	@Column(column = "mid")
	private String mid;

	@Column(column = "uid")
	private String uid;

	@Column(column = "head")
	private String head;

	@Column(column = "name")
	private String name;

	@Column(column="msg")
	private String msg;
	
	@Column(column="time")
	private String time;
	
	@Column(column="identity")
	private int identity;
	
	@Column(column="msgType")
	private int msgType;
	
	@Column(column="unRead",defaultValue="0")
	private int unRead;
	
	@Column(column="msgId")
	private long msgId;

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

	

	public long getMsgId() {
		return msgId;
	}

	public void setMsgId(long msgId) {
		this.msgId = msgId;
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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
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

	public int getUnRead() {
		return unRead;
	}

	public void setUnRead(int unRead) {
		this.unRead = unRead;
	}


}
