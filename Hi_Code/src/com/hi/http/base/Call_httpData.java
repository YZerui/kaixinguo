package com.hi.http.base;

import java.util.List;

/**
 * �����������Ļص�����ͨ���ݣ�
 * @author MM_Zerui
 *
 */
public abstract class Call_httpData<T>{
	public abstract void onStart();

	public abstract void onSuccess(T datas);

	public abstract void onFail();

	public abstract void onFinally();
}
