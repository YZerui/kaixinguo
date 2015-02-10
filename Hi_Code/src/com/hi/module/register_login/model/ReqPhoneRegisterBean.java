package com.hi.module.register_login.model;

public class ReqPhoneRegisterBean {
	private String phone;
	private String code;
	private String pw;
	private String rppw;
	private String avid;
	private String driveType;
	private String driviceCode;//设备唯一编码，这里采用avos创建的标识
	private String wifiMac;
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
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getRppw() {
		return rppw;
	}
	public void setRppw(String rppw) {
		this.rppw = rppw;
	}
	public String getAvid() {
		return avid;
	}
	public void setAvid(String avid) {
		this.avid = avid;
	}
	public String getDriveType() {
		return driveType;
	}
	public void setDriveType(String driveType) {
		this.driveType = driveType;
	}
	public String getDriviceCode() {
		return driviceCode;
	}
	public void setDriviceCode(String driviceCode) {
		this.driviceCode = driviceCode;
	}
	public String getWifiMac() {
		return wifiMac;
	}
	public void setWifiMac(String wifiMac) {
		this.wifiMac = wifiMac;
	}
}
