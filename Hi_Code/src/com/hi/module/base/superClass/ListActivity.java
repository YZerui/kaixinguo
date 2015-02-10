package com.hi.module.base.superClass;

import com.android.ruifeng.hi.R;
import com.hi.common.EXCEPTION_CODE;
import com.hi.module.base.application.AppManager;
import com.hi.view.customWidget.ProgressWheel;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

/**
 * @author MM_Zerui 请求服务端（本地）数据并进行列表显示的Activyt
 */
public abstract class ListActivity extends SuperActivity{
	protected ImageView defaultPage;
	protected ImageView defaultRefresh;
	protected RelativeLayout defaultLayout;
	
	protected int limit;// 每次请求的页数
	protected int start;// 每次请求的起始位置
	protected boolean initBool=true;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设定默认数值
		limit = 15;
		start = 0;
		obtainIntentValue();
		initResource();
		setOnClickListener();
	}

	/**
	 * 活动Intent传递的数值
	 */
	protected abstract void obtainIntentValue();


	/**
	 * 设定监听回调
	 */
	protected abstract void setOnClickListener();

	/**
	 * 初始化变量资源
	 */
	protected abstract void initResource();


	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();

	}

	protected abstract void outFinish();



}
