package com.hi.module.store.model;

/**
 * @author MM_Zerui
 * 关于店家评价的请求参数Bean
 */
public class RecvStoreCommentBean {
	private String uid;//用户唯一标识
	private String bid;//商家ID
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
