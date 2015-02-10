package com.hi.adapter;

import java.util.List;

import com.android.ruifeng.hi.R;
import com.constant.COMMON;
import com.format.utils.DataValidate;
import com.hi.common.http.E_Http_SendState;
import com.hi.dao.model.T_IMsgSeq;
import com.hi.module.base.superClass.SuperAdapter;
import com.hi.module.base.superClass.SuperViewHolder;
import com.hi.receiver.model.IMMessageBean.Enum_IM_MsgFormat;
import com.hi.utils.DBUtils;
import com.hi.utils.EmotionUtils;
import com.hi.utils.FormatUtils;
import com.hi.utils.TimeUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 显示消息列表的Adapter
 * 
 * @author MM_Zerui
 */
public class MsgItemAdapter extends SuperAdapter {
	private LayoutInflater inflater;
	private List<T_IMsgSeq> msgDatas;
	private Context context;

	public MsgItemAdapter(Context context) {
		// TODO Auto-generated constructor stub
		super();
		this.context = context;
		inflater = LayoutInflater.from(context);
		loadOptions = new DisplayImageOptions.Builder()
				.showImageForEmptyUri(R.drawable.default_img_head)
				.showImageOnFail(R.drawable.default_img_head)
				// .showStubImage(R.drawable.default_img_head)
				// .showStubImage(R.drawable.app_icon)
				.cacheInMemory(true).cacheOnDisc(true).build();
	}

	public void setData(List<T_IMsgSeq> msgDatas) {
		this.msgDatas = msgDatas;
	}

	public void updateListView(List<T_IMsgSeq> msgDatas) {
		this.msgDatas = msgDatas;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return msgDatas.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return msgDatas.get(position);
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
			convertView = inflater.inflate(R.layout.msg_page_list_item, null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.refreshData(position);
		return convertView;
	}

	public final class ViewHolder extends SuperViewHolder {
		private ImageView head, unReadNote;
		private TextView unRead, title, content, rightNote;
		private RelativeLayout unReadLayout, unReadNumLayout;

		public ViewHolder(View parent) {
			// TODO Auto-generated constructor stub
			initView(parent);
		}

		@Override
		public void initView(View parent) {
			// TODO Auto-generated method stub
			head = (ImageView) parent.findViewById(R.id.fixIcon);
			unReadNote = (ImageView) parent.findViewById(R.id.fix_unreadNote);
			unRead = (TextView) parent.findViewById(R.id.fix_unreadNumText);
			title = (TextView) parent.findViewById(R.id.fixDouble_title);
			content = (TextView) parent.findViewById(R.id.fixDouble_content);
			rightNote = (TextView) parent.findViewById(R.id.fixDouble_timer);
			unReadLayout = (RelativeLayout) parent
					.findViewById(R.id.fix_unreadLayout);
			unReadNumLayout = (RelativeLayout) parent
					.findViewById(R.id.fix_unreadNumLayout);
		}

		@Override
		public void refreshData(int position) {
			// TODO Auto-generated method stub
			T_IMsgSeq bean = msgDatas.get(position);
//			StringBuilder contentBuilder=new StringBuilder();
			SpannableStringBuilder contentBuilder=new SpannableStringBuilder();
//			CharSequence contentChar=new 
			title.setText(bean.getName());
			rightNote.setText(TimeUtils.millisecs2DateString(bean.getTime()));
			E_Http_SendState enumState=E_Http_SendState.get(bean.getSendState());
			switch (enumState) {
			case FAIL:
				contentBuilder.append(getRedText("发送失败 ",""));
				break;
			case HIDE:
				break;
			case NORMAL:
				break;
			case SUCCESS:
				break;
			default:
				break;
			}
			Enum_IM_MsgFormat enumFormat = Enum_IM_MsgFormat.get(bean
					.getMsgFormat());
			switch (enumFormat) {
			case TEXT:
				contentBuilder.append(EmotionUtils.replace(context, bean.getMsg()));
//				content.setText(EmotionUtils.replace(context, bean.getMsg()));
				break;
			case IMAGE:
				contentBuilder.append(getNoteText("[图片]",""));
//				content.setText(getNoteText("[图片]",""));
				break;
			case RADIO:
				contentBuilder.append(getNoteText("[语音]",""));
//				content.setText(getNoteText("[语音]",""));
				break;
			case LOCATION:
				contentBuilder.append(getNoteText("[位置]",""));
//				content.setText(getNoteText("[位置]",""));
				break;
			case UNKNOW:
				contentBuilder.append(getNoteText("[未知消息]",""));
//				content.setText(getNoteText("[未知消息]",""));
				break;
			default:
				break;
			}
			content.setText(contentBuilder);
			String editText=DBUtils.getSharedPreStr(context, bean.getUid(),null);
			//查看是否有缓存
			if(DataValidate.checkDataValid(editText)){
//				contentBuilder.
				content.setText(getNoteText("[草稿]", " "+editText));
			}
			// rightNote.setText(FormatUtils.getListItemTime(bean.getTime()));
			imageLoader.displayImage(bean.getHead(), head, loadOptions, null);
			if (bean.getUnRead() == 0) {
				unReadLayout.setVisibility(View.INVISIBLE);
			} else {
				unReadLayout.setVisibility(View.VISIBLE);
				// 如果未读信息数大于9，则以一个小红点表示
				if (bean.getUnRead() > 9) {
					unReadNumLayout.setVisibility(View.INVISIBLE);
					unReadNote.setVisibility(View.VISIBLE);
				} else {
					unReadNumLayout.setVisibility(View.VISIBLE);
					unRead.setText("" + bean.getUnRead());
				}
			}
		}
	}

	public SpannableString getNoteText(String note, String content) {
		SpannableString spannableString = new SpannableString(note + content);
		ForegroundColorSpan textColorSpan = new ForegroundColorSpan(
				COMMON.COLOR_THEME);
		spannableString.setSpan(textColorSpan, 0, note.length(),
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		return spannableString;

	}
	public SpannableString getRedText(String note, String content) {
		SpannableString spannableString = new SpannableString(note + content);
		ForegroundColorSpan textColorSpan = new ForegroundColorSpan(
				COMMON.COLOR_RED);
		spannableString.setSpan(textColorSpan, 0, note.length(),
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		return spannableString;
		
	}
}
