package com.hi.module.register_login.model;

/**
 * @author MM_Zerui ��������¼����Ĳ���
 */
public class ReqThirdLoginBean {
	// //������������û�id
	// private String userThirdId;
	// �������û�Ψһ��ʶ
	private String openid;
	// ���� 1:QQ 2:����
	private String type;
	// �ǳ�
	private String nickname;
	// �Ա� 0:Ů 1:��
	private String gender;
	// ͷ��URL��ַ
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
