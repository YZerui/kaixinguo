package com.hi.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import net.sf.json.JSONObject;

import com.android.ruifeng.hi.R;
import com.hi.module.base.application.AppContextApplication;
import com.hi.module.base.application.AppManager;
import com.hi.module.base.application.TabBarActivity;
import com.hi.module.msg.ui.MsgDetailActivity;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TabActivity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import android.widget.RemoteViews;

public class FunUtils {
	public static boolean ifNotify=false;
	public static String uid=new String();
	public static String name=new String();
	public static String head=new String();
	/**
	 * @param context
	 * @param mainTitleText
	 * @param contentTitle
	 * @param contentText
	 * @param id
	 * @param imgId
	 * @param defaults
	 *            发起通知，在通知栏进行显示
	 */
	public static void setNotification(Context context, String mainTitleText,
			String contentTitle, String contentText, int id, int imgId,
			int defaults, String sendId,String name,String head) {
		AcquireWakeLock(context);
		NotificationManager notificationManager = (NotificationManager) context
				.getSystemService(context.NOTIFICATION_SERVICE);
		Notification notification = new Notification(imgId, mainTitleText,
				System.currentTimeMillis());
		// 注意getActivity的方法
		Intent intent = new Intent(context, TabBarActivity.class);
//		intent.putExtra("DATA0", sendId);
		//设定弹出页面的相关标识
		
//		intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
				intent, 0);
	
//		notification.deleteIntent = contentIntent;
		FunUtils.ifNotify=true;
		FunUtils.uid=sendId;
		FunUtils.name=name;
		FunUtils.head=head;
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
				R.layout.notification_bar);
		notification.contentView = remoteViews;
		notification.setLatestEventInfo(context, contentTitle, contentText,
				contentIntent);
		notification.flags = Notification.FLAG_AUTO_CANCEL;
		notification.defaults = defaults;
		notificationManager.notify(id, notification);
	
	}

	public static void AcquireWakeLock(Context context) {
		PowerManager pm = (PowerManager) context
				.getSystemService(Context.POWER_SERVICE);

		WakeLock m_wakeObj = (WakeLock) pm.newWakeLock(
				PowerManager.FULL_WAKE_LOCK
						| PowerManager.ACQUIRE_CAUSES_WAKEUP
						| PowerManager.ON_AFTER_RELEASE, "");

		// m_wakeObj.acquire();

		// 点亮屏幕15秒钟
		m_wakeObj.acquire(1000 * 5);
		m_wakeObj.release();// 释放资源

	}

	/**
	 * @param jsonString
	 * @param beanCalss
	 * @return 将Json转化为Bean
	 */
	public static <T> T jsonToBean(String jsonString, Class<T> beanCalss) {

		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		T bean = (T) JSONObject.toBean(jsonObject, beanCalss);
		return bean;

	}

	/**
	 * @param bmp
	 * @param needRecycle
	 * @return 压缩图片
	 */
	public static byte[] bmpToByteArray(final Bitmap bmp,
			final boolean needRecycle) {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		bmp.compress(CompressFormat.PNG, 100, output);
		if (needRecycle) {
			bmp.recycle();
		}

		byte[] result = output.toByteArray();
		try {
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 删除指定路径的图片
	 * 
	 * @param path
	 * @param fileName
	 */
	public static void deletePhotoAtPathAndName(String path, String fileName) {
		if (checkSDCardAvailable()) {
			File folder = new File(path);
			File[] files = folder.listFiles();
			for (int i = 0; i < files.length; i++) {
				System.out.println(files[i].getName());
				if (files[i].getName().equals(fileName)) {
					files[i].delete();
				}
			}
		}
	}

	/**
	 * Check the SD card
	 * 
	 * @return
	 */
	public static boolean checkSDCardAvailable() {
		return android.os.Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED);
	}

	/**
	 * Save image to the SD card
	 * 
	 * @param photoBitmap
	 * @param photoName
	 * @param path
	 */
	public static void savePhotoToSDCard(Bitmap photoBitmap, String path,
			String photoName) {
		if (checkSDCardAvailable()) {
			File dir = new File(path);
			if (!dir.exists()) {
				dir.mkdirs();
			}

			File photoFile = new File(path, photoName + ".png");
			FileOutputStream fileOutputStream = null;
			try {
				fileOutputStream = new FileOutputStream(photoFile);
				if (photoBitmap != null) {
					if (photoBitmap.compress(Bitmap.CompressFormat.PNG, 100,
							fileOutputStream)) {
						fileOutputStream.flush();
						// fileOutputStream.close();
					}
				}
			} catch (FileNotFoundException e) {
				photoFile.delete();
				e.printStackTrace();
			} catch (IOException e) {
				photoFile.delete();
				e.printStackTrace();
			} finally {
				try {
					if (fileOutputStream != null) {
						fileOutputStream.close();
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/** 获取当前运行Activity **/
	public static String getRunningActivity() {
		ActivityManager am = (ActivityManager) AppContextApplication.getInstance()
				.getSystemService(Context.ACTIVITY_SERVICE);
		ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
		String className=cn.getClassName();	
//		int index=className.lastIndexOf(".");
//		String subStr=className.substring(index+1);
		return className;
	}
}
