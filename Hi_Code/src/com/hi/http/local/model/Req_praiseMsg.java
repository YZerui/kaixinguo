package com.hi.http.local.model;

/**
 * 对留言点赞/取消点赞服务
 * @author MM_Zerui
 *
 */
public class Req_praiseMsg {
	private String uid;
	private String mwID;
	private String type;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getMwID() {
		return mwID;
	}
	public void setMwID(String mwID) {
		this.mwID = mwID;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
