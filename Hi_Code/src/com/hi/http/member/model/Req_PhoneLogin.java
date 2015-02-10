package com.hi.http.member.model;

import com.hi.http.base.HttpRequestClass;

/**
 * 普通的手机登录
 * @author MM_Zerui
 *
 */
public class Req_PhoneLogin {
	private String phone;
	private String pw;
	private String avid;
	private String driviceCode;
	private String driveType;
	private String wifiMac;
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getAvid() {
		return avid;
	}
	public void setAvid(String avid) {
		this.avid = avid;
	}
	public String getDriviceCode() {
		return driviceCode;
	}
	public void setDriviceCode(String driviceCode) {
		this.driviceCode = driviceCode;
	}
	public String getDriveType() {
		return driveType;
	}
	public void setDriveType(String driveType) {
		this.driveType = driveType;
	}
	public String getWifiMac() {
		return wifiMac;
	}
	public void setWifiMac(String wifiMac) {
		this.wifiMac = wifiMac;
	}
	
}
