package com.hi.http.member.req;

import com.hi.common.http.E_Http_Port;
import com.hi.http.base.Call_httpData;
import com.hi.http.base.HttpRequestClass;
import com.hi.http.member.model.Recv_PhoneRegi;
import com.hi.http.member.model.Req_PhoneRegi;
import com.hi.module.base.callBack.httpResultCallBack;
import com.hi.service.HttpResultService;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

/**
 * ÊÖ»ú×¢²á
 * @author MM_Zerui
 *
 */
public class Http_PhoneRegi extends HttpRequestClass<Req_PhoneRegi,Recv_PhoneRegi>{
	private Req_PhoneRegi reqBean;
	public Http_PhoneRegi(Call_httpData<Recv_PhoneRegi> callBack) {
		// TODO Auto-generated constructor stub
		this.call_normal=callBack;
		reqBean=new Req_PhoneRegi();
	}
	
	@Override
	public HttpRequestClass onParams(Req_PhoneRegi reqBean) {
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
		httpAction(E_Http_Port.USER_PHONEREGI.value(), reqBean, new RequestCallBack<String>() {
			@Override
			public void onStart() {
				// TODO Auto-generated method stub
				super.onStart();
				call_normal.onStart();
			}
			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				new HttpResultService(arg0.result, new httpResultCallBack<Recv_PhoneRegi>() {
					@Override
					public void onData(boolean validity, Recv_PhoneRegi obj) {
						// TODO Auto-generated method stub
						super.onData(validity, obj);
						if(validity){
							call_normal.onSuccess(obj);
							return;
						}
						call_normal.onFail();
					}
					@Override
					public void onSuccess() {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void onRequestFail() {
						// TODO Auto-generated method stub
						call_normal.onFail();
					}


					@Override
					public void onFinally() {
						// TODO Auto-generated method stub
						call_normal.onFinally();
					}
				}, Recv_PhoneRegi.class,false);
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
