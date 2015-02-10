package com.hi.module.base.application;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONObject;

import com.android.ruifeng.hi.R;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.avos.avoscloud.Session;
import com.avos.avoscloud.SessionManager;

import com.exception.utils.P;
import com.hi.common.PARAMS;
import com.hi.common.param.Enum_Page;
import com.hi.dao.supImpl.Dao_IMsgSeq;
import com.hi.dao.supImpl.Dao_MsgSeq;
import com.hi.dao.supImpl.Dao_SelfIfo;
import com.hi.module.friend.ui.LocalFriendsActivity;
import com.hi.module.locale.ui.LocalFragmentActivity;
import com.hi.module.msg.ui.MsgActivity;
import com.hi.module.msg.ui.MsgDetailActivity;
import com.hi.module.register_login.ui.AppStartActivity;
import com.hi.module.register_login.ui.PasswordSetActivity;
import com.hi.module.register_login.ui.PhoneLoginActivity;
import com.hi.module.register_login.ui.RegiPhoneActivity;
import com.hi.module.register_login.ui.SSOLoginActivity;
import com.hi.module.register_login.ui.SubmitAuthActivity;
import com.hi.module.self.ui.MyselfActivity;
import com.hi.module.store.ui.ShopActivity;
import com.hi.service.LocalWifiCheckService;
import com.hi.service.LocalWifiSignService;
import com.hi.service.sync.SyncConpousService;
import com.hi.service.sync.SyncInfoService;
import com.hi.service.sync.SyncMsgService;
import com.hi.service.sync.SyncMyfreinds;
import com.hi.utils.AnimationUtil;
import com.hi.utils.FunUtils;
import com.hi.view.customDialogStyle.base.Effectstype;
import com.hi.view.customLayout.MyDialog;
import com.hi.view.customWidget.BadgeView;
import com.hi.view.customWidget.TabView;
import com.hi.view.customWidget.TabView2;
import com.lidroid.xutils.exception.DbException;

//import com.meimei.custom.view.MyDialog;

import android.app.Service;
import android.app.TabActivity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.provider.ContactsContract;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TabHost.TabSpec;

public class TabBarActivity extends TabActivity {
	private TabHost tabHost;
	private Context context;
	Service service;
	private TabWidget tabs;
	private BadgeView badgeView;

	// private Dao_MsgSeq groupDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.ActivityGroup#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_main);
		AppManager.getAppManager().addActivity(this);
		initView();
		init();
		dataRequest();
		closeRemainPage();
		registerReceive();
		// String str=null;
		// P.v(str.length()+"");
		contactsAuthorized();

		// ���м�ʱͨѶ��¼����
		imLogin();
		 AppContextApplication.initIMService();
		

	}

	private void imLogin() {
		final String uid = Dao_SelfIfo.getInstance().getMid();
		AVUser.logInInBackground(uid, uid, new LogInCallback<AVUser>() {
			@Override
			public void done(AVUser user, AVException e) {
				// if (e != null) {
				// // ��½ʧ��
				// } else {
				List<String> peerIds = new LinkedList<String>();
				Session session = SessionManager.getInstance(uid);
				session.open(peerIds);
				// }
			}

		});
	}

	private void closeRemainPage() {
		// TODO Auto-generated method stub
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				AppManager.getAppManager().finishActivity(
						AppStartActivity.class);
				AppManager.getAppManager().finishActivity(
						PasswordSetActivity.class);
				AppManager.getAppManager().finishActivity(
						PhoneLoginActivity.class);
				AppManager.getAppManager().finishActivity(
						RegiPhoneActivity.class);
				AppManager.getAppManager().finishActivity(
						SSOLoginActivity.class);
				AppManager.getAppManager().finishActivity(
						SubmitAuthActivity.class);
			}
		}, 500);

	}

	/**
	 * ��ȡͨѶ¼��Ȩ����Ȩ
	 */
	private void contactsAuthorized() {
		Cursor c = context.getContentResolver().query(
				ContactsContract.Contacts.CONTENT_URI,
				null,
				null,
				null,
				ContactsContract.Contacts.DISPLAY_NAME
						+ " COLLATE LOCALIZED ASC");
	}

	private void init() {
		badgeView = new BadgeView(this, tabs, 0);
		badgeView.setTextSize(11);
	}

	private void initView() {
		context = this;
		tabs = (TabWidget) findViewById(android.R.id.tabs);

		tabHost = getTabHost();
		TabView2 tabView1 = new TabView2(this, R.drawable.tab_1,
				R.drawable.tab_1_highlight);
		TabSpec tabSpec1 = tabHost.newTabSpec("tab1");
		tabSpec1.setIndicator(tabView1);
		Intent intent1 = new Intent(this, MsgActivity.class);
		tabSpec1.setContent(intent1);

		TabView2 tabView2 = new TabView2(this, R.drawable.tab_2,
				R.drawable.tab_2_highlight);
		TabSpec tabSpec2 = tabHost.newTabSpec("tab2");
		tabSpec2.setIndicator(tabView2);
		Intent intent2 = new Intent(this, LocalFriendsActivity.class);
		tabSpec2.setContent(intent2);

		TabView tabView = new TabView(this, R.drawable.tab_3,
				R.drawable.tab_3_highlight);
		TabSpec tabSpec3 = tabHost.newTabSpec("tab3");
		tabSpec3.setIndicator(tabView);
		Intent intent3 = new Intent(this, LocalFragmentActivity.class);
		tabSpec3.setContent(intent3);

		TabView2 tabView4 = new TabView2(this, R.drawable.tab_4,
				R.drawable.tab_4_highlight);
		TabSpec tabSpec4 = tabHost.newTabSpec("tab4");
		tabSpec4.setIndicator(tabView4);
		Intent intent4 = new Intent(this, ShopActivity.class);
		tabSpec4.setContent(intent4);

		TabView2 tabView5 = new TabView2(this, R.drawable.tab_5_xx,
				R.drawable.tab_5_highlight);
		TabSpec tabSpec5 = tabHost.newTabSpec("tab5");
		tabSpec5.setIndicator(tabView5);
		Intent intent5 = new Intent(this, MyselfActivity.class);
		tabSpec5.setContent(intent5);

		tabHost.addTab(tabSpec1);
		tabHost.addTab(tabSpec2);
		tabHost.addTab(tabSpec3);
		tabHost.addTab(tabSpec4);
		tabHost.addTab(tabSpec5);
		tabHost.setCurrentTabByTag("tab3");
	}

	/**
	 * ע�������
	 * 
	 */
	private void registerReceive() {
		// TODO Auto-generated method stub
		// ע�������
		msgReceive = new MsgNotifyReceive();
		IntentFilter filter_system = new IntentFilter();
		filter_system.addAction(Enum_Page.TAPBAR.name());
		this.registerReceiver(msgReceive, filter_system);
	}

	/**
	 * ���ݵ������û������ݽ���������
	 * 
	 * @tip_1 �����û���Ϣ�����ˣ���������ȡ����
	 * @tip_2 �����ڨ��Ϣ���ݣ����ˣ�
	 * @tip_3 �����ҵ�������Ϣ�����ˣ�������Ľ�����뱾�����ݿ���
	 * @tip_4 ����ͨѶ¼�������(�����Ѿ��Ƶ�����ģ����У�
	 * @tip_5 �����ֳ�wifi��ǩ������
	 */
	private void dataRequest() {

		// �������������ǩ��
		Intent wifiService = new Intent(this, LocalWifiSignService.class);
		bindService(wifiService, conn, Context.BIND_AUTO_CREATE);

		// ͬ��������Ϣ����
		AnimationUtil.startService(context, SyncInfoService.class);
		// ͬ����ϵ����Ϣ
		AnimationUtil.startService(context, SyncMyfreinds.class);

		// ��⵱ǰ����wifi
		Intent wifiCheck = new Intent(this, LocalWifiCheckService.class);
		bindService(wifiCheck, conn, Context.BIND_AUTO_CREATE);

		// ͬ���Ż݄��ķ���
		AnimationUtil.startService(context, SyncConpousService.class);
	}

	private ServiceConnection conn = new ServiceConnection() {
		/** ��ȡ�������ʱ�Ĳ��� */
		public void onServiceConnected(ComponentName name, IBinder service) {

		}

		/** �޷���ȡ���������ʱ�Ĳ��� */
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			service = null;
		}

	};

	protected void onDestroy() {
		// ȡ����������ע��
		this.unregisterReceiver(msgReceive);
		this.unbindService(conn);
		super.onDestroy();

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub

		if (AnimationUtil.ANIM_IN != 0 && AnimationUtil.ANIM_OUT != 0) {
			super.overridePendingTransition(AnimationUtil.ANIM_IN,
					AnimationUtil.ANIM_OUT);
			AnimationUtil.clear();
		}
		super.onPause();
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		System.out.println("press");
		AppManager.getAppManager().finishAllActivity();
		super.onBackPressed();

	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			new MyDialog(TabBarActivity.this).withTitle("���Ҫ�˳�ô")
					.withMessage("�˳����㽫���ֳ�������������Ŷ")
					.withSwitchBtnLayout("�˳�", "����").withAnimatDuration(350)
					.withAnimat(Effectstype.Slit)
					.setOkBtnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							AppManager.getAppManager().finishAllActivity();
						}
					}).show();
			return false;
		}
		return super.dispatchKeyEvent(event);
	}

	public static void refreshView() {

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		updateBadegView();
		System.out.println("TabActivity onResume...");
		// �ж��Ƿ�����֪ͨ����
		notifyCheckMethod();
	}

	private void notifyCheckMethod() {
		// TODO Auto-generated method stub
		if (FunUtils.ifNotify) {
			tabHost.setCurrentTabByTag("tab1");
			Handler handler = new Handler();
			handler.postDelayed(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					// ��ת�Ĺ����д����������������Է�UID���Է�ͷ�񣬶Է��ǳ�
					AnimationUtil.tab_in2LeftIntent(context,
							MsgDetailActivity.class, FunUtils.uid,
							FunUtils.head, FunUtils.name);
					FunUtils.ifNotify = false;
				}
			}, 100);

		}

	}

	private MsgNotifyReceive msgReceive;

	/**
	 * ����һ������֪ͨ���ڲ��࣬���ڸ�����ͼ
	 * 
	 * @author MM_Zerui
	 * @tip_1 ������Ϣ����ʱ�������ü�����
	 * @tip_2 ���������Activity����������zhuc
	 * @tip_3 ��������Activity����ʱ��ȡ��ע�᲻�ټ���
	 */
	class MsgNotifyReceive extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			try {
				if (intent.getAction().equals(Enum_Page.TAPBAR.name())) {
					updateBadegView();
					// if (intent.getStringExtra("DATA").equals("MSG")) {
					// // ��ڨ��Ϣͬ������
					// AnimationUtil.startService(context,
					// SyncMsgService.class);
					// }
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

	}

	/**
	 * ������Ϣ��ʾ��С���
	 */
	private void updateBadegView() {
		// int unRead=msgDaoImpl.getAllUnReadNum();

		try {
			int unRead;
			unRead = Dao_IMsgSeq.getTotalMsgUnRead();
			if (unRead > 0 && unRead < 10) {
				badgeView.setText(String.valueOf(unRead));
				badgeView.show(false);
				System.out.println("...1");
			} else if (unRead >= 10) {
				badgeView.setText("N");
				badgeView.show(false);
				System.out.println("...2");
			} else {
				badgeView.hide(false);
				System.out.println("...3");
			}
		} catch (DbException e) {
			// TODO Auto-generated catch block
			System.out.println("����С���ʱ���ִ���");
			e.printStackTrace();
		}

	}
}
