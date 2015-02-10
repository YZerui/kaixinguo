package com.hi.module.base.superClass;

import android.view.View;

public abstract class SuperViewHolder {
	/**
	 * 资源绑定
	 * @param parent
	 */
	public abstract void initView(View parent);
	/**
	 * 数据变化处理
	 * @param position
	 */
	public abstract void refreshData(int position);
	
}
