package com.hi.http.member.req;

import com.hi.common.http.E_Http_Port;
import com.hi.http.base.Call_httpData;
import com.hi.http.base.HttpRequestClass;
import com.hi.http.member.model.Recv_UploadPhotos;
import com.hi.http.member.model.Req_UploadPhotos;
import com.hi.module.base.callBack.httpResultCallBack;
import com.hi.service.HttpResultService;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

/**
 * ÉÏ´«ÕÕÆ¬Ç½
 * @author MM_Zerui
 *
 */
public class Http_UploadPhotos extends HttpRequestClass<Req_UploadPhotos, Recv_UploadPhotos>{
	Req_UploadPhotos reqBean;
	public Http_UploadPhotos(Call_httpData<Recv_UploadPhotos> callBack) {
		// TODO Auto-generated constructor stub
		this.call_normal=callBack;
		
	}
	@Override
	public HttpRequestClass onParams(Req_UploadPhotos reqBean) {
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
		httpAction(E_Http_Port.USER_PHOTOUPLOAD.value(), reqBean,new RequestCallBack<String>() {
			
			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				new HttpResultService(arg0.result, new httpResultCallBack<Recv_UploadPhotos>() {
					@Override
					public void onData(boolean validity, Recv_UploadPhotos obj) {
						// TODO Auto-generated method stub
						super.onData(validity, obj);
						if(validity){
							call_normal.onSuccess(obj);
							call_normal.onFinally();
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
				}, Recv_UploadPhotos.class, false);
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
