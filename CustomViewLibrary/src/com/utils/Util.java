package com.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

public class Util {
	/**
	 * �õ��豸��Ļ�Ŀ��
	 */
	public static int getScreenWidth(Context context) {
		return context.getResources().getDisplayMetrics().widthPixels;
	}

	/**
	 * �õ��豸��Ļ�ĸ߶�
	 */
	public static int getScreenHeight(Context context) {
		return context.getResources().getDisplayMetrics().heightPixels;
	}

	/**
	 * �õ��豸���ܶ�
	 */
	public static float getScreenDensity(Context context) {
		return context.getResources().getDisplayMetrics().density;
	}
	/**
	 * dpת��Ϊpx
	 * 
	 * @param context
	 * @param dpValue
	 * @return
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}
	
	/**
	 * ���ؼ���
	 * @param activity
	 * 
	 */
	public static void hideSoftInputView(Activity activity) {
	    if (activity.getWindow().getAttributes().softInputMode !=
	        WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
	      InputMethodManager manager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
	      View currentFocus = activity.getCurrentFocus();
	      if (currentFocus != null) {
	        manager.hideSoftInputFromWindow(currentFocus.getWindowToken(),
	            InputMethodManager.HIDE_NOT_ALWAYS);
	      }
	    }
	  }
}
