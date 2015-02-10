package com.hi.service;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.format.utils.DataValidate;
import com.hi.module.base.callBack.httpResultCallBack;
import com.hi.utils.ParseJson;

/**
 * 检测请求状况的服务
 * 
 * @author MM_Zerui
 * 
 */
public class HttpResultService {
	public HttpResultService(String httpResult, httpResultCallBack callBack,
			Class<?> beanClass, boolean ifArray) {
		
		if (!DataValidate.checkDataValid(httpResult)) {
			callBack.onNetError();
			callBack.onFinally();
			return;
		}
//		if (httpResult instanceof String) {
//			String str = (String) httpResult;
//			try {
//				jsonObject = new JSONObject(str);
//			} catch (JSONException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				callBack.onNetError();
//				callBack.onFinally();
//				return;
//			}
//		}
//		if (httpResult instanceof JSONObject) {
//			jsonObject = (JSONObject) httpResult;
//		}
//		String str = (String) httpResult;
		JSONObject jsonObject = new JSONObject();
		try {
			
			jsonObject = new JSONObject(httpResult);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			callBack.onNetError();
			callBack.onFinally();
			return;
		}
		if (jsonObject == null) {
			callBack.onNetError();
		} else if (ParseJson.parseJsonState(jsonObject)) {
			callBack.onSuccess();
			Object obj = new Object();
			if (!ifArray) {
				if (beanClass == null) {
					obj = ParseJson.parseResultData(jsonObject);
				} else {
					obj = ParseJson.parseResultJson(jsonObject, beanClass);
				}
				if (obj == null) {
					callBack.onData(false, null);
				} else {
					callBack.onData(true, obj);
				}
			} else {
				obj = ParseJson.parseLocalJsonArray(jsonObject, beanClass);
				if (!DataValidate.checkDataValid(obj)) {
					callBack.onListData(false, null);
				} else {
					callBack.onListData(true,(List)obj);
				}
			}
			
			// return;
		} else {
			callBack.onRequestFail();

		}
		callBack.onFinally();
	}
}
