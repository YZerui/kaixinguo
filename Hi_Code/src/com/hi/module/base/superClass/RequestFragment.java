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
 * ������������Fragment
 */
/**
 * @author MM_Zerui
 * 
 */
public abstract class RequestFragment extends Fragment{
	// ������ת����Դҳ��
	protected int SOURCE_PAGE_NOTE;
	// �����Ļ���
	protected Context context;
	// ͼ�������
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
	 * �Intent���ݵ���ֵ
	 */
	protected abstract void obtainIntentValue();

	/**
	 * ����ͼ��Դ
	 */
	protected abstract void findViewById();

	/**
	 * �趨�����ص�
	 */
	protected abstract void setOnClickListener();

	/**
	 * ��ʼ��������Դ
	 */
	protected abstract void initResource();

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();

	}

}
