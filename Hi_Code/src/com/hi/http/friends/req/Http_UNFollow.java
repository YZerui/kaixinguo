package com.hi.http.friends.req;

import com.hi.common.http.E_Http_Port;
import com.hi.http.base.Call_httpData;
import com.hi.http.base.HttpRequestClass;
import com.hi.http.friends.model.Req_UNFollow;
import com.hi.module.base.callBack.httpResultCallBack;
import com.hi.service.HttpResultService;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

/**
 * È¡Ïû¹Ø×¢
 * @author MM_Zerui
 *
 */
public class Http_UNFollow extends HttpRequestClass<Req_UNFollow, Class<?>>{
	private Req_UNFollow reqBean;
	public Http_UNFollow(Call_httpData<Class<?>> callBack) {
		// TODO Auto-generated constructor stub
		this.call_normal=callBack;
	}
	@Override
	public HttpRequestClass onParams(Req_UNFollow reqBean) {
		// TODO Auto-generated method stub
		this.reqBean=reqBean;
		return this;
	}

	@Override
	public void onAction() {
		// TODO Auto-generated method stub
		httpAction(E_Http_Port.FRIEND_UNFOLLOW.value(), reqBean, new RequestCallBack<String>() {
			
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
