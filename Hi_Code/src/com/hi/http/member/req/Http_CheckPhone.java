package com.hi.http.member.req;

import java.util.List;

import com.hi.common.http.E_Http_Port;
import com.hi.http.base.Call_httpListData;
import com.hi.http.base.HttpRequestClass;
import com.hi.http.member.model.Recv_CheckPhone;
import com.hi.http.member.model.Req_CheckPhone;
import com.hi.module.base.callBack.httpResultCallBack;
import com.hi.service.HttpResultService;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

/**
 * 检测通讯录的注册情况
 * @author MM_Zerui
 *
 */
public class Http_CheckPhone extends HttpRequestClass<Req_CheckPhone, Recv_CheckPhone>{
	private Req_CheckPhone reqBean;
	public Http_CheckPhone(Call_httpListData<Recv_CheckPhone> callBack) {
		// TODO Auto-generated constructor stub
		this.call_list=callBack;
	}
	@Override
	public HttpRequestClass onParams(Req_CheckPhone reqBean) {
		// TODO Auto-generated method stub]
		this.reqBean=reqBean;
		return this;
	}

	@Override
	public HttpRequestClass onInit() {
		// TODO Auto-generated method stub
		setIfInit(true);
		
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
		httpAction(E_Http_Port.USER_CHECKPHONE.value(), reqBean, new RequestCallBack<String>() {
			
			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				new HttpResultService(arg0.result, new httpResultCallBack<Recv_CheckPhone>() {
					@Override
					public void onListData(boolean validity,
							List<Recv_CheckPhone> listDatas) {
						// TODO Auto-generated method stub
						super.onListData(validity, listDatas);
						if(validity){
							call_list.onInit(listDatas);
							return;
						}
						call_list.onFail();
					}
					@Override
					public void onRequestFail() {
						// TODO Auto-generated method stub
						call_list.onFail();
					}

					@Override
					public void onSuccess() {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onFinally() {
						// TODO Auto-generated method stub
						call_list.onFinally();
					}
				},Recv_CheckPhone.class ,true);
			}
			
			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				call_list.onFail();
				call_list.onFinally();
			}
		});
	}

}
