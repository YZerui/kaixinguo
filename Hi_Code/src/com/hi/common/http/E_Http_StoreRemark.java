package com.hi.common.http;

/**
 * 店家评论类型
 * @author MM_Zerui
 *
 */
public enum E_Http_StoreRemark {
	DEFAULT(0),
	LIKE(1),
	DISLIKE(-1);
	private int value;
	private E_Http_StoreRemark(int value) {
		// TODO Auto-generated constructor stub
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
	public static E_Http_StoreRemark get(int value){
		switch (value) {
		case 0:
			return DEFAULT;
		case 1:
			return LIKE;
		case -1:
			return DISLIKE;
		default:
			return DEFAULT;
		}
	}
}
