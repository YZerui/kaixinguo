package com.hi.module.locale.ui;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.android.ruifeng.hi.R;
import com.format.utils.DataValidate;
import com.hi.adapter.MyFragmentAdapter;
import com.hi.module.base.application.ShareUnitDialog;
import com.hi.module.base.superClass.SuperFragment;
import com.hi.module.locale.ui.leavenote.LeaveNoteEditActivity;
import com.hi.module.locale.ui.leavenote.LeaveNoteFragment;
import com.hi.module.locale.ui.local.LocalSettingActivity;
import com.hi.module.locale.ui.local.LocalUserFragment;
import com.hi.utils.AnimationUtil;
import com.hi.utils.DBUtils;
import com.hi.utils.DeviceUtils;
import com.hi.utils.ScreenShot;
import com.hi.view.RoundedImageView;
import com.hi.view.customLayout.CustomToast;
import com.thread.RunnableService;
import com.thread.callBack.runCallBack;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

/**
 * 控制本地wifi环境的页面
 * @author MM_Zerui 
 * 
 */
public class LocalFragmentActivity extends FragmentActivity implements OnCheckedChangeListener,
		OnPageChangeListener, OnClickListener {
//	private View totalView;
	private ViewPager mViewPager;
	private List<Fragment> mFragmentList;
	private MyFragmentAdapter fragmentAdapter;

	private RadioGroup mRadioGroup;
	private RadioButton userRadio, noteRadio;

//	private TextView noteInputBtn, noteShareBtn, stateSetBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.local_fragment);
        initView();
        initListener();
    }

//    @Override
//	public View onCreateView(LayoutInflater inflater, ViewGroup container,
//			Bundle savedInstanceState) {
//		// TODO Auto-generated method stub
//		totalView = inflater.inflate(R.layout.local_fragment, null);
//		initView();
//		initListener();
//		return totalView;
//
//	}

	private void initView() {
		// TODO Auto-generated method stub
//		stateSetBtn = (TextView) totalView
//				.findViewById(R.id.local_wifi_set_btn);
//		stateSetBtn.setOnClickListener(this);
//		noteInputBtn = (TextView) totalView
//				.findViewById(R.id.local_note_input_btn);
//		noteShareBtn = (TextView) totalView
//				.findViewById(R.id.local_note_share_btn);
//		noteInputBtn.setOnClickListener(this);
//		noteShareBtn.setOnClickListener(this);

		mViewPager = (ViewPager) findViewById(R.id.local_pager);
		mRadioGroup = (RadioGroup)findViewById(R.id.local_radio);
		userRadio = (RadioButton) findViewById(R.id.local_user_radio);
		noteRadio = (RadioButton) findViewById(R.id.local_note_radio);

		mFragmentList = new ArrayList<Fragment>();
		mFragmentList.add(new LocalUserFragment());
		mFragmentList.add(new LeaveNoteFragment());
		fragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager(),
				mFragmentList);
		mViewPager.setAdapter(fragmentAdapter);

		// 初始选中现场wifi用户页面
		userRadio.setChecked(true);
		userRadio
				.setTextColor(getResources().getColor(R.color.app_theme_color));
	}

	private void initListener() {
		// TODO Auto-generated method stub
		mRadioGroup.setOnCheckedChangeListener(this);
		mViewPager.setOnPageChangeListener(this);
	}

	/**
	 * Radio 控件触发的事件捕获
	 * 
	 * @param group
	 * @param checkedId
	 */
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		switch (checkedId) {
		// 触发现场wifi用户列表时
		case R.id.local_user_radio:
			hideNoteBarBtn();
			userRadio.setTextColor(getResources().getColor(
					R.color.app_theme_color));
			noteRadio.setTextColor(getResources().getColor(R.color.color_topbar));
			mViewPager.setCurrentItem(0);
			break;
		// 触发现场留言列表时
		case R.id.local_note_radio:
			showNoteBarBtn();
			noteRadio.setTextColor(getResources().getColor(
					R.color.app_theme_color));
			userRadio.setTextColor(getResources().getColor(R.color.color_topbar));
			mViewPager.setCurrentItem(1);

			break;
		default:
			break;
		}
	}

	/**
	 * 隐藏留言墙标题栏控件
	 */
	private void hideNoteBarBtn() {
		// TODO Auto-generated method stub
//		noteInputBtn.setVisibility(View.INVISIBLE);
//		noteInputBtn.setEnabled(false);
//		noteShareBtn.setVisibility(View.INVISIBLE);
//		noteShareBtn.setEnabled(false);
//		stateSetBtn.setVisibility(View.VISIBLE);
//		stateSetBtn.setEnabled(true);
	}

	/**
	 * 显示留言墙标题栏控件
	 */
	private void showNoteBarBtn() {
//		noteInputBtn.setVisibility(View.VISIBLE);
//		noteInputBtn.setEnabled(true);
//		noteShareBtn.setVisibility(View.VISIBLE);
//		noteShareBtn.setEnabled(true);
//
//		stateSetBtn.setVisibility(View.INVISIBLE);
//		stateSetBtn.setEnabled(false);
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
		((RadioButton) mRadioGroup.getChildAt(position)).setChecked(true);
	}

//	@Override
//	public void onDetach() {
//		super.onDetach();
//		// 当fragment销毁时通过反射控制mChildFragmentManager也重置
//		try {
//			Field childFragmentManager = Fragment.class
//					.getDeclaredField("mChildFragmentManager");
//			childFragmentManager.setAccessible(true);
//			childFragmentManager.set(this, null);
//
//		} catch (NoSuchFieldException e) {
//			throw new RuntimeException(e);
//		} catch (IllegalAccessException e) {
//			throw new RuntimeException(e);
//		}
//	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
//		case R.id.local_wifi_set_btn:
//			//跳转到现场wifi设置页面
//			AnimationUtil.tab_in2TopIntent(getActivity(), LocalSettingActivity.class,"DATA0");
//			break;
//		// 切换到留言输入界面
//		case R.id.local_note_input_btn:
//			if(!DataValidate.checkDataValid(DeviceUtils.getWifiMac())){
//				new CustomToast(getActivity()).setText("未连接wifi，无法留言哦");
//				return;
//			}
//			AnimationUtil.tab_in2TopIntent_result(getActivity(),
//					LeaveNoteEditActivity.class,LeaveNoteFragment.LEAVENOTE);
//			break;
//		// 打开留言第三方分享界面
//		case R.id.local_note_share_btn:
//			new RunnableService(new runCallBack() {
//
//				@Override
//				public void start() {
//					// TODO Auto-generated method stub
//					String filePath = ScreenShot
//							.shoot(getActivity());
//					DBUtils.setSharedPreStr(getActivity(), "SHAREIMG", filePath);
//				}
//
//				@Override
//				public void end() {
//					// 跳转到分享页面
//					AnimationUtil.in2TopIntent(getActivity(),
//							ShareUnitDialog.class);
//				}
//			}, false);
//			break;

		default:
			break;
		}
	}
}
