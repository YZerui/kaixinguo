package com.hi.module.register_login.model;

/**
 * @author MM_Zerui
 *	手机注册后返回的信息Bean
 */
public class RecvPhoneRegiBean {
	private String mid;
	private String phone;
	private String nickname;
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
}
