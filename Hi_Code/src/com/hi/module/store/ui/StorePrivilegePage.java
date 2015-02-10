package com.hi.module.store.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.android.ruifeng.hi.R;
import com.customview.callBack.topBarCallBack;
import com.customview.view.CustomPageView;
import com.customview.view.CustomTopbarView;
import com.hi.adapter.ShopPrivilegeAdapter;
import com.hi.dao.model.T_Coupons;
import com.hi.http.base.Call_httpListData;
import com.hi.http.coupons.model.Recv_CouponsList;
import com.hi.http.coupons.model.Req_CouponsList;
import com.hi.http.coupons.req.Http_StoreCoupons;
import com.hi.module.base.superClass.ListActivity;
import com.hi.utils.AnimationUtil;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.xlistview.XListView;
import com.xlistview.XListView.listHttpCallBack;

/**
 * 显示某店优惠劵的页面
 * 
 * @author MM_Zerui
 */
public class StorePrivilegePage extends ListActivity {
	@ViewInject(R.id.topBar)
	private CustomTopbarView topBar;
	@ViewInject(R.id.pageView)
	private CustomPageView pageView;
	@ViewInject(R.id.xListView)
	private XListView xListView;

	public static final String SOURCE_STORE_PRIVILEGE = "1";
	private ShopPrivilegeAdapter adapter;
	private List<T_Coupons> items;
	private String storeId;
	private Http_StoreCoupons httpReq;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.shop_privilege_page_layout);
		ViewUtils.inject(this);
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void obtainIntentValue() {
		// TODO Auto-generated method stub
		storeId = getIntent().getStringExtra("DATA0");
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
		xListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// 跳转到优惠劵详情页，夹带店家ID和优惠劵ID参数 和 页面标识
				T_Coupons tBean = items.get(position - 1);
				Intent intent = new Intent(StorePrivilegePage.this,
						StorePrivilegeDetailPage.class);
				intent.putExtra("T_Coupons", tBean);
				startActivity(intent);
				overridePendingTransition(R.anim.in_right_to_left_page,
						R.anim.out_left_to_right_page);
			}
		});
	}

	@Override
	protected void initResource() {
		items = new ArrayList<T_Coupons>();
		adapter = new ShopPrivilegeAdapter(context);
		xListView.setHttpCallBack(new listHttpCallBack() {

			@Override
			public void initListView() {
				// TODO Auto-generated method stub
				pageView.setDefaultPage().onProgressOnly()
						.setVisibility(View.VISIBLE);
				Req_CouponsList reqBean = new Req_CouponsList();
				reqBean.setBid(storeId);
				httpReq = (Http_StoreCoupons) new Http_StoreCoupons(callBack)
						.onParams(reqBean).onInit();
			}

			@Override
			public void onRefresh() {
				// TODO Auto-generated method stub
				httpReq.onInit();
			}

			@Override
			public void onLoadMore() {
				// TODO Auto-generated method stub
				httpReq.onLoad();
			}

		});

	}

	private Call_httpListData<T_Coupons> callBack = new Call_httpListData<T_Coupons>() {

		@Override
		public void onStart() {
		}

		@Override
		public void onInit(List<T_Coupons> datas) {
			// TODO Auto-generated method stub
			pageView.setVisibility(View.GONE);
			items = datas;
			adapter.setData(items);
			xListView.setAdapter(adapter);
		}

		@Override
		public void onLoad(List<T_Coupons> datas) {
			// TODO Auto-generated method stub
			pageView.setVisibility(View.GONE);
			items.addAll(datas);
			adapter.notifyDataSetChanged();
		}

		@Override
		public void onFinally() {
			// TODO Auto-generated method stub
			xListView.onLoadStop();
		}

		@Override
		public void onFail() {
			// TODO Auto-generated method stub
			pageView.setTextOnly("暂无优惠劵信息").setVisibility(View.VISIBLE);
		}
	};

	@Override
	protected void outFinish() {
		// TODO Auto-generated method stub
		finish();
		AnimationUtil.finishOut2Bottom(context);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		outFinish();
	}

}
