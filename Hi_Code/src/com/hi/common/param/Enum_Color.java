package com.hi.common.param;

public enum Enum_Color {
	THEME(0xfffa723a),
	WHITE(0xeeffffff),
	RED(0xeeff0000);
	private int value;
	private Enum_Color(int value) {
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
