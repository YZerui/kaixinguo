package com.hi.utils;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVInstallation;
import com.hi.module.base.application.AppContextApplication;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * @author MM_Zerui 获取设备相关信息的工具
 */
public class DeviceUtils {
	/**
	 * @return 路由器的mac地址
	 */
	public static String getWifiMac() {
		WifiManager mWifi = (WifiManager) AppContextApplication.getInstance()
				.getSystemService(Context.WIFI_SERVICE);
		if (mWifi.isWifiEnabled()) {
			WifiInfo wifiInfo = mWifi.getConnectionInfo();
			String netName = wifiInfo.getSSID(); // 获取被连接网络的名称
			String netMac = wifiInfo.getBSSID(); // 获取被连接网络的mac地址
			String localMac = wifiInfo.getMacAddress();// 获得本机的MAC地址
			Log.d("MainActivity", "---netName:" + netName); // ---netName:HUAWEI
															// MediaPad
			Log.d("MainActivity", "---netMac:" + netMac); // ---netMac:78:f5:fd:ae:b9:97
			Log.d("MainActivity", "---localMac:" + localMac); // ---localMac:BC:76:70:9F:56:BD
			System.out.println("wifi mac:" + netMac);
			return netMac;
		}
		return null;
	}

	/**
	 * @param context
	 * @return 获得设备唯一标识
	 */
	public static String getIMEI() {
		// TelephonyManager TelephonyMgr =
		// (TelephonyManager)PushDemoApp.getInstance().getSystemService(PushDemoApp.getInstance().TELEPHONY_SERVICE);
		// String szImei = TelephonyMgr.getDeviceId();
		// System.out.println("设备唯一标识:"+szImei);
		return AVInstallation.getCurrentInstallation().getInstallationId();
	}

	/**
	 * @return 获取推送的设备标识码
	 */
	public static String getAvosId() {
		System.out.println("AvosId:"
				+ AVInstallation.getCurrentInstallation().getInstallationId());
		return AVInstallation.getCurrentInstallation().getInstallationId();

	}

	public static void deleteAvosId() {
		try {
			AVInstallation.getCurrentInstallation().delete();
		} catch (AVException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("删除失败...");
		}
	}

	/**
	 * dp转化为px
	 * 
	 * @param context
	 * @param dpValue
	 * @return
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	public static int getScreenWidth(Context context) {
		return context.getResources().getDisplayMetrics().widthPixels;
	}

	public static int getScreenHeight(Context context) {
		return context.getResources().getDisplayMetrics().heightPixels;
	}

	public static float getScreenDensity(Context context) {
		return context.getResources().getDisplayMetrics().density;
	}



}
