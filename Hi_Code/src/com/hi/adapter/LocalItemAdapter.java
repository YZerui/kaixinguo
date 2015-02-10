package com.hi.adapter;

import java.util.List;

import com.android.ruifeng.hi.R;
import com.hi.adapter.FriItemAdapter.ViewHolder;
import com.hi.common.PARAMS;
import com.hi.http.member.model.Recv_WifiList;
import com.hi.module.base.superClass.SuperAdapter;
import com.hi.module.base.superClass.SuperViewHolder;
import com.hi.module.msg.model.MsgBean;
import com.hi.utils.FormatUtils;
import com.hi.utils.ViewHandleUtils;
import com.hi.view.RoundedImageView;
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
 * 显示消息列表的Adapter
 * 
 * @author MM_Zerui
 */
public class LocalItemAdapter extends SuperAdapter {
	private LayoutInflater inflater;
	private List<Recv_WifiList> ifoDatas;
	public LocalItemAdapter(Context context) {
		inflater = LayoutInflater.from(context);
		loadOptions = new DisplayImageOptions.Builder()
				.showImageOnFail(R.drawable.default_img_head)
				.showImageForEmptyUri(R.drawable.default_img_head)
				.showStubImage(R.drawable.default_img_head)
				.cacheInMemory(true).cacheOnDisc(true).build();
	}

	public void setData(List<Recv_WifiList> ifoDatas) {
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
			
			convertView = inflater.inflate(R.layout.friend_page_list_item, null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.refreshData(position);
		return convertView;
	}

	public final class ViewHolder  extends SuperViewHolder{
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
			Recv_WifiList bean = ifoDatas.get(position);
			title.setText(bean.getNickName());
			imageLoader.displayImage(bean.getHead(),head,
					loadOptions, null);
			content.setText(bean.getCurrentState());
		}

	}
}
