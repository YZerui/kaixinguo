package com.hi.module.store.ui;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.android.ruifeng.hi.R;
import com.customview.callBack.topBarCallBack;
import com.customview.view.CustomPageView;
import com.customview.view.CustomTopbarView;
import com.hi.adapter.ShopPrivilegeAdapter;
import com.hi.dao.model.T_Coupons;
import com.hi.module.base.superClass.ListActivity;
import com.hi.service.base.Call_DBListData;
import com.hi.service.db.DBReq_Conpous;
import com.hi.utils.AnimationUtil;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.thread.HandlerExtend;
import com.thread.HandlerExtend.handleCallBack;
import com.xlistview.XListView;
import com.xlistview.XListView.listHttpCallBack;

/**
 * 显示我拥有的优惠劵页面
 * @author MM_Zerui
 *
 */
public class MyPrivilegeActivity extends ListActivity{
	@ViewInject(R.id.topBar)
	private CustomTopbarView topBar;
	@ViewInject(R.id.xListView)
	private XListView xListView;
	@ViewInject(R.id.pageView)
	private CustomPageView pageView;
	
	private List<T_Coupons> listDatas;
	private DBReq_Conpous dbReq;
	private ShopPrivilegeAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.shop_self_privilege_page_layout);
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
		topBar.setCallBack(new topBarCallBack() {
			@Override
			public void call_backBtnListener() {
				// TODO Auto-generated method stub
				super.call_backBtnListener();
				finish();
			}
		});
		xListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				T_Coupons tBean=listDatas.get(position - 1);
				AnimationUtil.in2LeftIntent(context, MyPrivilegeDetailPage.class,
						tBean.getCid(),
						tBean.getName(),
						tBean.getContent(),
						tBean.getPhone(),
						tBean.getAddress(),
						tBean.getImage());
			}
		});
	}

	@Override
	protected void initResource() {
		// TODO Auto-generated method stub
		xListView.setHttpCallBack(new listHttpCallBack() {
			@Override
			public void initListView() {
				// TODO Auto-generated method stub
				xListView.setMode(true, true);
				listDatas=new ArrayList<T_Coupons>();
				dbReq=new DBReq_Conpous(dbCallBack);
				adapter=new ShopPrivilegeAdapter(context);
				dbReq.onInit();
			}
			@Override
			public void onRefresh() {
				// TODO Auto-generated method stub
				dbReq.onInit();
			}
			
			@Override
			public void onLoadMore() {
				// TODO Auto-generated method stub
				dbReq.onLoad();
			}
			
			
		});
		
		
	}
	private Call_DBListData<T_Coupons> dbCallBack=new Call_DBListData<T_Coupons>() {
		
		@Override
		public void onStart() {
			// TODO Auto-generated method stub
		}

		@Override
		public void onInit(List<T_Coupons> datas) {
			// TODO Auto-generated method stub
			listDatas=datas;
			adapter.setData(listDatas);
			handlerExtend.onInitView();
		}
		@Override
		public void onLoad(List<T_Coupons> datas) {
			// TODO Auto-generated method stub
			listDatas.addAll(datas);
			handlerExtend.onLoadView();
		}
		
		
		@Override
		public void onFinally() {
			// TODO Auto-generated method stub
			handlerExtend.onFinally();
		}
		
		@Override
		public void onFail() {
			// TODO Auto-generated method stub
			if(dbReq.isIfInit()){
				handlerExtend.onFail();
				return;
			}
			handlerExtend.onLoadNull();
			
		}
	};
	private HandlerExtend handlerExtend=new HandlerExtend(new handleCallBack() {

		@Override
		public void call_onInit() {
			// TODO Auto-generated method stub
			pageView.setVisibility(View.GONE);
			xListView.setAdapter(adapter);
		}
		@Override
		public void call_onLoad() {
			// TODO Auto-generated method stub
			pageView.setVisibility(View.GONE);
			adapter.notifyDataSetChanged();
		}
		
		public void call_onFail() {
			pageView.setTextOnly("你还没有优惠劵哦，快去商家逛逛吧").setBottomLayoutVisible(false).
				setVisibility(View.VISIBLE);
		};
		public void call_onFinally() {
			xListView.onLoadStop();
		};
	});
	public void finish() {
		super.finish();
		AnimationUtil.finishOut2Right(context);
	};
	@Override
	protected void outFinish() {
		// TODO Auto-generated method stub
		
	}

}
