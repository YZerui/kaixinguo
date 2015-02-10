package com.hi.module.register_login.model;

/**
 * @author MM_Zerui
 * 短信“验证”的Bean
 */
public class ReqPhoneAuthoBean {
	private String phone;
	private String code;
	private String wifiMac;
	private String uid;

	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getWifiMac() {
		return wifiMac;
	}
	public void setWifiMac(String wifiMac) {
		this.wifiMac = wifiMac;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	
}
