package com.hi.module.friend.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONObject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.ruifeng.hi.R;
import com.customview.callBack.pageCallBack;
import com.customview.callBack.topBarCallBack;
import com.customview.view.CustomPageView;
import com.customview.view.CustomTopbarView;
import com.hi.adapter.FriItemAdapter;
import com.hi.common.PARAMS;
import com.hi.dao.model.T_MyFriends;
import com.hi.dao.supImpl.Dao_SelfIfo;
import com.hi.http.base.Call_httpListData;
import com.hi.http.friends.model.Recv_Friends;
import com.hi.http.friends.model.Req_Friends;
import com.hi.http.friends.req.Http_Friends;
import com.hi.module.base.superClass.ListActivity;
import com.hi.module.friend.model.FriendsBean;
import com.hi.module.locale.ui.UserDetailFragmentActivity;
import com.hi.service.ContactsUploadService;
import com.hi.service.base.Call_DBListData;
import com.hi.service.db.DBReq_MyFriends;
import com.hi.utils.AnimationUtil;
import com.hi.utils.CharacterParser;
import com.hi.utils.DBUtils;
import com.hi.utils.PinyinComparator2;
import com.hi.view.customWidget.ClearEditText;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.thread.HandlerExtend;
import com.thread.HandlerExtend.handleCallBack;
import com.xlistview.XListView;
import com.xlistview.XListView.listHttpCallBack;


/**
 * 朋友列表页面
 * @author MM_Zerui
 *
 */
public class LocalFriendsActivity extends ListActivity {
	@ViewInject(R.id.topBar)
	private CustomTopbarView topBar;
	@ViewInject(R.id.xListView)
	private XListView xListView;
	@ViewInject(R.id.pageView)
	private CustomPageView pageView;
	
	private FriItemAdapter adapter;
	private List<T_MyFriends> items;
	private List<T_MyFriends> recvList;
	
	private DBReq_MyFriends DBReq;
	
	private NotifyReceive notifyReceive;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.friend_page_layout);
		ViewUtils.inject(this);
		super.onCreate(savedInstanceState);
		//注册本页面的监听器
		registerReceive();
	}

	@Override
	protected void obtainIntentValue() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setOnClickListener() {
		topBar.setCallBack(new topBarCallBack() {
			@Override
			public void call_rightImgBtnListener() {
				// TODO Auto-generated method stub
				super.call_rightImgBtnListener();
				AnimationUtil.tab_in2LeftIntent(context, ContactsPage.class);
			}
			@Override
			public void call_leftImgBtnListener() {
				// TODO Auto-generated method stub
				super.call_leftImgBtnListener();
				//弹出搜索框
				AnimationUtil.nor_toIntent(context, SearchPage_Friends.class);
			}
		});
		xListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				T_MyFriends bean=items.get(position-1);
				AnimationUtil.tab_in2LeftIntent(context,UserDetailFragmentActivity.class,bean.getFid()
						,bean.getHead(),bean.getNickName());
			}
		});
		pageView.setCallBack(new pageCallBack() {
			
			@Override
			public void onRefreshClick() {
				// TODO Auto-generated method stub
				DBReq.onInit();
			}
		});
	}

	@Override
	protected void initResource() {
		// TODO Auto-generated method stub
		// 开启通讯录匹配服务
		topBar.setRightImgVisibility(false);
//		topBar.setProVisibility(true);
//		Intent intent = new Intent(this, ContactsUploadService.class);
//		startService(intent);
		
		
		items = new ArrayList<T_MyFriends>();
		adapter = new FriItemAdapter(this);
		recvList = new ArrayList<T_MyFriends>();
		DBReq=new DBReq_MyFriends(callBack);
		xListView.setHttpCallBack(new listHttpCallBack() {
			@Override
			public void initListView() {
				// TODO Auto-generated method stub
				xListView.setMode(true, true);
				pageView.onProgressOnly().setVisibility(View.VISIBLE);
				DBReq.onInit();
			}
			@Override
			public void onRefresh() {
				// TODO Auto-generated method stub
				DBReq.onInit();
			}
			
			@Override
			public void onLoadMore() {
				// TODO Auto-generated method stub
				DBReq.onLoad();
			}
			
		
		});
		
		pageView.setCallBack(new pageCallBack() {
			
			@Override
			public void onRefreshClick() {
				// TODO Auto-generated method stub
				pageView.progressRun();
			}
		});
		
	}
	/**
	 * 数据获取的回调
	 */
	private Call_DBListData<T_MyFriends> callBack=new Call_DBListData<T_MyFriends>() {
		
		@Override
		public void onStart() {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void onInit(List<T_MyFriends> datas) {
			// TODO Auto-generated method stub
			items=datas;
			adapter.setData(items);
			handlerExtend.onInitView();
		}
		@Override
		public void onLoad(List<T_MyFriends> datas) {
			// TODO Auto-generated method stub
			items.addAll(datas);
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
			if(DBReq.isIfInit()){
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
		public void call_onLoadNull() {
			
		};
		public void call_onFail() {
			pageView.setTextOnly("暂无朋友信息哦，赶快去寻找搭讪吧")
				.progressEnd().setVisibility(View.VISIBLE);
		};
		public void call_onFinally() {
			xListView.onLoadStop();
		};
	});
	public void registerReceive(){
		notifyReceive=new NotifyReceive();
		IntentFilter filter_system = new IntentFilter();
		filter_system.addAction(PARAMS.CONTACTS_DONE_RECEIVE);
		registerReceiver(notifyReceive, filter_system);
	}


	/**
	 * 朋友列表页面的监听器，用于监听
	 * @author MM_Zerui
	 *
	 */
	class NotifyReceive extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (intent.getAction().equals(PARAMS.CONTACTS_DONE_RECEIVE)) {
				//一旦收到消息，标识号码上传验证服务已经完毕
				topBar.setProVisibility(false).setRightImgVisibility(true);
			}
		}
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(notifyReceive);
	}
	@Override
	protected void outFinish() {
		// TODO Auto-generated method stub

	}

}
