package com.hi.module.store.ui;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RelativeLayout;

import com.android.ruifeng.hi.R;
import com.customview.callBack.pageCallBack;
import com.customview.callBack.topBarCallBack;
import com.customview.view.CustomPageView;

import com.customview.view.CustomTopbarView;
import com.hi.adapter.ShopItemAdapter;
import com.hi.adapter.ShopItemAdapter.onItemCommentClickListener;
import com.hi.common.API;
import com.hi.common.http.E_Http_StoreType;
import com.hi.dao.supImpl.Dao_SelfIfo;
import com.hi.http.base.Call_httpListData;
import com.hi.http.store.model.Recv_StoreList;
import com.hi.http.store.model.Req_StoreList;
import com.hi.http.store.req.Http_StoreList;
import com.hi.module.base.superClass.ListActivity;
import com.hi.module.store.model.RecvStoreCommentBean;
import com.hi.utils.AnimationUtil;
import com.hi.utils.DBUtils;
import com.hi.utils.ViewHandleUtils;
import com.hi.utils.network.HttpUtils;
import com.hi.view.ActionItem;
import com.hi.view.customLayout.TitlePopup;
import com.hi.view.customLayout.TitlePopup.OnItemOnClickListener;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.nhaarman.listviewanimations.swinginadapters.prepared.AlphaInAnimationAdapter;
import com.nhaarman.listviewanimations.swinginadapters.prepared.ScaleInAnimationAdapter;
import com.nhaarman.listviewanimations.swinginadapters.prepared.SwingBottomInAnimationAdapter;
import com.xlistview.XListView;
import com.xlistview.XListView.listHttpCallBack;

/**
 * 店铺列表
 * 
 * @author MM_Zerui
 */
public class ShopActivity extends ListActivity {
	private ShopItemAdapter adapter;
	private List<Recv_StoreList> items;
//	private TitlePopup titlePopup;
	private Req_StoreList reqBean;
	private Http_StoreList httpReq;

//	@ViewInject(R.id.store_seq_list_btn)
//	private RelativeLayout seqListBtn;
	@ViewInject(R.id.xListView)
	private XListView xListView;
	@ViewInject(R.id.pageView)
	private CustomPageView pageView;
	@ViewInject(R.id.topBar)
    private CustomTopbarView topBar;
	@ViewInject(R.id.leave_item_more)
	private View leaveMoreItem;
	// 动画工具
	private SwingBottomInAnimationAdapter swingBottomInAnimationAdapter;
	private AlphaInAnimationAdapter alphaInAnimationAdapter;
	private ScaleInAnimationAdapter scaleInAnimationAdapter;

	private boolean netError = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.shop_page_layout);
		com.lidroid.xutils.ViewUtils.inject(this);
		super.onCreate(savedInstanceState);
		initTitleBar();
	}

	@Override
	protected void obtainIntentValue() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setOnClickListener() {
		xListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				position=position-1;
				AnimationUtil.tab_in2LeftIntent(context, ShopDetailActivity.class,
						items.get(position).getId(), items.get(position)
								.getIslove());
			}
		});
	}

	@Override
	protected void initResource() {
        if(initBool){
            pageView.onProgressOnly().setVisibility(View.VISIBLE);
            initBool=false;
        }
		reqBean=new Req_StoreList();
	
		items = new ArrayList<Recv_StoreList>();
		adapter = new ShopItemAdapter(context);
		adapter.setOnItemCommentClickListener(onItemCommentClickListener);
		httpRequestMethod();
        topBar.setCallBack(new topBarCallBack() {
            @Override
            public void call_rightTextBtnListener() {
                super.call_rightTextBtnListener();
                //跳转到照片墙
                AnimationUtil.tab_in2LeftIntent(context,NearbyImgsActivity.class);

            }
        });
		xListView.setHttpCallBack(new listHttpCallBack() {
			@Override
			public void initListView() {
				// TODO Auto-generated method stub
				xListView.setMode(true, true);
				httpReq.onInit();
			}
			@Override
			public void onRefresh() {
				// TODO Auto-generated method stub
				httpReq.onInit();
			}
			
			@Override
			public void onLoadMore() {
				// TODO Auto-generated method stub
				httpReq.onLoad();
			}
			
			
		});

		pageView.setCallBack(new pageCallBack() {

			@Override
			public void onRefreshClick() {
				httpReq.onInit();
				pageView.progressRun();
			}
		});
	}

	private void httpRequestMethod() {
		// TODO Auto-generated method stub
		reqBean=new Req_StoreList();
		reqBean.setUid(Dao_SelfIfo.getInstance().getMid());
		httpReq=new Http_StoreList(new Call_httpListData<Recv_StoreList>() {
			
			@Override
			public void onStart() {
				// TODO Auto-generated method stub

				
			}
			
			@Override
			public void onLoad(List<Recv_StoreList> datas) {
				// TODO Auto-generated method stub
				
				items.addAll(datas);
				adapter.notifyDataSetChanged();
			}
			
			@Override
			public void onInit(List<Recv_StoreList> datas) {
				// TODO Auto-generated method stub
				pageView.setVisibility(View.GONE);
				items=datas;
				adapter.setData(items);
			
				swingBottomInAnimationAdapter = new SwingBottomInAnimationAdapter(
						adapter);
				swingBottomInAnimationAdapter
						.setAbsListView(xListView);
				swingBottomInAnimationAdapter
						.setInitialDelayMillis(300);
				xListView
						.setAdapter(swingBottomInAnimationAdapter);
			}
			
			@Override
			public void onFinally() {
				// TODO Auto-generated method stub
				xListView.onLoadStop();
			}
			
			@Override
			public void onFail() {
				// TODO Auto-generated method stub
				if(httpReq.isIfInit()){
					pageView.setDefaultPage().
						setErrorText("无法获取到商家信息哦").
						setBottomLayoutVisible(true).progressEnd().
						setVisibility(View.VISIBLE);
				}
				
			}
		});
		httpReq.onParams(reqBean);
	}

//	/**
//	 * 跳转到我的优惠劵列表
//	 *
//	 * @param v
//	 */
//	@OnClick(R.id.storePrivilegeBtn)
//	public void privilegeClick(View v) {
////		AnimationUtil.tab_in2LeftIntent(context, MyStorePrivilegePage.class);
//		AnimationUtil.tab_in2LeftIntent(context, MyPrivilegeActivity.class);
//	}

//	@OnClick(R.id.leave_item_more)
//	public void leaveMoreClick(View v){
//		toast.setText("跳转到留言列表页面");
//	}
//
//	@OnClick(R.id.fixIcon)
//	public void toCameraClick(View v){
//		toast.setText("跳转到拍照页面");
//	}

	@Override
	protected void outFinish() {
		// TODO Auto-generated method stub

	}

	/**
	 * 初始化界面顶栏
	 */
	private void initTitleBar() {

		// TODO Auto-generated method stub
//
//		titlePopup = new TitlePopup(this, LayoutParams.WRAP_CONTENT);
//		titlePopup.setAnimationStyle(R.style.popwin_anim_style);
//
//		seqListBtn.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				titlePopup.show(v);
//			}
//		});
//		// 给标题栏弹窗添加子类
//		titlePopup.addAction(new ActionItem(this, "喜欢/更新时间",
//				R.drawable.view_pager_indicator_white_selected));
//		titlePopup.addAction(new ActionItem(this, "朋友/距离",
//				R.drawable.view_pager_indicator_white_selected));
//		// 弹出栏的菜单选中时的触发
//		titlePopup.setItemOnClickListener(new OnItemOnClickListener() {
//
//			@Override
//			public void onItemClick(ActionItem item, int position) {
//				// TODO Auto-generated method stub
//				switch (position) {
//				// 喜欢、更新时间排序
//				case 0:
//					reqBean.setOrder(E_Http_StoreType.ORDER_INTEREST.toString());
//					httpReq.onParams(reqBean).onInit();
//					break;
//				// 朋友、距离排序
//				case 1:
//					reqBean.setOrder(E_Http_StoreType.ORDER_FRIEND.toString());
//					httpReq.onParams(reqBean).onInit();
//					break;
//				default:
//					break;
//				}
//			}
//		});
	}

	/**
	 * 店家喜欢按钮的触发事件
	 */
	private onItemCommentClickListener onItemCommentClickListener = new onItemCommentClickListener() {

		@Override
		public void click(int position, boolean ifLike) {
			// TODO Auto-generated method stub
			System.out.println("点击了" + position + "位置");
			StoreCommentMethod(position, ifLike);
		}

		/**
		 * 商家‘喜欢/取消喜欢’提交操作
		 */
		private void StoreCommentMethod(final int position, boolean ifLike) {
			// TODO Auto-generated method stub
			RecvStoreCommentBean bean = new RecvStoreCommentBean();
			bean.setBid(items.get(position).getId());
			bean.setUid(DBUtils.getUid());
			// 喜欢该店家
			if (ifLike) {
				http.send(HttpMethod.POST, API.STORE_LOVE,HttpUtils.convertBeanToParams(bean),new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub
						items.get(position).setIslove("0");
						swingBottomInAnimationAdapter.notifyDataSetChanged();
					}

					@Override
					public void onSuccess(ResponseInfo<String> params) {
						// TODO Auto-generated method stub
						items.get(position).setIslove("1");
						swingBottomInAnimationAdapter.notifyDataSetChanged();
					}
				});
			} else {
				http.send(HttpMethod.POST, API.STORE_LOVE_CANCEL,HttpUtils.convertBeanToParams(bean),new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub
						items.get(position).setIslove("1");
						swingBottomInAnimationAdapter.notifyDataSetChanged();
					}

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						// TODO Auto-generated method stub
						items.get(position).setIslove("0");
						swingBottomInAnimationAdapter.notifyDataSetChanged();
					}
				});
			}
		}
	};
	

}
