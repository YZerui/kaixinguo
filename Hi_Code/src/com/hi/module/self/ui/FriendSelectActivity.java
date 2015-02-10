package com.hi.module.self.ui;

import java.util.ArrayList;
import java.util.Collections;

import org.json.JSONObject;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.ruifeng.hi.R;
//import com.hi.adapter.FriendSelectAdapter;
import com.hi.adapter.HorizontalImgSelectAdapter;
//import com.hi.adapter.FriendSelectAdapter.onListItemClickListener;
import com.hi.common.API;
import com.hi.module.base.application.ThreadPool;
import com.hi.module.base.superClass.ListActivity;
import com.hi.module.friend.model.RecvFriendsBean;
import com.hi.module.friend.model.ReqFriendsBean;
import com.hi.utils.AnimationUtil;
import com.hi.utils.CharacterParser;
import com.hi.utils.DBUtils;
import com.hi.utils.ParseJson;
import com.hi.utils.PinyinComparator2;
import com.hi.view.customWidget.ClearEditText;



/**
 * @author MM_Zerui
 *	选择朋友的页面
 */
//public class FriendSelectActivity extends ListActivity{
//	private final static int FRIEND_REQUEST_RESULT=100;
//	public final static String FRIEND_SELECT_NOTE="FRIEND_SELECT";
//	public final static String FRIEND_UNSELECT_NOTE="FRIEND_UNSELECT";
//	
//	public Boolean ifFilter=false;//判断是否处于过滤状态
//	
//	private ArrayList<RecvFriendsBean> recvList;
//	private ArrayList<RecvFriendsBean> selectList;
//	ArrayList<RecvFriendsBean> filterList;
//	private ListView friListView;
//	private HorizontalListView selectListView;
//	private FriendSelectAdapter selectAdapter;
//	private HorizontalImgSelectAdapter imgSelectAdapter;
//	
//	private TextView selectNumNote;
//	
//	private ClearEditText filterEditText;
//	/**
//	 * 汉字转换成拼音的类
//	 */
//	private CharacterParser characterParser;
//	/**
//	 * 根据拼音来排列ListView里面的数据类
//	 */
//	private PinyinComparator2 pinyinComparator;
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		// TODO Auto-generated method stub
//		setContentView(R.layout.activity_friend_select_layout);
//		super.onCreate(savedInstanceState);
////		requestMethod();
//	}
//	@Override
//	public void obtainIntentValue() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void findViewById() {
//		// TODO Auto-generated method stub
//		friListView=(ListView)findViewById(R.id.friListView);
//		selectListView=(HorizontalListView)findViewById(R.id.select_imgList);
//		filterEditText=(ClearEditText)findViewById(R.id.filter_edit);
//		selectNumNote=(TextView)findViewById(R.id.select_num_note);
//	}
//
//	@Override
//	public void initResource() {
//		// TODO Auto-generated method stub
//		selectAdapter=new FriendSelectAdapter(context);
//		selectAdapter.setOnListItemClickListener(listItemClickListener);
//		imgSelectAdapter=new HorizontalImgSelectAdapter(context);
//		
//		pinyinComparator = new PinyinComparator2();
//		// 实例化汉字转拼音类
//		characterParser = CharacterParser.getInstance();
//	}
//
//	@Override
//	public void onClick(View v) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void initParams() {
//		// TODO Auto-generated method stub
//		getWindow().setSoftInputMode(
//				WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
//		filterEditText.addTextChangedListener(new TextWatcher() {
//
//			@Override
//			public void onTextChanged(CharSequence s, int start, int before,
//					int count) {
//				// TODO Auto-generated method stub
//				// 当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
//				filterData(s.toString());
//			}
//
//			@Override
//			public void beforeTextChanged(CharSequence s, int start, int count,
//					int after) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void afterTextChanged(Editable s) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
//		RecvFriendsBean defaultBean=new RecvFriendsBean();
//		defaultBean.setHead("DEFAULT_IMG");
//		selectList=new ArrayList<RecvFriendsBean>();
//		selectList.add(defaultBean);
//		imgSelectAdapter.setListItems(selectList);
//		selectListView.setAdapter(imgSelectAdapter);
//	}
//	/**
//	 * 根据输入框中的值来过滤数据并更新ListView
//	 * 
//	 * @param filterStr
//	 */
//	private void filterData(String filterStr) {
//		filterList = new ArrayList<RecvFriendsBean>();
//
//		if (TextUtils.isEmpty(filterStr)) {
//			filterList = recvList;
//			ifFilter=false;
//		} else {
//			ifFilter=true;
//			filterList.clear();
//			for (RecvFriendsBean item : recvList) {
//				String name = item.getNickName();
//				if (name.indexOf(filterStr.toString()) != -1
//						|| characterParser.getSelling(name).startsWith(
//								filterStr.toString())) {
//					filterList.add(item);
//				}
//			}
//		}
//		// 根据a-z进行排序
////		Collections.sort(filterList, pinyinComparator);
//		
//		selectAdapter.setListItems(filterList);
//		selectAdapter.notifyDataSetChanged();
//	}
//	@Override
//	public void requestMethod() {
//		// TODO Auto-generated method stub
//		ReqFriendsBean reqBean = new ReqFriendsBean();
//		reqBean.setUid(DBUtils.getUid());
//		HttpService service = new HttpService(API.FOLLOWER_LIST, reqBean,
//				handler,FRIEND_REQUEST_RESULT);
////		new Thread(service).start();
//		ThreadPool.getInstance().getExecutorService().execute(service);
//	}
//	@Override
//	protected void setOnClickListener() {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	protected void requestLoadMethod(String note) {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	protected void getRefreshData(Object obj) {
//		// TODO Auto-generated method stub
//		if (recvList != null) {
//			recvList.clear();
//		}
//		recvList = ParseJson.parseLocalJsonArray((JSONObject) obj,
//				RecvFriendsBean.class);
//		
//	
//		if (recvList.size()==0) {
////			switchToDefaultPage(R.drawable.default_friend_page);
//			return;
//		}
//		selectAdapter.setListItems(recvList);
//		friListView.setAdapter(selectAdapter);
//	}
//	@Override
//	protected void getLoadData(Object obj) {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public void handleMethod() {
//		// TODO Auto-generated method stub
//		httpListener=new HttpListener() {
//			
//			@Override
//			public void requestSuccess(Object obj, int... params) {
//				// TODO Auto-generated method stub
//				switch (params[0]) {
//				case FRIEND_REQUEST_RESULT:
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
//				
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
//				
//			}
//			
//			@Override
//			public void finalMethod() {
//				// TODO Auto-generated method stub
//				
//			}
//		};
//	}
//	public onListItemClickListener listItemClickListener=new onListItemClickListener() {
//		
//		@Override
//		public void click(Boolean item, int position) {
//			// TODO Auto-generated method stub
//			//如果选中,则提取选择的
//			if(item){
//				
//				if(ifFilter){
//					if(filterList.get(position)!=null&&selectList.contains(filterList.get(position))){
//						return;
//					}
//					filterList.get(position).setType(FRIEND_SELECT_NOTE);
//					selectList.add(0,filterList.get(position));		
//				}else {
//					if(recvList.get(position)!=null&&selectList.contains(recvList.get(position))){
//						return;
//					}
//					recvList.get(position).setType(FRIEND_SELECT_NOTE);
//					selectList.add(0,recvList.get(position));	
//				}			
//				imgSelectAdapter.notifyDataSetChanged();
//			}else {
//				if(ifFilter){
//					filterList.get(position).setType(FRIEND_UNSELECT_NOTE);
//					selectList.remove(filterList.get(position));
//				}else {
//					recvList.get(position).setType(FRIEND_UNSELECT_NOTE);
//					selectList.remove(recvList.get(position));
//				}		
//				imgSelectAdapter.notifyDataSetChanged();
//			}
//			
//			//更新选择数量
//			int selectNum=selectList.size()-1;
//			selectNumNote.setText("已选"+selectNum+"人");
//		}
//	};
//	@Override
//	public void outFinish() {
//		// TODO Auto-generated method stub
//		finish();
//		AnimationUtil.finishOut2Bottom(context);
//	}
//	@Override
//	public void onBackPressed() {
//		// TODO Auto-generated method stub
//		super.onBackPressed();
//		outFinish();
//	}
//}
