package com.hi.module.self.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.android.ruifeng.hi.R;
import com.constant.COMMON;
import com.customview.callBack.topBarCallBack;
import com.customview.view.CustomItemView;
import com.customview.view.CustomTopbarView;
import com.hi.common.db.E_DB_SelfIfo;
import com.hi.common.http.E_Http_InfoType;
import com.hi.common.writing.W_UserInfo;
import com.hi.dao.model.T_SelfIfo;
import com.hi.dao.supImpl.Dao_SelfIfo;
import com.hi.http.base.Call_httpData;
import com.hi.http.member.model.Req_LoginOut;
import com.hi.http.member.req.Http_LoginOut;
import com.hi.module.base.superClass.RequestActivity;
import com.hi.module.global.AppNotePage;
import com.hi.module.register_login.ui.SSOLoginActivity;
import com.hi.service.IMMessageService;
import com.hi.utils.AnimationUtil;
import com.hi.utils.FormatUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

/**
 * 个人信息编辑项
 * @author MM_Zerui
 *
 */
public class IfoSetListPage extends RequestActivity {
	@ViewInject(R.id.topBar)
	private CustomTopbarView topBar;
	@ViewInject(R.id.nickNameItem)
	private CustomItemView nameItem;
	@ViewInject(R.id.genderItem)
	private CustomItemView genderItem;
	@ViewInject(R.id.birthdayItem)
	private CustomItemView birthdayItem;
	@ViewInject(R.id.stateItem)
	private CustomItemView stateItem;
	@ViewInject(R.id.occupationItem)
	private CustomItemView occuItem;
	@ViewInject(R.id.labelItem)
	private CustomItemView labelItem;
	@ViewInject(R.id.quitItem)
	private CustomItemView quitItem;
	@ViewInject(R.id.img_layout)
	private RelativeLayout imgLayout;
	@ViewInject(R.id.moreItem)
	private CustomItemView moreItem;
	
	@ViewInject(R.id.selfSetImg)
	private ImageView selfSetImg;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.self_ifo_set_layout);
		ViewUtils.inject(this);
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void obtainIntentValue() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initResource() {
		// TODO Auto-generated method stub
		loadOptions= new DisplayImageOptions.Builder()			
		.showImageForEmptyUri(R.drawable.default_img_head)	
		.showImageOnFail(R.drawable.default_img_head)
		.showImageForEmptyUri(R.drawable.default_img_head)
		.showStubImage(R.drawable.default_img_head)
//		.showStubImage(R.drawable.app_icon)
		.cacheInMemory(true)						
		.cacheOnDisc(true)						
		.build();	
		//设定退出登录的文字颜色
		quitItem.setTextModeColor(COMMON.COLOR_THEME);
	}

	private void selfIfoInit() {
		T_SelfIfo infoBean=Dao_SelfIfo.getInstance();
		W_UserInfo.Default(E_DB_SelfIfo.nickName, nameItem.getContent(), infoBean.getNickName());
		W_UserInfo.Default(E_DB_SelfIfo.occupation,occuItem.getContent(), infoBean.getOccupation());
		W_UserInfo.Default(E_DB_SelfIfo.note,labelItem.getContent(), infoBean.getNote());
		W_UserInfo.Default(E_DB_SelfIfo.currentState,stateItem.getContent(), infoBean.getCurrentState());
		W_UserInfo.Default(E_DB_SelfIfo.birthDay,birthdayItem.getContent(), FormatUtils.getDateTime_BIRTHDAY(infoBean.getBirthDay()));
		W_UserInfo.Default(E_DB_SelfIfo.sex, genderItem.getContent(), W_UserInfo.getGender(infoBean.getSex()));
		imageLoader.displayImage(Dao_SelfIfo.getInstance().getHead(), selfSetImg, loadOptions);
	}

	@Override
	protected void setOnClickListener() {
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
	protected void requestMethod() {
		// TODO Auto-generated method stub

	}
	@OnClick(R.id.quitItem)
	public void quitItem(View v) {
//		ReqQuitBean quitBean = new ReqQuitBean();
//		quitBean.setUid(DBUtils.getUid());
//		http.send(HttpMethod.POST, API.QUIT_LOGIN,
//				HttpUtils.convertBeanToParams(quitBean), null);
		
		Req_LoginOut reqBean=new Req_LoginOut();
		reqBean.setUid(Dao_SelfIfo.getInstance().getMid());
		new Http_LoginOut(new Call_httpData<Class>(){

			@Override
			public void onStart() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Class datas) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onFail() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onFinally() {
				// TODO Auto-generated method stub
				// 清除个人信息
				Dao_SelfIfo.clear();
				AnimationUtil.nor_toIntent(context, SSOLoginActivity.class);
				//关闭Session服务
				IMMessageService imService=new IMMessageService();
				imService.closeSession();
			}}).onParams(reqBean).onAction();
		
	}

	@OnClick(R.id.nickNameItem)
	public void nameSetClick(View v) {
		AnimationUtil.in2TopIntent(context, IfoEditPage.class,
				E_Http_InfoType.NICKNAME.toString());
	}

	@OnClick(R.id.genderItem)
	public void genderSetClick(View v) {
		AnimationUtil.in2TopIntent(context, SetGenderDialog.class);
	}

	@OnClick(R.id.birthdayItem)
	public void birthdaySetClick(View v) {
		AnimationUtil.in2TopIntent(context, SelfBirthdayDialog.class,
				Dao_SelfIfo.getInstance().getBirthDay());
	}

	@OnClick(R.id.stateItem)
	public void stateSetClick(View v) {
		AnimationUtil.in2TopIntent(context, IfoEditPage.class,
				E_Http_InfoType.STATE.toString());
	}

	@OnClick(R.id.occupationItem)
	public void occuSetClick(View v) {
		AnimationUtil.in2TopIntent(context, IfoEditPage.class,
				E_Http_InfoType.OCCUPATION.toString());
	}

	@OnClick(R.id.labelItem)
	public void labelSetClick(View v) {
		AnimationUtil.in2TopIntent(context, IfoEditPage.class,
				E_Http_InfoType.LABEL.toString());
	}

	@OnClick(R.id.img_layout)
	public void imgSetClick(View v) {
		AnimationUtil.in2LeftIntent(context, ImgWallSettingActivity.class,
				"SELF_SET_PAGE");
	}
	@OnClick(R.id.moreItem)
	public void moreItemClick(View v){
		AnimationUtil.in2LeftIntent(context, AppNotePage.class);
	}
	@Override
	protected void outFinish() {
		// TODO Auto-generated method stub
		finish();
		AnimationUtil.finishOut2Right(context);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		outFinish();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		selfIfoInit();

	}

}
