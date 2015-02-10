package com.hi.common.http;

/**
 * �û���ϵ
 * 
 * @��ע ��ϵ���� Ĭ��0 -1:���� 0:�� 1:��ע�ҵ� 2:�ҹ�ע�� 3:�໥��ע��
 * @author MM_Zerui
 * 
 */
public enum E_Http_Relate {
	DEFAULT(0), BLOCK(-1), FOLLOW_ME(1), FOLLOW_OTHER(2), FOLLOW_EACH(3);
	private int value;

	private E_Http_Relate(int value) {
		// TODO Auto-generated constructor stub
		this.value = value;
	}

	public int value() {
		return value;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.valueOf(value);
	}

	public static E_Http_Relate get(int value) {
		switch (value) {
		case 0:
			return DEFAULT;
		case -1:
			return BLOCK;
		case 1:
			return FOLLOW_ME;
		case 2:
			return FOLLOW_OTHER;
		case 3:
			return FOLLOW_EACH;
		default:
			return DEFAULT;
		}
	}
}
