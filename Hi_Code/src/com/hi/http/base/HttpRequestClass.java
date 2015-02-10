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
 * @param <T> ��������Ĳ���Bean
 * @param <K> ������յĲ���Bean
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
	 * �趨�������
	 * 
	 * @param params
	 */
	public abstract HttpRequestClass onParams(T reqBean);

	/**
	 * ��ʼ����
	 */
	public HttpRequestClass onInit(){
		setIfInit(true);
		return this;
	}

	/**
	 * ���ز���
	 */
	public HttpRequestClass onLoad(){
		setIfInit(false);
		return this;
	}

	/**
	 * �������
	 */
	public abstract void onAction();

	protected String s(int value) {
		return String.valueOf(value);
	}

	protected int i(String str) {
		return Integer.valueOf(str);
	}


	/**
	 * ����ִ��
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
