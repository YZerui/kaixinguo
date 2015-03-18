package com.hi.http.local.model;

/**
 * 获取留言墙列表
 * @author MM_Zerui
 *
 */
public class Req_obtMsg {
    private String mid;//登录用户ID
	private String uid;
	private String bid;
	private String wifiMac;
	private String start;
	private String limit;
	private String m_long;
    private String m_lat;

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

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

    public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public String getWifiMac() {
		return wifiMac;
	}
	public void setWifiMac(String wifiMac) {
		this.wifiMac = wifiMac;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getLimit() {
		return limit;
	}
	public void setLimit(String limit) {
		this.limit = limit;
	}
	
}
