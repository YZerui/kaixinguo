package com.hi.module.friend.model;

/**
 * @author MM_Zerui
 *	对手机号码进行上传操作的参数Bean
 */
public class ReqPhoneCheckBean {
	private String uid;
	private String phones;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getPhones() {
		return phones;
	}
	public void setPhones(String phones) {
		this.phones = phones;
	}
}
