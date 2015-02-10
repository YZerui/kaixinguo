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
 * @author MM_Zerui �������ˣ����أ����ݲ������б���ʾ��Activyt
 */
public abstract class ListActivity extends SuperActivity{
	protected ImageView defaultPage;
	protected ImageView defaultRefresh;
	protected RelativeLayout defaultLayout;
	
	protected int limit;// ÿ�������ҳ��
	protected int start;// ÿ���������ʼλ��
	protected boolean initBool=true;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// �趨Ĭ����ֵ
		limit = 15;
		start = 0;
		obtainIntentValue();
		initResource();
		setOnClickListener();
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


	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();

	}

	protected abstract void outFinish();



}
