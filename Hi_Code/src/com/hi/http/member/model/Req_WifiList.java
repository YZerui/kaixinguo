package com.hi.http.member.model;

/**
 * wifi现场用户
 * @author MM_Zerui
 *
 */
public class Req_WifiList {
	private String uid;
	private String wifiMac;
	private String bid;
    private String m_long;
    private String m_lat;
	private String begin;
	private String limit;

    public String getM_long() {
        return m_long;
    }

    public void setM_long(String m_long) {
        this.m_long = m_long;
    }

    public String getM_lat() {
        return m_lat;
    }

    public void setM_lat(String m_lat) {
        this.m_lat = m_lat;
    }

    public String getBegin() {
		return begin;
	}
	public void setBegin(String begin) {
		this.begin = begin;
	}
	public String getLimit() {
		return limit;
	}
	public void setLimit(String limit) {
		this.limit = limit;
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
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	
}
