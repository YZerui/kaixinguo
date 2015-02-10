package com.hi.module.base.superClass;

import com.android.ruifeng.hi.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import android.view.View;
import android.view.ViewGroup;

public class SuperAdapter extends ArrayAdapter<Object>{
	protected ImageLoader imageLoader;
	protected DisplayImageOptions loadOptions;
	public SuperAdapter() {								
		imageLoader = ImageLoader.getInstance();
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return null;
	}

}
