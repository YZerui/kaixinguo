package com.hi.http.local.req;

import java.util.List;

import com.hi.common.http.E_Http_Port;
import com.hi.common.param.Enum_ListLimit;
import com.hi.dao.supImpl.Dao_SelfIfo;
import com.hi.http.base.Call_httpListData;
import com.hi.http.base.HttpRequestClass;
import com.hi.http.local.model.Recv_obtMsg;
import com.hi.http.local.model.Req_obtMsg;
import com.hi.module.base.callBack.httpResultCallBack;
import com.hi.service.HttpResultService;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

/**
 * 获取留言信息列表
 * @author MM_Zerui
 *
 */
public class Http_obtMsg extends HttpRequestClass<Req_obtMsg,Recv_obtMsg>{
	private Req_obtMsg reqBean;
	public Http_obtMsg(Call_httpListData<Recv_obtMsg> callBack) {
		this.call_list=callBack;
	}
	@Override
	public HttpRequestClass onParams(Req_obtMsg reqBean) {
		this.reqBean=reqBean;
		reqBean.setUid(Dao_SelfIfo.getInstance().getMid());
		return this;
	}
	@Override
	public HttpRequestClass onInit() {
		super.onInit();
		reqBean.setLimit(Enum_ListLimit.LEAVE_MSG.toString());
		reqBean.setStart(beginInit());
		onAction();
		return this;
	
	}
	@Override
	public HttpRequestClass onLoad() {
		super.onLoad();
		reqBean.setStart(beginLoad(Enum_ListLimit.LEAVE_MSG.value()));
		onAction();
		return this;
	}
	@Override
	public void onAction() {
		// TODO Auto-generated method stub
		httpAction(E_Http_Port.LEAVE_MSG_OBT.value(), reqBean, new RequestCallBack<String>() {
			
			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				new HttpResultService(arg0.result, new httpResultCallBack<Recv_obtMsg>() {
					@Override
					public void onListData(boolean validity,
							List<Recv_obtMsg> listDatas) {
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
				}, Recv_obtMsg.class,true);
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
