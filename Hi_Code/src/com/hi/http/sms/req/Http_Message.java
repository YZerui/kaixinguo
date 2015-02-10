package com.hi.http.sms.req;

import java.util.ArrayList;
import java.util.List;

import android.view.View;

import com.format.utils.DataValidate;
import com.hi.common.http.E_Http_Port;
import com.hi.common.param.Enum_ListLimit;
import com.hi.dao.model.T_MsgCache;
import com.hi.dao.supImpl.Dao_SelfIfo;
import com.hi.http.base.Call_httpListData;
import com.hi.http.base.HttpRequestClass;
import com.hi.http.sms.model.Req_Message;
import com.hi.module.base.callBack.httpResultCallBack;
import com.hi.service.HttpResultService;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

/**
 * 请求商机信息
 * @author MM_Zerui
 *
 */
public class Http_Message extends HttpRequestClass<Req_Message,T_MsgCache>{
	Req_Message reqBean;
	public Http_Message(Call_httpListData<T_MsgCache> callBack) {
		// TODO Auto-generated constructor stub
		this.call_list=callBack;
		reqBean=new Req_Message();
	}
	@Override
	public HttpRequestClass onParams(Req_Message reqBean) {
		// TODO Auto-generated method stub
		this.reqBean=reqBean;
		return this;
	}
	@Override
	public HttpRequestClass onInit() {
		// TODO Auto-generated method stub
		reqBean.setLimit(Enum_ListLimit.MSG.toString());
		reqBean.setUid(Dao_SelfIfo.getInstance().getMid());
		reqBean.setBegin(beginInit());
		onAction();
		return this;
	}
	@Override
	public HttpRequestClass onLoad() {
		// TODO Auto-generated method stub
		reqBean.setBegin(beginLoad(Enum_ListLimit.MSG.value()));
		onAction();
		return this;
	}
	@Override
	public void onAction() {
		// TODO Auto-generated method stub
		httpAction(E_Http_Port.MSG_LIST.value(), reqBean, new RequestCallBack<String>() {
			@Override
			public void onStart() {
				// TODO Auto-generated method stub
				super.onStart();
				call_list.onStart();
			}
			@Override
			public void onSuccess(ResponseInfo<String> params) {
				String result=params.result;
				result=result.replace("\"id\"","\"msgId\"");
				
				new HttpResultService(result, new httpResultCallBack<T_MsgCache>() {
					@Override
					public void onListData(boolean validity,
							List<T_MsgCache> listDatas) {
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
					public void onSuccess() {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onRequestFail() {
						// TODO Auto-generated method stub
						call_list.onFail();
					}

					@Override
					public void onFinally() {
						// TODO Auto-generated method stub
						call_list.onFinally();
					}
				}, T_MsgCache.class, true);
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
