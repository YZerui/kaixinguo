package com.hi.http.member.model;

/**
 * ����״̬���ֳ�/Wifi��
 * 
 * @author MM_Zerui
 * 
 */
public class Req_ChangeState {
	private String uid;
	private String t; // �޸��û�״̬��Ӧ����:1:ͨ��״̬ 2:�ֳ�״̬
	private String c;// ״̬����

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getT() {
		return t;
	}

	public void setT(String t) {
		this.t = t;
	}

	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}

}
