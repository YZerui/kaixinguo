package com.hi.http.member.model;

/**
 * 第三方登录
 * 
 * @author MM_Zerui
 * 
 */
public class Req_ThirdLogin {
	private String openid;// 第三方登录获取的accessToken
	private String type;// 第三方登录类型 1:QQ 2:新浪微博
	private String nickname;// 昵称：第三方登录获取的nickname
	private String gender;// 性别：男/女 第三方登录获取的gender
	private String figureurl;// 头像地址：第三方登录获取的figureurl
	private String avid;// AVOS获取的唯一标识
	private String wifiMac;// 当前wifiMac地址,可为空
	private String driviceCode;// 使用设备唯一标识
	private String driveType;// 设备类型 ios/android

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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getFigureurl() {
		return figureurl;
	}

	public void setFigureurl(String figureurl) {
		this.figureurl = figureurl;
	}

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

}
