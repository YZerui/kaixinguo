package com.hi.http.friends.req;

import java.util.List;

import com.hi.common.http.E_Http_Port;
import com.hi.common.param.Enum_ListLimit;
import com.hi.dao.model.T_MyFriends;
import com.hi.http.base.Call_httpListData;
import com.hi.http.base.HttpRequestClass;
import com.hi.http.friends.model.Recv_Friends;
import com.hi.http.friends.model.Req_Friends;
import com.hi.module.base.callBack.httpResultCallBack;
import com.hi.service.HttpResultService;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

/**
 * 获取朋友列表
 * @author MM_Zerui
 *
 */
public class Http_Friends extends HttpRequestClass<Req_Friends,T_MyFriends>{
	private Req_Friends reqBean;
	public Http_Friends(Call_httpListData<T_MyFriends> callBack) {
		this.call_list=callBack;
	}
	@Override
	public HttpRequestClass onParams(Req_Friends reqBean) {
		this.reqBean=reqBean;
		return this;
	}

	@Override
	public HttpRequestClass onInit() {
		super.onInit();
		reqBean.setBegin(beginInit());
		reqBean.setLimit(Enum_ListLimit.FRIEND_LIST.toString());
		onAction();
		return this;
	}

	@Override
	public HttpRequestClass onLoad() {
		// TODO Auto-generated method stub
		super.onLoad();
		reqBean.setBegin(beginLoad(Enum_ListLimit.FRIEND_LIST.value()));
		onAction();
		return this;
	}

	@Override
	public void onAction() {
		// TODO Auto-generated method stub
		httpAction(E_Http_Port.FRIEND_LIST.value(), reqBean, new RequestCallBack<String>() {
			@Override
			public void onStart() {
				// TODO Auto-generated method stub
				super.onStart();
				call_list.onStart();
			}
			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				new HttpResultService(arg0.result, new httpResultCallBack<T_MyFriends>() {
					@Override
					public void onListData(boolean validity,
							List<T_MyFriends> listDatas) {
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
				},T_MyFriends.class, true);
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
