package com.hi.http.member.model;

/**
 * 更好wifi现场
 * @author MM_Zerui
 *
 */
public class Req_ChangeWifi {
	private String uid;		
//	private String avid;
//	private String driviceCode;
//	private String driveType;
    private String m_lat;
    private String m_long;
	private String wifiMac;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}

    public String getM_lat() {
        return m_lat;
    }

    public void setM_lat(String m_lat) {
        this.m_lat = m_lat;
    }

    public String getM_long() {
        return m_long;
    }

    public void setM_long(String m_long) {
        this.m_long = m_long;
    }

    //	public String getAvid() {
//		return avid;
//	}
//	public void setAvid(String avid) {
//		this.avid = avid;
//	}
//	public String getDriviceCode() {
//		return driviceCode;
//	}
//	public void setDriviceCode(String driviceCode) {
//		this.driviceCode = driviceCode;
//	}
//	public String getDriveType() {
//		return driveType;
//	}
//	public void setDriveType(String driveType) {
//		this.driveType = driveType;
//	}
	public String getWifiMac() {
		return wifiMac;
	}
	public void setWifiMac(String wifiMac) {
		this.wifiMac = wifiMac;
	}
	
}
