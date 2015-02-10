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
 * �Ż݄�����ҳ
 * @author MM_Zerui
 * @note ��ʶ����ҳ��ı�־  SOURCE_ACTIVITY
 * @tip_1 �ӵ���Ż݄��б����Ľ�������ʾ��ҵİ�ť
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
		
		
		//�����жϸ��Ż݄��Ƿ��Ѿ�ʹ�ã�����Ѿ�ʹ�ã�����ʣ����Чʱ��
		//���δʹ�ã���ʾ�����ȡ
		tBean=Dao_Coupons.getCoupon(cIdStr);
		
		if(DataValidate.checkDataValid(tBean)){
			name.setText(tBean.getName());
			itemPhone.setTextContent(tBean.getPhone());
			itemLocate.setTextContent(tBean.getAddress());
			privilegeDetail.setText(tBean.getContent());
			imageLoader.displayImage(tBean.getLogo(), logo, loadOptions);
			if(tBean.getIsUse()==E_DB_ConpousUse.USE.value()){
				noteBtn.setVisibility(View.VISIBLE);
				noteBtn.setText("���Ż݄��ѹ���");
				noteBtn.setBtnStyle(CustomBtnView.BTN_RED);
				noteBtn.setEnabled(false);
			}else {
				//���Ż݄�δʹ��
				if(tBean.getIsUsing()==E_DB_ConpousUsing.USING.value()){
					if(tBean.getRemainTime()<=0){
						//�޸�ʹ�ñ�ʶ
						Dao_Coupons.updateCouponsUse(tBean.getCid(),true);
						//�޸��Ƿ�����ʹ�ñ�ʶ
						Dao_Coupons.updateCouponsUsing(tBean.getCid(), false);
						noteBtn.setVisibility(View.VISIBLE);
						noteBtn.setText("���Ż݄��ѹ���");
						noteBtn.setBtnStyle(CustomBtnView.BTN_RED);
						noteBtn.setEnabled(false);
					}else {
						noteBtn.setVisibility(View.GONE);
						timerBtn.setEndTime(tBean.getEndTime(), Enum_TimerType.TIMER_ALL,"�󽫹���");
					}
				}else {
					noteBtn.setVisibility(View.VISIBLE);
					noteBtn.setText("�����ȡ - ��ʼ��ʱ");
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
				//�޸�ʹ�ñ�ʶ
				Dao_Coupons.updateCouponsUse(tBean.getCid(),true);
				//�޸��Ƿ�����ʹ�ñ�ʶ
				Dao_Coupons.updateCouponsUsing(tBean.getCid(), false);
				noteBtn.setVisibility(View.VISIBLE);
				noteBtn.setText("���Ż݄��ѹ���");
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
//				//�����Ƿ���ʹ��ʱ����
//				timerBtn.setEndTime(tBean.getEndTime(), Enum_TimerType.TIMER_ALL,"�󽫹���");
//				timerBtn.reqeat();
//			
//				//�޸�ʹ��ʱ��
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
				//�����Ƿ���ʹ��ʱ����
				timerBtn.setEndTime(tBean.getEndTime(), Enum_TimerType.TIMER_ALL,"�󽫹���");
				timerBtn.reqeat();
				//�޸��Ƿ�����ʹ�ñ�ʶ
				Dao_Coupons.updateCouponsUsing(tBean.getCid(), true);
				//�޸�ʹ��ʱ��
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
