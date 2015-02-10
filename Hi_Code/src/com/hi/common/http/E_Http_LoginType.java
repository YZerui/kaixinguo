package com.hi.common.http;

/**
 * µÇÂ¼ÀàÐÍ
 * @author MM_Zerui
 *
 */
public enum E_Http_LoginType {
	QQ(1),
	SINA(2);
	private int value;
	private E_Http_LoginType(int value){
		this.value=value;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.valueOf(value);
	}
}
