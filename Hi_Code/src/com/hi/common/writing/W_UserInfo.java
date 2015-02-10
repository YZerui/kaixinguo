package com.hi.common.writing;

import android.widget.TextView;

import com.format.utils.DataValidate;
import com.hi.common.db.E_DB_SelfIfo;
import com.hi.common.http.E_Http_Gender;

/**
 * ������ϢĬ�ϴ���
 * 
 * @author MM_Zerui
 * 
 */
public class W_UserInfo {
	public static String getGender(String initValue) {
		try {
			if (initValue.equals(E_Http_Gender.MAN.toString())) {
				return "��";
			}
			if (initValue.equals(E_Http_Gender.WOMEN.toString())) {
				return "Ů";
			}
		} catch (Exception e) {
			// TODO: handle exception
			
		}
		return "���ޱ༭�Ա�";
	}

	public static void Default(E_DB_SelfIfo dbSelf, TextView textView,
			String info) {
		if (DataValidate.checkDataValid(info) && (!info.equals("null"))) {
			textView.setText(info);
			return;
		}
		switch (dbSelf) {
		case accostedEffe:// �Ƿ�ɴ�ڨ
			textView.setText("");
			break;
		case birthDay:
			textView.setText("���ޱ༭������Ϣ");
			break;
		case currentState:
			textView.setText("���ޱ༭��ǰ״̬");
			break;
		case head:
			textView.setText("���ޱ༭ͷ��");
			break;
		case interest:
			textView.setText("���ޱ༭��Ȥ������Ϣ");
			break;
		case locationEffe:// λ���Ƿ�ɼ�
			textView.setText("");
			break;
		case mid://
			break;
		case nickName:
			textView.setText("���ޱ༭�����ǳ�");
			break;
		case note:
			textView.setText("���ޱ༭���˱�ǩ");
			break;
		case occupation:
			textView.setText("���ޱ༭���˹�����Ϣ");
			break;
		case phone:
			textView.setText("���ޱ༭���˺�����Ϣ");
			break;
		case xingzuo:
			textView.setText("δ֪����");
			break;
		case age:
			textView.setText("δ֪����");
		default:
			break;
		}
	}
}
