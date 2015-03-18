package com.hi.service;


import android.content.Context;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.exception.utils.P;
import com.hi.module.base.application.AppContextApplication;

/**
 * 获取经纬度服务
 * @author MM_Zerui
 * 
 */
public class GetLocPointService {
	//GPS定位结果
	private final static int LOC_RESULT_GPS=61;
	//网络定位结果
	private final static int LOC_RESULT_NET=161;
	
	//定位标准
	private final static String TEMPCOOR_GJ="gcj02";
	private final static String TEMPCOOR_BD="bd09ll";
	private final static String TEMPCOOR_BD09="bd09";
	
	private CallBack_Loc callBack;
	private Context context;
	private LocationClient locationClient;
	public GetLocPointService(CallBack_Loc callBack,Context context){
		this.callBack=callBack;
        this.context=context;
		InitLocation();
		locatCallBack();
	}
	private void InitLocation(){
		LocationClientOption.LocationMode tempMode= LocationClientOption.LocationMode.Hight_Accuracy;
		String tempcoor=TEMPCOOR_GJ;
		locationClient=new LocationClient(AppContextApplication.getInstance());
		LocationClientOption option = new LocationClientOption();
		option.setLocationMode(tempMode);//设置定位模式
		option.setCoorType(tempcoor);//返回的定位结果是百度经纬度，默认值gcj02
		int span=1000;
		try {
			span = 5000;
		} catch (Exception e) {
			// TODO: handle exception
		}
		option.setScanSpan(span);//设置发起定位请求的间隔时间为5000ms
		option.setIsNeedAddress(true);
		locationClient.setLocOption(option);
	}
	private void locatCallBack(){
		locationClient.registerLocationListener(new BDLocationListener(){

			@Override
			public void onReceiveLocation(BDLocation result) {
                P.v("获取位置信息");
                result.getLocType();
				if(result.getLocType()==LOC_RESULT_GPS||result.getLocType()==LOC_RESULT_NET){
					//获取经纬度成功
					callBack.getLocPoint(result.getLongitude(), result.getLatitude(),result.getCity());
					locationClient.stop();
                    callBack.onFinally();
					return;
				}
				callBack.onFail();
				locationClient.stop();
                callBack.onFinally();
			}
		});
		locationClient.start();
        locationClient.requestLocation();
	}
	public static abstract class CallBack_Loc{
		public abstract void getLocPoint(double longitude,double latitude,String city);
		public abstract void onFail();
		public abstract void onFinally();
	}
}
