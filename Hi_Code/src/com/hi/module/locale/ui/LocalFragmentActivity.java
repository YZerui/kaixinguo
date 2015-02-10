package com.hi.module.locale.ui;

import com.android.ruifeng.hi.R;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVInstallation;
import com.avos.avoscloud.PushService;
import com.avos.avoscloud.SaveCallback;
import com.exception.utils.P;
import com.hi.common.http.E_Http_LoginPlat;
import com.hi.dao.supImpl.Dao_SelfIfo;
import com.hi.http.base.Call_httpData;
import com.hi.http.member.model.Req_ChangeWifi;
import com.hi.http.member.req.Http_ChangeWifi;
import com.hi.module.base.application.AppManager;
import com.hi.utils.DeviceUtils;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Fragment 的Activity
 * @author MM_Zerui 
 */
public class LocalFragmentActivity extends FragmentActivity {
	private FragmentManager fm = getSupportFragmentManager();
	private FragmentTransaction fragmentTransaction = getSupportFragmentManager()
			.beginTransaction();

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		
		AppManager.getAppManager().addActivity(this);
		
		setContentView(R.layout.local_fragment_activity);
		fragmentTransaction.replace(R.id.local_fragment, new LocalFragment());
		fragmentTransaction.commit();
		// 初始avos推送
		initAvos();
	}

	private void initAvos() {
		// TODO Auto-generated method stub
		// 保存 installation 到服务器
		// 设置默认打开的 Activity
		PushService.setDefaultPushCallback(this, LocalFragmentActivity.class);
		AVInstallation.getCurrentInstallation().saveInBackground(
				new SaveCallback() {
					@Override
					public void done(AVException e) {
						AVInstallation.getCurrentInstallation()
								.saveInBackground();
					}
				});
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		//每次回到现场都触发一次现场签到
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
}
