package com.hi.http.base;

import java.util.List;

/**
 * 网络请求结果的回调（列表数据）
 * @author MM_Zerui
 *
 */
public abstract class Call_httpListData<T>{
	public abstract void onStart();

	public abstract void onInit(List<T> datas);

	public abstract void onLoad(List<T> datas);
	public abstract void onFail();

	public abstract void onFinally();
}
