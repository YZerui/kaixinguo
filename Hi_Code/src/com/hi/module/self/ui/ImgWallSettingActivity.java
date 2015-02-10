package com.hi.module.self.ui;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarOutputStream;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.ruifeng.hi.R;
import com.customview.callBack.topBarCallBack;
import com.customview.view.CustomTopbarView;
import com.exception.utils.P;
import com.hi.common.API;
import com.hi.common.COMMON;
import com.hi.common.EXCEPTION_CODE;
import com.hi.common.PARAMS;
import com.hi.common.db.E_DB_SelfIfo;
import com.hi.dao.supImpl.Dao_Params;
import com.hi.dao.supImpl.Dao_SelfIfo;
import com.hi.module.base.application.ThreadPool;
import com.hi.module.base.callBack.httpResultCallBack;
import com.hi.module.base.img.ImageLoader;
import com.hi.module.base.superClass.RequestActivity;
import com.hi.module.locale.model.RecvPhotoUrlBean;
import com.hi.module.self.model.RecvImgUrlBean;
import com.hi.service.HttpResultService;
import com.hi.utils.AnimationUtil;
import com.hi.utils.DBUtils;
import com.hi.utils.FunUtils;
import com.hi.utils.ParseJson;
import com.hi.utils.UploadUtil;
import com.hi.utils.ViewHandleUtils;
import com.hi.utils.UploadUtil.OnUploadProcessListener;
import com.hi.view.RoundedImageView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

/**
 * 照片墙，每次进入创建文件
 * @author MM_Zerui
 *
 */
public class ImgWallSettingActivity extends RequestActivity {
	@ViewInject(R.id.topBar)
	private CustomTopbarView topBar;
	@ViewInject(R.id.self_img_set_btn)
	private ImageView img_head;
	@ViewInject(R.id.self_img_wall_set_1)
	private ImageView img_1;
	@ViewInject(R.id.self_img_wall_set_2)
	private ImageView img_2;
	@ViewInject(R.id.self_img_wall_set_3)
	private ImageView img_3;

	private int unitNote;
	Bitmap uploBitmap;
	
	public static String savePath = COMMON.APP_FILE_FOLDER
			+ "/head.png";
	public static String saveImgName="head";
	private String intentStr;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.self_photo_setting_page);
		ViewUtils.inject(this);
		super.onCreate(savedInstanceState);
//		createSDImgFolder();
		}

//	private void createSDImgFolder() {
//		// TODO Auto-generated method stub
//		File file=new File(COMMON.APP_FILE_FOLDER+"");
//		if(!file.exists()){
//			file.mkdirs();
//			P.v("创建SD文件夹");
//		}
//	}

	@OnClick(R.id.self_img_set_btn)
	public void imgCliK(View v) {
		unitNote = 0;
		toImgPage();
	}

	@OnClick(R.id.self_img_wall_set_1)
	public void img1Click(View v) {
		unitNote = 1;
		toImgPage();
	}

	@OnClick(R.id.self_img_wall_set_2)
	public void img2Click(View v) {
		unitNote = 2;
		toImgPage();
	}

	@OnClick(R.id.self_img_wall_set_3)
	public void img3Click(View v) {
		unitNote = 3;
		toImgPage();
	}

	public void toImgPage() {
		Intent intent = new Intent(this, ImgSetDialog.class);
		PARAMS.REQUEST_CODE = PARAMS.PHOTO_CAPTURE_COMPLETE;
		startActivityForResult(intent, PARAMS.REQUEST_CODE);
		overridePendingTransition(R.anim.dialog_enter, R.anim.dialog_exit);
	}

	@Override
	protected void obtainIntentValue() {
		// TODO Auto-generated method stub
		intentStr = getIntent().getStringExtra("DATA0");

	}

	@Override
	protected void initResource() {
		// TODO Auto-generated method stub
		unitNote = 0;
		// 初始化头像资源
		imageLoader.displayImage(Dao_SelfIfo.getInstance().getHead(), img_head, loadOptions);
		imageLoader.displayImage(Dao_SelfIfo.getInstance().getPhotos_1(), img_1, loadOptions);
		imageLoader.displayImage(Dao_SelfIfo.getInstance().getPhotos_2(), img_2, loadOptions);
		imageLoader.displayImage(Dao_SelfIfo.getInstance().getPhotos_3(), img_3, loadOptions);
	}

	@Override
	protected void setOnClickListener() {
		// TODO Auto-generated method stub
		topBar.setCallBack(new topBarCallBack() {
			@Override
			public void call_backBtnListener() {
				// TODO Auto-generated method stub
				super.call_backBtnListener();
				outFinish();
			}
		});
	}
	@Override
	protected void requestMethod() {
		// TODO Auto-generated method stub

	}


	@Override
	protected void outFinish() {
		// TODO Auto-generated method stub
		finish();
		if (intentStr != null) {
			AnimationUtil.finishOut2Right(context);
			return;
		}
		AnimationUtil.finishOut2Bottom(context);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch (resultCode) {
		// 从单元设置页面中返回数据
		case PARAMS.PHOTO_CAPTURE_COMPLETE:
			break;
		case PARAMS.PHOTO_CAPTURE_SUCCESS:
			System.out.println("拍照截图完毕");
			
			uploBitmap = BitmapFactory.decodeFile(savePath);
			if (uploBitmap == null) {
				ViewHandleUtils.toastNormal("上传失败");
				return;
			}
			httpUploadImgMethod(savePath);
			P.v("个人照片地址:"+savePath);
			break;

		default:
			break;
		}
	}

	private void httpUploadImgMethod(String imgPath) {
		String param1 = new String();
		RequestParams params = new RequestParams();
		String api = new String();
		switch (unitNote) {
		case 0:
			param1 = "head";
			api = API.SELF_HEAD_UPLOAD;
			break;
		case 1:
		case 2:
		case 3:
			api = API.SELF_WALL_UPLOAD;
			param1 = "photo";
			params.addBodyParameter("location",String.valueOf(unitNote));
			break;

		default:
			break;
		}
		
		params.addBodyParameter("uid", Dao_SelfIfo.getInstance().getMid());
		params.addBodyParameter(param1, new File(imgPath));
		http.send(HttpMethod.POST, api, params, new RequestCallBack<String>() {
			@Override
			public void onStart() {
				// TODO Auto-generated method stub
				super.onStart();
				httpRun();
			}
			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				httpEnd();
				toast.setText("网络异常,上传失败");
			}

			@Override
			public void onSuccess(ResponseInfo<String> params) {
				// TODO Auto-generated method stub
				new HttpResultService(params.result, new httpResultCallBack() {
					@Override
					public void onData(boolean validity, Object obj) {
						// TODO Auto-generated method stub
						super.onData(validity, obj);
						if (validity) {
							RecvImgUrlBean bean = (RecvImgUrlBean) obj;
							switch (unitNote) {
							case 0:
//								img_head.setImageBitmap(uploBitmap);
								imageLoader.displayImage(bean.getUrl(),
										img_head, loadOptions);
								Dao_SelfIfo.setParams(E_DB_SelfIfo.head, bean.getUrl());
								break;
							case 1:
								imageLoader.displayImage(bean.getUrl(), img_1,
										loadOptions);
								Dao_SelfIfo.setParams(E_DB_SelfIfo.photos_1, bean.getUrl());
								break;
							case 2:
								imageLoader.displayImage(bean.getUrl(), img_2,
										loadOptions);
								Dao_SelfIfo.setParams(E_DB_SelfIfo.photos_2, bean.getUrl());
								break;
							case 3:
								imageLoader.displayImage(bean.getUrl(), img_3,
										loadOptions);
								Dao_SelfIfo.setParams(E_DB_SelfIfo.photos_3, bean.getUrl());
								break;
							default:
								break;
							}
						}
					}

					@Override
					public void onSuccess() {
						// TODO Auto-generated method stub

					}

					@Override
					public void onRequestFail() {
						// TODO Auto-generated method stub
						toast.setText("上传失败");
					}

					@Override
					public void onFinally() {
						// TODO Auto-generated method stub
						httpEnd();
					}
				}, RecvImgUrlBean.class, false);
			}
		});
	}
	public void httpRun(){
//		progressDialog.withSector().withCircel().withNote("上传中...").show();
		topBar.setTitle("上传中...").setProVisibility(true);
	}
	public void httpEnd(){
//		progressDialog.dismiss();
		topBar.setTitle("设置头像").setProVisibility(false);
	}
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();

		outFinish();
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		//消除缓存
		ImageLoader.getInstance().recycleCache();
		if(ImgClipPage.clipSuccess){
			httpUploadImgMethod(savePath);
			ImgClipPage.clipSuccess=false;
		}
	}
}
