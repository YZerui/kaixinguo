package com.hi.module.self.ui;


import android.graphics.Bitmap;
import android.os.Bundle;

import com.android.ruifeng.hi.R;
import com.customview.callBack.topBarCallBack;
import com.customview.view.CustomTopbarView;
import com.hi.common.COMMON;
import com.hi.common.param.Enum_Color;
import com.hi.module.base.application.AppManager;
import com.hi.module.base.img.ImageAlbumList;
import com.hi.module.base.img.ImageCollectionPage;
import com.hi.module.base.img.ImageLoader;
import com.hi.module.base.img.ImageLoader.Type;
import com.hi.module.base.superClass.NormalActivity;
import com.hi.utils.AnimationUtil;
import com.hi.utils.FunUtils;
import com.hi.view.customClipLayout.ClipImageLayout;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 图片剪切页面
 * 
 * @author MM_Zerui
 * 
 */
public class ImgClipPage extends NormalActivity {
	@ViewInject(R.id.topBar)
	private CustomTopbarView topBar;
	@ViewInject(R.id.clipLayout)
	private ClipImageLayout clipLayout;

	private String imgPath;
	public static boolean clipSuccess = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.img_clip_page);
		ViewUtils.inject(this);
		super.onCreate(savedInstanceState);

	}

	@Override
	public void obtainIntentValue() {
		// TODO Auto-generated method stub
		imgPath = getIntent().getStringExtra("DATA0");
	}

	@Override
	public void initResource() {
		// TODO Auto-generated method stub
		ImageLoader.getInstance(3, Type.FIFO).loadImage(imgPath,
				clipLayout.getImageView());
		topBar.setRightTextColor(Enum_Color.RED.value());
		clipSuccess = false;
	}

	@Override
	public void onClickListener() {
		// TODO Auto-generated method stub
		topBar.setCallBack(new topBarCallBack() {
			@Override
			public void call_backBtnListener() {
				// TODO Auto-generated method stub
				super.call_backBtnListener();
				finish();
				AnimationUtil.finishOut2Right(context);
			}

			@Override
			public void call_rightTextBtnListener() {
				// TODO Auto-generated method stub
				super.call_rightTextBtnListener();
				// 确定截图事件
				ImageCollectionPage.ifTurnToClip = false;
				Bitmap bitmap = clipLayout.clip();
				if (bitmap != null) {

					// 将bitmap存入某固定路径当中
					FunUtils.savePhotoToSDCard(bitmap, COMMON.APP_FILE_FOLDER, 
							ImgWallSettingActivity.saveImgName);
					clipSuccess = true;
					AppManager.getAppManager().finishActivity(ImageAlbumList.class);
					AppManager.getAppManager().finishActivity(ImageCollectionPage.class);
					AppManager.getAppManager().finishActivity(ImgSetDialog.class);
					finish();
					AnimationUtil.finishOut2Bottom(context);
				} else {
					clipSuccess = false;
					toast.setText("图像出错，无法剪切");
				}
			}
		});
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
		
	}

	@Override
	public void outFinish() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		AnimationUtil.finishOut2Right(context);
	}

}
