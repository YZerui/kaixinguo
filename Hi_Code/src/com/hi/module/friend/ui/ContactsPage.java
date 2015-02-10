package com.hi.module.friend.ui;

import java.util.ArrayList;
import java.util.List;

import com.android.ruifeng.hi.R;
import com.customview.callBack.topBarCallBack;
import com.customview.view.CustomPageView;
import com.customview.view.CustomTopbarView;
import com.format.utils.DataValidate;
import com.hi.adapter.ContactsSortAdapter;
import com.hi.adapter.ContactsSortAdapter.onItemFollowClickListener;
import com.hi.common.API;
import com.hi.common.EXCEPTION_CODE;
import com.hi.common.PARAMS;
import com.hi.dao.model.T_Contacts;
import com.hi.dao.supImpl.Dao_Contacts;
import com.hi.dao.supImpl.Dao_Friends;
import com.hi.module.base.application.AppManager;
import com.hi.module.base.application.ThreadPool;
import com.hi.module.base.callBack.httpResultCallBack;
import com.hi.module.base.superClass.NormalActivity;
import com.hi.module.locale.model.ReqFollowBean;
import com.hi.service.GetContactService;
import com.hi.service.HttpResultService;
import com.hi.utils.AnimationUtil;
import com.hi.utils.DBUtils;
import com.hi.utils.HttpUtils;
import com.hi.utils.ViewHandleUtils;
import com.hi.view.customWidget.SideBar;
import com.hi.view.customWidget.SideBar.OnTouchingLetterChangedListener;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.xlistview.XListView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

/**
 * @author MM_Zerui 显示联系人信息
 * @tip_1 联系人信息从本地数据库中获取
 */
public class ContactsPage extends NormalActivity{
	
	@ViewInject(R.id.xListView)
	private XListView contactList;
	@ViewInject(R.id.topBar)
	private CustomTopbarView topBar;
	@ViewInject(R.id.pageView)
	private CustomPageView pageView;
	@ViewInject(R.id.pingYinDialog)
	private TextView pingYinDialog;
	@ViewInject(R.id.sidrbar)
	private SideBar sideBar;
	
	private ContactsSortAdapter sortAdapter;
	private ArrayList<T_Contacts> sortBeans;
	private Dao_Contacts contactDaoImpl;
	private Dao_Friends friendsDaoImpl;
	// 用于标识正在处理的项的位置
	private int HandlePosition;

	final static int FOLLOW_CONTACT = 01;
	final static int FOLLOW_REFRESH = 02;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.friend_contact_page);
		ViewUtils.inject(this);
		super.onCreate(savedInstanceState);
	}

	@Override
	public void obtainIntentValue() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initResource() {
		contactDaoImpl = new Dao_Contacts();
		friendsDaoImpl = new Dao_Friends();
		sideBar.setTextView(pingYinDialog);
		contactList.setMode(false, false);
		
		sideBar.setOnTouchingLetterChangedListener(new OnTouchingLetterChangedListener() {

			@Override
			public void onTouchingLetterChanged(String s) {
				// TODO Auto-generated method stub
				// 该字母首次出现的位置
				if (!DataValidate.checkDataValid(sortBeans)) {
					return;
				}
				int position = sortAdapter.getPositionForSection(s.charAt(0));
				if (position != -1) {
					contactList.setSelection(position);
				}
			}
		});
		ThreadPool.getInstance().getExecutorService().execute(run);
	}

	@Override
	public void onClickListener() {
		// TODO Auto-generated method stub
		topBar.setCallBack(new topBarCallBack() {
			@Override
			public void call_backBtnListener() {
				// TODO Auto-generated method stub
				super.call_backBtnListener();
				outFinish();
			}
		});
	}
	/**
	 * 该服务用于本地数据库中通信名单的获取
	 */
	public Runnable run = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			List<T_Contacts> list = contactDaoImpl.getAllContactsRecord();
			if (DataValidate.checkDataValid(list)) {
				Message msg = handler.obtainMessage();
				msg.obj = list;
				msg.what = EXCEPTION_CODE.CONTACT_OBT_SUCCESS;
				handler.sendMessage(msg);
			}
		}
	};
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			// 获取号码成功
			case EXCEPTION_CODE.CONTACT_OBT_SUCCESS:
				sortBeans = (ArrayList<T_Contacts>) msg.obj;
				if(!DataValidate.checkDataValid(sortBeans)){
					pageView.setBottomLayoutVisible(false).setVisibility(View.VISIBLE);
					return;
				}
				sortAdapter = new ContactsSortAdapter(context, sortBeans);
				// 设定关注按钮的监听
				sortAdapter
						.setOnItemFollowClickListener(itemFollowClitListener);
				contactList.setAdapter(sortAdapter);
				break;
			case FOLLOW_REFRESH:
				sortAdapter.updateListView(sortBeans);
				break;
			default:
				break;
			}
		};
	};
	/**
	 * 关注联系人好友的操作
	 */
	private onItemFollowClickListener itemFollowClitListener = new onItemFollowClickListener() {

		@Override
		public void click(int position) {
			// TODO Auto-generated method stub
			HandlePosition = position;
			ReqFollowBean bean = new ReqFollowBean();
			bean.setUid(DBUtils.getUid());
			bean.setBeUID(sortBeans.get(position).getUid());
			http.send(HttpMethod.POST, API.FOLLOW_USER,HttpUtils.convertBeanToParams(bean),new RequestCallBack<String>() {

				@Override
				public void onFailure(HttpException arg0, String arg1) {
					
				}

				@Override
				public void onSuccess(ResponseInfo<String> params) {
					new HttpResultService(params.result, new httpResultCallBack() {
						
						@Override
						public void onSuccess() {
//							friendsDaoImpl.updateFriendRelate(PARAMS.FOLLOW_OTHER,
//									sortBeans.get(HandlePosition).getUid());
							sortAdapter.updateListView(sortBeans);
						}
						
						@Override
						public void onRequestFail() {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void onFinally() {
							// TODO Auto-generated method stub
							
						}
					}, null, false);
				}
			});
		}
	};

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		AnimationUtil.finishOut2Right(context);
	}




	@Override
	public void outFinish() {
		// TODO Auto-generated method stub
		finish();
		AnimationUtil.finishOut2Right(context);
	}
}
