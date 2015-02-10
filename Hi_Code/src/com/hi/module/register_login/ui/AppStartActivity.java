package com.hi.module.register_login.ui;

import com.android.ruifeng.hi.R;
import com.hi.common.PARAMS;
import com.hi.dao.supImpl.Dao_SelfIfo;
import com.hi.module.base.application.AppManager;
import com.hi.module.base.application.TabBarActivity;
import com.hi.utils.AnimationUtil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

/**
 * @author MM_Zerui
 */
public class AppStartActivity extends Activity {
	/************************ 初始首页 ***************************/

	private Context context;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
		AppManager.getAppManager().addActivity(this);
		final View view = View.inflate(this, R.layout.app_action_first_page,
				null);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(view);

		AlphaAnimation aa = new AlphaAnimation(0.1f, 1.0f);
		aa.setDuration(PARAMS.APP_START_PAGE_LENGTH);
		view.startAnimation(aa);

		aa.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationEnd(Animation arg0) {
				checkLogin();
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationStart(Animation animation) {
			}
		});
	}

	/**
	 * 如果个人信息库不存在信息，则跳转到登录页面；如果存在，进入到主页
	 */
	private void checkLogin() {
		if (Dao_SelfIfo.checkExist()) {
			Intent intent = new Intent(this, TabBarActivity.class);
			startActivity(intent);
			// AnimationUtil.nor_toIntent(context, TabBarActivity.class);
			return;
		}
		Intent intent = new Intent(this, SSOLoginActivity.class);
		startActivity(intent);
		overridePendingTransition(R.anim.in_bottom_to_top_page, 0);
	}
}
