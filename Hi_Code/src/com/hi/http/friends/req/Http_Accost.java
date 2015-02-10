package com.hi.http.friends.req;

import com.hi.common.http.E_Http_Port;
import com.hi.http.base.Call_httpData;
import com.hi.http.base.HttpRequestClass;
import com.hi.http.friends.model.Recv_Accost;
import com.hi.http.friends.model.Req_Accost;
import com.hi.module.base.callBack.httpResultCallBack;
import com.hi.service.HttpResultService;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

/**
 * ´îÚ¨
 * @author MM_Zerui
 *
 */
public class Http_Accost extends HttpRequestClass<Req_Accost,Recv_Accost>{
	private Req_Accost reqBean;
	public Http_Accost(Call_httpData<Recv_Accost> callBack) {
		// TODO Auto-generated constructor stub
		this.call_normal=callBack;
	}
	@Override
	public HttpRequestClass onParams(Req_Accost reqBean) {
		// TODO Auto-generated method stub
		this.reqBean=reqBean;
		return this;
	}

	@Override
	public void onAction() {
		// TODO Auto-generated method stub
		httpAction(E_Http_Port.FRIEND_ACCOST.value(), reqBean, new RequestCallBack<String>() {
			
			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				new HttpResultService(arg0.result, new httpResultCallBack<Recv_Accost>() {
					@Override
					public void onData(boolean validity, Recv_Accost obj) {
						// TODO Auto-generated method stub
						super.onData(validity, obj);
						if(validity){
							call_normal.onSuccess(obj);
							return;
						}
						call_normal.onFail();
					}
					@Override
					public void onRequestFail() {
						// TODO Auto-generated method stub
						call_normal.onFail();
					}

					@Override
					public void onSuccess() {
						// TODO Auto-generated method stub
					}

					@Override
					public void onFinally() {
						// TODO Auto-generated method stub
						call_normal.onFinally();
					}
				},Recv_Accost.class, false);
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
