package com.hi.http.local.req;

import java.util.List;

import com.hi.common.http.E_Http_Port;
import com.hi.common.param.Enum_ListLimit;
import com.hi.http.base.Call_httpListData;
import com.hi.http.base.HttpRequestClass;
import com.hi.http.local.model.Recv_praiseList;
import com.hi.http.local.model.Req_praiseList;
import com.hi.module.base.callBack.httpResultCallBack;
import com.hi.service.HttpResultService;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

/**
 * 获取某留言的点赞列表
 * @author MM_Zerui
 *
 */
public class Http_praiseList extends HttpRequestClass<Req_praiseList,Recv_praiseList>{
	private Req_praiseList reqBean;
	public Http_praiseList(Call_httpListData<Recv_praiseList> callBack) {
		// TODO Auto-generated constructor stub
		this.call_list=callBack;
	}
	@Override
	public HttpRequestClass onInit() {
		// TODO Auto-generated method stub
		reqBean.setLimit(Enum_ListLimit.LEAVE_PRAISELIST.toString());
		reqBean.setStart(beginInit());
		onAction();
		return this;
	}
	@Override
	public HttpRequestClass onLoad() {
		// TODO Auto-generated method stub
		reqBean.setStart(beginLoad(Enum_ListLimit.LEAVE_PRAISELIST.value()));
		onAction();
		return this;
	}
	@Override
	public HttpRequestClass onParams(Req_praiseList reqBean) {
		// TODO Auto-generated method stub
		this.reqBean=reqBean;
		return this;
	}

	@Override
	public void onAction() {
		// TODO Auto-generated method stub
		httpAction(E_Http_Port.LEAVE_MSG_PRAISELIST.value(), reqBean, new RequestCallBack<String>() {
			
			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				new HttpResultService(arg0.result, new httpResultCallBack<Recv_praiseList>() {
					@Override
					public void onListData(boolean validity,
							List<Recv_praiseList> listDatas) {
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
				}, Recv_praiseList.class, true);
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
