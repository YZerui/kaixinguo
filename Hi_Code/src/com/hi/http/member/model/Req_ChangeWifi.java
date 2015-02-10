package com.hi.http.member.model;

/**
 * 更好wifi现场
 * @author MM_Zerui
 *
 */
public class Req_ChangeWifi {
	private String uid;		
	private String avid;
	private String driviceCode;
	private String driveType;	
	private String wifiMac;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
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
