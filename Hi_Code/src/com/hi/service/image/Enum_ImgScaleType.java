package com.hi.service.image;

public enum Enum_ImgScaleType {
	TYPE_0(0),//��	-	��	-	���ü�
	TYPE_1(1),//��	-	��	-	���вü�
	TYPE_2(2),//��	-	��	-	���ü�
	TYPE_3(3),//��	-	��	-	���ü�
	TYPE_4(4),//��	-	��	- 	���ü�
	TYPE_5(5);//��	-	��	- 	���вü�
	int value;
	private Enum_ImgScaleType(int value) {
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
