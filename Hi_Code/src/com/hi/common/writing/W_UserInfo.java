package com.hi.common.writing;

import android.widget.TextView;

import com.format.utils.DataValidate;
import com.hi.common.db.E_DB_SelfIfo;
import com.hi.common.http.E_Http_Gender;

/**
 * 个人信息默认处理
 * 
 * @author MM_Zerui
 * 
 */
public class W_UserInfo {
	public static String getGender(String initValue) {
		try {
			if (initValue.equals(E_Http_Gender.MAN.toString())) {
				return "男";
			}
			if (initValue.equals(E_Http_Gender.WOMEN.toString())) {
				return "女";
			}
		} catch (Exception e) {
			// TODO: handle exception
			
		}
		return "暂无编辑性别";
	}

	public static void Default(E_DB_SelfIfo dbSelf, TextView textView,
			String info) {
		if (DataValidate.checkDataValid(info) && (!info.equals("null"))) {
			textView.setText(info);
			return;
		}
		switch (dbSelf) {
		case accostedEffe:// 是否可搭讪
			textView.setText("");
			break;
		case birthDay:
			textView.setText("暂无编辑生日信息");
			break;
		case currentState:
			textView.setText("暂无编辑当前状态");
			break;
		case head:
			textView.setText("暂无编辑头像");
			break;
		case interest:
			textView.setText("暂无编辑兴趣爱好信息");
			break;
		case locationEffe:// 位置是否可见
			textView.setText("");
			break;
		case mid://
			break;
		case nickName:
			textView.setText("暂无编辑个人昵称");
			break;
		case note:
			textView.setText("暂无编辑个人标签");
			break;
		case occupation:
			textView.setText("暂无编辑个人工作信息");
			break;
		case phone:
			textView.setText("暂无编辑个人号码信息");
			break;
		case xingzuo:
			textView.setText("未知星座");
			break;
		case age:
			textView.setText("未知年龄");
		default:
			break;
		}
	}
}
