package com.hi.http.store.req;

import com.hi.common.http.E_Http_Port;
import com.hi.http.base.Call_httpData;
import com.hi.http.base.HttpRequestClass;
import com.hi.http.store.model.Req_StoreRemark;
import com.hi.module.base.callBack.httpResultCallBack;
import com.hi.service.HttpResultService;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

/**
 * 店家评价（感兴趣/非感兴趣）
 * @author MM_Zerui
 *
 */
public class Http_StoreRemark extends HttpRequestClass<Req_StoreRemark,Class<?>>{
	private Req_StoreRemark reqBean;
	private String url;
	public Http_StoreRemark(Call_httpData<Class<?>> callBack) {
		// TODO Auto-generated constructor stub
		this.call_normal=callBack;
	}
	@Override
	public HttpRequestClass onParams(Req_StoreRemark reqBean) {
		// TODO Auto-generated method stub
		this.reqBean=reqBean;
		return this;
	}
	public HttpRequestClass onType(StoreRemarkType enumType){
		switch (enumType) {
		case INTEREST:
			url=E_Http_Port.STORE_INTEREST.value();
			break;
		case INTEREST_CANCEL:
			url=E_Http_Port.STORE_CANCELINTEREST.value();
			break;
		case UNINTEREST:
			url=E_Http_Port.STORE_UNINTEREST.value();
			break;
		case UNINTEREST_CANCEL:
			url=E_Http_Port.STORE_CANCELUNINTEREST.value();
			break;
		default:
			break;
		}
		return this;
	}

	@Override
	public void onAction() {
		// TODO Auto-generated method stub
		httpAction(url, reqBean, new RequestCallBack<String>() {
			
			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				new HttpResultService(arg0.result, new httpResultCallBack<Class<?>>() {

					@Override
					public void onRequestFail() {
						// TODO Auto-generated method stub
						call_normal.onFail();
					}

					@Override
					public void onSuccess() {
						// TODO Auto-generated method stub
						call_normal.onSuccess(null);
					}

					@Override
					public void onFinally() {
						// TODO Auto-generated method stub
						call_normal.onFinally();
					}
				}, null,false);
			}
			
			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				call_normal.onFail();
				call_normal.onFinally();
			}
		});
	}
	public static enum StoreRemarkType{
		INTEREST,
		UNINTEREST,
		INTEREST_CANCEL,
		UNINTEREST_CANCEL;
	}
}
