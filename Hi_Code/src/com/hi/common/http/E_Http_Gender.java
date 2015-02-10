package com.hi.common.http;

public enum E_Http_Gender {
	MAN("1"),
	WOMEN("0");
	private String value;
	private E_Http_Gender(String value){
		this.value=value;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return value;
	}
}
