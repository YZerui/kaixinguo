package com.hi.module.register_login.model;

/**
 * @author MM_Zerui 第三方登录所需的参数
 */
public class ReqThirdLoginBean {
	// //第三方分配的用户id
	// private String userThirdId;
	// 第三方用户唯一标识
	private String openid;
	// 类型 1:QQ 2:新浪
	private String type;
	// 昵称
	private String nickname;
	// 性别 0:女 1:男
	private String gender;
	// 头像URL地址
	private String figureurl;

	private String avid;
	private String wifiMac;
	private String driviceCode;
	private String driveType;

	public String getAvid() {
		return avid;
	}

	public void setAvid(String avid) {
		this.avid = avid;
	}

	public String getWifiMac() {
		return wifiMac;
	}

	public void setWifiMac(String wifiMac) {
		this.wifiMac = wifiMac;
	}

	public String getDriviceCode() {
		return driviceCode;
	}

	public void setDriviceCode(String driviceCode) {
		this.driviceCode = driviceCode;
	}

	public String getDriveType() {
		return driveType;
	}

	public void setDriveType(String driveType) {
		this.driveType = driveType;
	}

	// public String getUserThirdId() {
	// return userThirdId;
	// }
	// public void setUserThirdId(String userThirdId) {
	// this.userThirdId = userThirdId;
	// }
	public String getFigureurl() {
		return figureurl;
	}

	public void setFigureurl(String figureurl) {
		this.figureurl = figureurl;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
