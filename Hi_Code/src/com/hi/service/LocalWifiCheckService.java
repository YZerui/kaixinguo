package com.hi.service;

import java.util.Timer;
import java.util.TimerTask;

import com.exception.utils.P;
import com.format.utils.DataValidate;
import com.hi.common.PARAMS;
import com.hi.common.param.Enum_Page;
import com.hi.common.param.Enum_Param;
import com.hi.http.base.Call_httpData;
import com.hi.http.store.model.Recv_StoreIfo;
import com.hi.http.store.model.Req_StoreWifimac;
import com.hi.http.store.req.Http_StoreWifimac;
import com.hi.utils.BroadcastUtil;
import com.hi.utils.DeviceUtils;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * �ֳ�wifi������
 * ������绷���ļ�����
 * @���� ���ڼ�ϼ�⵱ǰ����wifiMac
 * @���� �������֪ͨ�ֳ�ҳ����ʾ
 * @author MM_Zerui
 *
 */
public class LocalWifiCheckService extends Service{
	private TimerTask timerTask;
	private Timer timer;
	private String noteStr=new String();
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		P.v("����wifi���");
		init();
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		
		super.onCreate();
	}
	public void init(){
		timerTask = new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				requestMethod();
			}

			
		};
		timer = new Timer();
		// 1���Ӻ�������,ÿ��15���ӷ���һ��
		timer.schedule(timerTask,0,
				Enum_Param.TIMEOFFSET_WIFICHECK.value());
	}
	private void requestMethod() {
		// TODO Auto-generated method stub
		Req_StoreWifimac reqBean=new Req_StoreWifimac();
		reqBean.setWifiMac(DeviceUtils.getWifiMac());
		new Http_StoreWifimac(new Call_httpData<Recv_StoreIfo>() {
			
			@Override
			public void onSuccess(Recv_StoreIfo datas) {
				// TODO Auto-generated method stub
				noteStr="��ǰ�̼� - "+datas.getName();
			}
			
			@Override
			public void onStart() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onFinally() {
				// TODO Auto-generated method stub
				//����֪ͨ�㲥
				BroadcastUtil.sendBroadCast(getApplicationContext(), Enum_Page.WIFILOCAL.name(), noteStr);
				BroadcastUtil.sendBroadCast(getApplicationContext(), Enum_Page.MSG_NET.name(), noteStr);
				P.v("wifi������");
				P.v("���绷��������");
			}
			
			@Override
			public void onFail() {
				// TODO Auto-generated method stub
				if(DataValidate.checkDataValid(DeviceUtils.getWifiMac())){
					noteStr="����Wifiû�ж�Ӧ�̼�";
					return;
				}
				noteStr="��ǰ��û������WifiŶ";
			}
		}).onParams(reqBean).onAction();
	}
	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}


}
