package com.hi.module.store.model;

/**
 * @author MM_Zerui
 * 获得商家列表的请求Bean
 */
public class ReqStoreListBean {
	private String uid;
	private String order;	//请求的排序方式
	private String start;	//请求的页数
	private String limit;	//每页的条数
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
	
}
