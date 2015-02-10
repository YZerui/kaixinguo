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
 * @author MM_Zerui ��Ϣҳ�棬ָ�û��յ��Ĵ�ڨ���Żݵ���Ϣ
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
		// ע�������
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
				// ��ת�Ĺ����д����������������Է�UID���Է�ͷ�񣬶Է��ǳ�
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
						dialogBtn1 = "��Ϊδ��";
					} else {
						dialogBtn1 = "��Ϊ�Ѷ�";
					}
					dialogBtn2 = "���ô�ڨ";
					dialogBtn3 = "ɾ���ô�ڨ";

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
						// ��Ϊδ��
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
						// ���ô�ڨ

					}

					@Override
					public void click_btn3() {
						// TODO Auto-generated method stub
						super.click_btn3();
						// ɾ���ô�ڨ
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
		// ��ʼ����ѯ�Σ�����Ϊ��һ��
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
		// topBar.onNoteText("����һ���µ���Ϣ");
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
			pageView.setTextOnly("��û�д�ڨ��ϢŶ").setBottomLayoutVisible(false)
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
	 * ע�������
	 * 
	 */
	private void registerReceive() {
		// TODO Auto-generated method stub
		// ע�������
		listMsgReceive = new listNotifyReceive();
		IntentFilter filter_system = new IntentFilter();
		filter_system.addAction(Enum_Page.MSG.name());
		registerReceiver(listMsgReceive, filter_system);
	}

	/**
	 * ���δ����Ϣ����
	 */
	private void clearUnRead(int position, String id) {
		try {
			Dao_IMsgSeq.clearUnRead(id);
			items.get(position).setUnRead(0);
			adapter.notifyDataSetChanged();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("����δ����������ʱ���ִ���");
			e.printStackTrace();
		}
	}

	/*
	 * ����ҳ����������и��±�����Ϣ�б� (non-Javadoc)
	 * 
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		P.v("ˢ��...");
		DBReq.onInit();
		//������绷��
		if(!HttpUtils.isNetworkAvailable(context)){
			noteText.setText("���绷��������...");
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
	 * ����һ������֪ͨ���ڲ��࣬���ڸ����б���ͼ
	 * 
	 * @author MM_Zerui
	 * @tip_1 ������Ϣ����ʱ�������ü�����
	 * @tip_2 ���������Activity����������
	 * @tip_3 ��������Activity����ʱ��ȡ��ע�᲻�ټ���
	 */
	class listNotifyReceive extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			if (intent.getAction().equals(Enum_Page.MSG.name())) {
				DBReq.onInit();
			}
			if(intent.getAction().equals(Enum_Page.MSG_NET.name())){
				//������绷��
				if(!HttpUtils.isNetworkAvailable(context)){
					noteText.setText("���绷��������...");
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
		// ȡ����������ע��
		unregisterReceiver(listMsgReceive);
	}

	@Override
	protected void outFinish() {
		// TODO Auto-generated method stub

	}
}
