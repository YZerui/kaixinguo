package com.hi.adapter;

import java.util.ArrayList;
import java.util.List;

import com.android.ruifeng.hi.R;
import com.hi.dao.model.T_Coupons;
import com.hi.module.base.superClass.SuperAdapter;
import com.hi.module.store.model.RecvPrivilegeBean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 显示店铺优惠劵列表的Adapter
 * @author MM_Zerui 
 */
public class ShopPrivilegeAdapter extends SuperAdapter {
	
	private LayoutInflater inflater;
	private List<T_Coupons> ifoDatas;
	
	public ShopPrivilegeAdapter(Context context) {
		// TODO Auto-generated constructor stub
		inflater = LayoutInflater.from(context);
		loadOptions= new DisplayImageOptions.Builder()			
		.showImageForEmptyUri(R.drawable.default_load_img)	
		.showImageOnFail(R.drawable.default_load_img)
//		.showStubImage(R.drawable.default_img_msg)
//		.showStubImage(R.drawable.app_icon)
		.cacheInMemory(true)						
		.cacheOnDisc(true)						
		.build();	
	}

	public void setData(List<T_Coupons> ifoDatas) {
		this.ifoDatas = ifoDatas;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return ifoDatas.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return ifoDatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.shop_privilege_list_layout,
					null);
			holder.privilegeDetail=(TextView)convertView.findViewById(R.id.privilege_list_shop_detail);
			holder.privilegeTitle=(TextView)convertView.findViewById(R.id.privilege_list_main_title);
			holder.storeName=(TextView)convertView.findViewById(R.id.privilege_list_shop_name);
			holder.storeIcon=(ImageView)convertView.findViewById(R.id.privilege_list_icon);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		T_Coupons bean=ifoDatas.get(position);
		holder.storeName.setText(bean.getName().isEmpty()?"未知店名":bean.getName());
		holder.privilegeDetail.setText(bean.getContent().isEmpty()?"优惠详情":bean.getContent());
		holder.privilegeTitle.setText(bean.getTitle().isEmpty()?"优惠劵":bean.getTitle());
		imageLoader.displayImage(bean.getImage(),holder.storeIcon,loadOptions);
		return convertView;
	}

	public final class ViewHolder {
		public TextView storeName;
		public TextView privilegeTitle;
		public ImageView storeIcon;
		public TextView privilegeDetail;
	}
	private onItemCommentClickListener mItemCommentClickListener;
	public void setOnItemCommentClickListener(onItemCommentClickListener mItemCommentClickListener){
		this.mItemCommentClickListener=mItemCommentClickListener;
	}
	public interface onItemCommentClickListener{
		void click(int position);
	}
}
