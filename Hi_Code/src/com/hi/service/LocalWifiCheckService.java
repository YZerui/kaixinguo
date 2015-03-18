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
 * 现场wifi检测服务
 * 检测网络环境的检测服务
 * @功能 用于间断检测当前连接wifiMac
 * @功能 将检测结果通知现场页面显示
 * @author MM_Zerui
 *
 */
public class LocalWifiCheckService extends Service{
    public enum Enum_WIFICHECK{
        MATCH,
        UNMATCH,
        UNWIFI
    }
	private TimerTask timerTask;
	private Timer timer;
    private String note=new String();
	private String noteStr=new String();

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		P.v("开启wifi检测");
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
		// 1分钟后发送请求,每隔15分钟发送一次
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
                note=Enum_WIFICHECK.MATCH.toString();
				noteStr=datas.getName();
			}
            @Override
            public void onFail() {
                // TODO Auto-generated method stub
                if(DataValidate.checkDataValid(DeviceUtils.getWifiMac())){
                    note=Enum_WIFICHECK.UNMATCH.toString();
                    noteStr="所在Wifi没有对应商家";
                    return;
                }
                note=Enum_WIFICHECK.UNWIFI.toString();
                noteStr="当前还没有连接Wifi哦";
            }
			@Override
			public void onStart() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onFinally() {
				// TODO Auto-generated method stub
				//发送通知广播
				BroadcastUtil.sendBroadCast(getApplicationContext(), Enum_Page.WIFILOCAL.name(), note,noteStr);
//				BroadcastUtil.sendBroadCast(getApplicationContext(), Enum_Page.MSG_NET.name(), note);
				P.v("wifi检测完成");
				P.v("网络环境检测完成");
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
