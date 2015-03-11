package com.hi.module.self.ui;



import com.android.ruifeng.hi.R;
import com.exception.utils.P;
import com.hi.common.db.E_DB_SelfIfo;
import com.hi.common.http.E_Http_Gender;
import com.hi.common.http.E_Http_InfoType;
import com.hi.dao.supImpl.Dao_SelfIfo;
import com.hi.http.base.Call_httpData;
import com.hi.http.member.model.Req_UpdateIfo;
import com.hi.http.member.req.Http_UpdateIfo;
import com.hi.module.base.superClass.RequestActivity;
import com.hi.utils.DeviceUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * 性别设定的页面
 * @author MM_Zerui 
 */
public class SetGenderDialog extends RequestActivity implements OnClickListener {
	private static int screenHeight;
	@ViewInject(R.id.manSelectBtn)
	private Button manSelectBtn;
	@ViewInject(R.id.womanSelectBtn)
	private Button womenSelectBtn;
	@ViewInject(R.id.overallLayout)
	private LinearLayout overallLayout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.gender_choose_dialog);
		ViewUtils.inject(this);
		super.onCreate(savedInstanceState);
	
	}
	@Override
	protected void obtainIntentValue() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setOnClickListener() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initResource() {
		screenHeight = getWindow().getWindowManager().getDefaultDisplay()
				.getHeight();// 获取屏幕高度

		WindowManager.LayoutParams lp = getWindow().getAttributes();// //lp包含了布局的很多信息，通过lp来设置对话框的布局
		lp.width = LayoutParams.FILL_PARENT;
		lp.gravity = Gravity.BOTTOM;
		lp.height =DeviceUtils.dip2px(context, 45)*2;
		P.v("高度为:"+overallLayout.getHeight());
		getWindow().setAttributes(lp);// 将设置好属性的lp应用到对话框
	}

	@Override
	protected void requestMethod() {
		
	}
	@OnClick(R.id.manSelectBtn)
	public void manSetClick(View v){
		httpRequest(E_Http_Gender.MAN.toString());
	}
	@OnClick(R.id.womanSelectBtn)
	public void womenSetClick(View v){
		httpRequest(E_Http_Gender.WOMEN.toString());
	}
	public void httpRequest(final String gender){
		Req_UpdateIfo reqBean = new Req_UpdateIfo();
		reqBean.setUid(Dao_SelfIfo.getInstance().getMid());
		reqBean.setT(E_Http_InfoType.GENDER.toString());
		reqBean.setC(gender);
		new Http_UpdateIfo(new Call_httpData<Class>() {
			
			@Override
			public void onSuccess(Class datas) {
				// TODO Auto-generated method stub
				Dao_SelfIfo.setParams(E_DB_SelfIfo.sex,gender);
			}
			
			@Override
			public void onStart() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onFinally() {
				// TODO Auto-generated method stub
				outFinish();
			}
			
			@Override
			public void onFail() {
				// TODO Auto-generated method stub
				toast.setText("更改失败");
				
			}
		}).onParams(reqBean).onAction();
		
	}
	@Override
	protected void outFinish() {
		// TODO Auto-generated method stub
		finish();
		overridePendingTransition(0, R.anim.dialog_exit);
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}

}
