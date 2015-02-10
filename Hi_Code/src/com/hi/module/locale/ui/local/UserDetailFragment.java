package com.hi.module.locale.ui.local;

import com.android.ruifeng.hi.R;
import com.customview.view.CustomItemView;
import com.format.utils.DataValidate;
import com.format.utils.TextViewInit;
import com.hi.common.API;
import com.hi.common.db.E_DB_SelfIfo;
import com.hi.common.http.E_Http_Gender;
import com.hi.common.http.E_Http_Port;
import com.hi.common.http.E_Http_Relate;
import com.hi.common.param.Enum_Page;
import com.hi.common.writing.W_UserInfo;
import com.hi.dao.supImpl.Dao_Friends;
import com.hi.dao.supImpl.Dao_SelfIfo;
import com.hi.http.base.Call_httpData;
import com.hi.http.friends.model.Req_Follow;
import com.hi.http.friends.model.Req_UNFollow;
import com.hi.http.friends.req.Http_Follow;
import com.hi.http.friends.req.Http_UNFollow;
import com.hi.http.member.model.Recv_UserIfo;
import com.hi.http.member.model.Req_UserIfo;
import com.hi.http.member.req.Http_GetUserIfo;
import com.hi.module.base.callBack.httpResultCallBack;
import com.hi.module.base.superClass.RequestFragment;
import com.hi.module.locale.model.RecvUserIfoBean;
import com.hi.module.locale.model.ReqFollowBean;
import com.hi.module.locale.model.ReqUserIfoBean;
import com.hi.module.locale.ui.UserDetailFragmentActivity;
import com.hi.module.msg.ui.MsgDetailActivity;
import com.hi.service.HttpResultService;
import com.hi.utils.AnimationUtil;
import com.hi.utils.DBUtils;
import com.hi.utils.FormatUtils;
import com.hi.utils.HttpUtils;
import com.hi.utils.ViewHandleUtils;
import com.hi.utils.XingZuo;
import com.hi.view.RoundedImageView;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 显示用户详情的页面
 * 
 * @author MM_Zerui
 * 
 */
public class UserDetailFragment extends RequestFragment {
	private CheckBox likeCheckBox;
	private CustomItemView stateItem, labelItem;
	private RoundedImageView selfIcon;
	private TextView selfName, currentState, selfLabel, selfWorks, selfAge,
			selfXingZuo;
	private ImageView selfGender;
	private String UID, head, nickName, pageSource;
	private ImageView msgBtn;
	private View view;
	// private Dao_Friends friDaoImpl;
	private String age;
	private String xingZuo;
	Recv_UserIfo recvBean;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.local_detail_layout, null);
		super.onCreateView(inflater, container, savedInstanceState);
		return view;
	}

	@Override
	protected void obtainIntentValue() {
		// TODO Auto-generated method stub
		UID = UserDetailFragmentActivity.uId;
		head = UserDetailFragmentActivity.head;
		nickName = UserDetailFragmentActivity.nickName;
		pageSource = UserDetailFragmentActivity.pageSource;

	}

	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		stateItem = (CustomItemView) view.findViewById(R.id.state_item);
		labelItem = (CustomItemView) view.findViewById(R.id.label_item);

		selfIcon = (RoundedImageView) view
				.findViewById(R.id.local_user_detail_selIcon);
		selfName = (TextView) view.findViewById(R.id.local_user_detail_name);
		selfWorks = (TextView) view
				.findViewById(R.id.local_user_detail_selWorks);
		selfGender = (ImageView) view
				.findViewById(R.id.local_user_detail_genderIcon);

		msgBtn = (ImageView) view.findViewById(R.id.local_user_detail_flyMsg);

		selfAge = (TextView) view.findViewById(R.id.local_detail_selfAge);
		selfXingZuo = (TextView) view.findViewById(R.id.local_detail_xingZuo);

		likeCheckBox = (CheckBox) view
				.findViewById(R.id.local_user_detail_like);
	}

	@Override
	protected void initResource() {
		if (pageSource != null) {
			if (pageSource.equals(Enum_Page.MSG_DETAIL.name())
					|| pageSource.equals(Enum_Page.LEAVENOTE.name())) {
				msgBtn.setVisibility(View.GONE);
			}

		}
		loadOptions = new DisplayImageOptions.Builder()
				.showImageOnFail(R.drawable.default_img_head)
				.showImageForEmptyUri(R.drawable.default_img_head)
				.showStubImage(R.drawable.default_img_head).cacheInMemory(true)
				.cacheOnDisc(true).build();
		recvBean = new Recv_UserIfo();
		// 获得用户信息
		Req_UserIfo reqBean = new Req_UserIfo();
		reqBean.setId(UID);
		reqBean.setUid(Dao_SelfIfo.getInstance().getMid());
		new Http_GetUserIfo(new Call_httpData<Recv_UserIfo>() {

			@Override
			public void onStart() {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(Recv_UserIfo datas) {
				// TODO Auto-generated method stub
				// 关系的标识
				recvBean = datas;
				switch (E_Http_Relate.get(Integer.valueOf(recvBean.getType()))) {
				case FOLLOW_OTHER:
				case FOLLOW_EACH:
					likeCheckBox.setChecked(true);
					break;

				default:
					likeCheckBox.setChecked(false);
					;
				}

			}

			@Override
			public void onFail() {
				// TODO Auto-generated method stub
				toast.setText("信息获取有误");
				UserDetailFragmentActivity.topBar.setTitle("获取信息有误");
			}

			@Override
			public void onFinally() {
				// TODO Auto-generated method stub
				initPageContent(recvBean);
				UserDetailFragmentActivity.topBar.setProVisibility(false);
			}

		}).onParams(reqBean).onAction();
	}

	@Override
	protected void setOnClickListener() {
		// 好友关注操作
		likeCheckBox.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (likeCheckBox.isChecked()) {
					likeMethod(true);
				} else {
					likeMethod(false);
				}
			}
		});
		// 跳转到搭讪页面
		msgBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AnimationUtil.in2TopIntent(context, MsgDetailActivity.class,
						UID, head, nickName, "USER_DETAIL_PAGE");
			}
		});
	}

	public void initPageContent(Recv_UserIfo bean) {
		W_UserInfo.Default(E_DB_SelfIfo.nickName, selfName, bean.getNickName());
		W_UserInfo.Default(E_DB_SelfIfo.age, selfAge,
				FormatUtils.getAge(bean.getBirthDay()));
		W_UserInfo.Default(E_DB_SelfIfo.xingzuo, selfXingZuo,
				XingZuo.getXingZuo(bean.getBirthDay()));
		W_UserInfo.Default(E_DB_SelfIfo.note, labelItem.getContent(),
				bean.getNote());
		W_UserInfo.Default(E_DB_SelfIfo.occupation, selfWorks,
				bean.getOccupation());
		W_UserInfo.Default(E_DB_SelfIfo.currentState, stateItem.getContent(),
				bean.getCurrentState());
		if (bean.getSex() != null
				&& bean.getSex().equals(E_Http_Gender.MAN.toString())) {
			selfGender.setImageResource(R.drawable.ic_sex_boy);
		} else if (bean.getSex() != null
				&& bean.getSex().equals(E_Http_Gender.WOMEN.toString())) {
			selfGender.setImageResource(R.drawable.ic_sex_girl);
		} else {
			selfGender.setVisibility(View.GONE);
		}
		imageLoader.displayImage(bean.getHead(), selfIcon, loadOptions);
	}

	private void likeMethod(final boolean like) {

		if (like) {
			Req_Follow reqBean = new Req_Follow();
			reqBean.setUid(Dao_SelfIfo.getInstance().getMid());
			reqBean.setBeUID(UID);
			new Http_Follow(new Call_httpData<Class<?>>() {

				@Override
				public void onSuccess(Class<?> datas) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onStart() {
					// TODO Auto-generated method stub

				}

				@Override
				public void onFinally() {
					// TODO Auto-generated method stub

				}

				@Override
				public void onFail() {
					// TODO Auto-generated method stub
					ViewHandleUtils.toastNormal("操作失败");
					// if(likeCheckBox)
					likeCheckBox.setChecked(!likeCheckBox.isChecked());
				}
			}).onParams(reqBean).onAction();
		} else {
			Req_UNFollow reqBean = new Req_UNFollow();
			reqBean.setUid(Dao_SelfIfo.getInstance().getMid());
			reqBean.setBeUID(UID);
			new Http_UNFollow(new Call_httpData<Class<?>>() {

				@Override
				public void onSuccess(Class<?> datas) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onStart() {
					// TODO Auto-generated method stub

				}

				@Override
				public void onFinally() {
					// TODO Auto-generated method stub

				}

				@Override
				public void onFail() {
					// TODO Auto-generated method stub
					ViewHandleUtils.toastNormal("操作失败");
					// if(likeCheckBox)
					likeCheckBox.setChecked(!likeCheckBox.isChecked());
				}
			}).onParams(reqBean).onAction();
		}
	}
}
