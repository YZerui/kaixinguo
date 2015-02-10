package com.hi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.ruifeng.hi.R;
import com.hi.module.base.superClass.SuperAdapter;
import com.hi.module.base.superClass.SuperViewHolder;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

/**
 * ”≈ª›ÑªœÓµƒ  ≈‰
 * 
 * @author MM_Zerui
 * 
 */
public class MyStorePrivilegeItemAdapter extends SuperAdapter {
	private LayoutInflater inflater;
	private Context context;

	public MyStorePrivilegeItemAdapter(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
		inflater = LayoutInflater.from(context);
		loadOptions = new DisplayImageOptions.Builder()
				.showImageForEmptyUri(R.drawable.default_load_img)
				.showImageOnFail(R.drawable.default_load_img)
				.showImageForEmptyUri(R.drawable.default_load_img)
				// .showStubImage(R.drawable.default_img_msg)
				// .showStubImage(R.drawable.app_icon)
				.cacheInMemory(true).cacheOnDisc(true).build();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 6;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return super.getItem(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return super.getItemId(position);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.shop_privilege_list_layout,
					null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.refreshData(position);
		return convertView;
	}
	private class ViewHolder extends SuperViewHolder{
		public ViewHolder(View parent) {
			// TODO Auto-generated constructor stub
			initView(parent);
		}
		@Override
		public void initView(View parent) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void refreshData(int position) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
