package com.hi.module.friend.model;

/**
 * @author MM_Zerui
 *	封装从服务端接收到的手机验证信息Bean
 */
public class RecvPhoneAuthorBean {
	private String uid;
	private String phone;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
