package com.hi.module.self.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.ruifeng.hi.R;
import com.customview.callBack.topBarCallBack;
import com.customview.view.CustomTopbarView;
import com.exception.utils.P;
import com.hi.common.API;
import com.hi.common.EXCEPTION_CODE;
import com.hi.common.PARAMS;
import com.hi.common.db.E_DB_SelfIfo;
import com.hi.common.http.E_Http_InfoType;
import com.hi.dao.supImpl.Dao_Params;
import com.hi.dao.supImpl.Dao_SelfIfo;
import com.hi.http.base.Call_httpData;
import com.hi.http.member.model.Req_UpdateIfo;
import com.hi.http.member.req.Http_UpdateIfo;
import com.hi.module.base.application.ThreadPool;
import com.hi.module.base.callBack.httpResultCallBack;
import com.hi.module.base.superClass.RequestActivity;
import com.hi.module.self.model.ReqAlterIfoBean;
import com.hi.service.HttpResultService;
import com.hi.utils.AnimationUtil;
import com.hi.utils.DBUtils;
import com.hi.utils.DeviceUtils;
import com.hi.utils.FormatUtils;
import com.hi.utils.HttpUtils;
import com.hi.utils.ViewHandleUtils;
import com.hi.view.customWidget.StrericWheelAdapter;
import com.hi.view.customWidget.WheelView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;

public class SelfBirthdayDialog extends RequestActivity {
	@ViewInject(R.id.topBar)
	private CustomTopbarView topBar;
	@ViewInject(R.id.wheel_1)
	private WheelView yearView;
	@ViewInject(R.id.wheel_2)
	private WheelView monthView;
	@ViewInject(R.id.wheel_3)
	private WheelView dayView;

	private static int screenHeight;
	private String dateValue;
	private Long longValue;
	String values[];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.self_birthday_set_layout);
		ViewUtils.inject(this);
		super.onCreate(savedInstanceState);

	}

	@Override
	protected void obtainIntentValue() {
		// TODO Auto-generated method stub
		try {
			String timeValue = getIntent().getStringExtra("DATA0");
			String formatValue = FormatUtils.getDateTime_BIRTHDAY(timeValue);
			values = new String[3];
			values = formatValue.split("-");
			for (int i = 0; i < values.length; i++) {
				if (values[i] == null) {
					values[i] = "0";
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			P.v("生日信息有误");
			values[0] = "1993";
			values[1] = "11";
			values[2] = "24";
		}
	}

	@Override
	protected void setOnClickListener() {
		// TODO Auto-generated method stub
		topBar.setCallBack(new topBarCallBack() {
			@Override
			public void call_rightTextBtnListener() {
				// TODO Auto-generated method stub
				super.call_rightTextBtnListener();
				dataSet();
			}

			@Override
			public void call_leftTextBtnListener() {
				// TODO Auto-generated method stub
				super.call_leftTextBtnListener();
				outFinish();
			}
		});
	}

	@Override
	protected void initResource() {
		// TODO Auto-generated method stub
		screenHeight = getWindow().getWindowManager().getDefaultDisplay()
				.getHeight();// 获取屏幕高度

		WindowManager.LayoutParams lp = getWindow().getAttributes();// //lp包含了布局的很多信息，通过lp来设置对话框的布局
		lp.width = LayoutParams.FILL_PARENT;
		lp.gravity = Gravity.BOTTOM;
		lp.height = DeviceUtils.dip2px(context, 40)
				+ (WheelView.ADDITIONAL_ITEM_HEIGHT + 40)
				* WheelView.DEF_VISIBLE_ITEMS;
		getWindow().setAttributes(lp);// 将设置好属性的lp应用到对话框

		yearView.setAdapter(new StrericWheelAdapter(FormatUtils.getYearArrays()));
		yearView.setCurrentItem(FormatUtils.getYearItem(Integer
				.valueOf(values[0])));
		yearView.setCyclic(true);
		yearView.setInterpolator(new AnticipateOvershootInterpolator());

		monthView.setAdapter(new StrericWheelAdapter(FormatUtils
				.getMonthArrays()));
		monthView.setCurrentItem(FormatUtils.getMonthItem(Integer
				.valueOf(values[1])));
		monthView.setCyclic(true);
		monthView.setInterpolator(new AnticipateOvershootInterpolator());

		dayView.setAdapter(new StrericWheelAdapter(FormatUtils.getDayArrays()));
		dayView.setCurrentItem(FormatUtils.getDayItem(Integer
				.valueOf(values[2])));
		dayView.setCyclic(true);
		dayView.setInterpolator(new AnticipateOvershootInterpolator());
	}

	private void dataSet() {
		// TODO Auto-generated method stub
		String year = yearView.getCurrentItemValue();
		String month = monthView.getCurrentItemValue();
		String day = dayView.getCurrentItemValue();
		String yearValue = year.substring(0, year.length() - 1);
		String monthValue = month.substring(0, month.length() - 1);
		String dayValue = day.substring(0, day.length() - 1);
		dateValue = yearValue + "-" + monthValue + "-" + dayValue;

		Req_UpdateIfo reqBean = new Req_UpdateIfo();
		reqBean.setUid(Dao_SelfIfo.getInstance().getMid());
		reqBean.setT(E_Http_InfoType.BIRTHDAY.toString());
		reqBean.setC(FormatUtils.getDateValueBirthday(dateValue));
		new Http_UpdateIfo(new Call_httpData<Class>() {

			@Override
			public void onStart() {
				// TODO Auto-generated method stub
				httpRun();
			}

			@Override
			public void onSuccess(Class datas) {
				// TODO Auto-generated method stub
				Dao_SelfIfo.setParams(E_DB_SelfIfo.birthDay,
						FormatUtils.getDateValueBirthday(dateValue));
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
			}
		}).onParams(reqBean).onAction();
	}

	public void httpRun() {
		topBar.setProVisibility(true);
	}

	public void httpEnd() {
		topBar.setProVisibility(false).setRighTextVisibility(true);
	}

	@Override
	protected void outFinish() {
		// TODO Auto-generated method stub
		finish();
	
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
		AnimationUtil.finishOut2Bottom(context);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		outFinish();
	}

	@Override
	protected void requestMethod() {
		// TODO Auto-generated method stub

	}
}
