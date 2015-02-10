package com.hi.http.coupons.req;

import java.util.List;

import com.hi.common.http.E_Http_Port;
import com.hi.common.param.Enum_ListLimit;
import com.hi.http.base.Call_httpListData;
import com.hi.http.base.HttpRequestClass;
import com.hi.http.coupons.model.Recv_CouponsList;
import com.hi.http.coupons.model.Recv_CouponsSelf;
import com.hi.http.coupons.model.Req_CouponsList;
import com.hi.http.coupons.model.Req_CouponsSelf;
import com.hi.module.base.callBack.httpResultCallBack;
import com.hi.service.HttpResultService;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

/**
 * 查看自己拥有的优惠劵
 * @author MM_Zerui
 *
 */
public class Http_MyCoupons extends HttpRequestClass<Req_CouponsSelf, Recv_CouponsSelf>{
	private Req_CouponsSelf reqBean;
	public Http_MyCoupons(Call_httpListData<Recv_CouponsSelf> callBack) {
		// TODO Auto-generated constructor stub
		this.call_list=callBack;
	}
	@Override
	public HttpRequestClass onParams(Req_CouponsSelf reqBean) {
		// TODO Auto-generated method stub
		this.reqBean=reqBean;
		return this;
	}
	@Override
	public HttpRequestClass onInit() {
		// TODO Auto-generated method stub
		super.onInit();
		reqBean.setBegin(beginInit());
		reqBean.setLimit(Enum_ListLimit.COUPONS.toString());
		return this;
	}
	@Override
	public HttpRequestClass onLoad() {
		// TODO Auto-generated method stub
		super.onLoad();
		reqBean.setBegin(beginLoad(Enum_ListLimit.COUPONS.value()));
		return this;
	}
	@Override
	public void onAction() {
		// TODO Auto-generated method stub
		httpAction(E_Http_Port.CONPOUS_SELFLIST.value(), reqBean, new RequestCallBack<String>() {
			
			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				new HttpResultService(arg0.result, new httpResultCallBack<Recv_CouponsSelf>() {
					@Override
					public void onListData(boolean validity,
							List<Recv_CouponsSelf> listDatas) {
						// TODO Auto-generated method stub
						super.onListData(validity, listDatas);
						if(validity){
							if(isIfInit()){
								call_list.onInit(listDatas);
							}else {
								call_list.onLoad(listDatas);
							}
							return;
						}
						call_list.onFail();
					}
					@Override
					public void onRequestFail() {
						// TODO Auto-generated method stub
						call_list.onFail();
					}

					@Override
					public void onSuccess() {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onFinally() {
						// TODO Auto-generated method stub
						call_list.onFinally();
					}
				}, Recv_CouponsSelf.class,true);
			}
			
			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				call_list.onFail();
				call_list.onFinally();
			}
		});
	}

}
