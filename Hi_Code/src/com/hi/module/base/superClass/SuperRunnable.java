package com.hi.module.base.superClass;

import android.os.Handler;
import android.os.Message;

/**
 * @author »‘Û_Zerui
 * œﬂ≥Ã≥¨¿‡
 */
public class SuperRunnable implements Runnable{
	protected int state;
	protected Handler handler;
	protected Message msg;
	protected String reqUrl;
	protected int params;
	protected int[] index;
	public String getReqUrl() {
		return reqUrl;
	}
	public void setReqUrl(String reqUrl) {
		this.reqUrl = reqUrl;
	}
	protected Object beanObject;
	public Object getBeanObject() {
		return beanObject;
	}
	public void setBeanObject(Object beanObject) {
		this.beanObject = beanObject;
	}
	public SuperRunnable() {
		// TODO Auto-generated constructor stub
		msg=new Message();
	}
	public Message getMsg() {
		return msg;
	}
	public void setMsg(Message msg) {
		this.msg = msg;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Handler getHandler() {
		return handler;
	}
	public void setHandler(Handler handler) {
		this.handler = handler;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
