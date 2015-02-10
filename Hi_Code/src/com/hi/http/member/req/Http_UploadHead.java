package com.hi.http.member.req;

import com.hi.common.http.E_Http_Port;
import com.hi.http.base.Call_httpData;
import com.hi.http.base.HttpRequestClass;
import com.hi.http.member.model.Recv_UploadHead;
import com.hi.http.member.model.Req_UploadHead;
import com.hi.module.base.callBack.httpResultCallBack;
import com.hi.service.HttpResultService;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

/**
 * 上传用户头像
 * @author MM_Zerui
 *
 */
public class Http_UploadHead extends HttpRequestClass<Req_UploadHead, Recv_UploadHead>{
	private Req_UploadHead reqBean;
	public Http_UploadHead(Call_httpData<Recv_UploadHead> callBack) {
		// TODO Auto-generated constructor stub
		this.call_normal=callBack;
	}
	@Override
	public HttpRequestClass onParams(Req_UploadHead reqBean) {
		// TODO Auto-generated method stub
		this.reqBean=reqBean;
		return this;
	}


	@Override
	public void onAction() {
		// TODO Auto-generated method stub
		httpAction(E_Http_Port.USER_HEADUPDATE.value(), reqBean, new RequestCallBack<String>() {
			
			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				new HttpResultService(arg0.result, new httpResultCallBack<Recv_UploadHead>() {
					@Override
					public void onData(boolean validity, Recv_UploadHead obj) {
						// TODO Auto-generated method stub
						super.onData(validity, obj);
						if(validity){
							call_normal.onSuccess(obj);
							return;
						}
						call_normal.onFail();
						return;
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
				},Recv_UploadHead.class, false);
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
