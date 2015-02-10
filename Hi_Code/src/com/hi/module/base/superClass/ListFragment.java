package com.hi.module.base.superClass;

import com.android.ruifeng.hi.R;
import com.hi.common.EXCEPTION_CODE;
import com.hi.common.PARAMS;
import com.hi.module.base.application.AppManager;
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
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

/**
 * @author MM_Zerui �����������ݲ������б���ʾ��Activyt
 */
public abstract class ListFragment extends Fragment {
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
	protected boolean initBool;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		init();
		obtainIntentValue();
		findViewById();
		setOnClickListener();
		initResource();
		return container;
	}

	private void init() {
		// TODO Auto-generated method stub
		initBool=true;
		context=getActivity();
		imageLoader=ImageLoader.getInstance();
		http=new HttpUtils();
		progressDialog=new ProgressDialog(context);
		AppManager.getAppManager().addActivity(getActivity());
		toast=new CustomToast(context);
		toast.locatCenter();
	}

	/**
	 * �Intent���ݵ���ֵ
	 */
	protected void obtainIntentValue() {

	}

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
