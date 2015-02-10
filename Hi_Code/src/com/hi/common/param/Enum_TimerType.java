package com.hi.common.param;

/**
 * 倒计时时间显示模式
 * @author MM_Zerui
 *
 */
public enum Enum_TimerType {
	TIMER_SECOND(0),
	TIMER_MINUTE(1),
	TIMER_HOUR(2),
	TIMER_HOUR_MINUTE(3),
	TIMER_MINUTE_SECOND(4),
	TIMER_ALL(5);
	int value;
	private Enum_TimerType(int value) {
		// TODO Auto-generated constructor stub
		this.value=value;
	}
	public int value(){
		return value;
	}
	public static  Enum_TimerType get(int value){
		switch (value) {
		case 0:
			return TIMER_SECOND;
		case 1:
			return TIMER_MINUTE;
		case 2:
			return TIMER_HOUR;
		case 3:
			return TIMER_HOUR_MINUTE;
		case 4:
			return TIMER_MINUTE_SECOND;
		case 5:
			return TIMER_ALL;
		default:
			return TIMER_ALL;
		}
	}
}
