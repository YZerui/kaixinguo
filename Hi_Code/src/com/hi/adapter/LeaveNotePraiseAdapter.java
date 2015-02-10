package com.hi.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.ruifeng.hi.R;
import com.customview.view.ImageView_Rounded;
import com.hi.http.local.model.Recv_praiseList;
import com.hi.module.base.superClass.SuperAdapter;
import com.hi.module.base.superClass.SuperViewHolder;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

public class LeaveNotePraiseAdapter extends SuperAdapter {
	private LayoutInflater inflater;
	private List<Recv_praiseList> datas;

	public LeaveNotePraiseAdapter(Context context) {
		// TODO Auto-generated constructor stub
		inflater = LayoutInflater.from(context);
		loadOptions = new DisplayImageOptions.Builder()
				.showImageForEmptyUri(R.drawable.default_img_head)
				.showImageOnFail(R.drawable.default_img_head)
				.showImageForEmptyUri(R.drawable.default_img_head)
				.showStubImage(R.drawable.default_img_head).cacheInMemory(true)
				.cacheOnDisc(true).build();
	}

	public void setDatas(List<Recv_praiseList> datas) {
		this.datas = datas;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return datas.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.leave_note_praise_listitem,
					null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.refreshData(position);
		return convertView;
	}

	private class ViewHolder extends SuperViewHolder {
		private ImageView_Rounded image;
		private TextView text;

		public ViewHolder(View parent) {
			// TODO Auto-generated constructor stub
			initView(parent);
		}

		@Override
		public void initView(View parent) {
			// TODO Auto-generated method stub
			image = (ImageView_Rounded) parent.findViewById(R.id.ItemImage);
			text = (TextView) parent.findViewById(R.id.ItemText);
		}

		@Override
		public void refreshData(int position) {
			// TODO Auto-generated method stub
			Recv_praiseList recvBean = datas.get(position);
			imageLoader.displayImage(recvBean.getHead(), image, loadOptions);
			text.setText(recvBean.getNickName());
		}

	}
}
