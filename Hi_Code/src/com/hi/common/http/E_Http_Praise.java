package com.hi.common.http;

/**
 * 是否点赞的标识
 * @author MM_Zerui
 *
 */
public enum E_Http_Praise {
	UNPRAISE(0),
	PRAISE(1);
	private int value;
	private E_Http_Praise(int value) {
		// TODO Auto-generated constructor stub
		this.value=value;
	}
	private int value() {
		// TODO Auto-generated method stub
		return value;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.valueOf(value);
	}
}
