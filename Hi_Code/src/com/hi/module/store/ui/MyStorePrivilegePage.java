package com.hi.module.store.ui;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import com.android.ruifeng.hi.R;
import com.customview.callBack.topBarCallBack;
import com.customview.view.CustomPageView;
import com.customview.view.CustomTopbarView;
import com.format.utils.DataValidate;
import com.hi.adapter.MyStorePrivilegeAdapter;
import com.hi.adapter.MyStorePrivilegeAdapter.CallListItem;
import com.hi.adapter.MyStorePrivilegeItemAdapter;
import com.hi.adapter.ShopMyPrivilegeAdapter;
import com.hi.adapter.ShopPrivilegeAdapter;
import com.hi.common.API;
import com.hi.common.EXCEPTION_CODE;
import com.hi.common.PARAMS;
import com.hi.dao.supImpl.Dao_Params;
import com.hi.dao.supImpl.Dao_SelfIfo;
import com.hi.http.base.Call_httpListData;
import com.hi.http.store.model.Recv_StoreList;
import com.hi.http.store.model.Req_StoreList;
import com.hi.http.store.req.Http_StoreList;
import com.hi.module.base.application.ThreadPool;
import com.hi.module.base.callBack.httpResultCallBack;
import com.hi.module.base.superClass.ListActivity;
import com.hi.module.store.model.RecvMyPrivilegeBean;
import com.hi.module.store.model.RecvPrivilegeBean;
import com.hi.module.store.model.RecvPrivilegeDetailBean;
import com.hi.module.store.model.ReqMyStorePrivilegeBean;
import com.hi.module.store.model.ReqStorePrivilegeBean;
import com.hi.service.HttpResultService;
import com.hi.utils.AnimationUtil;
import com.hi.utils.DeviceUtils;
import com.hi.utils.ParseJson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.thread.HandlerExtend;
import com.thread.HandlerExtend.handleCallBack;
import com.tjerkw.slideexpandable.library.SlideExpandableListAdapter;
import com.xlistview.XListView;
import com.xlistview.XListView.listHttpCallBack;

/**
 * 显示本人拥有的所有优惠劵的页面
 * @author MM_Zerui 
 * @tip 操作步骤如下:
 * @tip_1 首先获取连接过wifi的商家
 */
public class MyStorePrivilegePage extends ListActivity{
	@ViewInject(R.id.xListView)
	private XListView xListView;
	@ViewInject(R.id.topBar)
	private CustomTopbarView topBar;
	@ViewInject(R.id.pageView)
	private CustomPageView pageView;
	
	private MyStorePrivilegeAdapter adapter;
	private List<Recv_StoreList> listDatas;
	private Http_StoreList httpReq;
	private Req_StoreList reqBean;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.shop_self_privilege_page_layout);
		ViewUtils.inject(this);
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void obtainIntentValue() {
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
	protected void initResource() {

		xListView.setHttpCallBack(new listHttpCallBack() {

			@Override
			public void initListView() {
				// TODO Auto-generated method stub
				topBar.setProVisibility(true);
				xListView.setMode(true, false);
				listDatas=new ArrayList<Recv_StoreList>();
				adapter = new MyStorePrivilegeAdapter(context);
				reqBean=new Req_StoreList();
				xListView.setMode(false, false);
				adapter.setCallBack(callList);
				reqBean.setUid(Dao_SelfIfo.getInstance().getMid());
				httpReq=new Http_StoreList(callBack);
				httpReq.onParams(reqBean).onInit();
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
	private CallListItem callList=new CallListItem() {
		
		@Override
		public void onExpandClick(int position, XListView listView) {
			
			//展开二级列表
			listView.setAdapter(new MyStorePrivilegeItemAdapter(context));
			ViewGroup.LayoutParams params = listView.getLayoutParams();
			params.height=DeviceUtils.dip2px(context, 70) * 6;
			listView.setLayoutParams(params);
		}

//		@Override
//		public void onExpandList(int position, XListView xListView) {
//			// TODO Auto-generated method stub
//			
//		}
	};
	private Call_httpListData<Recv_StoreList> callBack=new Call_httpListData<Recv_StoreList>() {
		
		@Override
		public void onStart() {
			// TODO Auto-generated method stub
		}
		@Override
		public void onInit(List<Recv_StoreList> datas) {
			// TODO Auto-generated method stub
			listDatas=datas;
			adapter.setData(listDatas);
			handlerExtend.onInitView();
		}
		@Override
		public void onLoad(List<Recv_StoreList> datas) {
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
			handlerExtend.onFail();
		}
	};
	private HandlerExtend handlerExtend=new HandlerExtend(new handleCallBack() {
		
		@Override
		public void call_onInit() {
			// TODO Auto-generated method stub
			pageView.setVisibility(View.GONE);
//			xListView.setAdapter(adapter);
			xListView.setAdapter(new SlideExpandableListAdapter(adapter,R.id.expandIcon,R.id.expandItem));
		}
		@Override
		public void call_onLoad() {
			// TODO Auto-generated method stub
			pageView.setVisibility(View.GONE);
			adapter.notifyDataSetChanged();
		}
	
		public void call_onFail() {
			pageView.setTextOnly("您还没到访过商家，暂无优惠劵哦")
				.setBottomLayoutVisible(false)
				.setVisibility(View.VISIBLE);
		};
		public void call_onFinally() {
			xListView.onLoadStop();
			topBar.setProVisibility(false);
		};
	});
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

}
