package com.hi.common.http;

/**
 * ״̬���ֳ�/ͨ�ã�
 * @author MM_Zerui
 *
 */
public enum E_Http_State {
	CURRENT(2),
	NOTMAL(1);
	private int value;
	private E_Http_State(int value) {
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
}
