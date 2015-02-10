package com.hi.dao.supImpl;

import java.util.ArrayList;


import android.content.Context;

import com.hi.common.PARAMS;
import com.hi.common.http.E_Http_LoginPlat;
import com.hi.http.member.model.Req_ThirdLogin;
import com.hi.module.base.application.AppContextApplication;
import com.hi.module.locale.model.RecvPhotoUrlBean;
import com.hi.module.locale.model.RecvUserIfoBean;
import com.hi.module.register_login.model.RecvPhoneRegiBean;
import com.hi.module.register_login.model.ReqPhoneLoginIfoBean;
import com.hi.module.register_login.model.ReqThirdLoginBean;
import com.hi.utils.DBUtils;
import com.hi.utils.DeviceUtils;
import com.hi.utils.FormatUtils;
import com.hi.utils.XingZuo;

/**
 * @author MM_Zerui 存储个人信息模块
 */
public class Dao_Params {
	private static Context context;
	static {
		context = AppContextApplication.getInstance();
	}
	/********************第三方登录信息********************************/
	/**
	 * 第三方登录信息的设定
	 * @param bean
	 */
	public static void SaveThirdLoginIfo(Req_ThirdLogin bean){	
		DBUtils.setSharedPreStr(context, "third_openid",bean.getOpenid());
		DBUtils.setSharedPreStr(context, "third_type",bean.getType());
		DBUtils.setSharedPreStr(context, "third_nickname",bean.getNickname());
		DBUtils.setSharedPreStr(context, "third_gender",bean.getGender());
		DBUtils.setSharedPreStr(context, "third_figureurl",bean.getFigureurl());
	}
	
	/**
	 * 获得第三方登录的请求数据
	 * @return
	 */
	public static Req_ThirdLogin getThirdLoginIfo(){
		Req_ThirdLogin bean=new Req_ThirdLogin();
		bean.setOpenid(DBUtils.getSharedPreStr(context, "third_openid", null));
		bean.setType(DBUtils.getSharedPreStr(context, "third_type",null));
		bean.setNickname(DBUtils.getSharedPreStr(context, "third_nickname",null));
		bean.setGender(DBUtils.getSharedPreStr(context, "third_gender", null));
		bean.setFigureurl(DBUtils.getSharedPreStr(context, "third_figureurl",null));
		bean.setAvid(DeviceUtils.getAvosId());
		bean.setWifiMac(DeviceUtils.getWifiMac());
		bean.setDriviceCode(DeviceUtils.getAvosId());
		bean.setDriveType(E_Http_LoginPlat.android.name());
		return bean;
	}
	
	/**
	 * 清空第三方登录的请求信息
	 */
	public static void clearThirdLoginBean(){
		DBUtils.setSharedPreStr(context, "third_openid",null);
		DBUtils.setSharedPreStr(context, "third_type",null);
		DBUtils.setSharedPreStr(context, "third_nickname",null);
		DBUtils.setSharedPreStr(context, "third_gender",null);
		DBUtils.setSharedPreStr(context, "third_figureurl",null);
	}
}

