package com.hi.common.db;

/**
 * 优惠劵是否可用
 * @author MM_Zerui
 *
 */
public enum E_DB_ConpousUsing {
	UNUSING(0),
	USING(1);
	private int value;
	private E_DB_ConpousUsing(int value) {
		// TODO Auto-generated constructor stub
		this.value=value;
	}
	public int value(){
		return value;
	}
}
