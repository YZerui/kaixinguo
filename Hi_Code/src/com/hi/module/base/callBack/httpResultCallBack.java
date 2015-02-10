package com.hi.module.base.callBack;

import java.util.List;

public abstract class httpResultCallBack<T> {
	public void onNetError(){
		
	}
	public abstract void onRequestFail();
	public abstract void onSuccess();
	public abstract void onFinally();
	
	public void onData(boolean validity,T obj){
		
	}
	public void onListData(boolean validity,List<T> listDatas){
		
	}
}
