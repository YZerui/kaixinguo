package com.hi.http.member.req;

import com.hi.common.http.E_Http_Port;
import com.hi.http.base.Call_httpData;
import com.hi.http.base.HttpRequestClass;
import com.hi.http.member.model.Recv_UserIfo;
import com.hi.http.member.model.Req_ThirdLogin;
import com.hi.module.base.callBack.httpResultCallBack;
import com.hi.service.HttpResultService;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

/**
 * µÚÈý·½µÇÂ¼
 * @author MM_Zerui
 *
 */
public class Http_LoginThird extends HttpRequestClass<Req_ThirdLogin,Recv_UserIfo>{
	private Req_ThirdLogin reqBean;
	public Http_LoginThird(Call_httpData<Recv_UserIfo> callBack) {
		this.call_normal=callBack;
	}
	@Override
	public HttpRequestClass onParams(Req_ThirdLogin reqBean) {
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
		httpAction(E_Http_Port.USER_THIRDLOGIN.value(),reqBean, new RequestCallBack<String>() {
			
			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				try {
					Recv_UserIfo recvBean=Recv_UserIfo.parse(arg0.result);
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
