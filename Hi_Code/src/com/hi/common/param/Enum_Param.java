package com.hi.common.param;

import com.hi.common.PARAMS;

public enum Enum_Param {
	TIMEOFFSET_WIFISIGN(PARAMS.MINUTE_MILLISECONDS * 15),//wifiǩ��
	TIMEOFFSET_WIFICHECK(PARAMS.MINUTE_MILLISECONDS*2),//wifi���
	TIMEOFFSET_LISTLOAD(300),
	TIMEOFFSET_PAGELOAD(150),
	TIMEOFFSET_BOXDOWN(150);
	private long value;
	Enum_Param(long value){
		this.value=value;
	}
	public long value(){
		return value;
	}
}
