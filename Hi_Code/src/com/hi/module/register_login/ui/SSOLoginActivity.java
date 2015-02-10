package com.hi.module.register_login.ui;

import java.util.HashMap;


import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;

import com.android.ruifeng.hi.R;
import com.format.utils.BeanUtils;
import com.hi.common.PARAMS;
import com.hi.dao.model.T_SelfIfo;
import com.hi.dao.supImpl.Dao_Params;
import com.hi.dao.supImpl.Dao_SelfIfo;
import com.hi.http.base.Call_httpData;
import com.hi.http.member.model.Recv_UserIfo;
import com.hi.http.member.model.Req_ThirdLogin;
import com.hi.http.member.req.Http_LoginThird;
import com.hi.module.base.application.AppManager;
import com.hi.module.locale.ui.local.LocalSettingActivity;
import com.hi.module.register_login.model.ThirdPlatUser;
import com.hi.service.IMMessageService;
import com.hi.utils.AnimationUtil;
import com.hi.utils.ViewHandleUtils;
import com.hi.view.customLayout.ProgressDialog;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.thread.RunnableService;
import com.thread.callBack.runCallBack;

import android.app.Activity;
import android.app.ActivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

/**
 * ������SSO��¼
 * 
 * @author MM_Zerui
 * 
 */
public class SSOLoginActivity extends Activity {
	private Req_ThirdLogin ssoBean;
	private final static int QQ_LOGIN_SUCCESS = 1000;
	private final static int WEIBO_LOGIN_SUCCESS = 1001;
	private final static int HTTP_THIRD_LOGIN = 1002;// ��������¼����Ӧ��
	private final static int SSO_LOGIN_CANCEL = 1003;// ��������¼����Ӧ��
	private final static int SSO_LOGIN_FAIL = 1004;// ��������¼����Ӧ��
	private final static int SSO_LOGIN_COMPLETE = 1005;// ��������ת���

	Platform sinaPlat, qqPlat;

	ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_main_layout);
		ViewUtils.inject(this);
		AppManager.getAppManager().addActivity(this);
		init();
		//�ر�Session����
		IMMessageService imService=new IMMessageService();
		imService.closeSession();
		
	}

	private void init() {
		// TODO Auto-generated method stub

		ssoBean = new Req_ThirdLogin();

		ShareSDK.initSDK(this);

		sinaPlat = ShareSDK.getPlatform(SinaWeibo.NAME);
		sinaPlat.SSOSetting(false);

		qqPlat = ShareSDK.getPlatform(QQ.NAME);
		qqPlat.SSOSetting(false);

		progressDialog = new ProgressDialog(SSOLoginActivity.this);
		
		Handler handler=new Handler();
		handler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				AppManager.getAppManager().finishActivity(AppStartActivity.class);
			}
		},500);

	}

	@OnClick(R.id.registerBtn)
	public void onRegisterClick(View v) {
		// ��ת��ע��ҳ��
		AnimationUtil.in2TopIntent(this, RegiPhoneActivity.class);
	}

	@OnClick(R.id.phoneLoginBtn)
	public void onPhoneLoginClick(View v) {
		AnimationUtil.in2TopIntent(this, PhoneLoginActivity.class);
	}

	@OnClick(R.id.sina_sign_layout)
	public void onSinaLoginClick(View v) {
		// new CustomToast(this).setText("��������΢����¼").locatBottom().show();
		progressDialog.withCircel().show();
		sinaPlat.authorize();

		sinaPlat.setPlatformActionListener(new PlatformActionListener() {

			@Override
			public void onError(Platform arg0, int arg1, Throwable arg2) {
				// TODO Auto-generated method stub
				handler.sendEmptyMessage(SSO_LOGIN_FAIL);
			}

			@Override
			public void onComplete(Platform plat, int action,
					HashMap<String, Object> arg2) {
				// TODO Auto-generated method stub
				if (action == Platform.ACTION_AUTHORIZING) {
					System.out.println("���΢�����û�ID:" + plat.getDb().getUserId());
					// ��ȡ��Ȩ��Ϣ
					ssoBean.setOpenid(plat.getDb().getUserId());
					ssoBean.setType(PARAMS.THIRD_LOGIN_WEIBO);
					// �����û���Ϣ�Ļ�ȡ
					sinaPlat.showUser(null);
					handler.sendEmptyMessage(SSO_LOGIN_COMPLETE);
				}
				if (action == Platform.ACTION_USER_INFOR) {
					ThirdPlatUser user = ThirdPlatUser.parseWeibo(arg2);
					ssoBean.setGender(user.getGender());
					ssoBean.setFigureurl(user.getFigure());
					ssoBean.setNickname(user.getNickName());

					// ֪ͨ΢����¼�ɹ�
					handler.sendEmptyMessage(WEIBO_LOGIN_SUCCESS);
				}
			}

			@Override
			public void onCancel(Platform arg0, int arg1) {
				// TODO Auto-generated method stub
				handler.sendEmptyMessage(SSO_LOGIN_CANCEL);
			}
		});
	}

	@OnClick(R.id.qq_sign_layout)
	public void onQQLoginClick(View v) {
		progressDialog.withCircel().show();
		qqPlat.authorize();
		qqPlat.setPlatformActionListener(new PlatformActionListener() {

			@Override
			public void onError(Platform arg0, int arg1, Throwable arg2) {
				// TODO Auto-generated method stub
				handler.sendEmptyMessage(SSO_LOGIN_FAIL);
			}

			@Override
			public void onComplete(Platform plat, int action,
					HashMap<String, Object> result) {
				// TODO Auto-generated method stub
				if (action == Platform.ACTION_AUTHORIZING) {
					System.out.println("���QQ���û�ID:" + plat.getDb().getUserId());
					// ��ȡ��Ȩ��Ϣ
					ssoBean.setOpenid(plat.getDb().getUserId());
					ssoBean.setType(PARAMS.THIRD_LOGIN_QQ);
					// �����û���Ϣ�Ļ�ȡ
					qqPlat.showUser(null);
					handler.sendEmptyMessage(SSO_LOGIN_COMPLETE);
				}
				if (action == Platform.ACTION_USER_INFOR) {
					ThirdPlatUser user = ThirdPlatUser.parseQQ(result);
					ssoBean.setFigureurl(user.getFigure());
					ssoBean.setNickname(user.getNickName());
					ssoBean.setGender(user.getGender());
					handler.sendEmptyMessage(QQ_LOGIN_SUCCESS);

				}
			}

			@Override
			public void onCancel(Platform arg0, int arg1) {
				// TODO Auto-generated method stub
				handler.sendEmptyMessage(SSO_LOGIN_CANCEL);
			}
		});

	}

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case QQ_LOGIN_SUCCESS:
			case WEIBO_LOGIN_SUCCESS:
				// ����¼��Ϣд�����ݿ���
				Dao_Params.SaveThirdLoginIfo(ssoBean);
				ssoBean = Dao_Params.getThirdLoginIfo();
				new Http_LoginThird(new Call_httpData<Recv_UserIfo>() {
					
					@Override
					public void onSuccess(Recv_UserIfo datas) {
						// TODO Auto-generated method stub
						T_SelfIfo bean=new T_SelfIfo();
						try {
							BeanUtils.copyProperties(bean,datas);
							Dao_SelfIfo.save(bean);
							//��ת����¼��ʼ����ҳ��
							AnimationUtil.in2SmallIntent(SSOLoginActivity.this,
									LocalSettingActivity.class);
							return;
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						ViewHandleUtils.toastNormal("��ȡ��Ϣ����,��½ʧ��");
					}
					
					@Override
					public void onStart() {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void onFinally() {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void onFail() {
						// TODO Auto-generated method stub
						ViewHandleUtils.toastNormal("��ȡ��Ϣ����,��½ʧ��");
					}
				}).onParams(ssoBean).onAction();
				break;
			case SSO_LOGIN_CANCEL:
				progressDialog.dismiss();
				break;
			case SSO_LOGIN_FAIL:
				ViewHandleUtils.toastNormal("��½ʧ��");
				progressDialog.dismiss();
				break;
			case SSO_LOGIN_COMPLETE:
				progressDialog.withCircel().show();
				break;
			default:
				break;
			}
		};
	};

	public void onBackPressed() {
		AppManager.getAppManager().finishAllActivity();
	};
}
