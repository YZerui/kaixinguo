package com.hi.http.coupons.model;

/**
 * 转发优惠劵
 * @author MM_Zerui
 *
 */
public class Req_CouponsTrans {
	private String cid;//优惠券对应ID
	private String uid;//用户的唯一标识UUID:登录过后获取到的mid
	private String uids;//被转发的用户ID(多个用户使用,号分割)
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUids() {
		return uids;
	}
	public void setUids(String uids) {
		this.uids = uids;
	}
	
}
