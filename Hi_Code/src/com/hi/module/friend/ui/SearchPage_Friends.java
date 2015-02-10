package com.hi.module.friend.ui;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.exception.utils.P;
import com.format.utils.DataValidate;
import com.hi.adapter.FriItemAdapter;
import com.hi.common.param.Enum_Color;
import com.hi.dao.model.T_MyFriends;
import com.hi.dao.supImpl.Dao_Friends;
import com.hi.module.base.superClass.SearchBoxActivity;
import com.thread.HandlerExtend;
import com.thread.HandlerExtend.handleCallBack;
import com.thread.RunnableService;
import com.thread.callBack.runCallBack;

/**
 * 联系人搜寻页面
 * 
 * @author MM_Zerui
 * 
 */
public class SearchPage_Friends extends SearchBoxActivity {
	private List<T_MyFriends> list;
	private FriItemAdapter adapter;
	private List<T_MyFriends> filterList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
		callBack();
	}

	private void init() {
		editText.setHint("搜索搭讪好友(昵称/状态)");
		list = new ArrayList<T_MyFriends>();
		adapter = new FriItemAdapter(context);
		adapter.setData(list);
		localListView.setAdapter(adapter);
		list=Dao_Friends.getAllFriends();
	}

	private void callBack() {
		// TODO Auto-generated method stub
		cancelBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		localListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				
			}
		});
		editText.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				localLayout.setVisibility(View.VISIBLE);
				netLayout.setVisibility(View.GONE);
				overallLayout
						.setBackgroundColor(Enum_Color.WHITE.value());
				// 当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
				filterData(s.toString());
			}
			
			private void filterData(final String filterStr) {
				// TODO Auto-generated method stub
				new RunnableService(new runCallBack() {
					
					@Override
					public void start() {
						// TODO Auto-generated method stub
						filterList = new ArrayList<T_MyFriends>();

						if (TextUtils.isEmpty(filterStr)) {
							filterList = new ArrayList<T_MyFriends>();
						} else {
							filterList.clear();
							if (!DataValidate.checkDataValid(list)) {
								return;
							}
							for (T_MyFriends item : list) {
								try {
									String nickName=item.getNickName();
									String state=item.getCurrentState();
									if (ifMatch(nickName, filterStr)
											|| ifMatch(state, filterStr)) {
										filterList.add(item);
									}
								} catch (Exception e) {
									// TODO: handle exception
									P.v("检索错误");
								}
							}
						}
						
					}
					
					@Override
					public void end() {
						// TODO Auto-generated method stub
						handlerExtend.onInitView();
					}
				}, true);
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	private HandlerExtend handlerExtend=new HandlerExtend(new handleCallBack() {
		
		@Override
		public void call_onLoad() {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void call_onInit() {
			// TODO Auto-generated method stub
			if (!DataValidate.checkDataValid(filterList)) {
				localResultNote.setVisibility(View.VISIBLE);
				localListView.setVisibility(View.GONE);
			} else {
				localResultNote.setVisibility(View.GONE);
				localListView.setVisibility(View.VISIBLE);
			}
			// 根据a-z进行排序
			adapter.setData(filterList);
			adapter.notifyDataSetChanged();
		}
	});
}
