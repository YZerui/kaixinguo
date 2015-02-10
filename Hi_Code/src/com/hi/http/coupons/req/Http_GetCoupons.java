package com.hi.http.coupons.req;

import com.hi.common.http.E_Http_Port;
import com.hi.http.base.Call_httpData;
import com.hi.http.base.HttpRequestClass;
import com.hi.http.coupons.model.Req_CouponsObt;
import com.hi.module.base.callBack.httpResultCallBack;
import com.hi.service.HttpResultService;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

/**
 * ªÒ»°”≈ª›Ñª
 * @author MM_Zerui
 *
 */
public class Http_GetCoupons extends HttpRequestClass<Req_CouponsObt, Class<?>>{
	private Req_CouponsObt reqBean;
	public Http_GetCoupons(Call_httpData<Class<?>> callBack) {
		this.call_normal=callBack;
	}
	@Override
	public HttpRequestClass onParams(Req_CouponsObt reqBean) {
		// TODO Auto-generated method stub
		this.reqBean=reqBean;	
		return this;
	}

	@Override
	public void onAction() {
		// TODO Auto-generated method stub
		httpAction(E_Http_Port.CONPOUS_OBTAIN.value(), reqBean, new RequestCallBack<String>() {
			
			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				new HttpResultService(arg0.result, new httpResultCallBack<Class<?>>() {

					@Override
					public void onRequestFail() {
						call_normal.onFail();
					}

					@Override
					public void onSuccess() {
						call_normal.onSuccess(null);
					}

					@Override
					public void onFinally() {
						call_normal.onFinally();
					}
				}, null, false);
			}
			
			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				call_normal.onFail();
				call_normal.onFinally();
			}
		});
	}
	
}
