package com.hi.module.store.model;

/**
 * @author MM_Zerui 请求获得所有优惠劵列表的参数Bean
 */
public class ReqStorePrivilegeBean {
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
