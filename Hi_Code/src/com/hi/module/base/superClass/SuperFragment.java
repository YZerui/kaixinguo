package com.hi.module.base.superClass;

import com.hi.module.base.application.AppManager;
import com.hi.view.customLayout.CustomToast;
import com.hi.view.customLayout.ProgressDialog;
import com.lidroid.xutils.HttpUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SuperFragment extends Fragment{
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
		// TODO Auto-generated method stub
		context=getActivity();
		imageLoader=ImageLoader.getInstance();
		http=new HttpUtils();
		progressDialog=new ProgressDialog(context);
		AppManager.getAppManager().addActivity(getActivity());
		toast=new CustomToast(context);
		toast.locatCenter();
		return super.onCreateView(inflater, container, savedInstanceState);
	}
}
