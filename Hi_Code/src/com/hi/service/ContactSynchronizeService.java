package com.hi.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * 同步手机本地号码和应用本地数据库的服务类
 * @author MM_Zerui
 * @tip_1 首先获取本地号码组（项信息:手机号码，姓名，姓名首字母）
 * @tip_2 根据号码组，对本地数据库进行检验工作，只有未插入本地数据库中的号码才能进行插入操作
 * @tip_3 插入完毕后更新完成，关闭该服务
 *
 */
public class ContactSynchronizeService extends Service{

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
	}

}
