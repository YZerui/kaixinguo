package com.hi.module.base.superClass;

import com.android.ruifeng.hi.R;
import com.hi.module.base.application.AppManager;
import com.hi.utils.ViewHandleUtils;
import com.hi.view.customWidget.ProgressWheel;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.core.display.BitmapDisplayer;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author MM_Zerui
 * ������������Activity
 */
/**
 * @author MM_Zerui
 * 
 */
public abstract class RequestActivity extends SuperActivity{


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		obtainIntentValue();
		initResource();
		setOnClickListener();
		requestMethod();
		

	}

	/**
	 * �Intent���ݵ���ֵ
	 */
	protected abstract void obtainIntentValue();


	/**
	 * �趨�����ص�
	 */
	protected abstract void setOnClickListener();

	/**
	 * ��ʼ��������Դ
	 */
	protected abstract void initResource();


	/**
	 * ������Դ���󷽷�
	 */
	protected abstract void requestMethod();
		

	/**
	 * ҳ���˳�����
	 */
	protected abstract void outFinish();


	@Override
	protected void onPause() {
		super.onPause();

	}

}
