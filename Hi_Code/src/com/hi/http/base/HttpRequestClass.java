package com.hi.http.base;

import com.format.utils.FormatUtils;
import com.hi.common.param.Enum_ListLimit;
import com.hi.module.base.callBack.httpResultCallBack;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

/**
 * @author MM_Zerui
 *
 * @param <T> 代表请求的参数Bean
 * @param <K> 代表接收的参数Bean
 */
public abstract class HttpRequestClass<T, K> {
	protected HttpUtils http;
	private boolean ifInit = true;
	protected Call_httpData<K> call_normal;
	protected Call_httpListData<K> call_list;
	private int begin = 0;

	public HttpRequestClass() {
		http = new HttpUtils();
	}

	protected String beginInit() {
		setIfInit(true);
		this.begin =0;
		return String.valueOf(begin);
	}
	protected String beginLoad(int limit) {
		setIfInit(false);
		return s((++begin) * limit + 1);
	}
	protected void setIfInit(boolean ifInit) {
		this.ifInit = ifInit;
	}

	public boolean isIfInit() {
		return ifInit;
	}

	/**
	 * 设定请求参数
	 * 
	 * @param params
	 */
	public abstract HttpRequestClass onParams(T reqBean);

	/**
	 * 初始操作
	 */
	public HttpRequestClass onInit(){
		setIfInit(true);
		return this;
	}

	/**
	 * 加载操作
	 */
	public HttpRequestClass onLoad(){
		setIfInit(false);
		return this;
	}

	/**
	 * 请求操作
	 */
	public abstract void onAction();

	protected String s(int value) {
		return String.valueOf(value);
	}

	protected int i(String str) {
		return Integer.valueOf(str);
	}


	/**
	 * 请求执行
	 * 
	 * @throws Exception
	 */
	protected void httpAction(String url, T reqBean,
			RequestCallBack<String> callBack) {
		try {
			if(reqBean==null){
				http.send(HttpMethod.POST, url,
						null, callBack);
				return;
			}
			http.send(HttpMethod.POST, url,
					FormatUtils.convertBeanToParams(reqBean), callBack);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
