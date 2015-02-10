package com.hi.module.locale.model;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author MM_Zerui 请求后封装的用户信息Bean
 */
public class RecvUserIfoBean {
	// private String avosID; // 推送的唯一设备标识 Installtion ID
	private String birthDay;// 生日
	private String currentState;// 目前状态(心情)
	private String head;// 用户头像地址
	private String interest;// 兴趣
	private String mid;// 用户唯一标识
	private String nickName;// 用户昵称
	private String note;// 用户标签
	private String occupation;// 职业
	private String openID;// 第三方唯一标识
	private String openType;// 第三方登录类型
	private String phone;// 联系方式
	private String remark;// 评论
	private String sex;// 性别
	private String state;// 账户状态
	private String accostTimes; // 搭讪次数
	private String photos[];// 图片
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
