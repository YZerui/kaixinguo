package com.hi.http.coupons.model;

/**
 * 店家优惠劵列表
 * @author MM_Zerui
 *
 */
public class Req_CouponsList {
	private String bid;
	private String begin;
	private String limit;
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
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
