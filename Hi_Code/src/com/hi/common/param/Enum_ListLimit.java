package com.hi.common.param;

/**
 * 列表每次获取个数
 * @author MM_Zerui
 *
 */
public enum Enum_ListLimit {
	
	LEAVE_PRAISELIST(120),//点赞用户列表
	LEAVE_MSG(20),//留言墙信息
	MSG_LIST(4),
	MSG(20),//搭讪消息
	COUPONS(20),//优惠劵
	STORE_LIST(20),
	FRIEND_LIST(20),
	
	WIFI_LIST(20);
	private int value;
	private Enum_ListLimit(int value) {
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
