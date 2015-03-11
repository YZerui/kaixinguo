package com.hi.module.locale.ui.local;

import com.android.ruifeng.hi.R;
import com.customview.callBack.topBarCallBack;
import com.customview.view.CustomItemView;
import com.customview.view.CustomTopbarView;
import com.hi.common.API;
import com.hi.common.PARAMS;
import com.hi.common.http.E_Http_Gender;

import com.hi.dao.supImpl.Dao_SelfIfo;
import com.hi.module.base.application.TabBarActivity;
import com.hi.module.base.callBack.httpResultCallBack;
import com.hi.module.base.superClass.RequestActivity;
import com.hi.module.locale.model.LocalAuthorBean;
import com.hi.module.self.ui.IfoEditPage;
import com.hi.service.HttpResultService;
import com.hi.utils.AnimationUtil;
import com.hi.utils.DBUtils;
import com.hi.utils.DeviceUtils;
import com.hi.utils.FormatUtils;
import com.hi.utils.network.HttpUtils;
import com.hi.view.RoundedImageView;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.widget.SlipButton.OnChangedListener;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * WIFI环境下设定的Activity
 * 
 * @author MM_Zerui
 */
public class LocalSettingActivity extends RequestActivity {

	@ViewInject(R.id.topBar)
	private CustomTopbarView topBar;
	@ViewInject(R.id.local_set_icon)
	private RoundedImageView selfIcon;
	@ViewInject(R.id.toLocalBtn)
	private ImageView toLocalBtn;
	@ViewInject(R.id.ifTalkAble)
	private CustomItemView ifTalkItem;
	@ViewInject(R.id.ifSiteDisplay)
	private CustomItemView ifSiteItem;
	@ViewInject(R.id.switchLocalState)
	private CustomItemView switchLocalState;
	@ViewInject(R.id.selfGenderIcon)
	private ImageView selfGenderIcon;
	@ViewInject(R.id.nickNameSet)
	private TextView nameTextSet;

	private Boolean ifLocalTalk, ifLocalDisplay;
	private LocalAuthorBean authorBean;

	private String intentParam;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.local_wifi_state_setting);
		com.lidroid.xutils.ViewUtils.inject(this);
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void obtainIntentValue() {
		// 判断是否从现场wifi页面跳转的
		intentParam = getIntent().getStringExtra("DATA0");
	}

	@Override
	protected void setOnClickListener() {
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

	@Override
	protected void initResource() {
		// TODO Auto-generated method stub
		
		loadOptions = new DisplayImageOptions.Builder() 
		.showImageForEmptyUri(R.drawable.default_img_head)
		.showImageOnFail(R.drawable.default_img_head) 
		.cacheInMemory(true) 
		.cacheOnDisc(true).build();
		
		authorBean = new LocalAuthorBean();
		if (intentParam != null && intentParam.equals("DATA0")) {
			topBar.setBackText("wifi现场");
			toLocalBtn.setVisibility(View.INVISIBLE);
			toLocalBtn.setEnabled(false);
		} else {
			topBar.setBackLayoutVisible(false);
		}

		// 初始化现场权限
		ifLocalDisplay = false;
		ifLocalTalk = false;
		// 初始化头像
		imageLoader.displayImage(Dao_SelfIfo.getInstance().getHead(), selfIcon, loadOptions,null);
		// 初始化姓名
		nameTextSet.setText(Dao_SelfIfo.getInstance().getNickName());
		// 显示性别
		if (Dao_SelfIfo.getInstance().getSex().equals(E_Http_Gender.MAN)) {
			selfGenderIcon.setImageResource(R.drawable.userinfo_icon_male);
		} else if (Dao_SelfIfo.getInstance().getSex().equals(E_Http_Gender.WOMEN)) {
			selfGenderIcon.setImageResource(R.drawable.userinfo_icon_female);
		} else {
			selfGenderIcon.setVisibility(View.INVISIBLE);
		}
		// 初始化设定控件
		slipBtn();
		
		switchLocalState.setItemListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AnimationUtil.in2TopIntent(context, IfoEditPage.class,
						String.valueOf(PARAMS.ALTER_CURRENT_WIFI_STATE));
			}
		});
	}

	

	@OnClick(R.id.toLocalBtn)
	private void toLocalClick(View v) {
		AnimationUtil.in2BigIntent(context, TabBarActivity.class);
	}

	private void slipBtn() {
		// TODO Auto-generated method stub
		// 初始化设定
		String accostedEffe = Dao_SelfIfo.getInstance().getAccostedEffe();
		String locationEffe = Dao_SelfIfo.getInstance().getLocationEffe();
		if (accostedEffe != null && accostedEffe.equals("true")) {
			ifLocalTalk = true;
			ifTalkItem.setSlipCheck(true);
		}
		if (locationEffe != null && locationEffe.equals("true")) {
			ifLocalDisplay = true;
			ifSiteItem.setSlipCheck(true);
		}
		ifTalkItem.setSlipListener(new OnChangedListener() {

			@Override
			public void OnChanged(boolean CheckState) {
				// TODO Auto-generated method stub
				ifLocalTalk = CheckState;
				localAutorSetting(ifLocalTalk, ifLocalDisplay);
			}
		});
		ifSiteItem.setSlipListener(new OnChangedListener() {

			@Override
			public void OnChanged(boolean CheckState) {
				// TODO Auto-generated method stub
				ifLocalDisplay = CheckState;
				localAutorSetting(ifLocalTalk, ifLocalDisplay);
			}
		});
	}

	private void localAutorSetting(Boolean talkBool, Boolean displayBool) {
		// TODO Auto-generated method stub
		authorBean.setUid(DBUtils.getUid());
		authorBean.setWifiMac(DeviceUtils.getWifiMac());
		authorBean.setLe(FormatUtils.paseBool2Str(talkBool));
		authorBean.setAe(FormatUtils.paseBool2Str(displayBool));

		http.send(HttpMethod.POST, API.LOCAL_AUTHOR_SWITCH,
				HttpUtils.convertBeanToParams(authorBean),
				new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub
						ifSiteItem.setSlipCheck(Dao_SelfIfo.getInstance().getLocationEffe()
								.equals("true") ? true : false);
						ifTalkItem.setSlipCheck(Dao_SelfIfo.getInstance().getAccostedEffe()
								.equals("true") ? true : false);
					}

					@Override
					public void onSuccess(ResponseInfo<String> params) {
						// TODO Auto-generated method stub
						new HttpResultService(params.result,
								new httpResultCallBack() {

									@Override
									public void onSuccess() {
										// TODO Auto-generated method stub
										Dao_SelfIfo.getInstance()
												.setLocationEffe(ifLocalDisplay ? "true"
														: "false");
										Dao_SelfIfo.getInstance()
												.setAccostedEffe(ifLocalTalk ? "true"
														: "false");
									}

									@Override
									public void onRequestFail() {
										// TODO Auto-generated method stub
										ifSiteItem.setSlipCheck(Dao_SelfIfo.getInstance()
												.getLocationEffe().equals(
														"true") ? true : false);
										ifTalkItem.setSlipCheck(Dao_SelfIfo.getInstance()
												.getAccostedEffe().equals(
														"true") ? true : false);
									}

									@Override
									public void onFinally() {
										// TODO Auto-generated method stub

									}
								}, null,false);
					}
				});
	}

	public void onBackPressed() {
		outFinish();
	};

	@Override
	protected void outFinish() {
		// TODO Auto-generated method stub
		if (intentParam != null && intentParam.equals("DATA0")) {
			finish();
			AnimationUtil.finishOut2Bottom(context);
			return;
		}
		AnimationUtil.in2BigIntent(context, TabBarActivity.class);
		finish();
	}

	@Override
	protected void requestMethod() {
		// TODO Auto-generated method stub
		
	}

}
