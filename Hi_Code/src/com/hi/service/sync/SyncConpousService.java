package com.hi.service.sync;

import java.util.ArrayList;
import java.util.List;

import com.exception.utils.P;
import com.format.utils.DataValidate;
import com.hi.dao.model.T_Coupons;
import com.hi.dao.supImpl.Dao_Coupons;
import com.hi.dao.supImpl.Dao_SelfIfo;
import com.hi.http.base.Call_httpData;
import com.hi.http.base.Call_httpListData;
import com.hi.http.coupons.model.Recv_CouponsList;
import com.hi.http.coupons.model.Req_CouponsList;
import com.hi.http.coupons.req.Http_GetCoupons;
import com.hi.http.coupons.req.Http_StoreCoupons;
import com.hi.http.store.model.Recv_StoreList;
import com.hi.http.store.model.Req_StoreList;
import com.hi.http.store.req.Http_StoreList;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * 同步优惠劵的服务
 * 
 * @author MM_Zerui
 * 
 */
public class SyncConpousService extends Service {
	private List<Recv_StoreList> storeList = new ArrayList<Recv_StoreList>();
	private List<T_Coupons> couponsList = new ArrayList<T_Coupons>();
	private Http_StoreList httpReq;
	private Http_StoreCoupons httpReqCoupons;
	private Req_CouponsList reqBeanCoupons;
	private int i = 0;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		httpStoreMethod();
	}

	private void httpStoreMethod() {
		// TODO Auto-generated method stub
		Req_StoreList reqBean = new Req_StoreList();
		reqBean.setUid(Dao_SelfIfo.getInstance().getMid());
		httpReq = new Http_StoreList(new Call_httpListData<Recv_StoreList>() {

			@Override
			public void onStart() {
				// TODO Auto-generated method stub

			}

			@Override
			public void onInit(List<Recv_StoreList> datas) {
				// TODO Auto-generated method stub
				storeList.addAll(datas);
				httpReq.onLoad();
			}

			@Override
			public void onLoad(List<Recv_StoreList> datas) {
				// TODO Auto-generated method stub
				storeList.addAll(datas);
				httpReq.onLoad();
			}

			@Override
			public void onFinally() {
				// TODO Auto-generated method stub
//				try {
					httpCouponsMethod();
//				} catch (Exception e) {
//					// TODO: handle exception
//					P.v("同步优惠劵信息错误" + e.getMessage());
//				}
			}

			@Override
			public void onFail() {
				// TODO Auto-generated method stub

			}
		});
		httpReq.onParams(reqBean).onInit();
	}

	private void httpCouponsMethod() {
		reqBeanCoupons = new Req_CouponsList();
		if(i>=storeList.size()){
			stopSelf();
			return;
		}
		httpReqCoupons = new Http_StoreCoupons(
				new Call_httpListData<T_Coupons>() {

					@Override
					public void onStart() {
						// TODO Auto-generated method stub

					}

					@Override
					public void onInit(List<T_Coupons> datas) {
						couponsList.addAll(datas);
						httpReqCoupons.onLoad();
					}

					@Override
					public void onLoad(List<T_Coupons> datas) {
						couponsList.addAll(datas);
						httpReqCoupons.onLoad();
					}

					@Override
					public void onFinally() {
						// TODO Auto-generated method stub

					}

					@Override
					public void onFail() {
						// TODO Auto-generated method stub
						if (i >= storeList.size()) {
							// 本地数据的处理
							dbCouponsMethod();
							// 结束获取
							stopSelf();
							return;
						}
//						try {
							reqBeanCoupons.setBid(storeList.get(i++).getId());
							httpReqCoupons.onParams(reqBeanCoupons).onInit();
//						} catch (Exception e) {
//							// TODO: handle exception
//							stopSelf();
//						}
					}

				});
		reqBeanCoupons.setBid(storeList.get(i++).getId());
		httpReqCoupons.onParams(reqBeanCoupons).onInit();
	
		
	}

	/**
	 * 优惠劵本地同步操作
	 * 
	 * @步骤 如果优惠劵存在 则跳过
	 * @步骤 如果不存在则插入
	 */
	private void dbCouponsMethod() {
		// TODO Auto-generated method stub
		for (T_Coupons item : couponsList) {
			Dao_Coupons.addCoupons(item);
		}
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		P.v("同步优惠劵结束");
		super.onDestroy();
	}

}
