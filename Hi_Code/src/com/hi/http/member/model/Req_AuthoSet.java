package com.hi.http.member.model;

/**
 * ����Ȩ�ޣ��Ƿ�ɼ����Ƿ�������ڨ��
 * @author MM_Zerui
 *
 */
public class Req_AuthoSet {
	private String uid;
	private String wifiMac;
	private String le;//λ���Ƿ�ɼ�
	private String ae;//�Ƿ�ɱ���ڨ
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getWifiMac() {
		return wifiMac;
	}
	public void setWifiMac(String wifiMac) {
		this.wifiMac = wifiMac;
	}
	public String getLe() {
		return le;
	}
	public void setLe(String le) {
		this.le = le;
	}
	public String getAe() {
		return ae;
	}
	public void setAe(String ae) {
		this.ae = ae;
	}
	
}
