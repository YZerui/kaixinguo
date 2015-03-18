package com.hi.http.local.model;

/**
 * ∑¢≤º¡Ù—‘
 * @author MM_Zerui
 *
 */
public class Req_issueMsg {
	private String uid;
	private String bid;
	private String wifiMac;
	private String content;
    private String m_long;
    private String m_lat;

    private String img;
    private String imgUrl;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
}
