package com.hi.http.member.model;

public class Recv_WifiList {
	private String birthday;
	private String currentState;
	private String head;
	private String lineTimes;
	private String mid;
	private String nickName;
    private String isOnLine;
	private String sex;
	private String stateTime;
	private String driverType;
	private String avosid;

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getIsOnLine() {
        return isOnLine;
    }

    public void setIsOnLine(String isOnLine) {
        this.isOnLine = isOnLine;
    }

    public String getDriverType() {
		return driverType;
	}
	public void setDriverType(String driverType) {
		this.driverType = driverType;
	}
	public String getAvosid() {
		return avosid;
	}
	public void setAvosid(String avosid) {
		this.avosid = avosid;
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
	public String getLineTimes() {
		return lineTimes;
	}
	public void setLineTimes(String lineTimes) {
		this.lineTimes = lineTimes;
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getStateTime() {
		return stateTime;
	}
	public void setStateTime(String stateTime) {
		this.stateTime = stateTime;
	}
	
}
