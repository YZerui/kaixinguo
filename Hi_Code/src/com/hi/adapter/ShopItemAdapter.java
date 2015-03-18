package com.hi.adapter;

import java.util.List;

import com.android.ruifeng.hi.R;
import com.hi.common.PARAMS;
import com.hi.http.store.model.Recv_StoreList;
import com.hi.module.base.superClass.SuperAdapter;
import com.hi.utils.FormatUtils;
import com.hi.utils.ViewHandleUtils;
import com.hi.view.RectImageView;
import com.hi.view.RoundedImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author MM_Zerui 显示店铺列表的Adapter
 */
public class ShopItemAdapter extends SuperAdapter{
	private LayoutInflater inflater;
	private List<Recv_StoreList> ifoDatas;
	private boolean ifChange=false;
	public ShopItemAdapter(Context context) {
		// TODO Auto-generated constructor stub
		super();
		inflater = LayoutInflater.from(context);
		loadOptions= new DisplayImageOptions.Builder()			
		.showImageOnFail(R.drawable.default_load_img)
		.showImageForEmptyUri(R.drawable.default_load_img)
		.showStubImage(R.drawable.default_load_img)
//		.showStubImage(R.drawable.app_icon)
		.cacheInMemory(true)						
		.cacheOnDisc(true)						
		.build();	
	}

	public void setData(List<Recv_StoreList> ifoDatas) {
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
		holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater
					.inflate(R.layout.shop_list_item_layout, null);
			holder.ShopName = (TextView) convertView
					.findViewById(R.id.shopItemName);
			holder.ShopIcon = (ImageView) convertView
					.findViewById(R.id.shopIcon);
//			holder.ShopMsg = (TextView) convertView
//					.findViewById(R.id.shopItemMsg);
//			holder.shopComment = (CheckBox) convertView
//					.findViewById(R.id.shopComment);
//			holder.shopDistance = (TextView) convertView
//					.findViewById(R.id.shop_item_distance);
			holder.shopIntro = (TextView) convertView
					.findViewById(R.id.shop_item_intro);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		Recv_StoreList bean = ifoDatas.get(position);
		holder.ShopName.setText(bean.getName());
        holder.shopIntro.setText(bean.getRemark());
//		holder.ShopMsg.setText(bean.getFriends() + "个朋友关注");
//		holder.shopIntro.setText(bean.getAddress());
		//如果没有喜欢该店家
//		if(bean.getIslove().equals(PARAMS.STORE_LOVE)){
//			System.out.println("list check true");
//			holder.shopComment.setChecked(true);
//		}else {
//			System.out.println("list check false");
//			holder.shopComment.setChecked(false);
//		}
//		holder.shopComment.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				mItemCommentClickListener.click(position,holder.shopComment.isChecked());
//			}
//		});
		imageLoader.displayImage(bean.getLogo(), holder.ShopIcon, loadOptions, null);
		return convertView;
	}
	private ViewHolder holder;
	public final class ViewHolder {
		public TextView ShopName;
		public TextView ShopMsg;
		public ImageView ShopIcon;
		public TextView shopIntro;
		public TextView shopDistance;
		public CheckBox shopComment;
	}

	private onItemCommentClickListener mItemCommentClickListener;

	public void setOnItemCommentClickListener(
			onItemCommentClickListener mItemCommentClickListener) {
		this.mItemCommentClickListener = mItemCommentClickListener;
	}

	public interface onItemCommentClickListener {
		void click(int position,boolean ifLike);
	}
}
