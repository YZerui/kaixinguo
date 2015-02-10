package com.hi.module.store.model;

/**
 * @author MM_Zerui
 * 自己拥有的优惠劵列表Bean
 */
public class RecvMyPrivilegeBean {
	//优惠劵ID
	private String CID;
	private String ID;
	//商家ID
	private String MID;
	//是否使用
	private String useEffe;
	//使用时间
	private String useTime;
	public String getCID() {
		return CID;
	}
	public void setCID(String cID) {
		CID = cID;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getMID() {
		return MID;
	}
	public void setMID(String mID) {
		MID = mID;
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
