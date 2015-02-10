package com.hi.module.msg.model;

import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author MM_Zerui
 *封装消息详情的Bean
 */
public class MsgDetailBean {

	private String title;
	private String msg;
	private String head;
	private int msgType;
	private String time;
	private int identity;
	private boolean state;//标明发送状态
	

	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}

	public String getTime() {
		return time;
	}
	public int getIdentity() {
		return identity;
	}
	public void setIdentity(int identity) {
		this.identity = identity;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getMsgType() {
		return msgType;
	}
	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	
}
