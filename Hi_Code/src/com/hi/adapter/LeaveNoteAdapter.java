package com.hi.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.ruifeng.hi.R;
import com.customview.view.CheckBoxRectView;
import com.customview.view.CheckBoxRectView.CallBack;
import com.customview.view.CustomListItemView;
import com.format.utils.DataValidate;
import com.format.utils.FormatUtils;
import com.hi.adapter.MsgItemAdapter.ViewHolder;
import com.hi.common.http.E_Http_Gender;
import com.hi.common.http.E_Http_Praise;
import com.hi.http.local.model.Recv_obtMsg;
import com.hi.module.base.superClass.SuperAdapter;
import com.hi.module.base.superClass.SuperViewHolder;
import com.hi.utils.TimeUtils;
import com.hi.view.HorizontalListView;
import com.hi.view.customAdapter.CommonAdapter;
import com.hi.view.customWidget.ImageView_Gender;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

/**
 * 留言墙列表的Item适配
 * 
 * @author MM_Zerui
 * 
 */
public class LeaveNoteAdapter extends SuperAdapter {
	private LayoutInflater inflater;
	private List<Recv_obtMsg> datas;
	protected DisplayImageOptions loadOptions2;

	public LeaveNoteAdapter(Context context) {
		inflater = LayoutInflater.from(context);
		loadOptions = new DisplayImageOptions.Builder()
				.showImageOnFail(R.drawable.default_img_head)
				.showImageForEmptyUri(R.drawable.default_img_head)
				.showStubImage(R.drawable.default_img_head).cacheInMemory(true)
				.cacheOnDisc(true).build();
		loadOptions2 = new DisplayImageOptions.Builder()
				.showImageForEmptyUri(R.drawable.default_img_load)
				.showImageOnFail(R.drawable.default_img_load)
				.showStubImage(R.drawable.photo_loading).cacheInMemory(true)
				.displayer(new FadeInBitmapDisplayer(300)).cacheOnDisc(true)
				.build();
	}

	public void setDatas(List<Recv_obtMsg> datas) {
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
			convertView = inflater.inflate(R.layout.local_leavenote_list_item2,
					null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.refreshData(position);
		return convertView;
	}

	private callBack call;

	public void setCallBack(callBack call) {
		this.call = call;
	}

	private class ViewHolder extends SuperViewHolder implements OnClickListener {
		private TextView name, age, content, time, favourNum;
		private ImageView img_head, img_content;
		private ImageView_Gender gender;
		private CheckBoxRectView zanCheckBox;
		private int position;
		private RelativeLayout btn_more;
		private HorizontalListView imgListView;
		public ViewHolder(View parent) {
			initView(parent);
		}

		@Override
		public void initView(View parent) {
			name = (TextView) parent.findViewById(R.id.text_name);
			age = (TextView) parent.findViewById(R.id.text_age);
			content = (TextView) parent.findViewById(R.id.text_content);
			time = (TextView) parent.findViewById(R.id.text_time);
			img_head = (ImageView) parent.findViewById(R.id.icon_head);
			img_content = (ImageView) parent.findViewById(R.id.icon_content);
			gender = (ImageView_Gender) parent.findViewById(R.id.icon_gender);
			zanCheckBox = (CheckBoxRectView) parent.findViewById(R.id.btn_zan);
			btn_more = (RelativeLayout) parent.findViewById(R.id.btn_more);
			imgListView=(HorizontalListView)parent.findViewById(R.id.imgListView);
			img_head.setOnClickListener(this);
			img_content.setOnClickListener(this);
			btn_more.setOnClickListener(this);
		}

		@Override
		public void refreshData(final int position) {
			this.position = position;
			try {
				Recv_obtMsg recvBean = datas.get(position);
				name.setText(recvBean.getNickName());
				// age.setText(recvBean.set)
				content.setText(recvBean.getContent());
				imageLoader.displayImage(recvBean.getHead(), img_head,
						loadOptions);
				if (DataValidate.checkDataValid(recvBean.getImg())) {
					img_content.setVisibility(View.VISIBLE);
					imageLoader.displayImage(recvBean.getImg(), img_content,
							loadOptions2);
				} else {
					img_content.setVisibility(View.GONE);
				}
				time.setText(TimeUtils.millisecs2DateString(Long
						.valueOf(recvBean.getTime())));
				zanCheckBox.onNum(Integer.valueOf(recvBean.getFavourNum()));
				if (recvBean.getType().equals(E_Http_Praise.PRAISE.toString())) {
					zanCheckBox.onChecked(true);
				} else {
					zanCheckBox.onChecked(false);
				}
				zanCheckBox.onCheckChangeListener(new CallBack() {

					@Override
					public void onCheck(boolean isCheck) {
						// TODO Auto-generated method stub
						call.onFavour(position, isCheck);
					}
				});
				age.setText(FormatUtils.getAge(recvBean.getBirthDay()));
				if (recvBean.getSex().equals(E_Http_Gender.MAN.toString())) {
					gender.setGender(true);
				} else {
					gender.setGender(false);
				}
			} catch (Exception e) {

			}

		}

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.icon_head:
				call.onHeadClick(position);
				break;
			case R.id.icon_content:
				call.onImageClick(position);
				break;
			case R.id.btn_more:
				break;
			// case R.id.favour_checkbox:
			// call.onFavour(position, favourCheckBox.isChecked());
			// break;
			default:
				break;
			}
		}

	}

	public static abstract class callBack {
		public abstract void onImageClick(int position);

		public abstract void onHeadClick(int position);

		public abstract void onFavour(int position, boolean isCheck);
	}
	class ZanItem{
		private String head;
		private String id;
		public String getHead() {
			return head;
		}
		public void setHead(String head) {
			this.head = head;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		
	}
	/** 点赞头像列表适配器 **/
	class ImgListAdapter extends CommonAdapter<ZanItem>{

		public ImgListAdapter(Context context, List<ZanItem> mDatas,
				int itemLyoutId) {
			super(context, mDatas, itemLyoutId);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void convert(com.hi.view.customAdapter.ViewHolder holder,
				int position, ZanItem item) {
			// TODO Auto-generated method stub
			holder.setHttpImg(R.id.img,item.getHead());
			
		}
		
	}
}
