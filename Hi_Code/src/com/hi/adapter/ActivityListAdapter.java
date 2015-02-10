//package com.hi.adapter;
//
//import java.util.ArrayList;
//
//
//import com.android.ruifeng.hi.R;
//import com.hi.a.base.superClass.SuperAdapter;
//import com.hi.a.self.model.RecvActivityBean;
//import com.hi.custom.view.RoundedImageView;
//import com.hi.utils.FormatUtils;
//
//import com.nostra13.universalimageloader.core.DisplayImageOptions;
//
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
///**
// * 活动列表
// * @author MM_Zerui 
// */
//public class ActivityListAdapter extends SuperAdapter {
//	private LayoutInflater inflater;
//	private ArrayList<RecvActivityBean> datas;
//	private Context context;
//	public ActivityListAdapter(Context context) {
//		// TODO Auto-generated constructor stub
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
//		this.context = context;
//	}
//
//	public void setData(ArrayList<RecvActivityBean> datas) {
//		this.datas = datas;
//	}
//
//	@Override
//	public int getCount() {
//		// TODO Auto-generated method stub
//		return datas.size();
//	}
//
//	@Override
//	public Object getItem(int position) {
//		// TODO Auto-generated method stub
//		return datas.get(position);
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
//			holder = new ViewHolder();
//			convertView = inflater.inflate(R.layout.self_activity_list_layout, null);
//			holder.genderIcon=(ImageView)convertView.findViewById(R.id.activityPeoGender);
//			holder.head=(RoundedImageView)convertView.findViewById(R.id.activityPeoIcon);
//			holder.interest=(TextView)convertView.findViewById(R.id.activity_interest_text);
//			holder.invite=(TextView)convertView.findViewById(R.id.activity_invite_text);
//			holder.msg=(TextView)convertView.findViewById(R.id.activity_content);
//			holder.time=(TextView)convertView.findViewById(R.id.activity_lunch_time);
//			holder.inviteBtn=(RelativeLayout)convertView.findViewById(R.id.activity_item_invite);
//			holder.interestBtn=(Button)convertView.findViewById(R.id.activity_interest_btn);
//			convertView.setTag(holder);
//		} else {
//			holder = (ViewHolder) convertView.getTag();
//		}
//		RecvActivityBean bean = datas.get(position);
//		holder.interest.setText("感兴趣 "+ bean.getJoinTimes());
//		imageLoader.displayImage(bean.getHead(), holder.head, loadOptions,
//				null);
//		holder.invite.setText("已邀请 "+bean.getInviteTimes());
//		holder.msg.setText(bean.getRemark());
//		holder.time.setText(FormatUtils.getDateTime(bean.getBuildTimes()));
//		//邀请的触发事件
//		holder.inviteBtn.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				listItemClickListener.click(ActivityListPage.ACTIVITY_INVITE_TRIGGER, position);
//			}
//		});
//		//感兴趣的触发事件
//		holder.interestBtn.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				listItemClickListener.click(ActivityListPage.ACTIVITY_INTEREST_TRIGGER, position);
//			}
//		});
//		return convertView;
//	}
//
//	public final class ViewHolder {
//		public TextView invite;
//		public TextView interest;
//		public TextView msg;
//		public TextView time;
//		public RoundedImageView head;
//		public ImageView genderIcon;
//		public Button interestBtn;
//		public RelativeLayout inviteBtn;
//	}
//	private onListItemClickListener listItemClickListener;
//
//	public void setOnListItemClickListener(
//			onListItemClickListener listItemClickListener) {
//		this.listItemClickListener = listItemClickListener;
//	}
//
//	public interface onListItemClickListener {
//		void click(int item,int position);
//	}
//}
