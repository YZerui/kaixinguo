package com.hi.common.db;

/**
 * 消息类型
 * @author MM_Zerui
 *
 */
public enum E_DB_MsgType {
	DEFAULT(-1),
	ACCOST(0),
	RECOMMEND(1),
	FOLLOW(2),
	INVITE(3);
	private int value;
	private E_DB_MsgType(int value) {
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
	public static E_DB_MsgType get(int i){
		switch (i) {
		case 0:
			return ACCOST;
		case 1:
			return RECOMMEND;
		case 2:
			return FOLLOW;
		case 3:
			return INVITE;
		default:
			return DEFAULT;
		}
	}
}
