package com.hi.http.local.model;

/**
 * 对某留言进行点赞的留言列表
 * @author MM_Zerui
 *
 */
public class Req_praiseList {
	private String mwID;
	private String start;
	private String limit;
	public String getMwID() {
		return mwID;
	}
	public void setMwID(String mwID) {
		this.mwID = mwID;
	}
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
	
}
