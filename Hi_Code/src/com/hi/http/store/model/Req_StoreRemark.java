package com.hi.http.store.model;

/**
 * 店家的兴趣评价
 * @author MM_Zerui
 *
 */
public class Req_StoreRemark {
	private String uid;//用户 ID
	private String bid;//店家 ID
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
	
}
