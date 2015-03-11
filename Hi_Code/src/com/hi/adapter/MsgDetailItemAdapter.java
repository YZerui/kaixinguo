package com.hi.adapter;

import java.util.List;

import com.android.ruifeng.hi.R;
import com.hi.common.db.E_DB_ImgUrlMode;
import com.hi.common.db.E_DB_MsgSource;
import com.hi.common.db.E_DB_MsgType;
import com.hi.common.http.E_Http_SendState;
import com.hi.dao.model.T_IMsg;
import com.hi.dao.supImpl.Dao_SelfIfo;
import com.hi.module.base.face.FaceConversionUtil;
import com.hi.module.base.superClass.SuperAdapter;
import com.hi.module.base.superClass.SuperViewHolder;
import com.hi.receiver.model.IMMessageBean.Enum_IM_MsgFormat;
import com.hi.receiver.model.IMMessageBean.Enum_IM_MsgType;
import com.hi.utils.EmotionUtils;
import com.hi.utils.FormatUtils;
import com.hi.utils.TimeUtils;
import com.hi.utils.ViewHandleUtils;
import com.imagefetch.util.ImageDisplayer;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * @author MM_Zerui 消息详情页列表
 */
public class MsgDetailItemAdapter extends SuperAdapter {
    private LayoutInflater inflater;
    private List<T_IMsg> datas;
    private Context context;
    DisplayImageOptions loadOptions2;
    private callBack call;

    public MsgDetailItemAdapter(Context context) {
        // TODO Auto-generated constructor stub
        super();
        inflater = LayoutInflater.from(context);
        this.context = context;
        loadOptions = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.default_img_head)
                .showImageOnFail(R.drawable.default_img_head)

                .cacheInMemory(true)
                .cacheOnDisc(true)
                .build();
        loadOptions2 = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.default_load_img)
                .showImageOnFail(R.drawable.default_load_img)
                .showStubImage(R.drawable.default_load_img)
                .cacheInMemory(true)
                .cacheOnDisc(true)
                .build();
    }

    public void setCallBack(callBack call) {
        this.call = call;

    }

    public void setData(List<T_IMsg> datas) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.msg_detail_list_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.refreshData(position);
        return convertView;
    }

    public final class ViewHolder extends SuperViewHolder implements OnClickListener {
        public TextView title;
        public TextView msg;
        public TextView time;
        public ImageView head;
        public ImageView noteIcon;
        public ImageView imgContent;
        public ProgressBar progressBar;

        private int position;

        public ViewHolder(View parent) {
            // TODO Auto-generated constructor stub
            initView(parent);
        }

        @Override
        public void initView(View convertView) {
            // TODO Auto-generated method stub
            msg = (TextView) convertView
                    .findViewById(R.id.msg_detail_content);
            title = (TextView) convertView
                    .findViewById(R.id.msg_detail_title);
            time = (TextView) convertView
                    .findViewById(R.id.msg_detail_time);
            head = (ImageView) convertView
                    .findViewById(R.id.msg_detail_icon);
            noteIcon = (ImageView) convertView
                    .findViewById(R.id.msg_detail_note_icon);
            imgContent = (ImageView) convertView.findViewById(R.id.img);
            progressBar = (ProgressBar) convertView.findViewById(R.id.customProgress);
        }

        @Override
        public void refreshData(int position) {
            // TODO Auto-generated method stub
            imgContent.setOnClickListener(this);
            this.position = position;
            T_IMsg bean = datas.get(position);
            head.setOnClickListener(this);
            if (position == 0
                    || TimeUtils.haveTimeGap(
                    datas.get(position - 1).getTime(),
                    bean.getTime())) {
                time.setVisibility(View.VISIBLE);
                time.setText(TimeUtils.millisecs2DateString(bean
                        .getTime()));
            } else {
                time.setVisibility(View.GONE);
            }
            if (bean.getIdentity() != E_DB_MsgSource.SELF.value()) {
                imageLoader.displayImage(bean.getHead(), head, loadOptions,
                        null);
                noteIcon.setImageResource(R.drawable.fly_msg_accost);

            } else {
                imageLoader.displayImage(Dao_SelfIfo.getInstance().getHead(), head,
                        loadOptions, null);
                noteIcon.setImageResource(R.drawable.fly_msg_accost_green);
            }
            switch (E_Http_SendState.get(bean.getSendState())) {
                //默认状态
                case NORMAL:
                    title.setVisibility(View.INVISIBLE);
                    progressBar.setVisibility(View.VISIBLE);
                    noteIcon.setVisibility(View.INVISIBLE);
                    break;
                //准备发送
                case START:
                    title.setVisibility(View.VISIBLE);
                    title.setTextColor(0x55000000);
                    title.setText("发送...");
                    progressBar.setVisibility(View.VISIBLE);
                    noteIcon.setVisibility(View.INVISIBLE);
                    break;
                //发送成功
                case SUCCESS:
                    title.setVisibility(View.VISIBLE);
                    title.setTextColor(0x55000000);
                    title.setText("发送成功");
                    progressBar.setVisibility(View.INVISIBLE);
                    noteIcon.setVisibility(View.VISIBLE);
                    break;
                //消息到达对方
                case DELIVERED:
                    title.setVisibility(View.INVISIBLE);
                    progressBar.setVisibility(View.INVISIBLE);
                    noteIcon.setVisibility(View.VISIBLE);
                    break;
                case FAIL:
                    title.setVisibility(View.VISIBLE);
                    title.setText("发送失败");
                    title.setTextColor(Color.RED);
                    progressBar.setVisibility(View.INVISIBLE);
                    noteIcon.setVisibility(View.VISIBLE);
                    noteIcon.setImageResource(R.drawable.msg_send_error);
                    break;
                default:
                    break;
            }
            Enum_IM_MsgType enumType = Enum_IM_MsgType.get(bean.getMsgType());
            switch (enumType) {
                // 搭讪标识
                case ACCOST:

                    break;
//			// 推荐标识
//			case RECOMMEND:
//				break;
//			// 关注标识
//			case FOLLOW:
//				break;
//			// 邀请标识
//			case INVITE:
//				break;
                default:
                    break;
            }
            Enum_IM_MsgFormat enumFormat = Enum_IM_MsgFormat.get(bean.getMsgFormat());
            switch (enumFormat) {
                case TEXT:
                    msg.setVisibility(View.VISIBLE);
                    imgContent.setVisibility(View.GONE);
                    msg.setText(FaceConversionUtil.getInstace().getExpressionString(context, bean.getMsg()));
                    break;
                case IMAGE:
                    imgContent.setVisibility(View.VISIBLE);
                    msg.setVisibility(View.GONE);
                    if (bean.getImageUrlMode() == E_DB_ImgUrlMode.LOCAL.value()) {
                        imageLoader.displayImage("file://" + bean.getMsg(), imgContent, loadOptions2);
                    } else {
                        imageLoader.displayImage(bean.getMsg(), imgContent,
                                loadOptions2, null);
                    }
                    break;
                case LOCATION:
                    break;
                case RADIO:
                    break;
                case UNKNOW:
                    break;
                default:
                    break;
            }
        }

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            switch (v.getId()) {
                case R.id.img:
                    call.onImageClick(position);
                    break;
                case R.id.msg_detail_icon:
                    call.onHeadClick(position);
                    break;
                default:
                    break;
            }
        }
    }

    public static abstract class callBack {
        public abstract void onImageClick(int position);

        public abstract void onHeadClick(int position);
    }
}
