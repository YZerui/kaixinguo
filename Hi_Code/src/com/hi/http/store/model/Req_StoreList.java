package com.hi.http.store.model;

/**
 * 商家列表
 * @author MM_Zerui
 *
 */
public class Req_StoreList {
	private String uid;
    private String m_long;
    private String m_lat;
	private String order;
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

    public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
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
	
}
