package com.hi.common.db;

/**
 * �Ż݄��Ƿ����
 * @author MM_Zerui
 *
 */
public enum E_DB_ConpousUse {
	UNUSE(0),
	USE(1);
	private int value;
	private E_DB_ConpousUse(int value) {
		// TODO Auto-generated constructor stub
		this.value=value;
	}
	public int value(){
		return value;
	}
}
