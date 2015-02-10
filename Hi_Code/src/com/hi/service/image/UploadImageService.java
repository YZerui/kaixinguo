package com.hi.service.image;

import org.json.JSONException;
import org.json.JSONObject;

import com.hi.http.base.Call_httpData;
import com.hi.http.global.req.Http_QiniuToken;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

/**
 * 图片上传服务
 * 
 * @功能 用于上传图片资源获取访问url，并设定相应的资源格式
 * @author MM_Zerui
 * 
 */
public class UploadImageService {
	private final String URL = "http://7u2h3g.com2.z0.glb.clouddn.com";
	private String token;
	private String width;
	private boolean isWidth = false;
	private String height;
	private boolean isHeight = false;
	private Enum_ImgScaleType enumType;
	private boolean isType=false;
	private String imgPath;
	public UploadImageService setWidth(int width) {
		this.width = String.valueOf(width);
		isWidth = true;
		return this;
	}

	public UploadImageService setHeight(int height) {
		this.height = String.valueOf(height);
		isHeight = true;
		return this;
	}

	public UploadImageService setImageType(Enum_ImgScaleType enumType) {
		this.enumType = enumType;
		isType=true;
		return this;
	}
	public UploadImageService setImagePath(String imgPath){
		this.imgPath=imgPath;
		return this;
	}
	public void uploadImage(final CallBack callBack) {

		new Http_QiniuToken(new Call_httpData<String>() {

			@Override
			public void onSuccess(String datas) {
				// TODO Auto-generated method stub
				token = datas;
				UploadManager uploadManager = new UploadManager();
				uploadManager.put(imgPath, null, token, new UpCompletionHandler() {

					@Override
					public void complete(String arg0, ResponseInfo resInfo,
							JSONObject result) {
						// TODO Auto-generated method stub
						if (resInfo.isOK()) {
							// 获取文件名
							try {
								String imgName = result.getString("name");
								callBack.onSuccess(withImgType(imgName,
										enumType));
								callBack.onFinally();
								return;
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								callBack.onFail();
							}
						} else {
							callBack.onFail();
						}
						callBack.onFinally();
					}

				}, null);
			}

			@Override
			public void onStart() {
				// TODO Auto-generated method stub
				callBack.onStart();
			}

			@Override
			public void onFinally() {
				// TODO Auto-generated method stub
				callBack.onFinally();
			}

			@Override
			public void onFail() {
				// TODO Auto-generated method stub
				callBack.onFail();
			}
		}).onAction();
	}

	private String withImgType(String imgName, Enum_ImgScaleType enumType) {
		StringBuilder strBuilder = new StringBuilder(URL);
		strBuilder.append(addParams(imgName));
	
		if(isType){
			strBuilder.append("?").append("imageView2");
			strBuilder.append(addParams(enumType.toString()));
			if (isWidth) {
				strBuilder.append(addParams("w")).append(addParams(width));
			}
			if (isHeight) {
				strBuilder.append(addParams("h")).append(addParams(height));
			}
		}
		
		return strBuilder.toString();
	}

	private String addParams(String param) {
		return "/" + param;
	}

	public static abstract class CallBack {
		public abstract void onStart();

		public abstract void onFail();

		public abstract void onSuccess(String url);

		public abstract void onFinally();

	}
}
