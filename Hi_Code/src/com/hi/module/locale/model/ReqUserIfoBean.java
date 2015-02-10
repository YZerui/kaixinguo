package com.hi.module.locale.model;

/**
 * @author MM_Zerui
 * 请求用户详细信息的参数Bean
 */
public class ReqUserIfoBean {
	//本人ID
	private String uid;
	//用户唯一标识的ID
	private String id;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
