package com.hi.http.member.model;

/**
 * 更改状态（现场/Wifi）
 * 
 * @author MM_Zerui
 * 
 */
public class Req_ChangeState {
	private String uid;
	private String t; // 修改用户状态对应编码:1:通用状态 2:现场状态
	private String c;// 状态内容

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getT() {
		return t;
	}

	public void setT(String t) {
		this.t = t;
	}

	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}

}
