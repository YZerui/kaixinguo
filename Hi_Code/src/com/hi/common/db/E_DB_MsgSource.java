package com.hi.common.db;

/**
 * ��Ϣ��Դ
 * @author MM_Zerui
 *
 */
public enum E_DB_MsgSource {
	SELF(-1),//�����Լ�
	USER(0),//�����û�
	STORE(1);//�����̼�
	private int value;
	private E_DB_MsgSource(int value){
		this.value=value;
	}
	public int value(){
		return value;
	}
}
