package com.hi.common.http;

/**
 * 消息格式
 * 
 * @author MM_Zerui
 * 
 */
public enum E_Http_MsgFormat {
	UNKNOW(-1), // 未知
	TEXT(1), IMG(2), RADIO(3), LOCATION(4);
	int value;

	private E_Http_MsgFormat(int value) {
		// TODO Auto-generated constructor stub
		this.value = value;
	}
	public int value(){
		return value;
	}
	public static E_Http_MsgFormat get(int value) {
		switch (value) {
		case 1:
			return TEXT;
		case 2:
			return IMG;
		case 3:
			return RADIO;
		case 4:
			return LOCATION;
		default:
			return UNKNOW;
		}
	}
}
