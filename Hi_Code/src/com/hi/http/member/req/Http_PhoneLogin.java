package com.hi.http.member.req;

import com.hi.common.http.E_Http_Port;
import com.hi.http.base.Call_httpData;
import com.hi.http.base.HttpRequestClass;
import com.hi.http.member.model.Recv_UserIfo;
import com.hi.http.member.model.Req_PhoneLogin;
import com.hi.module.base.callBack.httpResultCallBack;
import com.hi.service.HttpResultService;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

public class Http_PhoneLogin extends HttpRequestClass<Req_PhoneLogin,Recv_UserIfo>{
	private Req_PhoneLogin reqBean;
	private Recv_UserIfo recvBean;
	public Http_PhoneLogin(Call_httpData<Recv_UserIfo> callBack) {
		// TODO Auto-generated constructor stub
		this.call_normal=callBack;
		reqBean=new Req_PhoneLogin();
		recvBean=new Recv_UserIfo();
	}
	@Override
	public HttpRequestClass onParams(Req_PhoneLogin reqBean) {
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
		httpAction(E_Http_Port.USER_LOGIN.value(), reqBean,new RequestCallBack<String>() {
			@Override
			public void onStart() {
				// TODO Auto-generated method stub
				super.onStart();
				call_normal.onStart();
			}
			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				try {
					recvBean=Recv_UserIfo.parse(arg0.result);
					call_normal.onSuccess(recvBean);
					call_normal.onFinally();
					return;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				call_normal.onFail();
				call_normal.onFinally();
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
