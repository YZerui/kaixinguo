package com.hi.common.writing;

import android.widget.TextView;

import com.format.utils.DataValidate;
import com.hi.common.http.E_Http_StoreInfo;

/**
 * 店家信息的默认处理
 * @author MM_Zerui
 *
 */
public class W_StoreInfo {
	public static void Default(E_Http_StoreInfo enumType,TextView textView,String info){
		if (DataValidate.checkDataValid(info) && (!info.equals("null"))) {
			textView.setText(info);
			return;
		}
		switch (enumType) {
		case address:
			textView.setText("暂无地址信息");
			break;
		case contact:
			textView.setText("暂无联系人信息");
			break;
		case fixPhone:
			textView.setText("暂无固定电话");
			break;
		case name:
			textView.setText("未知点名");
			break;
		case remark:
			textView.setText("暂无相关信息");
			break;
		default:
			break;
		}
	}
}
