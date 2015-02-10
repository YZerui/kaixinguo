package com.hi.module.friend.model;

/**
 * @author MM_Zerui 排序的联系人信息
 */
public class ContactSortBean {
	private String mid;//个人唯一标识
	private String name;
	private String phone;
	private String sortLetters;// 用于拼音排序的标识
	private String ifRegister;// 是否注册美美应用的标识
	private String uid;// 该用户的UID

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getIfRegister() {
		return ifRegister;
	}

	public void setIfRegister(String ifRegister) {
		this.ifRegister = ifRegister;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSortLetters() {
		return sortLetters;
	}

	public void setSortLetters(String sortLetters) {
		this.sortLetters = sortLetters;
	}
}
