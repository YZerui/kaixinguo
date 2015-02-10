package com.hi.module.self.ui;

import java.util.ArrayList;

import org.json.JSONObject;
import com.android.ruifeng.hi.R;
//import com.hi.adapter.ActivityListAdapter;
//import com.hi.adapter.ActivityListAdapter.onListItemClickListener;
import com.hi.common.API;
import com.hi.common.EXCEPTION_CODE;
import com.hi.module.base.application.ThreadPool;
import com.hi.module.base.superClass.ListActivity;
import com.hi.module.self.model.RecvActivityBean;
import com.hi.module.self.model.ReqActivityBean;
import com.hi.utils.AnimationUtil;
import com.hi.utils.DBUtils;
import com.hi.utils.ParseJson;
import com.hi.utils.ViewHandleUtils;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;

///**
// * @author MM_Zerui 显示活动的页面
// */
//public class ActivityListPage extends ListActivity {
//	final static int ACTIVITY_DATA_REQUEST = 100;
//
//	// 邀请事件的触发
//	public final static int ACTIVITY_INVITE_TRIGGER = 120;
//	// 对活动感兴趣的触发
//	public final static int ACTIVITY_INTEREST_TRIGGER = 121;
//
//	private ImageView activityAddBtn;
//	private RelativeLayout backLayout;
//	private ReqActivityBean reqBean;
//	private HttpService httpService;
//	private ActivityListAdapter listAdapter;
//	ArrayList<RecvActivityBean> beanList;
//
//	// private RelativeLayout inviteLayout;
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		// TODO Auto-generated method stub
//		setContentView(R.layout.self_activity_page_layout);
//		super.onCreate(savedInstanceState);
//	}
//
//	@Override
//	protected void obtainIntentValue() {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	protected void findViewById() {
//		// TODO Auto-generated method stub
//		activityAddBtn = (ImageView) findViewById(R.id.activity_add_btn);
//		backLayout = (RelativeLayout) findViewById(R.id.backLayout);
//		xListView = (XListView) findViewById(R.id.activityListView);
//
//		defaultPage = (ImageView) findViewById(R.id.defalutErrorPage);
//		defaultProgressBar = (ProgressBar) findViewById(R.id.defaultProgressBar);
//		defaultRefresh = (ImageView) findViewById(R.id.defaultRefresh);
//		defaultLayout = (RelativeLayout) findViewById(R.id.defaultLayout);
//
//		// inviteLayout=(RelativeLayout)findViewById(R.id)
//	}
//
//	@Override
//	protected void setOnClickListener() {
//		// TODO Auto-generated method stub
//		activityAddBtn.setOnClickListener(this);
//		backLayout.setOnClickListener(this);
//		xListView.setOnItemClickListener(this);
//
//	}
//
//	@Override
//	protected void initResource() {
//		// TODO Auto-generated method stub
//		reqBean = new ReqActivityBean();
//		listAdapter = new ActivityListAdapter(context);
//		beanList = new ArrayList<RecvActivityBean>();
//		
//	}
//
//	@Override
//	protected void initParams() {
//		// TODO Auto-generated method stub
//		xListView.setPullLoadEnable(false);
//		xListView.setPullRefreshEnable(true);
//		xListView.setXListViewListener(this);
//		listAdapter.setOnListItemClickListener(listItemClickListener);
//	}
//
//	@Override
//	protected void requestMethod() {
//		// TODO Auto-generated method stub
//		reqBean.setUid(DBUtils.getUid());
//		httpService = new HttpService(API.ACTIVITY_LIST, reqBean, handler,
//				ACTIVITY_DATA_REQUEST);
////		new Thread(httpService).start();
//		ThreadPool.getInstance().getExecutorService().execute(httpService);
//	}
//
//	@Override
//	protected void requestLoadMethod(String note) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	protected void getRefreshData(Object obj) {
//		// TODO Auto-generated method stub
//		beanList.clear();
//		beanList = ParseJson.parseLocalJsonArray((JSONObject) obj,
//				RecvActivityBean.class);
//		if (beanList.size() == 0) {
//			return;
//		}
//		listAdapter.setData(beanList);
//		xListView.setAdapter(listAdapter);
//	}
//
//	@Override
//	protected void getLoadData(Object obj) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void onClick(View v) {
//		// TODO Auto-generated method stub
//		switch (v.getId()) {
//		// 添加活动
//		case R.id.activity_add_btn:
//			AnimationUtil.in2TopIntent(context, ActivityLunchPage.class);
//			break;
//		// 返回按钮
//		case R.id.backLayout:
//			outFinish();
//			break;
//
//		default:
//			break;
//		}
//	}
//
//	@Override
//	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void onRefresh() {
//		// TODO Auto-generated method stub
//		requestMethod();
//
//	}
//
//	@Override
//	public void onLoadMore() {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void handleMethod() {
//		// TODO Auto-generated method stub
//		httpListener = new HttpListener() {
//
//			@Override
//			public void requestSuccess(Object obj, int... params) {
//				// TODO Auto-generated method stub
//				switch (params[0]) {
//				case ACTIVITY_DATA_REQUEST:
//					switchRequest(obj, requestMode);
//					break;
//
//				default:
//					break;
//				}
//			}
//
//			@Override
//			public void requestFail(int... params) {
//				// TODO Auto-generated method stub
//				ViewHandleUtils.toastNormal("数据出状况了");
//			}
//
//			@Override
//			public void othersState(int... params) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void netWorkError(int... params) {
//				// TODO Auto-generated method stub
//				ViewHandleUtils.toastNormal("网络开小差了");
//			}
//
//			@Override
//			public void finalMethod() {
//				// TODO Auto-generated method stub
//				onLoadStop();
//			}
//		};
//	}
//
//	/**
//	 * 列表项的点击回调
//	 */
//	private onListItemClickListener listItemClickListener = new onListItemClickListener() {
//
//		@Override
//		public void click(int item, int position) {
//			// TODO Auto-generated method stub
//			switch (item) {
//			case ACTIVITY_INTEREST_TRIGGER:
//				
//				break;
//			case ACTIVITY_INVITE_TRIGGER:
//				AnimationUtil.in2TopIntent(context, FriendSelectActivity.class);
//				break;
//
//			default:
//				break;
//			}
//		}
//	};
//
//	@Override
//	protected void outFinish() {
//		// TODO Auto-generated method stub
//		finish();
//		AnimationUtil.finishOut2Right(context);
//	}
//
//}
