package com.hi.module.store.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;

import com.android.ruifeng.hi.R;
import com.customview.callBack.topBarCallBack;
import com.customview.view.CustomPageView;
import com.customview.view.CustomTopbarView;
import com.hi.adapter.LeaveNoteAdapter;
import com.hi.adapter.LeaveNoteAdapter.callBack;
import com.hi.common.http.E_Http_Praise;
import com.hi.dao.supImpl.Dao_SelfIfo;
import com.hi.http.base.Call_httpData;
import com.hi.http.base.Call_httpListData;
import com.hi.http.local.model.Recv_obtMsg;
import com.hi.http.local.model.Req_obtMsg;
import com.hi.http.local.model.Req_praiseMsg;
import com.hi.http.local.req.Http_obtMsg;
import com.hi.http.local.req.Http_praiseMsg;
import com.hi.module.base.superClass.NormalActivity;
import com.hi.module.locale.ui.leavenote.LeaveNotePraisePage;
import com.hi.utils.AnimationUtil;
import com.hi.utils.DeviceUtils;
import com.hi.view.scaleImg.DragImgShowActivity;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.thread.HandlerExtend;
import com.thread.HandlerExtend.handleCallBack;
import com.xlistview.XListView;
import com.xlistview.XListView.listHttpCallBack;

public class LeaveNotePage extends NormalActivity{
	private View view;
	
	@ViewInject(R.id.pageView)
	private CustomPageView pageView;
	@ViewInject(R.id.xListView)
	private XListView xListView;
	@ViewInject(R.id.topBar)
	private CustomTopbarView topBar;
	
	private LeaveNoteAdapter adapter;
	private Http_obtMsg reqHttp;
	private List<Recv_obtMsg> recvList = new ArrayList<Recv_obtMsg>();
	private Req_obtMsg reqBean = new Req_obtMsg();
	private String storeId;
	private int favourPosition;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.shop_leavenote_page);
		ViewUtils.inject(this);
		super.onCreate(savedInstanceState);
	}
	@Override
	public void obtainIntentValue() {
		// TODO Auto-generated method stub
		storeId=getIntent().getStringExtra("DATA0");
	}

	@Override
	public void initResource() {
		// TODO Auto-generated method stub
		adapter = new LeaveNoteAdapter(context);
		xListView.setHttpCallBack(new listHttpCallBack() {

			@Override
			public void initListView() {
				// TODO Auto-generated method stub
				xListView.setMode(true, true);
				adapter.setCallBack(call);
//				reqBean.setWifiMac(DeviceUtils.getWifiMac());// 测试用
				reqBean.setBid(storeId);
				reqHttp = (Http_obtMsg) new Http_obtMsg(callBack).onParams(
						reqBean).onInit();
			}

			@Override
			public void onRefresh() {
				// TODO Auto-generated method stub
				reqHttp.onInit();
			}

			@Override
			public void onLoadMore() {
				// TODO Auto-generated method stub
				reqHttp.onLoad();
			}

		});
		xListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				AnimationUtil.tab_in2LeftIntent(context,
						LeaveNotePraisePage.class, recvList.get(position - 1)
								.getId());
			}
		});
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
	}
	private Call_httpListData<Recv_obtMsg> callBack = new Call_httpListData<Recv_obtMsg>() {

		@Override
		public void onStart() {
			// TODO Auto-generated method stub

		}

		@Override
		public void onInit(List<Recv_obtMsg> datas) {
			// TODO Auto-generated method stub
			recvList = datas;
			adapter.setDatas(recvList);
			handlerExtend.onInitView();
		}

		@Override
		public void onLoad(List<Recv_obtMsg> datas) {
			// TODO Auto-generated method stub
			recvList.addAll(datas);
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
			if (reqHttp.isIfInit()) {
				handlerExtend.onFail();
			} else {
				handlerExtend.onLoadNull();
			}
		}
	};
	private HandlerExtend handlerExtend = new HandlerExtend(
			new handleCallBack() {
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
					pageView.setTextOnly("快来留下你的留言吧")
							.setBottomLayoutVisible(false)
							.setVisibility(View.VISIBLE);
				};

				public void call_onFinally() {
					xListView.onLoadStop();
				};

			});
	private callBack call = new callBack() {

		@Override
		public void onImageClick(int position) {
			// AnimationUtil.nor_toIntent(context, ImageViewPage.class, recvList
			// .get(position).getImg());
			AnimationUtil.nor_toIntent(context, DragImgShowActivity.class,
					recvList.get(position).getImg());
		}

		@Override
		public void onHeadClick(int position) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onFavour(int position, boolean isCheck) {
			// TODO Auto-generated method stub
			Recv_obtMsg recvBean = recvList.get(position);
			Req_praiseMsg reqBean = new Req_praiseMsg();
			reqBean.setUid(Dao_SelfIfo.getInstance().getMid());
			reqBean.setMwID(recvBean.getId());
			favourPosition = position;
			if (isCheck) {
				reqBean.setType(E_Http_Praise.PRAISE.toString());
				new Http_praiseMsg(callPraise).onParams(reqBean).onAction();
			} else {
				reqBean.setType(E_Http_Praise.UNPRAISE.toString());
				new Http_praiseMsg(callUnPraise).onParams(reqBean).onAction();
			}

		}
	};
	private Call_httpData<Class<?>> callPraise = new Call_httpData<Class<?>>() {

		@Override
		public void onSuccess(Class<?> datas) {
			Recv_obtMsg recvBean = recvList.get(favourPosition);
			recvBean.setType(E_Http_Praise.PRAISE.toString());
			recvBean.setFavourNum(getFavourNum(true, recvBean.getFavourNum()));
		}

		@Override
		public void onFail() {
			Recv_obtMsg recvBean = recvList.get(favourPosition);
			recvBean.setType(E_Http_Praise.UNPRAISE.toString());
			recvBean.setFavourNum(getFavourNum(false, recvBean.getFavourNum()));
		}

		@Override
		public void onStart() {

		}

		@Override
		public void onFinally() {
			adapter.notifyDataSetChanged();
		}

	};

	private Call_httpData<Class<?>> callUnPraise = new Call_httpData<Class<?>>() {

		@Override
		public void onSuccess(Class<?> datas) {
			// TODO Auto-generated method stub
			Recv_obtMsg recvBean = recvList.get(favourPosition);
			recvBean.setType(E_Http_Praise.UNPRAISE.toString());
			recvBean.setFavourNum(getFavourNum(false, recvBean.getFavourNum()));
		}

		@Override
		public void onFail() {
			// TODO Auto-generated method stub
			Recv_obtMsg recvBean = recvList.get(favourPosition);
			recvBean.setType(E_Http_Praise.PRAISE.toString());
			recvBean.setFavourNum(getFavourNum(true, recvBean.getFavourNum()));
		}

		@Override
		public void onStart() {

		}

		@Override
		public void onFinally() {
			adapter.notifyDataSetChanged();
		}

	};

	private String getFavourNum(boolean isPlus, String favourNum) {
		try {
			if (isPlus) {
				return String.valueOf(Integer.valueOf(favourNum) + 1);
			}
			if (Integer.valueOf(favourNum) <= 0) {
				return "0";
			}
			return String.valueOf(Integer.valueOf(favourNum) - 1);
		} catch (Exception e) {
			// TODO: handle exception
			return favourNum;
		}
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
		AnimationUtil.finishOut2Bottom(context);
	}
	@Override
	public void outFinish() {
		// TODO Auto-generated method stub
		
	}

}
