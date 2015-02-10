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
import com.hi.common.db.E_DB_ConpousUsing;
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
 * @author MM_Zerui
 * @note 标识进入页面的标志  SOURCE_ACTIVITY
 * @tip_1 从店家优惠劵列表进入的将隐藏显示店家的按钮
 * 
 *
 */
public class MyPrivilegeDetailPage extends NormalActivity{

//	private String 
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
	
	private String cIdStr;
	private T_Coupons tBean=new T_Coupons();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		setContentView(R.layout.shop_privilege_detail_page);
		ViewUtils.inject(this);
		super.onCreate(savedInstanceState);
		
	}
	@Override
	public void obtainIntentValue() {
		// TODO Auto-generated method stub
		cIdStr=getIntent().getStringExtra("DATA0");
		
	}

	@Override
	public void initResource() {
		
		
		//首先判断该优惠劵是否已经使用，如果已经使用，计算剩余有效时间
		//如果未使用，显示点击获取
		tBean=Dao_Coupons.getCoupon(cIdStr);
		
		if(DataValidate.checkDataValid(tBean)){
			name.setText(tBean.getName());
			itemPhone.setTextContent(tBean.getPhone());
			itemLocate.setTextContent(tBean.getAddress());
			privilegeDetail.setText(tBean.getContent());
			imageLoader.displayImage(tBean.getLogo(), logo, loadOptions);
			if(tBean.getIsUse()==E_DB_ConpousUse.USE.value()){
				noteBtn.setVisibility(View.VISIBLE);
				noteBtn.setText("该优惠劵已过期");
				noteBtn.setBtnStyle(CustomBtnView.BTN_RED);
				noteBtn.setEnabled(false);
			}else {
				//该优惠劵未使用
				if(tBean.getIsUsing()==E_DB_ConpousUsing.USING.value()){
					if(tBean.getRemainTime()<=0){
						//修改使用标识
						Dao_Coupons.updateCouponsUse(tBean.getCid(),true);
						//修改是否正在使用标识
						Dao_Coupons.updateCouponsUsing(tBean.getCid(), false);
						noteBtn.setVisibility(View.VISIBLE);
						noteBtn.setText("该优惠劵已过期");
						noteBtn.setBtnStyle(CustomBtnView.BTN_RED);
						noteBtn.setEnabled(false);
					}else {
						noteBtn.setVisibility(View.GONE);
						timerBtn.setEndTime(tBean.getEndTime(), Enum_TimerType.TIMER_ALL,"后将过期");
					}
				}else {
					noteBtn.setVisibility(View.VISIBLE);
					noteBtn.setText("点击获取 - 开始计时");
				}
				
			}
		}
	}

	@Override
	public void onClickListener() {
		topBar.setCallBack(new topBarCallBack() {
			@Override
			public void call_backBtnListener() {
				super.call_backBtnListener();
				outFinish();
			}
		});
		timerBtn.setClockListener(new ClockListener() {
			
			@Override
			public void timeEnd() {
				//修改使用标识
				Dao_Coupons.updateCouponsUse(tBean.getCid(),true);
				//修改是否正在使用标识
				Dao_Coupons.updateCouponsUsing(tBean.getCid(), false);
				noteBtn.setVisibility(View.VISIBLE);
				noteBtn.setText("该优惠劵已过期");
				noteBtn.setBtnStyle(CustomBtnView.BTN_RED);
				noteBtn.setEnabled(false);
//				timerBtn.setBackgroundColor(Enum_Color.RED.value());
			}
			
			@Override
			public void remainFiveMinutes() {
				// TODO Auto-generated method stub
				
			}
		});
//		timerBtn.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				tBean.setUseTime(FormatUtils.getCurrentDateValue());
//				timerBtn.setEnabled(false);
//				//计算是否在使用时间内
//				timerBtn.setEndTime(tBean.getEndTime(), Enum_TimerType.TIMER_ALL,"后将过期");
//				timerBtn.reqeat();
//			
//				//修改使用时间
//				Dao_Coupons.updateCouponsUseTime(FormatUtils.getCurrentDateValue(), tBean.getCid());
//			}
//		});
		noteBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				noteBtn.setVisibility(View.GONE);
				tBean.setUseTime(FormatUtils.getCurrentDateValue());
				timerBtn.setEnabled(false);
				//计算是否在使用时间内
				timerBtn.setEndTime(tBean.getEndTime(), Enum_TimerType.TIMER_ALL,"后将过期");
				timerBtn.reqeat();
				//修改是否正在使用标识
				Dao_Coupons.updateCouponsUsing(tBean.getCid(), true);
				//修改使用时间
				Dao_Coupons.updateCouponsUseTime(FormatUtils.getCurrentDateValue(), tBean.getCid());
			}
		});
	}

	@Override
	public void outFinish() {
		// TODO Auto-generated method stub
		finish();
		AnimationUtil.finishOut2Right(context);
	}

}
