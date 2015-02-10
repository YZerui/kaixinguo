package com.hi.module.store.ui;

import java.util.ArrayList;

import com.android.ruifeng.hi.R;
import com.customview.callBack.topBarCallBack;
import com.customview.view.CustomTopbarView;
import com.format.utils.DataValidate;
import com.hi.adapter.GalleryImageAdapter;
import com.hi.common.API;
import com.hi.common.PARAMS;
import com.hi.common.http.E_Http_StoreInfo;
import com.hi.common.http.E_Http_StoreRemark;
import com.hi.common.writing.W_StoreInfo;
import com.hi.dao.supImpl.Dao_Params;
import com.hi.dao.supImpl.Dao_SelfIfo;
import com.hi.http.base.Call_httpData;
import com.hi.http.store.model.Recv_StoreIfo;
import com.hi.http.store.model.Req_StoreIfo;
import com.hi.http.store.model.Req_StoreRemark;
import com.hi.http.store.req.Http_GetStroreIfo;
import com.hi.http.store.req.Http_StoreRemark;
import com.hi.http.store.req.Http_StoreRemark.StoreRemarkType;
import com.hi.module.base.application.ShareUnitDialog;
import com.hi.module.base.superClass.NormalActivity;
import com.hi.module.store.model.RecvStoreDetailBean;
import com.hi.utils.AnimationUtil;
import com.hi.utils.DBUtils;
import com.hi.utils.ParseJson;
import com.hi.utils.ScreenShot;
import com.hi.utils.ViewHandleUtils;
import com.hi.view.MyGallery;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.thread.RunnableService;
import com.thread.callBack.runCallBack;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 商店详情的Activity
 * 
 * @author MM_Zerui
 */
public class ShopDetailActivity extends NormalActivity {

	private GalleryImageAdapter imageAdapter;
	// private ArrayList<String> data;
	private int current = 0;
	private String storeId;
	private String storeComment;

	@ViewInject(R.id.topBar)
	private CustomTopbarView topBar;
	@ViewInject(R.id.gy)
	private MyGallery gallery;
	@ViewInject(R.id.ll)
	private LinearLayout bottom;
	@ViewInject(R.id.store_detail_introduce_layout)
	private RelativeLayout toIntroduceBtn;
	@ViewInject(R.id.store_privilege_layout)
	private RelativeLayout privilegeBtn;
	@ViewInject(R.id.store_share_layout)
	private RelativeLayout shareBtn;
	@ViewInject(R.id.shop_name)
	private TextView storeName;
	@ViewInject(R.id.shop_address)
	private TextView storeAddress;
	@ViewInject(R.id.shop_phone)
	private TextView storePhone;
	@ViewInject(R.id.store_detail_like_checkbox)
	private CheckBox likeCheckBox;
	@ViewInject(R.id.store_detail_dislike_checkbox)
	private CheckBox disLikeCheckBox;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.shop_detail_layout);
		ViewUtils.inject(this);
		super.onCreate(savedInstanceState);
		requestMethod();
		// initValue();
		checkBoxChangeListener();
	}

	@Override
	public void obtainIntentValue() {
		// 获取店家ID
		storeId = getIntent().getStringExtra("DATA0");
		storeComment = getIntent().getStringExtra("DATA1") == null ? "0"
				: getIntent().getStringExtra("DATA1");
	}

	@Override
	public void initResource() {
	}

	private void initStoreValue(Recv_StoreIfo bean) {
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int width = dm.widthPixels;
		String logos[] = bean.getLogos();
		imageAdapter = new GalleryImageAdapter(this, logos, width);
		gallery.setAdapter(imageAdapter);
		for (int i = 0; i < logos.length; i++) {
			ImageView point = new ImageView(this);
			if (i == 0) {
				point.setBackgroundResource(R.drawable.feature_point_cur);
			} else {
				point.setBackgroundResource(R.drawable.feature_point);
			}
			bottom.addView(point);
		}
		gallery.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				View _view = bottom.getChildAt(position);
				View _currentview = bottom.getChildAt(current);
				if (_currentview != null && _view != null) {
					ImageView pointView = (ImageView) _view;
					ImageView curpointView = (ImageView) _currentview;
					curpointView
							.setBackgroundResource(R.drawable.feature_point);
					pointView
							.setBackgroundResource(R.drawable.feature_point_cur);
					current = position;
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		W_StoreInfo.Default(E_Http_StoreInfo.address, storeAddress, bean.getAddress());
		W_StoreInfo.Default(E_Http_StoreInfo.name, storeName, bean.getName());
		W_StoreInfo.Default(E_Http_StoreInfo.fixPhone,storePhone , bean.getPhone());
		E_Http_StoreRemark enumType=E_Http_StoreRemark.get(Integer.valueOf(storeComment));
		switch (enumType) {
		case DEFAULT:
			
			break;
		case DISLIKE:
			disLikeCheckBox.setChecked(true);
			break;
		case LIKE:
			likeCheckBox.setChecked(true);
			break;
		default:
			break;
		}
	}

	@Override
	public void onClickListener() {
		// TODO Auto-generated method stub
		topBar.setCallBack(new topBarCallBack() {
			@Override
			public void call_backBtnListener() {
				super.call_backBtnListener();
				outFinish();
			}

			@Override
			public void call_rightTextBtnListener() {
				// TODO Auto-generated method stub
				super.call_rightTextBtnListener();
				//跳转到留言墙页面
				AnimationUtil.in2TopIntent(context, LeaveNotePage.class,
						storeId);
			}
		});
	}

	/**
	 * 完成店铺详情请求的操作
	 */
	private void requestMethod() {
		// TODO Auto-generated method stub
		Req_StoreIfo reqBean=new Req_StoreIfo();
		reqBean.setId(storeId);
		new Http_GetStroreIfo(new Call_httpData<Recv_StoreIfo>() {
			
			@Override
			public void onSuccess(Recv_StoreIfo datas) {
				// TODO Auto-generated method stub
				initStoreValue(datas);
			}
			
			@Override
			public void onStart() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onFinally() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onFail() {
				// TODO Auto-generated method stub
				toast.setText("信息获取失败");
			}
		}).onParams(reqBean).onAction();
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		AnimationUtil.finishOut2Right(context);
	}
	@OnClick(R.id.store_detail_introduce_layout)
	public void introClick(View v){
		AnimationUtil.in2LeftIntent(context, StoreIntroducePage.class);
	}
	/**
	 * 分享店家事件
	 * @param v
	 */
	@OnClick(R.id.store_share_layout)
	public void storeShareClick(View v){
		new RunnableService(new runCallBack() {

			@Override
			public void start() {
				// TODO Auto-generated method stub
				String filePath = ScreenShot
						.shoot(ShopDetailActivity.this);
				DBUtils.setSharedPreStr(ShopDetailActivity.this, "SHAREIMG", filePath);
			}

			@Override
			public void end() {
				// 跳转到分享页面
				AnimationUtil.in2TopIntent(ShopDetailActivity.this,
						ShareUnitDialog.class);
			}
		}, false);
	}
	@OnClick(R.id.store_privilege_layout)
	public void privilegeClick(View v){
		AnimationUtil.in2TopIntent(context, StorePrivilegePage.class, storeId);
	}

	private void checkBoxChangeListener() {
		// TODO Auto-generated method stub
		likeCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
			}
		});
		disLikeCheckBox
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
					}
				});
		likeCheckBox.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (likeCheckBox.isChecked()) {
					storeLikeRequestMethod(StoreRemarkType.INTEREST);
				} else {
					storeLikeRequestMethod(StoreRemarkType.INTEREST_CANCEL);
				}
			}
		});
		disLikeCheckBox.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (disLikeCheckBox.isChecked()) {
					storeLikeRequestMethod(StoreRemarkType.UNINTEREST);
				} else {
					storeLikeRequestMethod(StoreRemarkType.UNINTEREST_CANCEL);
				}
			}
		});
	}

	public void storeLikeRequestMethod(final StoreRemarkType enumType) {
		Req_StoreRemark reqBean=new Req_StoreRemark();
		reqBean.setBid(storeId);
		reqBean.setUid(Dao_SelfIfo.getInstance().getMid());
		Http_StoreRemark httpReq=(Http_StoreRemark) new Http_StoreRemark(new Call_httpData<Class<?>>() {
			
			@Override
			public void onSuccess(Class<?> datas) {
				// TODO Auto-generated method stub
				switch (enumType) {
				case INTEREST:
					toast.setText("喜欢上了该店");
					disLikeCheckBox.setChecked(false);
					break;
				case INTEREST_CANCEL:
					toast.setText("取消对该店的喜欢");
					break;
				case UNINTEREST:
					toast.setText("狠狠鄙视了该店");
					likeCheckBox.setChecked(false);
					break;
				case UNINTEREST_CANCEL:
					toast.setText("你取消了对该店的鄙视");
					break;
				default:
					break;
				}
			}
			
			@Override
			public void onStart() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onFinally() {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void onFail() {
				// TODO Auto-generated method stub
				toast.setText("操作失败");
				switch (enumType) {
				case INTEREST:
					likeCheckBox.setChecked(false);
					break;
				case INTEREST_CANCEL:
					likeCheckBox.setChecked(true);
					break;
				case UNINTEREST:
					disLikeCheckBox.setChecked(false);
					break;
				case UNINTEREST_CANCEL:
					disLikeCheckBox.setChecked(true);
					break;
				default:
					break;
				}
			}
		}).onParams(reqBean);
		httpReq.onType(enumType).onAction();
	}
	@Override
	public void outFinish() {
		// TODO Auto-generated method stub
		finish();
		AnimationUtil.finishOut2Right(context);
	}

}
