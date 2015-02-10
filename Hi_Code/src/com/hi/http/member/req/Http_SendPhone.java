package com.hi.http.member.req;

import com.hi.common.http.E_Http_Port;
import com.hi.http.base.Call_httpData;
import com.hi.http.base.HttpRequestClass;
import com.hi.http.member.model.Req_SendPhone;
import com.hi.module.base.callBack.httpResultCallBack;
import com.hi.service.HttpResultService;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

/**
 * 发送手机验证（判断是否已经注册，如果未注册，则可进入下一步操作）
 * @author MM_Zerui
 *
 */
public class Http_SendPhone extends HttpRequestClass<Req_SendPhone,Class>{
	private Req_SendPhone reqBean;
	public Http_SendPhone(Call_httpData<Class> callBack) {
		// TODO Auto-generated constructor stub
		this.call_normal=callBack;
		reqBean=new Req_SendPhone();
		
	}


	@Override
	public HttpRequestClass onParams(Req_SendPhone reqBean) {
		// TODO Auto-generated method stub
		this.reqBean=reqBean;
		return this;
	}

	@Override
	public HttpRequestClass onInit() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public HttpRequestClass onLoad() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public void onAction() {
		// TODO Auto-generated method stub
		httpAction(E_Http_Port.USER_SENDPHONE.value(), reqBean, new RequestCallBack<String>() {
			@Override
			public void onStart() {
				// TODO Auto-generated method stub
				super.onStart();
				call_normal.onStart();
			}
			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				new HttpResultService(arg0.result, new httpResultCallBack<Class>() {

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
