package com.hi.module.store.model;

/**
 * 请求获得自己所有优惠劵列表的参数Bean
 * @author MM_Zerui 
 */
public class ReqMyStorePrivilegeBean {
	private String uid;
	private String begin;
	private String limit;
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
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
