package com.hi.service.base;

/**
 * 数据库的处理服务基类
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
	 * 初始操作
	 */
	public DBSuperClass<K> onInit() {
		setIfInit(true);
		onAction();
		return this;
	}

	/**
	 * 加载操作
	 */
	public DBSuperClass<K> onLoad() {
		setIfInit(false);
		onAction();
		return this;
	}

	/**
	 * 请求操作
	 */
	protected abstract void onAction();

	protected String s(int value) {
		return String.valueOf(value);
	}

	protected int i(String str) {
		return Integer.valueOf(str);
	}

}
