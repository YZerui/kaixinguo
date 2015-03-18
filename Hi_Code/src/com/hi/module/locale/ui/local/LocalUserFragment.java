package com.hi.module.locale.ui.local;

import java.util.ArrayList;
import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.android.ruifeng.hi.R;
import com.customview.callBack.pageCallBack;
import com.customview.view.CustomPageView;
import com.format.utils.DataValidate;
import com.hi.adapter.LocalItemAdapter;
import com.hi.common.param.Enum_Page;
import com.hi.dao.supImpl.Dao_SelfIfo;
import com.hi.http.base.Call_httpListData;
import com.hi.http.member.model.Recv_WifiList;
import com.hi.http.member.model.Req_WifiList;
import com.hi.http.member.req.Http_WifiList;
import com.hi.module.base.superClass.ListFragment;
import com.hi.module.locale.ui.UserDetailFragmentActivity;
import com.hi.service.LocalWifiCheckService;
import com.hi.utils.AnimationUtil;
import com.hi.utils.DeviceUtils;
import com.lidroid.xutils.ViewUtils;
import com.thread.HandlerExtend;
import com.thread.HandlerExtend.handleCallBack;
import com.xlistview.XListView;
import com.xlistview.XListView.listHttpCallBack;

/**
 * 显示现场用户的列表页面
 * 
 * @author MM_Zerui
 */
public class LocalUserFragment extends ListFragment {

	private LocalItemAdapter localItemAdapter;
	private List<Recv_WifiList> peoList;
	private View view;

	private XListView xListView;
	private CustomPageView pageView;
	private Http_WifiList httpReq;
	private Req_WifiList reqBean;
	private listNotifyReceive listMsgReceive;
    private RelativeLayout barLayout;
    private ImageView localImg;
	private TextView localNote;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.local_user_frament, null);
//		ViewUtils.inject(view);
		super.onCreateView(inflater, container, savedInstanceState);
		registerReceive();
		return view;
	}

	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		xListView = (XListView) view.findViewById(R.id.xListView);
		pageView = (CustomPageView) view.findViewById(R.id.pageView);
		localNote=(TextView)view.findViewById(R.id.barTextNote);
        barLayout=(RelativeLayout)view.findViewById(R.id.barLayout);
        localImg=(ImageView)view.findViewById(R.id.barImageNote);
	}

	@Override
	protected void setOnClickListener() {
		pageView.setCallBack(new pageCallBack() {

			@Override
			public void onRefreshClick() {
				reqBean.setWifiMac(DeviceUtils.getWifiMac());
				httpReq.onParams(reqBean);
				pageView.progressRun();
				httpReq.onInit();

			}
		});
	}

	@Override
	protected void initResource() {
		peoList = new ArrayList<Recv_WifiList>();
		localItemAdapter = new LocalItemAdapter(context);
		httpMethod();
		xListView.setHttpCallBack(new listHttpCallBack() {
			
			@Override
			public void onRefresh() {
				// TODO Auto-generated method stub
				reqBean.setWifiMac(DeviceUtils.getWifiMac());
				httpReq.onParams(reqBean);
				httpReq.onInit();
			}
			
			@Override
			public void onLoadMore() {
				// TODO Auto-generated method stub
				httpReq.onLoad();
			}
			
			@Override
			public void initListView() {
				// TODO Auto-generated method stub
				xListView.setMode(true, true);
				httpReq.onInit();
			}
		});
		xListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				Recv_WifiList bean = peoList.get(position - 1);
				AnimationUtil.tab_in2LeftIntent(context,
						UserDetailFragmentActivity.class, bean.getMid(),
						bean.getHead(), bean.getNickName());
			}
		});
	}

	private void httpMethod() {
		// TODO Auto-generated method stub
		reqBean = new Req_WifiList();
		reqBean.setUid(Dao_SelfIfo.getInstance().getMid());
		reqBean.setWifiMac(DeviceUtils.getWifiMac());
		if (initBool) {
			pageView.onProgressOnly().setVisibility(View.VISIBLE);
			initBool = false;
		}
		httpReq = new Http_WifiList(new Call_httpListData<Recv_WifiList>() {

			@Override
			public void onStart() {
				// TODO Auto-generated method stub
				// pageView.onProgressOnly().setVisibility(
				// View.VISIBLE);
				

			}

			@Override
			public void onInit(List<Recv_WifiList> datas) {
				// TODO Auto-generated method stub
				peoList = datas;
				localItemAdapter.setData(peoList);
				handlerExtend.onInitView();

			}

			@Override
			public void onLoad(List<Recv_WifiList> datas) {
				// TODO Auto-generated method stub

				peoList.addAll(datas);
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
		});
		httpReq.onParams(reqBean);
	}

	private HandlerExtend handlerExtend = new HandlerExtend(
			new handleCallBack() {

				@Override
				public void call_onInit() {
					// TODO Auto-generated method stub
					pageView.setVisibility(View.GONE);
					xListView.setAdapter(localItemAdapter);
				}

				@Override
				public void call_onLoad() {
					// TODO Auto-generated method stub
					pageView.setVisibility(View.GONE);
					localItemAdapter.notifyDataSetChanged();
				}

				public void call_onFail() {
					if (httpReq.isIfInit()) {
						pageView.setDefaultPage().setTextOnly("现场好像木有人哦")
								.setBottomLayoutVisible(true).progressEnd()
								.setVisibility(View.VISIBLE);
					}
				};

				public void call_onFinally() {
					xListView.onLoadStop();
				};
			});
	/**
	 * 注册监听器
	 * 
	 */
	private void registerReceive() {
		// TODO Auto-generated method stub
		// 注册监听器
		listMsgReceive = new listNotifyReceive();
		IntentFilter filter_system = new IntentFilter();
		filter_system.addAction(Enum_Page.WIFILOCAL.name());
		getActivity().registerReceiver(listMsgReceive, filter_system);
	}
	/**
	 * 定义一个接受通知的内部类，用于更新当前wifi连接情况
	 * 
	 * @author MM_Zerui
	 * @tip_1 当有消息到达时，触发该监听器
	 * @tip_2 监听器随该Activity启动而开启
	 * @tip_3 监听器当Activity销毁时便取消注册不再监听
	 */
	class listNotifyReceive extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			if (intent.getAction().equals(Enum_Page.WIFILOCAL.name())) {
				if(DataValidate.checkDataValid(intent.getStringExtra("DATA0"))){
                    barLayout.setVisibility(View.VISIBLE);
//                    localNote.setText(intent.getStringExtra("DATA1"));
//                    localNote.setVisibility(View.VISIBLE);
                    String note=intent.getStringExtra("DATA0");
                    String noteStr=intent.getStringExtra("DATA1");
                    //该wifi已经注册
                    if(note.equals(LocalWifiCheckService.Enum_WIFICHECK.MATCH.name())){
                        localImg.setImageResource(R.drawable.wifi_locat_icon);
                        localNote.setText("当前位置:"+noteStr);
                    }
                    //该wifi未注册
                    else if (note.equals(LocalWifiCheckService.Enum_WIFICHECK.UNMATCH.name())){
                        localImg.setImageResource(R.drawable.wifi_warn_icon);
                        localNote.setText("该wifi还未被登记哦，帮忙注册有奖");
                    }
                    //目前未连接wifi
                    else {
                        localImg.setImageResource(R.drawable.wifi_warn_icon);
                        localNote.setText("当前未连接wifi哦");
                    }

				}
			}
		}
	}
}
