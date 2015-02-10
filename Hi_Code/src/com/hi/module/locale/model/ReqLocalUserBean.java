package com.hi.module.locale.model;

/**
 * @author MM_Zerui
 * 获取现场wifi用户的请求参数Bean
 */
public class ReqLocalUserBean {
	private String uid;
	private String wifiMac;
	private String bid;
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getWifiMac() {
		return wifiMac;
	}
	public void setWifiMac(String wifiMac) {
		this.wifiMac = wifiMac;
	}
}
