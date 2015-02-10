package com.hi.module.msg.ui;

import java.util.ArrayList;
import java.util.List;

import com.android.ruifeng.hi.R;
import com.customview.callBack.pageCallBack;
import com.customview.view.CustomPageView;
import com.customview.view.CustomTopbarView;
import com.dialog.Dialog_Select;
import com.dialog.Dialog_Select.callBack_Dialog;
import com.dialog.animstyle.Effectstype;
import com.exception.utils.P;
import com.hi.adapter.MsgItemAdapter;
import com.hi.common.PARAMS;
import com.hi.common.db.E_DB_MsgType;
import com.hi.common.http.E_Http_InfoType;
import com.hi.common.param.Enum_ListLimit;
import com.hi.common.param.Enum_Page;
import com.hi.common.param.Enum_Param;
import com.hi.dao.model.T_IMsgSeq;
import com.hi.dao.supImpl.Dao_IMsgSeq;
import com.hi.dao.supImpl.Dao_MsgSeq;
import com.hi.module.base.superClass.ListActivity;
import com.hi.service.base.Call_DBListData;
import com.hi.service.db.DBReq_IMsgSeq;
import com.hi.service.db.DBReq_MsgSeq;
import com.hi.utils.AnimationUtil;
import com.hi.utils.BroadcastUtil;
import com.hi.utils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.thread.HandlerExtend;
import com.thread.HandlerExtend.handleCallBack;
import com.xlistview.XListView;
import com.xlistview.XListView.listHttpCallBack;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.TextView;

/**
 * @author MM_Zerui 消息页面，指用户收到的搭讪、优惠等信息
 */
public class MsgActivity extends ListActivity {
	@ViewInject(R.id.xListView)
	private XListView mListView;
	@ViewInject(R.id.topBar)
	private CustomTopbarView topBar;
	@ViewInject(R.id.pageView)
	private CustomPageView pageView;

	private MsgItemAdapter adapter;
	private List<T_IMsgSeq> items;

	private listNotifyReceive listMsgReceive;
	private DBReq_IMsgSeq DBReq;
	private Dialog_Select dialog_Select;
	private TextView noteText;
	public void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.msg_page_layout);
		ViewUtils.inject(this);
		super.onCreate(savedInstanceState);
		// 注册监听器
		registerReceive();

	}

	@Override
	protected void obtainIntentValue() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setOnClickListener() {
		// TODO Auto-generated method stub
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				T_IMsgSeq bean = items.get(position - 1);
				// 跳转的过程中传递了三个参数：对方UID，对方头像，对方昵称
				AnimationUtil.tab_in2LeftIntent(context,
						MsgDetailActivity.class, bean.getUid(), bean.getHead(),
						bean.getName());
				clearUnRead(position - 1, bean.getUid());
			}
		});
		mListView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					final int position, long arg3) {
				// TODO Auto-generated method stub
				final T_IMsgSeq msgSeq = items.get(position - 1);
				E_DB_MsgType typeEnum = E_DB_MsgType.get(msgSeq.getMsgType());
				dialog_Select.withTitle(msgSeq.getName());
				String dialogBtn1 = "", dialogBtn2 = "", dialogBtn3 = "";
				switch (typeEnum) {
				case ACCOST:
					if (msgSeq.getUnRead() == 0) {
						dialogBtn1 = "标为未读";
					} else {
						dialogBtn1 = "标为已读";
					}
					dialogBtn2 = "顶置搭讪";
					dialogBtn3 = "删除该搭讪";

					break;
				case FOLLOW:
					break;
				case INVITE:
					break;
				case RECOMMEND:
					break;

				default:
					break;
				}
				dialog_Select.withBtn_1(dialogBtn1).withBtn_2(dialogBtn2)
						.withBtn_3(dialogBtn3);
				dialog_Select.setCallBack(new callBack_Dialog() {
					@Override
					public void click_btn1() {
						// TODO Auto-generated method stub
						super.click_btn1();
						// 标为未读
						if(msgSeq.getUnRead()==0){
							items.remove(msgSeq);
							items.add(0, msgSeq);
							msgSeq.setUnRead(1);
							Dao_IMsgSeq.addMsgUnRead(msgSeq.getUid());
							
						}else {
							msgSeq.setUnRead(0);
							Dao_IMsgSeq.clearUnRead(msgSeq.getUid());
						}
						adapter.notifyDataSetChanged();
						BroadcastUtil.sendBroadCast(context, Enum_Page.TAPBAR.name());
					}

					@Override
					public void click_btn2() {
						// TODO Auto-generated method stub
						super.click_btn2();
						// 顶置搭讪

					}

					@Override
					public void click_btn3() {
						// TODO Auto-generated method stub
						super.click_btn3();
						// 删除该搭讪
						Dao_IMsgSeq.hideUserMsg(msgSeq.getUid());
						items.remove(position - 1);
						adapter.notifyDataSetChanged();
					}

				}).show();

				return true;
			}
		});
	}

	@Override
	protected void initResource() {
		// TODO Auto-generated method stub
		dialog_Select = new Dialog_Select(context)
				.withAnimat(Effectstype.Fadein)
				;
		// 初始化查询段，初次为第一段
		items = new ArrayList<T_IMsgSeq>();
		// mListView.setListCallBack(listCallBack);
		mListView.setHttpCallBack(listCallBack);
		noteText=(TextView)findViewById(R.id.barTextNote);
		pageView.setCallBack(new pageCallBack() {

			@Override
			public void onRefreshClick() {
				// TODO Auto-generated method stub
			}
		});
		// handler.postDelayed(new Runnable() {
		//
		// @Override
		// public void run() {
		// // TODO Auto-generated method stub
		// topBar.onNoteText("你有一条新的消息");
		// }
		// }, 3000);
	}

	private listHttpCallBack listCallBack = new listHttpCallBack() {
		@Override
		public void initListView() {
			// TODO Auto-generated method stub
			if (initBool) {
				pageView.onProgressOnly().setVisibility(View.VISIBLE);
				initBool = false;
			}
			mListView.setMode(true, false);
			adapter = new MsgItemAdapter(context);
			adapter.setData(items);
			mListView.setAdapter(adapter);
			DBReq = new DBReq_IMsgSeq(DBCallBack);

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

	};
	private Call_DBListData<T_IMsgSeq> DBCallBack = new Call_DBListData<T_IMsgSeq>() {

		@Override
		public void onStart() {
			// TODO Auto-generated method stub

		}

		@Override
		public void onInit(List<T_IMsgSeq> datas) {
			// TODO Auto-generated method stub
			items.clear();
			items.addAll(datas);
			// adapter.setData(items);
			handlerExtend.onInitView();

		}

		@Override
		public void onLoad(List<T_IMsgSeq> datas) {
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
			if (DBReq.isIfInit()) {
				handlerExtend.onFail();

				return;
			}
			handlerExtend.onLoadNull();

		}
	};
	HandlerExtend handlerExtend = new HandlerExtend(new handleCallBack() {
		public void call_onFail() {
			pageView.setTextOnly("你没有搭讪消息哦").setBottomLayoutVisible(false)
					.setVisibility(View.VISIBLE);
		};

		@Override
		public void call_onLoad() {
			// TODO Auto-generated method stub
			pageView.setVisibility(View.GONE);
			adapter.notifyDataSetChanged();
		}

		@Override
		public void call_onInit() {
			// TODO Auto-generated method stub
			pageView.setVisibility(View.GONE);
			// mListView.setAdapter(adapter);
			adapter.notifyDataSetChanged();

		}

		public void call_onFinally() {
			mListView.onLoadStop();
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
		filter_system.addAction(Enum_Page.MSG.name());
		registerReceiver(listMsgReceive, filter_system);
	}

	/**
	 * 清除未读消息数量
	 */
	private void clearUnRead(int position, String id) {
		try {
			Dao_IMsgSeq.clearUnRead(id);
			items.get(position).setUnRead(0);
			adapter.notifyDataSetChanged();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("消除未读数的数据时出现错误");
			e.printStackTrace();
		}
	}

	/*
	 * 返回页面操作，带有更新本地信息列表 (non-Javadoc)
	 * 
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		P.v("刷新...");
		DBReq.onInit();
		//检测网络环境
		if(!HttpUtils.isNetworkAvailable(context)){
			noteText.setText("网络环境不可用...");
			noteText.setVisibility(View.VISIBLE);
		}else {
			noteText.setVisibility(View.GONE);
		}
		// items.clear();
		// daoIndex = 0;
		// ArrayList<MsgBean> datas = getData();
		// if(DataValidate.checkDataValid(datas)){
		// adapter.updateListView(datas);
		// pageView.setVisibility(View.GONE);
		// return;
		// }
		// pageView.setVisibility(View.VISIBLE);

	}

	/**
	 * 定义一个接受通知的内部类，用于更新列表视图
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
			if (intent.getAction().equals(Enum_Page.MSG.name())) {
				DBReq.onInit();
			}
			if(intent.getAction().equals(Enum_Page.MSG_NET.name())){
				//检测网络环境
				if(!HttpUtils.isNetworkAvailable(context)){
					noteText.setText("网络环境不可用...");
					noteText.setVisibility(View.VISIBLE);
				}else {
					noteText.setVisibility(View.GONE);
				}
			}
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		// 取消监听器的注册
		unregisterReceiver(listMsgReceive);
	}

	@Override
	protected void outFinish() {
		// TODO Auto-generated method stub

	}
}
