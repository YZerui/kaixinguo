package com.hi.service;


import android.content.Context;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.exception.utils.P;
import com.hi.module.base.application.AppContextApplication;

/**
 * ��ȡ��γ�ȷ���
 * @author MM_Zerui
 * 
 */
public class GetLocPointService {
	//GPS��λ���
	private final static int LOC_RESULT_GPS=61;
	//���綨λ���
	private final static int LOC_RESULT_NET=161;
	
	//��λ��׼
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
		option.setLocationMode(tempMode);//���ö�λģʽ
		option.setCoorType(tempcoor);//���صĶ�λ����ǰٶȾ�γ�ȣ�Ĭ��ֵgcj02
		int span=1000;
		try {
			span = 5000;
		} catch (Exception e) {
			// TODO: handle exception
		}
		option.setScanSpan(span);//���÷���λ����ļ��ʱ��Ϊ5000ms
		option.setIsNeedAddress(true);
		locationClient.setLocOption(option);
	}
	private void locatCallBack(){
		locationClient.registerLocationListener(new BDLocationListener(){

			@Override
			public void onReceiveLocation(BDLocation result) {
                P.v("��ȡλ����Ϣ");
                result.getLocType();
				if(result.getLocType()==LOC_RESULT_GPS||result.getLocType()==LOC_RESULT_NET){
					//��ȡ��γ�ȳɹ�
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
