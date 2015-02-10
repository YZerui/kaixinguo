//package com.hi.adapter;
//
//import java.util.ArrayList;
//
//import com.android.ruifeng.hi.R;
//import com.hi.a.base.superClass.SuperAdapter;
//import com.hi.a.friend.model.RecvFriendsBean;
//import com.hi.a.self.model.FriendSelectBean;
//import com.hi.a.self.ui.FriendSelectActivity;
//import com.hi.adapter.ActivityListAdapter.onListItemClickListener;
//import com.hi.adapter.FriItemAdapter.ViewHolder;
//import com.hi.custom.view.RoundedImageView;
//import com.nostra13.universalimageloader.core.DisplayImageOptions;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.CheckBox;
//import android.widget.CompoundButton;
//import android.widget.CompoundButton.OnCheckedChangeListener;
//import android.widget.TextView;
//
///**
// * 选择朋友的列表适配器
// * @author MM_Zerui
// * @tip_1 这里以RecvFriendsBean中的type作为是否选中的标识
// *
// */
//public class FriendSelectAdapter extends SuperAdapter{
//	private ArrayList<RecvFriendsBean> items;
//	private LayoutInflater inflater;
//	
//	public FriendSelectAdapter(Context context) {
//		// TODO Auto-generated constructor stub
//		super();
//		inflater = LayoutInflater.from(context);
//		loadOptions= new DisplayImageOptions.Builder()			
//		.showImageForEmptyUri(R.drawable.default_img_head)	
//		.showImageOnFail(R.drawable.default_img_head)
//		.showImageForEmptyUri(R.drawable.default_img_head)
//		.showStubImage(R.drawable.default_img_head)
////		.showStubImage(R.drawable.app_icon)
//		.cacheInMemory(true)						
//		.cacheOnDisc(true)						
//		.build();		
//	}
//	public void setListItems(ArrayList<RecvFriendsBean> items){
////		this.items.clear();
//	
//		this.items=items;
//		
//	}
//	@Override
//	public int getCount() {
//		// TODO Auto-generated method stub
//		return items.size();
//	}
//
//	@Override
//	public Object getItem(int position) {
//		// TODO Auto-generated method stub
//		return items.get(position);
//	}
//
//	@Override
//	public long getItemId(int position) {
//		// TODO Auto-generated method stub
//		return position;
//	}
//
//	@Override
//	public View getView(final int position, View convertView, ViewGroup parent) {
//		// TODO Auto-generated method stub
//		ViewHolder holder = null;
//		if (convertView == null) {
//			holder=new ViewHolder();
//			convertView=inflater.inflate(R.layout.activity_friend_select_item,null);
//			holder.head=(RoundedImageView)convertView.findViewById(R.id.friendIcon);
//			holder.name=(TextView)convertView.findViewById(R.id.friend_name);
//			holder.selectBox=(CheckBox)convertView.findViewById(R.id.friendSelectBtn);
//			convertView.setTag(holder);
//		}else {
//			holder = (ViewHolder) convertView.getTag();
//		}
//		RecvFriendsBean bean=items.get(position);
//		if(bean.getHead()!=null){
//			imageLoader.displayImage(bean.getHead(),holder.head, loadOptions, null);
//		}
//		
//		holder.name.setText(bean.getNickName());
//		if(bean!=null&&bean.getType().equals(FriendSelectActivity.FRIEND_SELECT_NOTE)){
//			holder.selectBox.setChecked(true);
//		}else {
//			holder.selectBox.setChecked(false);
//		}
//		holder.selectBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//			
//			@Override
//			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//				// TODO Auto-generated method stub
//				listItemClickListener.click(isChecked, position);
//			}
//		});
//		return convertView;
//	}
//	public class ViewHolder{
//		private RoundedImageView head;
//		private TextView name;
//		private CheckBox selectBox;
//	}
//	private onListItemClickListener listItemClickListener;
//
//	public void setOnListItemClickListener(
//			onListItemClickListener listItemClickListener) {
//		this.listItemClickListener = listItemClickListener;
//	}
//
//	public interface onListItemClickListener {
//		void click(Boolean item,int position);
//	}
//}
