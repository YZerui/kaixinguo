package com.hi.module.store.ui;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.android.ruifeng.hi.R;
import com.customview.callBack.topBarCallBack;
import com.customview.view.CustomBtnView;
import com.customview.view.CustomItemView;
import com.customview.view.CustomTopbarView;
import com.customview.view.ImageView_Rounded;
import com.format.utils.DataValidate;
import com.hi.common.db.E_DB_ConpousUse;
import com.hi.common.param.Enum_Color;
import com.hi.common.param.Enum_TimerType;
import com.hi.dao.model.T_Coupons;
import com.hi.dao.supImpl.Dao_Coupons;
import com.hi.module.base.superClass.NormalActivity;
import com.hi.utils.AnimationUtil;
import com.hi.utils.FormatUtils;
import com.hi.view.customWidget.CustomDigitalClock;
import com.hi.view.customWidget.CustomDigitalClock.ClockListener;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 优惠劵详情页
 * 
 * @author MM_Zerui
 * @note 标识进入页面的标志 SOURCE_ACTIVITY
 * @tip_1 从店家优惠劵列表进入的将隐藏显示店家的按钮
 * 
 * 
 */
public class StorePrivilegeDetailPage extends NormalActivity {

	// private String
	@ViewInject(R.id.topBar)
	private CustomTopbarView topBar;
	@ViewInject(R.id.timerBtn)
	private CustomDigitalClock timerBtn;
	@ViewInject(R.id.noteBtn)
	private CustomBtnView noteBtn;
	@ViewInject(R.id.image)
	private ImageView_Rounded logo;
	@ViewInject(R.id.name)
	private TextView name;
	@ViewInject(R.id.item_phone)
	private CustomItemView itemPhone;
	@ViewInject(R.id.item_locate)
	private CustomItemView itemLocate;
	@ViewInject(R.id.privilegeDetail)
	private TextView privilegeDetail;
	@ViewInject(R.id.privilegeNote)
	private TextView privilegeNote;

	// private String cIdStr;
	private T_Coupons tBean = new T_Coupons();

	// private String nameStr;
	// private String contentStr;
	// private String phoneStr;
	// private String addressStr;
	// private String logoStr;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		setContentView(R.layout.shop_privilege_detail_page);
		ViewUtils.inject(this);
		super.onCreate(savedInstanceState);

	}

	@Override
	public void obtainIntentValue() {
		tBean = (T_Coupons) getIntent().getSerializableExtra("T_Coupons");
	}

	@Override
	public void initResource() {
		if (!DataValidate.checkDataValid(tBean)) {
			noteBtn.setBtnStyle(CustomBtnView.BTN_RED);
			noteBtn.setEnabled(false);
			noteBtn.setText("该优惠劵无效");
			return;
		}
		name.setText(tBean.getName());
		itemPhone.setTextContent(tBean.getPhone());
		itemLocate.setTextContent(tBean.getAddress());
		privilegeDetail.setText(tBean.getContent());
		imageLoader.displayImage(tBean.getLogo(), logo, loadOptions);
		// 首先判断该优惠劵是否已经存在本地种，如果存在，显示已拥有
		// 如果不存在，显示添加
		if (Dao_Coupons.checkCouponsExist(tBean.getCid())) {
			noteBtn.setBtnStyle(CustomBtnView.BTN_RED);
			noteBtn.setText("已收藏该优惠劵");
			noteBtn.setEnabled(false);
		} else {
			noteBtn.setBtnStyle(CustomBtnView.BTN_GREEN);
			noteBtn.setText("点击收藏该优惠劵");
		}
	}

	@Override
	public void onClickListener() {
		// TODO Auto-generated method stub
		topBar.setCallBack(new topBarCallBack() {
			@Override
			public void call_backBtnListener() {
				// TODO Auto-generated method stub
				super.call_backBtnListener();
				finish();
			}
		});
		noteBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 添加该优惠劵信息到本地
				Dao_Coupons.addCoupons(tBean);
				noteBtn.setBtnStyle(CustomBtnView.BTN_RED);
				noteBtn.setText("已收藏该优惠劵");
				noteBtn.setEnabled(false);
			
			}
		});
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
		AnimationUtil.finishOut2Right(context);
	}

	@Override
	public void outFinish() {
		// TODO Auto-generated method stub
		finish();
		AnimationUtil.finishOut2Right(context);
	}

}
