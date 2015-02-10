package com.hi.module.base.superClass;


import android.os.Bundle;

/**
 * @author MM_Zerui
 * ³£¹æµÄActivity
 */
public abstract class NormalActivity extends SuperActivity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		obtainIntentValue();
		initResource();
		onClickListener();
		
	}

	public abstract void obtainIntentValue();


	public abstract void initResource();

	public abstract void onClickListener();


	public abstract void outFinish();
}
