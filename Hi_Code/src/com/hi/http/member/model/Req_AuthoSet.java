package com.hi.http.member.model;

/**
 * 设置权限（是否可见，是否允许被搭讪）
 * @author MM_Zerui
 *
 */
public class Req_AuthoSet {
	private String uid;
	private String wifiMac;
	private String le;//位置是否可见
	private String ae;//是否可被搭讪
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
	public String getLe() {
		return le;
	}
	public void setLe(String le) {
		this.le = le;
	}
	public String getAe() {
		return ae;
	}
	public void setAe(String ae) {
		this.ae = ae;
	}
	
}
