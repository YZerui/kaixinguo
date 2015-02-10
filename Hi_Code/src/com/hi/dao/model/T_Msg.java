package com.hi.dao.model;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Table;
import com.lidroid.xutils.db.annotation.Transient;

/**
 * 记录用户间搭讪记录的表
 * 
 * @author MM_Zerui
 * 
 */
@Table(name = "T_Msg", execAfterTableCreated = "CREATE INDEX index_eachother ON T_Msg(mid,uid)")
public class T_Msg extends EntityBase {

	@Column(column = "mid")
	private String mid;

	@Column(column = "uid")
	private String uid;

	@Column(column = "head")
	private String head;

	@Column(column = "name")
	private String name;

	@Column(column = "msg")
	private String msg;

	@Column(column = "time")
	private String time;

	@Column(column = "identity")
	private int identity;

	@Column(column = "msgType")
	private int msgType;

	@Column(column = "msgId")
	private long msgId;

	@Column(column = "wifiMac")
	private String wifiMac;
	
	//默认状态下是发送成功的
	@Column(column="sendState",defaultValue="0")
	private int sendState;

	public long getMsgId() {
		return msgId;
	}

	public void setMsgId(long msgId) {
		this.msgId = msgId;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "uid:" + getUid();
	}


	public int getSendState() {
		return sendState;
	}

	public void setSendState(int sendState) {
		this.sendState = sendState;
	}

	public int getMsgType() {
		return msgType;
	}

	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}

	public String getWifiMac() {
		return wifiMac;
	}

	public void setWifiMac(String wifiMac) {
		this.wifiMac = wifiMac;
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

}
