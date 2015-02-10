package com.hi.http.member.req;

import com.hi.common.http.E_Http_Port;
import com.hi.dao.supImpl.Dao_SelfIfo;
import com.hi.http.base.Call_httpData;
import com.hi.http.base.HttpRequestClass;
import com.hi.http.member.model.Recv_UserIfo;
import com.hi.http.member.model.Req_UserIfo;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

/**
 * 获取用户信息
 * @author MM_Zerui
 *
 */
public class Http_GetUserIfo extends HttpRequestClass<Req_UserIfo,Recv_UserIfo>{
//	private Call_httpData<Recv_UserIfo> callBack;
	private Req_UserIfo reqBean=new Req_UserIfo();
	private Recv_UserIfo recvBean=new Recv_UserIfo();
	public Http_GetUserIfo(Call_httpData<Recv_UserIfo> callBack) {
		this.call_normal=callBack;
		reqBean=new Req_UserIfo(); 
	}

	@Override
	public HttpRequestClass onParams(Req_UserIfo reqBean) {
		this.reqBean=reqBean;
		return this;
	}
	@Override
	public HttpRequestClass onInit() {
		return this;
	}

	@Override
	public HttpRequestClass onLoad() {
		return this;
	}

	@Override
	public void onAction() {
		httpAction(E_Http_Port.COM_MEMBER.value(), reqBean, new RequestCallBack<String>() {
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
