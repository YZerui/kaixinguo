package com.hi.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * ͬ���ֻ����غ����Ӧ�ñ������ݿ�ķ�����
 * @author MM_Zerui
 * @tip_1 ���Ȼ�ȡ���غ����飨����Ϣ:�ֻ����룬��������������ĸ��
 * @tip_2 ���ݺ����飬�Ա������ݿ���м��鹤����ֻ��δ���뱾�����ݿ��еĺ�����ܽ��в������
 * @tip_3 ������Ϻ������ɣ��رո÷���
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
