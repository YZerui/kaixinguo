package com.hi.common.param;

/**
 * �б�ÿ�λ�ȡ����
 * @author MM_Zerui
 *
 */
public enum Enum_ListLimit {
	
	LEAVE_PRAISELIST(120),//�����û��б�
	LEAVE_MSG(20),//����ǽ��Ϣ
	MSG_LIST(4),
	MSG(20),//��ڨ��Ϣ
	COUPONS(20),//�Ż݄�
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
