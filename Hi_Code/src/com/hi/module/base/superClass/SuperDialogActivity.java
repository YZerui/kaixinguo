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
	 * 设定窗体高度
	 * 	@note 此处必须添加代码设定高度属性: lp.height=h 其中h表示实际窗口高度值
	 */
	public abstract void setPageHeight();
	private void init() {
		
		screenHeight = getWindow().getWindowManager().getDefaultDisplay()
				.getHeight();// 获取屏幕高度
		lp = getWindow().getAttributes();// //lp包含了布局的很多信息，通过lp来设置对话框的布局
		lp.width = LayoutParams.FILL_PARENT;
		lp.gravity = Gravity.BOTTOM;
	}
	/**
	 * 活动Intent传递的数值
	 */
	protected abstract void obtainIntentValue();


	/**
	 * 初始化变量资源
	 */
	protected abstract void initResource();
}
