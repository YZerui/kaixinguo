package com.hi.module.base.superClass;


import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

import com.hi.module.base.application.AppManager;

public abstract class SuperDialogActivity extends SuperActivity{
	protected static int screenHeight;
	protected WindowManager.LayoutParams lp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
        AppManager.getAppManager().addActivity(this);
		init();
		setPageHeight();
		super.onCreate(savedInstanceState);
		
		

	}
	/**
	 * �趨����߶�
	 * 	@note �˴�������Ӵ����趨�߶�����: lp.height=h ����h��ʾʵ�ʴ��ڸ߶�ֵ
	 */
	public abstract void setPageHeight();
	private void init() {
		
		screenHeight = getWindow().getWindowManager().getDefaultDisplay()
				.getHeight();// ��ȡ��Ļ�߶�
		lp = getWindow().getAttributes();// //lp�����˲��ֵĺܶ���Ϣ��ͨ��lp�����öԻ���Ĳ���
		lp.width = LayoutParams.FILL_PARENT;
		lp.gravity = Gravity.BOTTOM;
	}
	/**
	 * �Intent���ݵ���ֵ
	 */
	protected abstract void obtainIntentValue();


	/**
	 * ��ʼ��������Դ
	 */
	protected abstract void initResource();
}
