package com.hi.module.friend.model;

/**
 * @author MM_Zerui �������ϵ����Ϣ
 */
public class ContactSortBean {
	private String mid;//����Ψһ��ʶ
	private String name;
	private String phone;
	private String sortLetters;// ����ƴ������ı�ʶ
	private String ifRegister;// �Ƿ�ע������Ӧ�õı�ʶ
	private String uid;// ���û���UID

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
