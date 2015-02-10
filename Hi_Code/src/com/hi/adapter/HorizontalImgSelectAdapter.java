package com.hi.adapter;

import java.util.ArrayList;

import com.android.ruifeng.hi.R;
import com.hi.adapter.FriItemAdapter.ViewHolder;
import com.hi.module.base.superClass.SuperAdapter;
import com.hi.module.friend.model.RecvFriendsBean;
import com.hi.module.self.model.FriendSelectBean;
import com.hi.view.RoundedImageView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

/**
 * 水平陈列的头像列表
 * 
 * @author MM_Zerui
 * 
 */
public class HorizontalImgSelectAdapter extends SuperAdapter {
	private ArrayList<RecvFriendsBean> items;
	private LayoutInflater inflater;

	public HorizontalImgSelectAdapter(Context context) {
		// TODO Auto-generated constructor stub
		super();
		inflater = LayoutInflater.from(context);
		loadOptions= new DisplayImageOptions.Builder()			
		.showImageForEmptyUri(R.drawable.default_img_head)	
		.showImageOnFail(R.drawable.default_img_head)
		.showImageForEmptyUri(R.drawable.default_img_head)
		.showStubImage(R.drawable.default_img_head)
//		.showStubImage(R.drawable.app_icon)
		.cacheInMemory(true)						
		.cacheOnDisc(true)						
		.build();	
	}
	public void setListItems(ArrayList<RecvFriendsBean> items){
		this.items=items;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return items.get(position);
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
			convertView = inflater.inflate(
					R.layout.activity_select_img_horizontal_layout, null);
			holder.head = (RoundedImageView) convertView
					.findViewById(R.id.selectRoundImage);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		if(items.get(position).getHead().equals("DEFAULT_IMG")){
			holder.head.setImageResource(R.drawable.self_img_wall_bg);
		}else {
			imageLoader.displayImage(items.get(position).getHead(), holder.head, loadOptions,
					null);
		}
	
		return convertView;
	}

	public final class ViewHolder {
		private RoundedImageView head;
		// private TextView name;
		// private CheckBox selectBox;
	}
	// private onListItemClickListener listItemClickListener;
	//
	// public void setOnListItemClickListener(
	// onListItemClickListener listItemClickListener) {
	// this.listItemClickListener = listItemClickListener;
	// }
	//
	// public interface onListItemClickListener {
	// void click(Boolean item,int position);
	// }
}
