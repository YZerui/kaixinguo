package com.hi.module.register_login.ui;

import java.util.ArrayList;

import org.json.JSONObject;



import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


import com.android.ruifeng.hi.R;
import com.customview.callBack.topBarCallBack;
import com.customview.view.CustomBtnView;
import com.customview.view.CustomTopbarView;

import com.format.utils.BeanUtils;
import com.hi.common.http.E_Http_LoginPlat;
import com.hi.dao.model.T_SelfIfo;
import com.hi.dao.supImpl.Dao_SelfIfo;
import com.hi.http.base.Call_httpData;
import com.hi.http.member.model.Recv_UserIfo;
import com.hi.http.member.model.Req_PhoneLogin;
import com.hi.http.member.req.Http_PhoneLogin;
import com.hi.module.base.superClass.NormalActivity;
import com.hi.module.locale.ui.local.LocalSettingActivity;
import com.hi.utils.AnimationUtil;
import com.hi.utils.DeviceUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

/**
 * 手机号码登录的页面
 * 
 * @author MM_Zerui
 */
public class PhoneLoginActivity extends NormalActivity{
	@ViewInject(R.id.topBar)
	private CustomTopbarView topBar;
	@ViewInject(R.id.phone_login_number_edit)
	private EditText numberEdit;
	@ViewInject(R.id.phone_login_password_edit)
	private EditText passWordEdit;
	@ViewInject(R.id.phoneLoginBtn)
	private CustomBtnView loginBtn;
	
	private String number, password;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.login_phone_login_layout);
		com.lidroid.xutils.ViewUtils.inject(this);
		super.onCreate(savedInstanceState);
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
				outFinish();
			}
		});
	};


	@OnClick(R.id.phoneLoginBtn)
	private void loginClick(View v){
		number = numberEdit.getText().toString();
		password = passWordEdit.getText().toString();
		Req_PhoneLogin reqBean=new Req_PhoneLogin();
		
		reqBean.setDriviceCode(DeviceUtils.getAvosId());
		reqBean.setAvid(DeviceUtils.getAvosId());
		reqBean.setPhone(number);
		reqBean.setPw(password);
		reqBean.setWifiMac(DeviceUtils.getWifiMac());
		reqBean.setDriveType(E_Http_LoginPlat.android.name());
		new Http_PhoneLogin(new Call_httpData<Recv_UserIfo>(){

			@Override
			public void onStart() {
				// TODO Auto-generated method stub
				httpRun("登录中...");
			}

			@Override
			public void onSuccess(Recv_UserIfo datas) {
				// TODO Auto-generated method stub
				//保存个人信息
				try {
					Dao_SelfIfo.save((T_SelfIfo)BeanUtils.copyProperties(new T_SelfIfo(), datas));
					AnimationUtil.in2TopIntent(context, LocalSettingActivity.class);
					return;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				toast.setText("信息出错,登录失败");
			}

			@Override
			public void onFail() {
				// TODO Auto-generated method stub
				httpEnd();
				toast.setText("登录出错,密码有误？");
			}

			@Override
			public void onFinally() {
				// TODO Auto-generated method stub
				httpEnd();
			}
			
		}).onParams(reqBean).onAction();
//		http.send(HttpMethod.POST, API.PHONE_LOGIN,HttpUtils.convertBeanToParams(reqBean),new RequestCallBack<String>() {
//			@Override
//			public void onStart() {
//				// TODO Auto-generated method stub
//				super.onStart();
//				httpRun("登录中...");
//			}
//			@Override
//			public void onFailure(HttpException arg0, String arg1) {
//				// TODO Auto-generated method stub
//				httpEnd();
//				toast.setText("登录出错,是不是网络出状况了").show();
//			}
//
//			@Override
//			public void onSuccess(final ResponseInfo<String> params) {
//				// TODO Auto-generated method stub
//				new HttpResultService(params.result,new httpResultCallBack() {
//					
//					@Override
//					public void onSuccess() {
//						// TODO Auto-generated method stub
//						// 接收登陆后返回的信息
////						RecvUserIfoBean bean = new RecvUserIfoBean();
////						bean = ParseJson.parseLocalJson(params.result,
////								RecvUserIfoBean.class);
////						if (bean == null) {						
////							toast.setText("登录出错").show();
////							return;
////						}
////						
////						// 表明是手机登录
////						SelfDBImpl.setLoginType(PARAMS.LOGIN_TYPE_PHONE);
////						// 初始个人信息
////						SelfDBImpl.save(bean);
////						// 保存账号密码
////						SelfDBImpl.savePhoneLogin(number, password);
////						AnimationUtil.in2TopIntent(context, LocalSettingActivity.class);
//					}
//					@Override
//					public void onData(boolean validity, Object obj) {
//						// TODO Auto-generated method stub
//						super.onData(validity, obj);
//						if(!validity){
//							toast.setText("登录出错").show();
//							return;
//						}
//						RecvUserIfoBean bean=(RecvUserIfoBean)obj;
//						// 表明是手机登录
//						Dao_Params.setLoginType(PARAMS.LOGIN_TYPE_PHONE);
//						// 初始个人信息
//						Dao_Params.save(bean);
//						// 保存账号密码
//						Dao_Params.savePhoneLogin(number, password);
//						AnimationUtil.in2TopIntent(context, LocalSettingActivity.class);
//					}
//
//					@Override
//					public void onRequestFail() {
//						// TODO Auto-generated method stub
//						toast.setText("登录出错,密码不对哦").show();
//					}
//					
//					@Override
//					public void onFinally() {
//						// TODO Auto-generated method stub
//						httpEnd();
//					}
//				}, RecvUserIfoBean.class,false);
//			}
//		});
	}
	
	
	private void httpRun(String note) {
		// TODO Auto-generated method stub
		loginBtn.setEnabled(false);
		topBar.setTitle(note).setProVisibility(true);
	}
	private void httpEnd(){
		loginBtn.setEnabled(true);
		topBar.setTitle("登录").setProVisibility(false);
	}
	
	@Override
	public void outFinish() {
		// TODO Auto-generated method stub
		finish();
		AnimationUtil.finishOut2Bottom(context);

	}

	public void onBackPressed() {
		outFinish();
	}


}
