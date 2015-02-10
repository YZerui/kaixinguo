package com.hi.module.locale.ui.leavenote;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.android.ruifeng.hi.R;
import com.customview.callBack.topBarCallBack;
import com.customview.view.CustomBarView;
import com.customview.view.CustomPageView;
import com.customview.view.CustomTopbarView;
import com.hi.adapter.LeaveNotePraiseAdapter;
import com.hi.http.base.Call_httpListData;
import com.hi.http.local.model.Recv_praiseList;
import com.hi.http.local.model.Req_praiseList;
import com.hi.http.local.req.Http_praiseList;
import com.hi.module.base.superClass.NormalActivity;
import com.hi.module.locale.ui.UserDetailFragmentActivity;
import com.hi.utils.AnimationUtil;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.thread.HandlerExtend;
import com.thread.HandlerExtend.handleCallBack;

/**
 * 查看某留言的所有点赞列表
 * @author MM_Zerui
 *
 */
public class LeaveNotePraisePage extends NormalActivity{
	private LeaveNotePraiseAdapter adapter;
	private Req_praiseList reqBean;
	private String mwID;
	private List<Recv_praiseList> listDatas=new ArrayList<Recv_praiseList>();
	@ViewInject(R.id.gridView)
	private GridView gridView;
	@ViewInject(R.id.topBar)
	private CustomTopbarView topBar;
	@ViewInject(R.id.pageView)
	private CustomPageView pageView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.leave_note_praise_page);
		ViewUtils.inject(this);
		super.onCreate(savedInstanceState);
	
	}
	@Override
	public void obtainIntentValue() {
		// TODO Auto-generated method stub
		mwID=getIntent().getStringExtra("DATA0");
	}

	@Override
	public void initResource() {
		// TODO Auto-generated method stub
		reqBean=new Req_praiseList();
		reqBean.setMwID(mwID);
		adapter=new LeaveNotePraiseAdapter(context);
		new Http_praiseList(new Call_httpListData<Recv_praiseList>(){

			@Override
			public void onStart() {
				// TODO Auto-generated method stub
				topBar.setProVisibility(true);
			}

			@Override
			public void onInit(List<Recv_praiseList> datas) {
				// TODO Auto-generated method stub
				listDatas=datas;
				adapter.setDatas(listDatas);
				handlerExtend.onInitView();
			}

			@Override
			public void onLoad(List<Recv_praiseList> datas) {
				// TODO Auto-generated method stub
				listDatas.addAll(datas);
				handlerExtend.onLoadView();
			}

			@Override
			public void onFail() {
				// TODO Auto-generated method stub
				handlerExtend.onFail();
			}

			@Override
			public void onFinally() {
				// TODO Auto-generated method stub
				handlerExtend.onFinally();
			}
			
		}).onParams(reqBean).onInit();
	}
	HandlerExtend handlerExtend=new HandlerExtend(new handleCallBack() {
		@Override
		public void call_onInit() {
			// TODO Auto-generated method stub
			gridView.setAdapter(adapter);
		}
		@Override
		public void call_onLoad() {
			// TODO Auto-generated method stub
			adapter.notifyDataSetChanged();
		}
		public void call_onFail() {
			pageView.setTextOnly("还木有小伙伴点赞哦").
				setBottomLayoutVisible(false).setVisibility(View.VISIBLE);
		};
		public void call_onFinally() {
			topBar.setProVisibility(false);
		};
		
	});
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
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				Recv_praiseList recvBean=listDatas.get(position);
				AnimationUtil.in2LeftIntent(context, UserDetailFragmentActivity.class,
						recvBean.getMid(),recvBean.getHead(),recvBean.getNickName());
			}
		});
	}

	@Override
	public void outFinish() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
		AnimationUtil.finishOut2Right(context);
	}
	
}
