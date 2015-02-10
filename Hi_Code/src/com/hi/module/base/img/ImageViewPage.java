package com.hi.module.base.img;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.android.ruifeng.hi.R;
import com.hi.module.base.superClass.SuperActivity;
import com.hi.module.locale.ui.leavenote.LeaveNoteFragment;
import com.hi.utils.ShowBigPicUtil;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * Õº∆¨∑≈¥Û“≥√Ê
 * 
 * @author MM_Zerui
 * 
 */
public class ImageViewPage extends SuperActivity {
	@ViewInject(R.id.statu_2_parent)
	private RelativeLayout parent;
//	@ViewInject(R.id.imageView)
//	private ImageView imageView;
	
	private String imgPath;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.img_view_page);
		ViewUtils.inject(this);
		super.onCreate(savedInstanceState);
		getIntentValue();
	}
	
	private void getIntentValue() {
		// TODO Auto-generated method stub
		imgPath=getIntent().getStringExtra("DATA0");
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		ShowBigPicUtil bigPicUtil = new ShowBigPicUtil(this, LeaveNoteFragment.imageView, parent,
				null,
				imgPath);
		bigPicUtil.show();
	}
}
