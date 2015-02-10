package com.hi.common.db;

/**
 * 消息来源
 * @author MM_Zerui
 *
 */
public enum E_DB_MsgSource {
	SELF(-1),//来自自己
	USER(0),//来自用户
	STORE(1);//来自商家
	private int value;
	private E_DB_MsgSource(int value){
		this.value=value;
	}
	public int value(){
		return value;
	}
}
