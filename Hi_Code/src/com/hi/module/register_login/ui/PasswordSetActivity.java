package com.hi.module.register_login.ui;


import com.android.ruifeng.hi.R;
import com.customview.callBack.topBarCallBack;
import com.customview.view.CustomBtnView;
import com.customview.view.CustomTopbarView;
import com.format.callBack.callBack_dataVaildate;
import com.format.utils.BeanUtils;
import com.format.utils.DataValidate;
import com.hi.common.http.E_Http_LoginPlat;
import com.hi.dao.model.T_SelfIfo;
import com.hi.dao.supImpl.Dao_SelfIfo;
import com.hi.http.base.Call_httpData;
import com.hi.http.member.model.Recv_PhoneRegi;
import com.hi.http.member.model.Recv_UserIfo;
import com.hi.http.member.model.Req_PhoneLogin;
import com.hi.http.member.model.Req_PhoneRegi;
import com.hi.http.member.req.Http_PhoneLogin;
import com.hi.http.member.req.Http_PhoneRegi;
import com.hi.module.base.superClass.NormalActivity;
import com.hi.module.locale.ui.local.LocalSettingActivity;
import com.hi.utils.AnimationUtil;
import com.hi.utils.DeviceUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * 设定密码
 * @author MM_Zerui
 *
 */
public class PasswordSetActivity extends NormalActivity {
	@ViewInject(R.id.toHomeBtn)
	private CustomBtnView toHomeBtn;
	@ViewInject(R.id.topBar)
	private CustomTopbarView topBar;
	@ViewInject(R.id.phone_password_edit)
	private EditText passWord1;
	@ViewInject(R.id.phone_password_edit_second)
	private EditText passWord2;

	private Req_PhoneRegi reqBean;
	private String authorCode, phone;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.password_setting_layout);
		com.lidroid.xutils.ViewUtils.inject(this);
		super.onCreate(savedInstanceState);
	}

	@Override
	public void obtainIntentValue() {
		// TODO Auto-generated method stub
		phone = getIntent().getStringExtra("DATA0");
		authorCode = "888888";

	}

	@Override
	public void initResource() {
		// TODO Auto-generated method stub
		reqBean = new Req_PhoneRegi();
	}

	@OnClick(R.id.toHomeBtn)
	public void confirmClick(View v) {
		final String word1 = passWord1.getText().toString().trim();
		final String word2 = passWord2.getText().toString().trim();
		DataValidate.checkPassword(word1, new callBack_dataVaildate() {
			
			@Override
			public void call_valid() {
				// TODO Auto-generated method stub
				if(!word1.equals(word2)){
					toast.setText("前后密码不一致");
					return;
				}
				reqBean.setAvid(DeviceUtils.getAvosId());
				reqBean.setCode(authorCode);
				reqBean.setDriveType(E_Http_LoginPlat.android.name());
				reqBean.setDriviceCode(DeviceUtils.getAvosId());
				reqBean.setPhone(phone);
				reqBean.setPw(word1);
				reqBean.setRppw(word2);
				new Http_PhoneRegi(new Call_httpData<Recv_PhoneRegi>() {
					
					@Override
					public void onSuccess(Recv_PhoneRegi datas) {
						// TODO Auto-generated method stub
						//注册成功后进行登录服务
//						Dao_SelfIfo.getInstance().setPhone(datas.getPhone());
//						Dao_SelfIfo.getInstance().setMid(datas.getMid());
//						Dao_SelfIfo.getInstance().setNickName(datas.getNickname());
						LoginMethod(datas);
					}
					
					@Override
					public void onStart() {
						// TODO Auto-generated method stub
						httpRun("提交...");
					}
					
					@Override
					public void onFinally() {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void onFail() {
						// TODO Auto-generated method stub
						toast.setText("设定失败,网络开小差了~").show();
						httpEnd();
					}
				}).onParams(reqBean).onAction();
				
			}
			
			@Override
			public void call_lengthShort() {
				// TODO Auto-generated method stub
				toast.setText("密码太短,请设定3位以上");
			}
			
			@Override
			public void call_lengthNull() {
				// TODO Auto-generated method stub
				toast.setText("请输入密码");
			}
			
			@Override
			public void call_lengthLong() {
				// TODO Auto-generated method stub
				toast.setText("密码太长,请设定适当位数");
			}
			
			@Override
			public void call_lengthInvalid() {
				// TODO Auto-generated method stub
				toast.setText("密码格式不对,请选择数字和字母组合的方式");
			}
		});
		
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
	}
	public void httpRun(String note){
		topBar.setTitle(note).setProVisibility(true);
		toHomeBtn.setEnabled(false);
	}
	public void httpEnd(){
		topBar.setTitle("设置密码").setProVisibility(false);
		toHomeBtn.setEnabled(true);
	}
	private void LoginMethod(Recv_PhoneRegi datas){
		Req_PhoneLogin loginBean = new Req_PhoneLogin();
		loginBean.setDriviceCode(DeviceUtils
				.getAvosId());
		loginBean.setAvid(DeviceUtils
				.getAvosId());
		loginBean.setPhone(reqBean.getPhone());
		loginBean.setPw(reqBean.getPw());
		loginBean.setWifiMac(DeviceUtils
				.getWifiMac());
		loginBean.setDriveType(E_Http_LoginPlat.android.name());
		new Http_PhoneLogin(new Call_httpData<Recv_UserIfo>(){

			@Override
			public void onStart() {
				// TODO Auto-generated method stub
				httpRun("正在登录...");
			}

			@Override
			public void onSuccess(Recv_UserIfo datas) {
				// TODO Auto-generated method stub
				//保存个人信息
				try {
					Dao_SelfIfo.save((T_SelfIfo)BeanUtils.copyProperties(new T_SelfIfo(), datas));
					AnimationUtil.in2TopIntent(context,
							LocalSettingActivity.class);
					return;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				toast.setText("登录失败,请稍后重试");
			}

			@Override
			public void onFail() {
				// TODO Auto-generated method stub
				httpEnd();
				toast.setText("登录失败,请稍后重试");
			}

			@Override
			public void onFinally() {
				// TODO Auto-generated method stub
				httpEnd();
			}
			
		}).onParams(loginBean).onAction();
	}

	@Override
	public void outFinish() {
		// TODO Auto-generated method stub
		finish();
		AnimationUtil.finishOut2Bottom(context);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		outFinish();
	}
}
