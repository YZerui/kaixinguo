package com.hi.http.store.req;

import com.hi.common.http.E_Http_Port;
import com.hi.http.base.Call_httpData;
import com.hi.http.base.HttpRequestClass;
import com.hi.http.store.model.Recv_StoreIfo;
import com.hi.http.store.model.Req_StoreIfo;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

/**
 * 获取店家信息
 * @author MM_Zerui
 *
 */
public class Http_GetStroreIfo extends HttpRequestClass<Req_StoreIfo,Recv_StoreIfo>{
	private Req_StoreIfo reqBean;
	private Recv_StoreIfo recvBean;
	public Http_GetStroreIfo(Call_httpData<Recv_StoreIfo> callBack) {
		// TODO Auto-generated constructor stub
		this.call_normal=callBack;
		reqBean=new Req_StoreIfo();
		recvBean=new Recv_StoreIfo();
	}


	@Override
	public HttpRequestClass onParams(Req_StoreIfo reqBean) {
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
		httpAction(E_Http_Port.COM_BUSINESS.value(), reqBean, new RequestCallBack<String>() {
			
			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				try {
					recvBean=recvBean.parse(arg0.result);
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
