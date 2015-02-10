package com.hi.http.local.req;

import java.io.File;

import com.exception.utils.P;
import com.format.utils.DataValidate;
import com.hi.common.http.E_Http_Port;
import com.hi.dao.supImpl.Dao_SelfIfo;
import com.hi.http.base.Call_httpData;
import com.hi.http.base.HttpRequestClass;
import com.hi.http.local.model.Req_issueMsg;
import com.hi.module.base.callBack.httpResultCallBack;
import com.hi.service.HttpResultService;
import com.hi.utils.DeviceUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

/**
 * ·¢²¼ÁôÑÔ²Ù×÷
 * 
 * @author MM_Zerui
 * 
 */
public class Http_IssueMsg extends HttpRequestClass<Req_issueMsg, Class<?>> {

	private Req_issueMsg reqBean;

	public Http_IssueMsg(Call_httpData<Class<?>> callBack) {
		// TODO Auto-generated constructor stub
		this.call_normal = callBack;
	}

	@Override
	public HttpRequestClass onParams(Req_issueMsg reqBean) {
		// TODO Auto-generated method stub
		this.reqBean = reqBean;

		return this;
	}

	@Override
	public void onAction() {
		// TODO Auto-generated method stub
		RequestParams params = new RequestParams();
		params.addBodyParameter("uid", reqBean.getUid());
		params.addBodyParameter("wifiMac",reqBean.getWifiMac());

		if (DataValidate.checkDataValid(reqBean.getImg())) {
			params.addBodyParameter("img", new File(reqBean.getImg()));
			P.v("ÁôÑÔÇ½Í¼Æ¬Â·¾¶:"+reqBean.getImg());
		}
		if (DataValidate.checkDataValid(reqBean.getContent())) {
			params.addBodyParameter("content",reqBean.getContent());
		}
		http.send(HttpMethod.POST, E_Http_Port.LEAVE_MSG_ISSUE.value(), params,
				new RequestCallBack<String>() {
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
						new HttpResultService(arg0.result,
								new httpResultCallBack<Class<?>>() {

									@Override
									public void onRequestFail() {
										// TODO Auto-generated method stub
										call_normal.onFail();
									}

									@Override
									public void onSuccess() {
										// TODO Auto-generated method stub
										call_normal.onSuccess(null);
									}

									@Override
									public void onFinally() {
										// TODO Auto-generated method stub
										call_normal.onFinally();
									}
								}, null, false);
					}
				});
	}

}
