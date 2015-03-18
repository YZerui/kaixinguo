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
 * ���Ʊ���wifi������ҳ��
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

		// ��ʼѡ���ֳ�wifi�û�ҳ��
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
	 * Radio �ؼ��������¼�����
	 * 
	 * @param group
	 * @param checkedId
	 */
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		switch (checkedId) {
		// �����ֳ�wifi�û��б�ʱ
		case R.id.local_user_radio:
			hideNoteBarBtn();
			userRadio.setTextColor(getResources().getColor(
					R.color.app_theme_color));
			noteRadio.setTextColor(getResources().getColor(R.color.color_topbar));
			mViewPager.setCurrentItem(0);
			break;
		// �����ֳ������б�ʱ
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
	 * ��������ǽ�������ؼ�
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
	 * ��ʾ����ǽ�������ؼ�
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

	/** Fragmentҳ���л�ʱ���¼����� **/
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
//		// ��fragment����ʱͨ���������mChildFragmentManagerҲ����
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
//			//��ת���ֳ�wifi����ҳ��
//			AnimationUtil.tab_in2TopIntent(getActivity(), LocalSettingActivity.class,"DATA0");
//			break;
//		// �л��������������
//		case R.id.local_note_input_btn:
//			if(!DataValidate.checkDataValid(DeviceUtils.getWifiMac())){
//				new CustomToast(getActivity()).setText("δ����wifi���޷�����Ŷ");
//				return;
//			}
//			AnimationUtil.tab_in2TopIntent_result(getActivity(),
//					LeaveNoteEditActivity.class,LeaveNoteFragment.LEAVENOTE);
//			break;
//		// �����Ե������������
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
//					// ��ת������ҳ��
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
