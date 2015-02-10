package com.hi.module.register_login.model;

import java.util.HashMap;

import com.hi.common.PARAMS;

/**
 * 获取第三方用户信息的Model
 * 
 * @author MM_Zerui
 * 
 */
public class ThirdPlatUser {
	private String gender;
	private String figure;
	private String nickName;

	public static ThirdPlatUser parseWeibo(HashMap<String, Object> ifoMap) {
		ThirdPlatUser ifo = new ThirdPlatUser();
		ifo.setFigure(check(ifoMap.get("avatar_hd")));
		String gender = (check(ifoMap.get("gender")));
		if (gender.equals("m")) {
			ifo.setGender(PARAMS.MAN);
		} else {
			ifo.setGender(PARAMS.WOMEN);
		}
		ifo.setNickName(check(ifoMap.get("screen_name")));
		return ifo;
	}

	public static ThirdPlatUser parseQQ(HashMap<String, Object> ifoMap) {
		ThirdPlatUser ifo = new ThirdPlatUser();

		ifo.setFigure(check(ifoMap.get("figureurl_2")));

		String gender = (check(ifoMap.get("gender")));
		if (gender.equals("男")) {
			ifo.setGender(PARAMS.MAN);
		} else {
			ifo.setGender(PARAMS.WOMEN);
		}
		ifo.setNickName(check(ifoMap.get("nickname")));
		return ifo;
	}

	public static String check(Object object) {
		if (object == null) {
			return "";
		}
		return (String) object;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getFigure() {
		return figure;
	}

	public void setFigure(String figure) {
		this.figure = figure;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

}
