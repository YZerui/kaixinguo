package com.hi.common.http;

/**
 * 商家类型
 * @author MM_Zerui
 *
 */
public enum E_Http_StoreType {
	ORDER_INTEREST(1),
	ORDER_FRIEND(2);
	private int value;
	private E_Http_StoreType(int value){
		this.value=value;
	}
	public int value(){
		return value;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.valueOf(value);
	}
}
