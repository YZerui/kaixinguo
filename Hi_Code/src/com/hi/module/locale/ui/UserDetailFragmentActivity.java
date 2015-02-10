package com.hi.module.locale.ui;

import com.android.ruifeng.hi.R;
import com.customview.callBack.topBarCallBack;
import com.customview.view.CustomTopbarView;
import com.hi.common.param.Enum_Page;
import com.hi.module.base.application.AppManager;
import com.hi.utils.AnimationUtil;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

/**
 * 显示用户详情信息的页面
 * @author MM_Zerui
 * 
 */
public class UserDetailFragmentActivity extends FragmentActivity{
	private FragmentManager fm = getSupportFragmentManager();
	private FragmentTransaction fragmentTransaction = getSupportFragmentManager()
			.beginTransaction();
	public static String uId;
	public static String head;
	public static String nickName;
	public static String pageSource;
//	private RelativeLayout backLayout;
	public static CustomTopbarView topBar;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);	
		AppManager.getAppManager().addActivity(this);
		//获得用户的Id
		uId=getIntent().getStringExtra("DATA0");
		head = getIntent().getStringExtra("DATA1");
		nickName = getIntent().getStringExtra("DATA2");
		pageSource=getIntent().getStringExtra("DATA3");
		setContentView(R.layout.local_detail_fragment_activity);
		topBar=(CustomTopbarView)findViewById(R.id.topBar);
		topBar.setCallBack(new topBarCallBack() {
			@Override
			public void call_backBtnListener() {
				// TODO Auto-generated method stub
				super.call_backBtnListener();
				finish();
				AnimationUtil.finishOut2Right(UserDetailFragmentActivity.this);
			}
		});
		topBar.setProVisibility(true);
		topBar.setTitle(nickName);
		fragmentTransaction.replace(R.id.local_detail_fragment, new UserFragment());
		fragmentTransaction.commit();
		
	
	}
}
