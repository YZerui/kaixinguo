package com.hi.service.base;

/**
 * ���ݿ�Ĵ���������
 * 
 * @author MM_Zerui
 * 
 * @param <K>
 */
public abstract class DBSuperClass<K> {
	private boolean ifInit = true;
	protected Call_DBData<K> call_normal;
	protected Call_DBListData<K> call_list;
	private int begin = 0;

	protected int beginInit() {
		this.begin = 0;
		return begin;
	}

	protected int beginLoad(int limit) {
		return (++begin);
	}

	protected void setIfInit(boolean ifInit) {
		this.ifInit = ifInit;
	}

	public boolean isIfInit() {
		return ifInit;
	}

	/**
	 * ��ʼ����
	 */
	public DBSuperClass<K> onInit() {
		setIfInit(true);
		onAction();
		return this;
	}

	/**
	 * ���ز���
	 */
	public DBSuperClass<K> onLoad() {
		setIfInit(false);
		onAction();
		return this;
	}

	/**
	 * �������
	 */
	protected abstract void onAction();

	protected String s(int value) {
		return String.valueOf(value);
	}

	protected int i(String str) {
		return Integer.valueOf(str);
	}

}
