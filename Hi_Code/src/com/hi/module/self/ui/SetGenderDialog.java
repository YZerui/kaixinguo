package com.hi.module.self.ui;



import com.android.ruifeng.hi.R;
import com.exception.utils.P;
import com.hi.common.db.E_DB_SelfIfo;
import com.hi.common.http.E_Http_Gender;
import com.hi.common.http.E_Http_InfoType;
import com.hi.dao.supImpl.Dao_SelfIfo;
import com.hi.http.base.Call_httpData;
import com.hi.http.member.model.Req_UpdateIfo;
import com.hi.http.member.req.Http_UpdateIfo;
import com.hi.module.base.superClass.RequestActivity;
import com.hi.utils.DeviceUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * �Ա��趨��ҳ��
 * @author MM_Zerui 
 */
public class SetGenderDialog extends RequestActivity implements OnClickListener {
	private static int screenHeight;
	@ViewInject(R.id.manSelectBtn)
	private Button manSelectBtn;
	@ViewInject(R.id.womanSelectBtn)
	private Button womenSelectBtn;
	@ViewInject(R.id.overallLayout)
	private LinearLayout overallLayout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.gender_choose_dialog);
		ViewUtils.inject(this);
		super.onCreate(savedInstanceState);
	
	}
	@Override
	protected void obtainIntentValue() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setOnClickListener() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initResource() {
		screenHeight = getWindow().getWindowManager().getDefaultDisplay()
				.getHeight();// ��ȡ��Ļ�߶�

		WindowManager.LayoutParams lp = getWindow().getAttributes();// //lp�����˲��ֵĺܶ���Ϣ��ͨ��lp�����öԻ���Ĳ���
		lp.width = LayoutParams.FILL_PARENT;
		lp.gravity = Gravity.BOTTOM;
		lp.height =DeviceUtils.dip2px(context, 45)*2;
		P.v("�߶�Ϊ:"+overallLayout.getHeight());
		getWindow().setAttributes(lp);// �����ú����Ե�lpӦ�õ��Ի���
	}

	@Override
	protected void requestMethod() {
		
	}
	@OnClick(R.id.manSelectBtn)
	public void manSetClick(View v){
		httpRequest(E_Http_Gender.MAN.toString());
	}
	@OnClick(R.id.womanSelectBtn)
	public void womenSetClick(View v){
		httpRequest(E_Http_Gender.WOMEN.toString());
	}
	public void httpRequest(final String gender){
		Req_UpdateIfo reqBean = new Req_UpdateIfo();
		reqBean.setUid(Dao_SelfIfo.getInstance().getMid());
		reqBean.setT(E_Http_InfoType.GENDER.toString());
		reqBean.setC(gender);
		new Http_UpdateIfo(new Call_httpData<Class>() {
			
			@Override
			public void onSuccess(Class datas) {
				// TODO Auto-generated method stub
				Dao_SelfIfo.setParams(E_DB_SelfIfo.sex,gender);
			}
			
			@Override
			public void onStart() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onFinally() {
				// TODO Auto-generated method stub
				outFinish();
			}
			
			@Override
			public void onFail() {
				// TODO Auto-generated method stub
				toast.setText("����ʧ��");
				
			}
		}).onParams(reqBean).onAction();
		
	}
	@Override
	protected void outFinish() {
		// TODO Auto-generated method stub
		finish();
		overridePendingTransition(0, R.anim.dialog_exit);
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}

}
