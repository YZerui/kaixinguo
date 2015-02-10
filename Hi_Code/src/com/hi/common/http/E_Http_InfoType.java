package com.hi.common.http;

public enum E_Http_InfoType {
	DEFAULT(0),
	NICKNAME(1),
	GENDER(2),
	BIRTHDAY(3),
	INTEREST(4),
	LABEL(5),
	OCCUPATION(6),
	STATE(7),
	WIFISTATE(8);
	private int value;
	private E_Http_InfoType(int value) {
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
	public static E_Http_InfoType get(int value){
		switch (value) {
		case 1:
			return NICKNAME;
		case 2:
			return GENDER;
		case 3:
			return BIRTHDAY;
		case 4:
			return INTEREST;
		case 5:
			return LABEL;
		case 6:
			return OCCUPATION;
		case 7:
			return STATE;
		case 8:
			return WIFISTATE;
		default:
			return DEFAULT;
		}
		
	}
}
