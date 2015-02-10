package com.hi.module.locale.ui;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.ruifeng.hi.R;
import com.format.utils.DataValidate;
import com.hi.adapter.MyFragmentAdapter;
import com.hi.common.API;
import com.hi.common.EXCEPTION_CODE;
import com.hi.dao.supImpl.Dao_SelfIfo;
import com.hi.http.base.Call_httpData;
import com.hi.http.member.model.Recv_UserIfo;
import com.hi.http.member.model.Req_UserIfo;
import com.hi.http.member.req.Http_GetUserIfo;
import com.hi.module.base.application.ShareUnitDialog;
import com.hi.module.base.application.ThreadPool;
import com.hi.module.base.superClass.RequestFragment;
import com.hi.module.locale.model.RecvPhotoUrlBean;
import com.hi.module.locale.model.RecvUserIfoBean;
import com.hi.module.locale.model.ReqUserIfoBean;
import com.hi.module.locale.ui.leavenote.LeaveNoteEditActivity;
import com.hi.module.locale.ui.leavenote.LeaveNoteFragment;
import com.hi.module.locale.ui.local.LocalSettingActivity;
import com.hi.module.locale.ui.local.UserDetailFragment;
import com.hi.module.locale.ui.local.UserImgFragment;
import com.hi.module.msg.ui.MsgDetailActivity;
import com.hi.module.self.model.RecvImgUrlBean;
import com.hi.utils.AnimationUtil;
import com.hi.utils.DBUtils;
import com.hi.utils.HttpUtils;
import com.hi.utils.ParseJson;
import com.hi.utils.ScreenShot;
import com.hi.view.RoundedImageView;
import com.thread.RunnableService;
import com.thread.callBack.runCallBack;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

/**
 * 控制用户详情页面的类
 * 
 * @author MM_Zerui
 * 
 */
public class UserFragment extends Fragment implements OnPageChangeListener,
		OnClickListener {
	private View totalView;
	private ViewPager mViewPager;
	private List<Fragment> mFragmentList;
	private MyFragmentAdapter fragmentAdapter;
	private String uId;
//	private RecvUserIfoBean userBean;
	private Context context;
//	private RelativeLayout backLayout;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		context = getActivity();
		totalView = inflater.inflate(
				R.layout.local_user_detail_fragment_layout, null);
		initView();
		initListener();
		obtIntentValue();
		requestPhotoMethod();
		return totalView;

	}

	private void obtIntentValue() {
		// TODO Auto-generated method stub
		uId = UserDetailFragmentActivity.uId;
	}

	private void initView() {
		// TODO Auto-generated method stub
//		backLayout=(RelativeLayout)totalView.findViewById(R.id.backLayout);
		mViewPager = (ViewPager) totalView.findViewById(R.id.local_user_pager);
		mFragmentList = new ArrayList<Fragment>();
		mFragmentList.add(new UserDetailFragment());
	}

	private void initListener() {
		// TODO Auto-generated method stub
		mViewPager.setOnPageChangeListener(this);
	}

//	/**
//	 * 对请求的数据进行处理
//	 * 
//	 * @param obj
//	 */
//	private void handleRequestMethod(Object obj) {
//		userBean = ParseJson.parseResultJson((JSONObject) obj,
//				RecvUserIfoBean.class);
//		if (userBean != null) {
//			String photosUrl[] = userBean.getPhotos();
//			ArrayList<RecvPhotoUrlBean> beanList=ParseJson.getPhotoList(photosUrl);
//			for(RecvPhotoUrlBean item:beanList){
//				mFragmentList.add(new UserImgFragment(item.getUrl()));
//			}
//		}
//
//	}

	/**
	 * 请求用户相片信息的页面
	 */
	public void requestPhotoMethod() {
		Req_UserIfo reqBean=new Req_UserIfo();
		reqBean.setId(uId);
		reqBean.setUid(Dao_SelfIfo.getInstance().getMid());
		new Http_GetUserIfo(new Call_httpData<Recv_UserIfo>() {
			
			@Override
			public void onSuccess(Recv_UserIfo datas) {
				// TODO Auto-generated method stub
				if(DataValidate.checkDataValid(datas.getPhotos_1())){
					mFragmentList.add(new UserImgFragment(datas.getPhotos_1()));
				}
				if(DataValidate.checkDataValid(datas.getPhotos_2())){
					mFragmentList.add(new UserImgFragment(datas.getPhotos_2()));
				}
				if(DataValidate.checkDataValid(datas.getPhotos_3())){
					mFragmentList.add(new UserImgFragment(datas.getPhotos_3()));
				}
			}
			
			@Override
			public void onStart() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onFinally() {
				// TODO Auto-generated method stub
				fragmentAdapter = new MyFragmentAdapter(getChildFragmentManager(),
						mFragmentList);
				mViewPager.setAdapter(fragmentAdapter);
			}
			
			@Override
			public void onFail() {
				// TODO Auto-generated method stub
				
			}
		}).onParams(reqBean).onAction();
	}


	/** Fragment页面切换时的事件捕获 **/
	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageSelected(int position) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDetach() {
		super.onDetach();
		// 当fragment销毁时通过反射控制mChildFragmentManager也重置
		try {
			Field childFragmentManager = Fragment.class
					.getDeclaredField("mChildFragmentManager");
			childFragmentManager.setAccessible(true);
			childFragmentManager.set(this, null);

		} catch (NoSuchFieldException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.local_wifi_set_btn:
			// 跳转到现场wifi设置页面
			AnimationUtil.tab_in2TopIntent(getActivity(),
					LocalSettingActivity.class, "DATA0");
			break;
		// 切换到留言输入界面
		case R.id.local_note_input_btn:
//			AnimationUtil.tab_in2TopIntent(getActivity(),
//					LeaveNoteEditActivity.class);
			AnimationUtil.tab_in2TopIntent_result(getActivity(), LeaveNoteEditActivity.class, 
					LeaveNoteFragment.LEAVENOTE);
			break;
		// 打开留言第三方分享界面
		case R.id.local_note_share_btn:
			
			break;
		case R.id.backLayout:
			
			break;
		default:
			break;
		}
	}
	
}
