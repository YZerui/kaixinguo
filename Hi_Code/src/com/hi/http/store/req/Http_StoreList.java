package com.hi.http.store.req;

import java.util.List;

import com.hi.common.http.E_Http_Port;
import com.hi.common.param.Enum_ListLimit;
import com.hi.http.base.Call_httpListData;
import com.hi.http.base.HttpRequestClass;
import com.hi.http.store.model.Recv_StoreList;
import com.hi.http.store.model.Req_StoreList;
import com.hi.module.base.callBack.httpResultCallBack;
import com.hi.service.HttpResultService;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

/**
 * µÍº“¡–±Ì
 * @author MM_Zerui
 *
 */
public class Http_StoreList extends HttpRequestClass<Req_StoreList, Recv_StoreList>{
	private Req_StoreList reqBean;
	public Http_StoreList(Call_httpListData<Recv_StoreList> callBack) {
		// TODO Auto-generated constructor stub
		this.call_list=callBack;
	}
	@Override
	public HttpRequestClass onParams(Req_StoreList reqBean) {
		// TODO Auto-generated method stub
		this.reqBean=reqBean;
		return this;
	}
	@Override
	public HttpRequestClass onInit() {
		// TODO Auto-generated method stub
		super.onInit();
		reqBean.setLimit(Enum_ListLimit.STORE_LIST.toString());
		reqBean.setBegin(beginInit());
		onAction();
		return this;
	}
	@Override
	public HttpRequestClass onLoad() {
		// TODO Auto-generated method stub
		super.onLoad();
		reqBean.setBegin(beginLoad(Enum_ListLimit.STORE_LIST.value()));
		onAction();
		return this;
	}
	@Override
	public void onAction() {
		// TODO Auto-generated method stub
		httpAction(E_Http_Port.STORE_LIST.value(), reqBean,new RequestCallBack<String>() {
			@Override
			public void onStart() {
				// TODO Auto-generated method stub
				super.onStart();
				call_list.onStart();
			}
			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				new HttpResultService(arg0.result, new httpResultCallBack<Recv_StoreList>() {
					@Override
					public void onListData(boolean validity,
							List<Recv_StoreList> listDatas) {
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
				}, Recv_StoreList.class, true);
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
