package com.hi.module.base.application;

import java.util.List;

import com.hi.module.register_login.ui.AppStartActivity;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;

/**
 * �ؼ����룬��������ҳ��
 * @author Administrator
 *
 */
public class FirstActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Intent intent=new Intent(this, AppStartActivity.class);
		//�������ԣ��½�����ջ
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
		finish();
	}

}
