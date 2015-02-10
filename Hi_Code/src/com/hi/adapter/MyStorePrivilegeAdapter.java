package com.hi.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.ruifeng.hi.R;
import com.hi.http.coupons.model.Recv_CouponsList;
import com.hi.http.store.model.Recv_StoreList;
import com.hi.module.base.superClass.SuperAdapter;
import com.hi.module.base.superClass.SuperViewHolder;
import com.hi.utils.DeviceUtils;
import com.hi.utils.FormatUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.xlistview.XListView;

/**
 * 显示我所联系商家的列表（优惠劵板块）
 * 
 * @author MM_Zerui
 * 
 */
public class MyStorePrivilegeAdapter extends SuperAdapter {
	private LayoutInflater inflater;
	private Context context;
	private List<Recv_StoreList> listDatas;
	private CallListItem callBack;

	public MyStorePrivilegeAdapter(Context context) {
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

	public void setData(List<Recv_StoreList> listDatas) {
		this.listDatas = listDatas;
	}

	public void setCallBack(CallListItem callBack) {
		this.callBack = callBack;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listDatas.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listDatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = inflater.inflate(
					R.layout.shop_privilege_list_item_layout, null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.refreshData(position);
		return convertView;
	}

	public static abstract class CallListItem {
		public abstract void onExpandClick(int position, XListView xListView);
	
		// public abstract void onExpandList(int position,XListView xListView);
	}

	private class ViewHolder extends SuperViewHolder implements OnClickListener {
		private ImageView head;
		private ImageView expandIcon;
		private TextView name;
		private int position;
		private XListView expandList;

		public ViewHolder(View parent) {
			initView(parent);
		}

		@Override
		public void initView(View parent) {
			// TODO Auto-generated method stub
			head = (ImageView) parent.findViewById(R.id.storeIcon);
			expandIcon = (ImageView) parent.findViewById(R.id.expandIcon);
			name = (TextView) parent.findViewById(R.id.storeName);
			expandList = (XListView) parent.findViewById(R.id.expandList);
//			expandList.setAdapter(new MyStorePrivilegeItemAdapter(context));
//			ViewGroup.LayoutParams params = expandList.getLayoutParams();
//			params.height=DeviceUtils.dip2px(context, 70) * 6;
//			expandList.setLayoutParams(params);
		}

		@Override
		public void refreshData(int position) {
			// TODO Auto-generated method stub
			this.position = position;
			Recv_StoreList recvBean = listDatas.get(position);
			imageLoader.displayImage(recvBean.getLogo(), head, loadOptions);
			name.setText(recvBean.getName());
			expandIcon.setOnClickListener(this);
			// callBack.onExpandList(position, expandList);
		}

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.expandIcon:
				callBack.onExpandClick(position, expandList);
				break;

			default:
				break;
			}
		}

	}
}
