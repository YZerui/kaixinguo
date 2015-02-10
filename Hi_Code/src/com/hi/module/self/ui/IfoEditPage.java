package com.hi.module.self.ui;

import android.os.Bundle;
import android.widget.EditText;
import com.android.ruifeng.hi.R;
import com.customview.callBack.topBarCallBack;
import com.customview.view.CustomTopbarView;
import com.format.utils.DataValidate;
import com.hi.common.PARAMS;
import com.hi.common.db.E_DB_SelfIfo;
import com.hi.common.http.E_Http_Gender;
import com.hi.common.http.E_Http_InfoType;
import com.hi.common.http.E_Http_State;
import com.hi.common.writing.W_UserInfo;
import com.hi.dao.supImpl.Dao_SelfIfo;
import com.hi.http.base.Call_httpData;
import com.hi.http.member.model.Req_ChangeState;
import com.hi.http.member.model.Req_UpdateIfo;
import com.hi.http.member.req.Http_ChangeState;
import com.hi.http.member.req.Http_UpdateIfo;
import com.hi.module.base.superClass.RequestActivity;
import com.hi.utils.AnimationUtil;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 个人信息编辑页面
 * @author MM_Zerui 
 */
public class IfoEditPage extends RequestActivity {

	@ViewInject(R.id.topBar)
	private CustomTopbarView topBar;
	@ViewInject(R.id.edit)
	private EditText edit;

	private int unit;
	private String content;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.self_ifo_item_set_layout);
		ViewUtils.inject(this);
		super.onCreate(savedInstanceState);
	}
	@Override
	protected void obtainIntentValue() {
		// TODO Auto-generated method stub
		unit = Integer.valueOf(getIntent().getStringExtra("DATA0"));
	}
	private void InfoSubimtMethod() {
		content=edit.getText().toString();
		if (!DataValidate.checkDataValid(content)) {
			toast.setText("请编辑信息后再修改");
			return;
		}
		E_Http_InfoType enumType=E_Http_InfoType.get(unit);
		switch (enumType) {
		// 提交现场状态
		case WIFISTATE:
			StateRequestMethod(content, String.valueOf(PARAMS.CURRENT_STATE));
			break;
		// 提交通用状态
		case STATE:
			StateRequestMethod(content, String.valueOf(PARAMS.NORMAL_STATE));
			break;
		default:
			selfIfoSubmitMethod(content);
			break;
		}

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

			@Override
			public void call_rightTextBtnListener() {
				// TODO Auto-generated method stub
				super.call_rightTextBtnListener();
				InfoSubimtMethod();
			}
		});
	}

	@Override
	protected void initResource() {
		E_Http_InfoType enumType=E_Http_InfoType.get(unit);
		switch (enumType) {
		case NICKNAME:
			topBar.setTitle("昵称");
			edit.setText(Dao_SelfIfo.getInstance().getNickName());
			break;
		case GENDER:
			topBar.setTitle("性别");
			edit.setText(W_UserInfo.getGender(Dao_SelfIfo.getInstance().getSex()));
			break;
		case BIRTHDAY:
			topBar.setTitle("生日");
			break;
		case INTEREST:
			topBar.setTitle("爱好");
			edit.setText(Dao_SelfIfo.getInstance().getInterest());
			break;
		case LABEL:
			topBar.setTitle("标签");
			edit.setText(Dao_SelfIfo.getInstance().getNote());
			break;
		case OCCUPATION:
			topBar.setTitle("职业");
			edit.setText(Dao_SelfIfo.getInstance().getOccupation());
			break;
		// 通用状态
		case STATE:
			topBar.setTitle("我的状态");
			edit.setText(Dao_SelfIfo.getInstance().getCurrentState());
			return;
			// 现场状态
		case WIFISTATE:
			topBar.setTitle("现场状态");

			return;
		default:
			break;
		}
	}


	@Override
	protected void requestMethod() {
		// TODO Auto-generated method stub

	}

	/**
	 * 现场状态修改的请求
	 * @param content
	 * @param type
	 */
	private void StateRequestMethod(final String content, final String type) {

		Req_ChangeState reqBean = new Req_ChangeState();
		reqBean.setC(content);
		reqBean.setUid(Dao_SelfIfo.getInstance().getMid());
		reqBean.setT(type);
		new Http_ChangeState(new Call_httpData<Class<?>>() {
			
			@Override
			public void onSuccess(Class<?> datas) {
				// TODO Auto-generated method stub
				if(Integer.valueOf(type)==E_Http_State.NOTMAL.value()){
					Dao_SelfIfo.setParams(E_DB_SelfIfo.currentState,content);
				}
				finish();
			}
			
			@Override
			public void onStart() {
				// TODO Auto-generated method stub
				httpRun();
			}
			
			@Override
			public void onFinally() {
				// TODO Auto-generated method stub
				httpEnd();
			}
			
			@Override
			public void onFail() {
				// TODO Auto-generated method stub
				toast.setText("更改失败");
				httpEnd();
			}
		}).onParams(reqBean).onAction();
	}
	
	/**
	 * 个人信息更改
	 * @param content
	 */
	public void selfIfoSubmitMethod(final String content){
		Req_UpdateIfo reqBean=new Req_UpdateIfo();
		reqBean.setT(String.valueOf(unit));
		reqBean.setC(content);
		reqBean.setUid(Dao_SelfIfo.getInstance().getMid());
		new Http_UpdateIfo(new Call_httpData<Class>(){

			@Override
			public void onStart() {
				// TODO Auto-generated method stub
				httpRun();
			}

			@Override
			public void onSuccess(Class datas) {
				// TODO Auto-generated method stub
				switch (unit) {
				case PARAMS.ALTER_NAME:
					Dao_SelfIfo.setParams(E_DB_SelfIfo.nickName,content);
					break;
				case PARAMS.ALTER_GENDER:
					Dao_SelfIfo.setParams(E_DB_SelfIfo.sex,content.equals("男") ? E_Http_Gender.MAN.toString() :
						E_Http_Gender.WOMEN.toString());
					break;
				case 3:

					break;
				case PARAMS.ALTER_INTEREST:
					Dao_SelfIfo.setParams(E_DB_SelfIfo.interest,content);
					break;
				case PARAMS.ALTER_LABEL:
					Dao_SelfIfo.setParams(E_DB_SelfIfo.note,content);
					break;
				case PARAMS.ALTER_WORK:
					Dao_SelfIfo.setParams(E_DB_SelfIfo.occupation,content);
					break;
				default:
					break;
				}
				outFinish();
			}

			@Override
			public void onFail() {
				// TODO Auto-generated method stub
				toast.setText("更改失败");
			}

			@Override
			public void onFinally() {
				// TODO Auto-generated method stub
				httpEnd();
			}}).onParams(reqBean).onAction();
	}

	public void httpRun(){
		topBar.setTitle("提交...");
		topBar.setProVisibility(true);
	}
	public void httpEnd(){
		topBar.setTitle("请再次提交");
		topBar.setProVisibility(false).setRighTextVisibility(true);
	}
		
	@Override
	protected void outFinish() {
		// TODO Auto-generated method stub
		finish();
		AnimationUtil.finishOut2Bottom(context);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		outFinish();
	}
}
