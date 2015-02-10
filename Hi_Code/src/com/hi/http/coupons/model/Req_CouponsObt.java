package com.hi.http.coupons.model;

/**
 * 获取优惠劵
 * @author MM_Zerui
 *
 */
public class Req_CouponsObt {
	private String uid;
	private String cid;//优惠劵ID
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	
}
