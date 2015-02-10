package com.hi.service.image;

public enum Enum_ImgScaleType {
	TYPE_0(0),//边	-	多	-	不裁剪
	TYPE_1(1),//宽	-	少	-	居中裁剪
	TYPE_2(2),//宽	-	多	-	不裁剪
	TYPE_3(3),//宽	-	少	-	不裁剪
	TYPE_4(4),//边	-	少	- 	不裁剪
	TYPE_5(5);//边	-	少	- 	居中裁剪
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
