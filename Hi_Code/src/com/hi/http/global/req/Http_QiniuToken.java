package com.hi.http.global.req;

import com.hi.common.http.E_Http_Port;
import com.hi.common.http.E_Http_QNType;
import com.hi.http.base.Call_httpData;
import com.hi.http.base.HttpRequestClass;
import com.hi.http.global.model.Req_QiniuToken;
import com.hi.module.base.callBack.httpResultCallBack;
import com.hi.service.HttpResultService;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

/**
 * 获取七牛资源上传的Token
 * @author MM_Zerui
 *
 */
public class Http_QiniuToken extends HttpRequestClass<Req_QiniuToken,String>{
    private Req_QiniuToken reqBean;
	public Http_QiniuToken(Call_httpData<String> callBack) {
		// TODO Auto-generated constructor stub
		this.call_normal=callBack;
        reqBean=new Req_QiniuToken();
        //默认模式
        reqBean.setQn_t(E_Http_QNType.NORMAL.toString());
	}
    public Http_QiniuToken setType(E_Http_QNType typeEnum){
        reqBean.setQn_t(typeEnum.toString());
        return this;
    }
	@Override
	public HttpRequestClass onParams(Req_QiniuToken reqBean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onAction() {
		// TODO Auto-generated method stub
		httpAction(E_Http_Port.QINIU_TOKEN.value(),reqBean, new RequestCallBack<String>() {
			@Override
			public void onStart() {
				// TODO Auto-generated method stub
				super.onStart();
				call_normal.onStart();
			}
			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				call_normal.onFail();
				call_normal.onFinally();
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				new HttpResultService(arg0.result, new httpResultCallBack<String>() {
					@Override
					public void onData(boolean validity, String obj) {
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
				}, null,false);
				
			}
		});
	}

}
