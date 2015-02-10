package com.hi.module.locale.model;

/**
 * @author MM_Zerui
 * 封装现场用户列表信息的Bean
 */
public class RecvLocalUserItemBean {
	private String head;
	private String nickName;
	private String mid;
	private String currentState;
	private String stateTime;
	private String birthDay;
	private String sex;
	private String lineTimes;
	
	public String getStateTime() {
		return stateTime;
	}
	public void setStateTime(String stateTime) {
		this.stateTime = stateTime;
	}
	public String getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getLineTimes() {
		return lineTimes;
	}
	public void setLineTimes(String lineTimes) {
		this.lineTimes = lineTimes;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	
	public String getCurrentState() {
		return currentState;
	}
	public void setCurrentState(String currentState) {
		this.currentState = currentState;
	}
	public String getIconUrl() {
		return head;
	}
	public void setIconUrl(String iconUrl) {
		this.head = iconUrl;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
}
