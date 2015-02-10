package com.hi.module.global;

import android.os.Bundle;
import android.view.View;

import com.android.ruifeng.hi.R;
import com.customview.callBack.topBarCallBack;
import com.customview.view.CustomTopbarView;
import com.hi.module.base.superClass.NormalActivity;
import com.hi.utils.AnimationUtil;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * app协议主页
 * @author MM_Zerui
 *
 */
public class AppNotePage extends NormalActivity{
	@ViewInject(R.id.topBar)
	private CustomTopbarView topBar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.app_note_page);
		ViewUtils.inject(this);
		super.onCreate(savedInstanceState);
	}
	


	@OnClick(R.id.item_1)
	public void oneClick(View v){
		//用户协议
		AnimationUtil.in2LeftIntent(context, ProtocolPage.class);
	}
	@OnClick(R.id.item_2)
	public void twoClick(View v){
		//关于我们
		AnimationUtil.in2LeftIntent(context, AboutUsPage.class);
	}
	@OnClick(R.id.item_3)
	public void threeClick(View v){
		//新版本检测
		toast.setText("当前为测试版,敬请期待");
	}
	@OnClick(R.id.item_4)
	public void fourClick(View v){
		//清除缓存
		ImageLoader.getInstance().clearMemoryCache();
		ImageLoader.getInstance().clearDiskCache();
		ImageLoader.getInstance().clearDiscCache();
		toast.setText("清除完成");
	}
	
	/*@OnClick(R.id.item_4)
	public void fourClick(View v){
		//关于我们
	}*/
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
		AnimationUtil.finishOut2Right(context);
	}
	@Override
	public void outFinish() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void obtainIntentValue() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void initResource() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void onClickListener() {
		// TODO Auto-generated method stub
		topBar.setCallBack(new topBarCallBack() {
			@Override
			public void call_backBtnListener() {
				// TODO Auto-generated method stub
				super.call_backBtnListener();
				finish();
			}
		});
	}
}
