package com.hi.module.locale.model;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author MM_Zerui ������װ���û���ϢBean
 */
public class RecvUserIfoBean {
	// private String avosID; // ���͵�Ψһ�豸��ʶ Installtion ID
	private String birthDay;// ����
	private String currentState;// Ŀǰ״̬(����)
	private String head;// �û�ͷ���ַ
	private String interest;// ��Ȥ
	private String mid;// �û�Ψһ��ʶ
	private String nickName;// �û��ǳ�
	private String note;// �û���ǩ
	private String occupation;// ְҵ
	private String openID;// ������Ψһ��ʶ
	private String openType;// ��������¼����
	private String phone;// ��ϵ��ʽ
	private String remark;// ����
	private String sex;// �Ա�
	private String state;// �˻�״̬
	private String accostTimes; // ��ڨ����
	private String photos[];// ͼƬ
	private String accostedEffe; 
	private String type;
	private String locationEffe;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAccostedEffe() {
		return accostedEffe;
	}

	public void setAccostedEffe(String accostedEffe) {
		this.accostedEffe = accostedEffe;
	}

	public String getLocationEffe() {
		return locationEffe;
	}

	public void setLocationEffe(String locationEffe) {
		this.locationEffe = locationEffe;
	}

	public String[] getPhotos() {
		return photos;
	}

	public void setPhotos(String[] photos) {
		this.photos = photos;
	}

	public String getAccostTimes() {
		return accostTimes;
	}

	public void setAccostTimes(String accostTimes) {
		this.accostTimes = accostTimes;
	}

	public String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	public String getCurrentState() {
		return currentState;
	}

	public void setCurrentState(String currentState) {
		this.currentState = currentState;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getOpenID() {
		return openID;
	}

	public void setOpenID(String openID) {
		this.openID = openID;
	}

	public String getOpenType() {
		return openType;
	}

	public void setOpenType(String openType) {
		this.openType = openType;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
