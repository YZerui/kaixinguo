package com.hi.module.store.model;

/**
 * @author MM_Zerui
 * ����̼��б������Bean
 */
public class ReqStoreListBean {
	private String uid;
	private String order;	//���������ʽ
	private String start;	//�����ҳ��
	private String limit;	//ÿҳ������
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
