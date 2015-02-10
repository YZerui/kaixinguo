package com.hi.module.locale.ui.leavenote;

import java.io.File;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;

import com.android.ruifeng.hi.R;
import com.customview.callBack.sectorBtnCallBack;
import com.customview.view.CustomSectorView;
import com.hi.module.base.img.ImageAlbumList;
import com.hi.module.base.img.ImageLoader;
import com.hi.module.base.superClass.SuperDialogActivity;
import com.hi.utils.AnimationUtil;
import com.imagefetch.model.ImageItem;
import com.imagefetch.util.CustomConstants;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 图片选取方式对话框
 * @author MM_Zerui
 *
 */
public class ImgSelectDialog extends SuperDialogActivity{
	private static final int TAKE_PICTURE = 0x000000;
	@ViewInject(R.id.sector)
	private CustomSectorView sector;
	
	private String imgPath;
	public static boolean ifSelect=false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.img_select_dialog);
		ViewUtils.inject(this);
		super.onCreate(savedInstanceState);
		onClickListener();
	}
	private void onClickListener() {
		// TODO Auto-generated method stub
		sector.setCallBack(new sectorBtnCallBack() {
			@Override
			public void call_bottomBtnClick() {
				// TODO Auto-generated method stub
				super.call_bottomBtnClick();
				finish();
			}
			@Override
			public void call_firstBtnClick() {
				// TODO Auto-generated method stub
				super.call_firstBtnClick();
				//拍照
				takePhoto();
			}
			@Override
			public void call_secondBtnClick() {
				// TODO Auto-generated method stub
				super.call_secondBtnClick();
				//相册选取
				
				AnimationUtil.in2TopIntent(context, ImageAlbumList.class);
				
			}
			@Override
			public void onFinal() {
				// TODO Auto-generated method stub
				
			}
		});
	}
	@Override
	public void setPageHeight() {
		// TODO Auto-generated method stub
		lp.height=sector.getHeight_Px(ImgSelectDialog.this);
	}

	@Override
	protected void obtainIntentValue() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initResource() {
		// TODO Auto-generated method stub
		ifSelect=false;
	}
	public void takePhoto()
	{
		Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		
		File vFile = new File(Environment.getExternalStorageDirectory()
				+ "/hi/image", String.valueOf(System.currentTimeMillis())
				+ ".png");
		if (!vFile.exists())
		{
			File vDirPath = vFile.getParentFile();
			vDirPath.mkdirs();
		}
//		else
//		{
//			if (vFile.exists())
//			{
//				vFile.delete();
//			}
//		}
		LeaveNoteEditActivity.path = vFile.getPath();
		Uri cameraUri = Uri.fromFile(vFile);
		openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, cameraUri);
		startActivityForResult(openCameraIntent, TAKE_PICTURE);
	}
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		switch (requestCode)
		{
		case TAKE_PICTURE:
//			if (mDataList.size() < CustomConstants.MAX_IMAGE_SIZE
//					&& resultCode == -1 && !TextUtils.isEmpty(imgPath))
//			{
//				ImageItem item = new ImageItem();
//				item.sourcePath = imgPath;
//			}
//			LeaveNoteEditActivity.path=imgPath;
			ifSelect=true;
			finish();
			break;
		}
	}
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
		AnimationUtil.finishOut2Bottom(context);
	}

}
