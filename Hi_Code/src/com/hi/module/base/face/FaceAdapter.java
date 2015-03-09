package com.hi.module.base.face;

import java.util.List;

import com.android.ruifeng.hi.R;


import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 *
 ******************************************
 * @author ���˲�
 * @�ļ�����	:  FaceAdapter.java
 * @����ʱ��	: 2013-1-27 ����02:34:01
 * @�ļ�����	: ���������
 ******************************************
 */
public class FaceAdapter extends BaseAdapter {

    private List<ChatEmoji> data;

    private LayoutInflater inflater;

    private int size=0;
    private callBack call;
    public FaceAdapter(Context context, List<ChatEmoji> list) {
        this.inflater=LayoutInflater.from(context);
        this.data=list;
        this.size=list.size();
    }
    public void setCallBack(callBack call){
        this.call=call;
    }
    @Override
    public int getCount() {
        return this.size;
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ChatEmoji emoji=data.get(position);
        ViewHolder viewHolder=null;
        if(convertView == null) {
            viewHolder=new ViewHolder();
            convertView=inflater.inflate(R.layout.chat_emotion_item, null);
            viewHolder.iv_face=(ImageView)convertView.findViewById(R.id.emotionImageView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder=(ViewHolder)convertView.getTag();
        }
        if(emoji.getId() == R.drawable.face_del_icon) {
            convertView.setBackgroundDrawable(null);
            viewHolder.iv_face.setImageResource(emoji.getId());
//            viewHolder.iv_face.setOnClickListener(new OnClickListener() {
//				
//				@Override
//				public void onClick(View v) {
//					// TODO Auto-generated method stub
//					call.del();
//				}
//			});
        } else if(TextUtils.isEmpty(emoji.getCharacter())) {
            convertView.setBackgroundDrawable(null);
            viewHolder.iv_face.setImageDrawable(null);
        } else {
            viewHolder.iv_face.setTag(emoji);
            viewHolder.iv_face.setImageResource(emoji.getId());
        }

        return convertView;
    }
    class ViewHolder {

        public ImageView iv_face;
    }
    public static abstract class callBack{
        public abstract void del();
    }
}