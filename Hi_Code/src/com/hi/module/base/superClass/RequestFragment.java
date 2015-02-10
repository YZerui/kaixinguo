package com.hi.module.base.superClass;

import com.android.ruifeng.hi.R;
import com.hi.module.base.application.AppManager;
import com.hi.module.self.ui.ImgWallSettingActivity;
import com.hi.view.customLayout.CustomToast;
import com.hi.view.customLayout.ProgressDialog;
import com.hi.view.customWidget.ProgressWheel;
import com.lidroid.xutils.HttpUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.app.Activity;
import android.app.Dialog;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.TextView;

/**
 * @author MM_Zerui
 * 针对网络请求的Fragment
 */
/**
 * @author MM_Zerui
 * 
 */
public abstract class RequestFragment extends Fragment{
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

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		init();
		obtainIntentValue();
		findViewById();
		setOnClickListener();
		initResource();
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	private void init() {
		// TODO Auto-generated method stub
		context=getActivity();
		imageLoader=ImageLoader.getInstance();
		http=new HttpUtils();
		progressDialog=new ProgressDialog(context);
		AppManager.getAppManager().addActivity(getActivity());
		toast=new CustomToast(context);
		toast.locatCenter();
		
		loadOptions = new DisplayImageOptions.Builder()
				.showImageForEmptyUri(R.drawable.app_icon)
				.showImageOnFail(R.drawable.app_icon).cacheInMemory(true)
				.showStubImage(R.drawable.app_icon).cacheOnDisc(true).build();
		imageLoader = ImageLoader.getInstance();
	}

	/**
	 * 活动Intent传递的数值
	 */
	protected abstract void obtainIntentValue();

	/**
	 * 绑定视图资源
	 */
	protected abstract void findViewById();

	/**
	 * 设定监听回调
	 */
	protected abstract void setOnClickListener();

	/**
	 * 初始化变量资源
	 */
	protected abstract void initResource();

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();

	}

}
