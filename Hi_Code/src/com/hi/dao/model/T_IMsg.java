package com.hi.dao.model;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Table;
import com.lidroid.xutils.db.annotation.Transient;
@Table(name = "T_IMsg", execAfterTableCreated = "CREATE INDEX index_eachother ON T_IMsg(mid,uid)")
public class T_IMsg extends EntityBase{
	@Column(column="objectId")
	private String objectId;
	
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
	
	@Column(column="wifiMac")
	private String wifiMac;
	
	@Column(column="sendState")
	private int sendState;
	
	@Column(column="msgId")
	private String msgId;

	@Column(column="ImageUrlMode")
	
	
	private int ImageUrlMode; //图像路径方式 :本地路径或网络url  
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
