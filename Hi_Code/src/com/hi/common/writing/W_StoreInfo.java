package com.hi.common.writing;

import android.widget.TextView;

import com.format.utils.DataValidate;
import com.hi.common.http.E_Http_StoreInfo;

/**
 * �����Ϣ��Ĭ�ϴ���
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
			textView.setText("���޵�ַ��Ϣ");
			break;
		case contact:
			textView.setText("������ϵ����Ϣ");
			break;
		case fixPhone:
			textView.setText("���޹̶��绰");
			break;
		case name:
			textView.setText("δ֪����");
			break;
		case remark:
			textView.setText("���������Ϣ");
			break;
		default:
			break;
		}
	}
}
