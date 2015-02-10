package com.hi.adapter;

import java.util.List;

import com.android.ruifeng.hi.R;
import com.customview.view.CustomListItemView;
import com.format.utils.DataValidate;
import com.hi.adapter.MsgItemAdapter.ViewHolder;
import com.hi.common.PARAMS;
import com.hi.common.db.E_DB_SelfIfo;
import com.hi.common.writing.W_UserInfo;
import com.hi.dao.model.T_MyFriends;
import com.hi.module.base.superClass.SuperAdapter;
import com.hi.module.base.superClass.SuperViewHolder;
import com.hi.utils.FormatUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * @author MM_Zerui 显示朋友列表的Adapter
 */
public class FriItemAdapter extends SuperAdapter {
	private LayoutInflater inflater;
	private List<T_MyFriends> friDatas;

	public FriItemAdapter(Context context) {
		super();
		inflater = LayoutInflater.from(context);
		loadOptions= new DisplayImageOptions.Builder()			
		.showImageForEmptyUri(R.drawable.default_img_head)	
		.showImageOnFail(R.drawable.default_img_head)
		.showStubImage(R.drawable.default_img_head)
//		.showStubImage(R.drawable.app_icon)
		.cacheInMemory(true)						
		.cacheOnDisc(true)						
		.build();		
	}

	public void setData(List<T_MyFriends> friDatas) {
		this.friDatas = friDatas;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return friDatas.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return friDatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	@Override
	public void notifyDataSetChanged() {
		// TODO Auto-generated method stub
		super.notifyDataSetChanged();
	}
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if (convertView == null) {
			
			convertView = inflater.inflate(R.layout.friend_page_list_item, null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.refreshData(position);
		
//		if (DataValidate.checkDataValid(bean.getType())) {
//			switch (Integer.valueOf(bean.getType())) {
//			// 关注我的
//			case PARAMS.FOLLOW_ME:
//				holder.item.setRightImg(R.drawable.follow_me_note_icon);
//				break;
//			// 我关注的
//			case PARAMS.FOLLOW_OTHER:
//				holder.item.setRightImg(R.drawable.follow_other_note_icon);
//				break;
//			// 互相关注的
//			case PARAMS.FOLLOW_EACH_OTHER:
//				holder.item.setRightImg(R.drawable.follow_each_other_note_icon);
//				break;
//			default:
//				break;
//			}
//
//		}
		return convertView;
	}
	public final class ViewHolder extends SuperViewHolder{
		private ImageView head,unReadNote;
		private TextView unRead,title,content,rightNote;
		private RelativeLayout unReadLayout,unReadNumLayout;
		
		public ViewHolder(View parent) {
			// TODO Auto-generated constructor stub
			initView(parent);
		}
		@Override
		public void initView(View parent) {
			// TODO Auto-generated method stub
			head=(ImageView)parent.findViewById(R.id.fixIcon);
			unReadNote=(ImageView)parent.findViewById(R.id.fix_unreadNote);
			unRead=(TextView)parent.findViewById(R.id.fix_unreadNumText);
			title=(TextView)parent.findViewById(R.id.fixDouble_title);
			content=(TextView)parent.findViewById(R.id.fixDouble_content);
			rightNote=(TextView)parent.findViewById(R.id.fixDouble_timer);
			unReadLayout=(RelativeLayout)parent.findViewById(R.id.fix_unreadLayout);
			unReadNumLayout=(RelativeLayout)parent.findViewById(R.id.fix_unreadNumLayout);
		}
		@Override
		public void refreshData(int position) {
			// TODO Auto-generated method stub
			T_MyFriends bean = friDatas.get(position);
			W_UserInfo.Default(E_DB_SelfIfo.nickName,title,bean.getNickName());
			W_UserInfo.Default(E_DB_SelfIfo.currentState, content,bean.getCurrentState());
			imageLoader.displayImage(bean.getHead(),head, loadOptions, null);
		}
	}
}
