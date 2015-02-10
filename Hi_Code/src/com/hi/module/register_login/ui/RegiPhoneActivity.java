package com.hi.module.register_login.ui;

import java.util.ArrayList;


import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

import com.android.ruifeng.hi.R;
import com.customview.callBack.topBarCallBack;
import com.customview.view.CustomBtnView;
import com.customview.view.CustomTopbarView;
import com.format.callBack.callBack_dataVaildate;
import com.format.utils.DataValidate;
import com.hi.common.PARAMS;
import com.hi.http.base.Call_httpData;
import com.hi.http.member.model.Req_SendPhone;
import com.hi.http.member.req.Http_SendPhone;
import com.hi.module.base.superClass.NormalActivity;
import com.hi.utils.AnimationUtil;
import com.hi.view.customWidget.ClearEditText;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import android.os.Bundle;
import android.view.View;


/**
 * 提交手机号码进行验证的类
 * @author MM_Zerui
 *
 */
public class RegiPhoneActivity extends NormalActivity{


	private String phone;
	
	@ViewInject(R.id.submitPhoneText)
	private ClearEditText phoneEditText;
	@ViewInject(R.id.topBar)
	private CustomTopbarView topBar;
	@ViewInject(R.id.phoneRegiBtn)
	private CustomBtnView phoneRegiBtn;
	
	private boolean getAuthoCode=true;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.regi_phone_layout);
		com.lidroid.xutils.ViewUtils.inject(this);
		super.onCreate(savedInstanceState);
		initShareSDK();
		
	}
	private void initShareSDK() {
		// TODO Auto-generated method stub
		SMSSDK.initSDK(this, PARAMS.APP_KEY, PARAMS.APP_SECRET);
		SMSSDK.registerEventHandler(eventHandler);
	}
	private EventHandler eventHandler=new EventHandler(){
		public void afterEvent(int event, int result, Object data) {
			switch (event) {
			case SMSSDK.EVENT_GET_VERIFICATION_CODE:
				if(getAuthoCode){
					getAuthoCode=false;
					AnimationUtil.in2TopIntent(context, SubmitAuthActivity.class,phone);
					System.out.println("获取短信验证码");
					
				}
				
				
				break;
			default:
				break;
			}
		};
	};
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
		topBar.setCallBack(new topBarCallBack() {	
			@Override
			public void call_backBtnListener() {
				// TODO Auto-generated method stub
				super.call_backBtnListener();
				outFinish();
			}
			
		});
	}
	/**
	 * 提交手机号码注册
	 * @param v
	 */
	@OnClick(R.id.phoneRegiBtn)
	public void regiClick(View v){
		phone=phoneEditText.getText().toString().trim();
		DataValidate.checkPhone(phone, new callBack_dataVaildate() {
			
			@Override
			public void call_valid() {
				// TODO Auto-generated method stub
//				SMSSDK.getVerificationCode("86",phone);
				//提交手机号码检测
				Req_SendPhone reqBean=new Req_SendPhone();
				reqBean.setPhone(phone);
				new Http_SendPhone(new Call_httpData<Class>() {
					
					@Override
					public void onSuccess(Class datas) {
						// TODO Auto-generated method stub
						SMSSDK.getVerificationCode("86",phone);	
//						AnimationUtil.in2TopIntent(context,PasswordSetActivity.class,phone);
					}
					
					@Override
					public void onStart() {
						// TODO Auto-generated method stub
						httpRun("提交...");
					}
					
					@Override
					public void onFinally() {
						// TODO Auto-generated method stub
						httpEnd();
					}
					
					@Override
					public void onFail() {
						// TODO Auto-generated method stub
						httpEnd();
						toast.setText("注册失败,该号码已经注册或网络异常");
					}
				}).onParams(reqBean).onAction();
//				http.send(HttpMethod.POST, API.PHONE_SUBMIT, params, new RequestCallBack<String>() {
//					@Override
//					public void onStart() {
//						// TODO Auto-generated method stub
//						super.onStart();
//						httpRun("提交...");
//					}
//					@Override
//					public void onFailure(HttpException arg0, String arg1) {
//						// TODO Auto-generated method stub
//						httpEnd();
//						toast.setText("网络异常,注册失败");
//					}
//					
//					@Override
//					public void onSuccess(ResponseInfo<String> params) {	
//						new HttpResultService(params.result,new httpResultCallBack() {
//							
//							@Override
//							public void onSuccess() {
//								// TODO Auto-generated method stub
//								SMSSDK.getVerificationCode("86",phone);	
//							}
//							
//							@Override
//							public void onRequestFail() {
//								// TODO Auto-generated method stub
//								toast.setText("提交失败,该号码可能已被注册");
//								
//							}
//
//							@Override
//							public void onFinally() {
//								// TODO Auto-generated method stub
//								httpEnd();
//							}		
//						}, null,false);
//					}
//				});
			}
			
			@Override
			public void call_lengthShort() {
				// TODO Auto-generated method stub
				toast.setText("号码过短,请输入11位数字");
			}
			
			@Override
			public void call_lengthNull() {
				// TODO Auto-generated method stub
				toast.setText("请输入号码");
			}
			
			@Override
			public void call_lengthLong() {
				// TODO Auto-generated method stub
				toast.setText("号码过长,请输入11位数字");
			}
			
			@Override
			public void call_lengthInvalid() {
				// TODO Auto-generated method stub
				toast.setText("输入的号码格式不对,请输入11位数字");
			}
		});
		
		
	}
	private void httpRun(String note){
		topBar.setProVisibility(true);
		topBar.setTitle(note);
		phoneRegiBtn.setEnabled(false);
	}
	private void httpEnd(){
		topBar.setProVisibility(false);
		topBar.setTitle("注册加入");
		phoneRegiBtn.setEnabled(true);
	}
	@Override
	public void outFinish() {
		// TODO Auto-generated method stub
		finish();
		AnimationUtil.finishOut2Bottom(context);
	}

}
