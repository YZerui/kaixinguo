package com.hi.http.member.model;

/**
 * 手机注册后返回信息
 * @author MM_Zerui
 *
 */
public class Recv_PhoneRegi {
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
