package com.hi.http.member.req;

import java.util.List;

import cn.sharesdk.framework.authorize.e;

import com.hi.common.http.E_Http_Port;
import com.hi.common.param.Enum_ListLimit;
import com.hi.http.base.Call_httpListData;
import com.hi.http.base.HttpRequestClass;
import com.hi.http.member.model.Recv_WifiList;
import com.hi.http.member.model.Req_WifiList;
import com.hi.module.base.callBack.httpResultCallBack;
import com.hi.service.HttpResultService;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

/**
 * 获取现场wifi用户列表
 * @author MM_Zerui
 *
 */
public class Http_WifiList extends HttpRequestClass<Req_WifiList, Recv_WifiList>{
	private Req_WifiList reqBean;
	public Http_WifiList(Call_httpListData<Recv_WifiList> callBack) {
		this.call_list=callBack;
	}
	@Override
	public HttpRequestClass onParams(Req_WifiList reqBean) {
		this.reqBean=reqBean;
		return this;
	}

	@Override
	public HttpRequestClass onInit() {
		setIfInit(true);
		reqBean.setBegin(beginInit());
		reqBean.setLimit(Enum_ListLimit.WIFI_LIST.toString());
		onAction();
		return this;
	}

	@Override
	public HttpRequestClass onLoad() {
		setIfInit(false);
		reqBean.setBegin(beginLoad(Enum_ListLimit.WIFI_LIST.value()));
		onAction();
		return this;
	}
	
	

	@Override
	public void onAction() {
		// TODO Auto-generated method stub
		httpAction(E_Http_Port.USER_LOCATELIST.value(), reqBean, new RequestCallBack<String>() {
			@Override
			public void onStart() {
				// TODO Auto-generated method stub
				super.onStart();
				call_list.onStart();
			}
			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				new HttpResultService(arg0.result, new httpResultCallBack<Recv_WifiList>() {
					@Override
					public void onListData(boolean validity,
							List<Recv_WifiList> listDatas) {
						// TODO Auto-generated method stub
						super.onListData(validity, listDatas);
						if(validity){
							if(isIfInit()){
								call_list.onInit(listDatas);
							}else {
								call_list.onLoad(listDatas);
							}
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
				}, Recv_WifiList.class, true);
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
