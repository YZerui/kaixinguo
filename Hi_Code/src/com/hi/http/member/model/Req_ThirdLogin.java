package com.hi.http.member.model;

/**
 * ��������¼
 * 
 * @author MM_Zerui
 * 
 */
public class Req_ThirdLogin {
	private String openid;// ��������¼��ȡ��accessToken
	private String type;// ��������¼���� 1:QQ 2:����΢��
	private String nickname;// �ǳƣ���������¼��ȡ��nickname
	private String gender;// �Ա���/Ů ��������¼��ȡ��gender
	private String figureurl;// ͷ���ַ����������¼��ȡ��figureurl
	private String avid;// AVOS��ȡ��Ψһ��ʶ
	private String wifiMac;// ��ǰwifiMac��ַ,��Ϊ��
	private String driviceCode;// ʹ���豸Ψһ��ʶ
	private String driveType;// �豸���� ios/android

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
