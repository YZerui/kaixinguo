package com.hi.module.register_login.ui;


import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

import com.android.ruifeng.hi.R;
import com.customview.callBack.topBarCallBack;
import com.customview.view.CustomBtnView;
import com.customview.view.CustomTopbarView;
import com.hi.common.PARAMS;
import com.hi.common.param.Enum_TimerType;
import com.hi.module.base.superClass.NormalActivity;
import com.hi.utils.AnimationUtil;
import com.hi.view.customWidget.CustomDigitalClock;
import com.hi.view.customWidget.CustomDigitalClock.ClockListener;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

/**
 * �ύ��֤�����
 * @author MM_Zerui 
 */
public class SubmitAuthActivity extends NormalActivity{
	
	private static final int AUTHO_SUCCESS=1000;
	private static final int AUTHO_FAIL=1001;
	
	@ViewInject(R.id.topBar)
	private CustomTopbarView topBar;
	@ViewInject(R.id.phoneTimer)
	private CustomDigitalClock timerWidget;
	@ViewInject(R.id.phone_author_code)
	private EditText authorCode;
	@ViewInject(R.id.regiPhoneText)
	private TextView regiPhone;
	@ViewInject(R.id.submitPhoneBtn)
	private CustomBtnView customBtnView;
	private String phone;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.regi_sub_phone_layout);
		ViewUtils.inject(this);
		super.onCreate(savedInstanceState);
		initShareSDK();
		
	}
	private void initShareSDK() {
		// TODO Auto-generated method stub
		SMSSDK.initSDK(this, PARAMS.APP_KEY, PARAMS.APP_SECRET);
		SMSSDK.registerEventHandler(eventHandler);
	}
	
	private EventHandler eventHandler = new EventHandler() {
		public void afterEvent(int event, int result, Object data) {
			switch (event) {		
			case SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE:
				System.out.println("������֤��");
				if(result==SMSSDK.RESULT_COMPLETE){
					handler.sendEmptyMessage(AUTHO_SUCCESS);			
				}else {
					handler.sendEmptyMessage(AUTHO_FAIL);
				}		
				break;
			default:
				System.out.print("�����¼�:"+event);
				break;
			}
		};
	};
	@Override
	public void obtainIntentValue() {
		phone=getIntent().getStringExtra("DATA0");
		System.out.println("phone:"+phone);
	}

	@Override
	public void initResource() {
		// TODO Auto-generated method stub
		regiPhone.setText("(+86)"+phone);
		timerWidget.setEndTime(System.currentTimeMillis() + 60 * 1000,
				Enum_TimerType.TIMER_SECOND, "������·�����֤��");
	}
	@OnClick(R.id.submitPhoneBtn)
	public void submitClick(View v){
		//��֤��֤�����ȷ��
		httpRun();
		String code=authorCode.getText().toString().trim();
		SMSSDK.submitVerificationCode("86",phone, code);
	}
	@Override
	public void onClickListener() {
		// TODO Auto-generated method stub
		timerWidget.setClockListener(new ClockListener() {

			@Override
			public void timeEnd() {
				// TODO Auto-generated method stub
				timerWidget.setEnabled(true);
				timerWidget.setText("������·���");
			}

			@Override
			public void remainFiveMinutes() {
				// TODO Auto-generated method stub
				
			}
		});
		timerWidget.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				timerWidget.reqeat();
				timerWidget.setEnabled(false);
				//���»�ȡ��֤��
				SMSSDK.getVerificationCode("86",phone);	
			}
		});
		topBar.setCallBack(new topBarCallBack() {
			@Override
			public void call_backBtnListener() {
				// TODO Auto-generated method stub
				super.call_backBtnListener();
				outFinish();
			}
		});
	}
	@Override
	public void outFinish() {
		// TODO Auto-generated method stub
		finish();
		AnimationUtil.finishOut2Bottom(context);
	}
	private Handler handler=new Handler(){
		public void handleMessage(android.os.Message msg) {
			httpEnd();
			switch (msg.what) {
			case AUTHO_SUCCESS:
				//���ֻ��������֤�봫������ע��ҳ
				AnimationUtil.in2TopIntent(context, PasswordSetActivity.class,phone);
				break;
			case AUTHO_FAIL:
				toast.setText("��֤ʧ��").show();
				break;
			default:
				break;
			}
		};
	};
	private void httpRun(){
		topBar.setProVisibility(true);
		topBar.setTitle("��֤...");
		customBtnView.setEnabled(false);
	}
	private void httpEnd(){
		topBar.setProVisibility(false);
		topBar.setTitle("��д��֤��");
		customBtnView.setEnabled(true);
	}
}
