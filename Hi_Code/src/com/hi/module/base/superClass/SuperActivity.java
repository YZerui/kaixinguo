package com.hi.module.base.superClass;

import com.hi.module.base.application.AppManager;
import com.hi.view.customLayout.CustomToast;
import com.hi.view.customLayout.ProgressDialog;
import com.lidroid.xutils.HttpUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;

public class SuperActivity extends Activity {
	// 标明跳转的来源页面
	protected int SOURCE_PAGE_NOTE;
	// 上下文环境
	protected Context context;
	// 图像加载器
	protected ImageLoader imageLoader;
	protected DisplayImageOptions loadOptions;
	protected HttpUtils http;
	protected ProgressDialog progressDialog;
	protected CustomToast toast;
	protected Handler handler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		context=this;
		imageLoader=ImageLoader.getInstance();
		http=new HttpUtils();
		progressDialog=new ProgressDialog(context);
		AppManager.getAppManager().addActivity(this);
		toast=new CustomToast(context);
		toast.locatCenter();
		handler=new Handler();
		super.onCreate(savedInstanceState);
	}
}
