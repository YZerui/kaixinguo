package com.hi.module.store.model;

/**
 * @author MM_Zerui
 * �Լ�ӵ�е��Ż݄��б�Bean
 */
public class RecvMyPrivilegeBean {
	//�Ż݄�ID
	private String CID;
	private String ID;
	//�̼�ID
	private String MID;
	//�Ƿ�ʹ��
	private String useEffe;
	//ʹ��ʱ��
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
