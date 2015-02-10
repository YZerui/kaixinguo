package com.hi.common.db;


public enum E_DB_ImgUrlMode {
	LOCAL(0),
	URL(1);
	private int value;
	private E_DB_ImgUrlMode(int value) {
		// TODO Auto-generated constructor stub
		this.value=value;
	}
	public int value(){
		return value;
	}
	public static E_DB_ImgUrlMode get(int value){
		switch (value) {
		case 0:
			return LOCAL;
		case 1:
			return URL;
		default:
			return LOCAL;
		}
	}
}
