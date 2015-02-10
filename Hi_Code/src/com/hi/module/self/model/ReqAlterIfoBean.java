package com.hi.module.self.model;

/**
 * @author MM_Zerui 提交更改用户信息的Bean
 */
public class ReqAlterIfoBean {
	private String uid; // 系统标识ID
	private String t; // 修改内容编号
	private String c; // 修改内容

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
