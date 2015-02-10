package com.hi.http.coupons.model;

/**
 * 自己拥有的优惠劵
 * @author MM_Zerui
 *
 */
public class Recv_CouponsSelf {
	private String cid;
	private String id;
	private String mid;
	private String useEffe;
	private String useTime;
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getUseEffe() {
		return useEffe;
	}
	public void setUseEffe(String useEffe) {
		this.useEffe = useEffe;
	}
	public String getUseTime() {
		return useTime;
	}
	public void setUseTime(String useTime) {
		this.useTime = useTime;
	}
	
}
