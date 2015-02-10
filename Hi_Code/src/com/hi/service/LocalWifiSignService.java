package com.hi.service;

import java.util.Timer;
import java.util.TimerTask;

import com.exception.utils.P;
import com.hi.common.API;
import com.hi.common.PARAMS;
import com.hi.common.http.E_Http_LoginPlat;
import com.hi.common.http.E_Http_LoginType;
import com.hi.common.param.Enum_Param;
import com.hi.dao.supImpl.Dao_SelfIfo;
import com.hi.http.base.Call_httpData;
import com.hi.http.member.model.Req_ChangeWifi;
import com.hi.http.member.req.Http_ChangeWifi;
import com.hi.module.locale.model.ReqWifiSignBean;
import com.hi.utils.DBUtils;
import com.hi.utils.DeviceUtils;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * wifi现场进行签到的服务
 * 
 * @author MM_Zerui
 * @tip_1 当应用登入现场后开启该服务
 * @tip_2 每隔20分钟发送一次现场签到
 * @tip_3 当应用退出时退出该服务,标识结束该签到
 * 
 */
public class LocalWifiSignService extends Service {
	private TimerTask timerTask;
	private Timer timer;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub

		// requestMethod();
		super.onCreate();
	}

	private void init() {
		// TODO Auto-generated method stub

		timerTask = new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				requestMethod();
			}
		};
		timer = new Timer();
		// 1分钟后发送请求,每隔15分钟发送一次
		timer.schedule(timerTask,0,
				Enum_Param.TIMEOFFSET_WIFISIGN.value());
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
	}

	/**
	 * 
	 */
	public void requestMethod() {
		Req_ChangeWifi reqBean=new Req_ChangeWifi();
		reqBean.setAvid(DeviceUtils.getAvosId());
		reqBean.setDriveType(E_Http_LoginPlat.android.name());
		reqBean.setDriviceCode(DeviceUtils.getAvosId());
		reqBean.setUid(Dao_SelfIfo.getInstance().getMid());
		reqBean.setWifiMac(DeviceUtils.getWifiMac());
		new Http_ChangeWifi(new Call_httpData<Class>() {
			
			@Override
			public void onSuccess(Class datas) {
				// TODO Auto-generated method stub
				P.v("更新现场状态成功");
			}
			
			@Override
			public void onStart() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onFinally() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onFail() {
				// TODO Auto-generated method stub
				P.v("更新现场状态失败");
			}
		}).onParams(reqBean).onAction();
		

	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		System.out.println("wifi签到服务关闭");
		super.onDestroy();
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		init();
		return null;
	}

}
