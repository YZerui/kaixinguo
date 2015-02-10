package com.hi.adapter;

import java.util.List;

import com.android.ruifeng.hi.R;
import com.hi.adapter.ShopItemAdapter.onItemCommentClickListener;
import com.hi.common.PARAMS;
import com.hi.dao.model.T_Contacts;
import com.hi.dao.supImpl.Dao_Friends;
import com.hi.module.friend.model.ContactSortBean;
import com.hi.utils.DBUtils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.SectionIndexer;
import android.widget.TextView;

/**
 * @author MM_Zerui
 * 
 */
public class ContactsSortAdapter extends BaseAdapter implements SectionIndexer {
	private List<T_Contacts> list = null;
	private Context mContext;
	private Dao_Friends friendDaoImpl;

	public ContactsSortAdapter(Context mContext, List<T_Contacts> list) {
		this.mContext = mContext;
		this.list = list;
		friendDaoImpl = new Dao_Friends();
	}

	/**
	 * ��ListView���ݷ����仯ʱ,���ô˷���������ListView
	 * 
	 * @param list
	 */
	public void updateListView(List<T_Contacts> list) {
		this.list = list;
		notifyDataSetChanged();
	}

	public int getCount() {
		return this.list.size();
	}

	public Object getItem(int position) {
		return list.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getView(int, android.view.View,
	 * android.view.ViewGroup)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getView(int, android.view.View,
	 * android.view.ViewGroup)
	 */
	public View getView(final int position, View view, ViewGroup arg2) {
		ViewHolder viewHolder = null;
		final T_Contacts mContent = list.get(position);
		if (view == null) {
			viewHolder = new ViewHolder();
			view = LayoutInflater.from(mContext).inflate(
					R.layout.friend_contact_item_layout, null);
			viewHolder.tvTitle = (TextView) view.findViewById(R.id.contactName);
			viewHolder.tvLetter = (TextView) view.findViewById(R.id.catalog);
			viewHolder.followBtn = (Button) view
					.findViewById(R.id.contactAddBtn);
			viewHolder.followNote = (TextView) view
					.findViewById(R.id.contactFollowText);
			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}
		// ע��״̬
		if (mContent.getIfRegister() == Integer
				.valueOf(PARAMS.CONTACT_REGISTER)) {
			// �жϺͱ����û��Ƿ�Ϊ��ע״̬
//			if (friendDaoImpl.checkFollowRelate(mContent.getUid())) {
//				// ����ѹ�ע�Է�
//				viewHolder.followBtn.setVisibility(View.INVISIBLE);
//				viewHolder.followNote.setVisibility(View.VISIBLE);
//
//			} else {
//				viewHolder.followBtn.setVisibility(View.VISIBLE);
//				viewHolder.followNote.setVisibility(View.INVISIBLE);
//				viewHolder.followBtn.setOnClickListener(new OnClickListener() {
//
//					@Override
//					public void onClick(View v) {
//						// TODO Auto-generated method stub
//						if (mItemFollowClickListener != null) {
//							mItemFollowClickListener.click(position);
//						}
//					}
//				});
//			}
		} else {
			viewHolder.followBtn.setVisibility(View.INVISIBLE);
			viewHolder.followNote.setVisibility(View.INVISIBLE);
		}

		// ����position��ȡ���������ĸ��Char asciiֵ
		int section = getSectionForPosition(position);

		// �����ǰλ�õ��ڸ÷�������ĸ��Char��λ�� ������Ϊ�ǵ�һ�γ���
		if (position == getPositionForSection(section)) {
			viewHolder.tvLetter.setVisibility(View.VISIBLE);
			viewHolder.tvLetter.setText(mContent.getFirstLetter());
		} else {
			viewHolder.tvLetter.setVisibility(View.GONE);
		}

		viewHolder.tvTitle.setText(this.list.get(position).getName());

		return view;

	}

	final static class ViewHolder {
		TextView tvLetter;
		TextView tvTitle;
		TextView followNote;
		Button followBtn;
	}

	/**
	 * ����ListView�ĵ�ǰλ�û�ȡ���������ĸ��Char asciiֵ
	 */
	public int getSectionForPosition(int position) {
		return list.get(position).getFirstLetter().charAt(0);
	}

	/**
	 * ���ݷ��������ĸ��Char asciiֵ��ȡ���һ�γ��ָ�����ĸ��λ��
	 */
	public int getPositionForSection(int section) {
		for (int i = 0; i < getCount(); i++) {
			String sortStr = list.get(i).getFirstLetter();
			char firstChar = sortStr.toUpperCase().charAt(0);
			if (firstChar == section) {
				return i;
			}
		}

		return -1;
	}

	private onItemFollowClickListener mItemFollowClickListener;

	public void setOnItemFollowClickListener(
			onItemFollowClickListener mItemFollowClickListener) {
		this.mItemFollowClickListener = mItemFollowClickListener;
	}

	public interface onItemFollowClickListener {
		void click(int position);
	}

	/**
	 * ��ȡӢ�ĵ�����ĸ����Ӣ����ĸ��#���档
	 * 
	 * @param str
	 * @return
	 */
	private String getAlpha(String str) {
		String sortStr = str.trim().substring(0, 1).toUpperCase();
		// ������ʽ���ж�����ĸ�Ƿ���Ӣ����ĸ
		if (sortStr.matches("[A-Z]")) {
			return sortStr;
		} else {
			return "#";
		}
	}

	@Override
	public Object[] getSections() {
		return null;
	}
}