package com.hi.module.self.ui;

import java.io.File;

import com.android.ruifeng.hi.R;
import com.exception.utils.P;
import com.hi.common.COMMON;
import com.hi.common.PARAMS;
import com.hi.module.base.application.AppManager;
import com.hi.module.base.img.ImageAlbumList;
import com.hi.module.base.img.ImageCollectionPage;
import com.hi.utils.AnimationUtil;
import com.hi.utils.FunUtils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;

/**
 * @author MM_Zerui 设定头像的页面
 */
public class ImgSetDialog extends Activity implements OnClickListener {
	private static int screenHeight;
	private Button exitBtn, photoSelectBtn, captureSelectBtn;
	private final static int TAKE_CAMERA_REQUEST = 1100;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.photo_choose_dialog);
		AppManager.getAppManager().addActivity(this);
		// 设定页面
		init();
		initView();
	}

	private void initView() {
		// TODO Auto-generated method stub
		exitBtn = (Button) findViewById(R.id.imgSetCancelBtn);
		photoSelectBtn = (Button) findViewById(R.id.photoSelectBtn);
		captureSelectBtn = (Button) findViewById(R.id.captureSelectBtn);

		exitBtn.setOnClickListener(this);
		photoSelectBtn.setOnClickListener(this);
		captureSelectBtn.setOnClickListener(this);
	}

	private void init() {

		screenHeight = getWindow().getWindowManager().getDefaultDisplay()
				.getHeight();// 获取屏幕高度

		WindowManager.LayoutParams lp = getWindow().getAttributes();// //lp包含了布局的很多信息，通过lp来设置对话框的布局
		lp.width = LayoutParams.FILL_PARENT;
		lp.gravity = Gravity.BOTTOM;
		lp.height = screenHeight / 2;// lp高度设置为屏幕的一半
		getWindow().setAttributes(lp);// 将设置好属性的lp应用到对话框

	}

	// 重写finish（）方法，加入关闭时的动画
	public void finish() {
		super.finish();
		this.overridePendingTransition(0, R.anim.dialog_exit);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.imgSetCancelBtn:
			finish();
			break;
		case R.id.photoSelectBtn:
			photoSelect();
			break;
		case R.id.captureSelectBtn:
			photoCapture();
			break;

		default:
			break;
		}
	}

	/**
	 * 从相册中选择图片
	 */
	private void photoSelect() {
		// TODO Auto-generated method stub
		// Intent openAlbumIntent = new Intent(Intent.ACTION_GET_CONTENT);
		// PARAMS.REQUEST_CODE = PARAMS.PHOTO_CROP;
		// openAlbumIntent.setDataAndType(
		// MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
		// startActivityForResult(openAlbumIntent, PARAMS.REQUEST_CODE);
		ImageCollectionPage.ifTurnToClip = true;
		AnimationUtil.in2TopIntent(ImgSetDialog.this, ImageAlbumList.class);
	}

	/**
	 * 拍照取图
	 */
	private void photoCapture() {
		Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		Uri imageUri = Uri.fromFile(new File(ImgWallSettingActivity.savePath));
		// P.v(localCameraPath);
		openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
		startActivityForResult(openCameraIntent, TAKE_CAMERA_REQUEST);
		overridePendingTransition(0, R.anim.out_right_to_left_page);
		// Uri imageUri = null;
		// String fileName = null;
		// Intent openCameraIntent = new
		// Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		// // 删除上一次截图的临时文件
		// SharedPreferences sharedPreferences = getSharedPreferences("temp",
		// Context.MODE_WORLD_WRITEABLE);
		// FunUtils.deletePhotoAtPathAndName(Environment
		// .getExternalStorageDirectory().getAbsolutePath(),
		// sharedPreferences.getString("tempName", ""));
		// // 保存本次截图临时文件名字
		// // fileName = String.valueOf(System.currentTimeMillis()) + ".jpg";
		// fileName = "local.jpg";
		// Editor editor = sharedPreferences.edit();
		// editor.putString("tempName", fileName);
		// editor.commit();
		//
		// imageUri = Uri.fromFile(new File(Environment
		// .getExternalStorageDirectory(), fileName));
		// // 指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
		// openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
		// PARAMS.REQUEST_CODE = PARAMS.PHOTO_CROP;
		// startActivityForResult(openCameraIntent, PARAMS.REQUEST_CODE);
	}

	// 截取图片
	public void cropImage(Uri uri, int outputX, int outputY, int requestCode) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		intent.putExtra("crop", "true");
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		intent.putExtra("outputX", outputX);
		intent.putExtra("outputY", outputY);
		intent.putExtra("outputFormat", "JPEG");
		intent.putExtra("noFaceDetection", true);
		intent.putExtra("return-data", true);
		startActivityForResult(intent, requestCode);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onActivityResult(int, int,
	 * android.content.Intent) 当页面调回时处理
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case TAKE_CAMERA_REQUEST:
			if (resultCode == RESULT_OK) {
				AnimationUtil.in2LeftIntent(ImgSetDialog.this,
						ImgClipPage.class, ImgWallSettingActivity.savePath);
			}else {
				finish();
			}
		

			break;
		// 当截图后跳回时,这里应该对截后的图进行保存、显示等处理
		case PARAMS.PHOTO_CAPTURE:
			Bitmap photo = null;
			if (data != null) {
				Uri photoUri = data.getData();
				System.out.println("URI:" + photoUri);
				if (photoUri != null) {
					photo = BitmapFactory.decodeFile(photoUri.getPath());
				}
				if (photo == null) {
					Bundle extra = data.getExtras();
					if (extra != null) {
						photo = (Bitmap) extra.get("data");
					}
				}
				if (photo == null) {
					return;
				}
				// 保存截图图片到本地
				FunUtils.savePhotoToSDCard(photo, COMMON.APP_FILE_FOLDER, "head");
				PARAMS.REQUEST_CODE = PARAMS.PHOTO_CAPTURE_SUCCESS;
				Intent intent = new Intent(this, ImgWallSettingActivity.class);
				// intent.putExtra("bitmap", photo);
				// intent.putExtra("SELECT_PHOTO_URL", data);
				setResult(PARAMS.REQUEST_CODE, intent);
				// startActivityForResult(intent,Params.REQUEST_CODE);
				finish();
			}

			break;
		// 开启拍照截图模式时返回
		case PARAMS.PHOTO_CROP:
			Uri uri = null;
			if (data != null) {
				uri = data.getData();
				System.out.println("Data");
			} else {
				System.out.println("File");
				String fileName = getSharedPreferences("temp",
						Context.MODE_WORLD_WRITEABLE).getString("tempName", "");
				uri = Uri.fromFile(new File(Environment
						.getExternalStorageDirectory(), fileName));

			}
			cropImage(uri, PARAMS.IMG_WALL_SIZE, PARAMS.IMG_WALL_SIZE,
					PARAMS.PHOTO_CAPTURE);
			break;
		default:
			break;
		}
	}
}
