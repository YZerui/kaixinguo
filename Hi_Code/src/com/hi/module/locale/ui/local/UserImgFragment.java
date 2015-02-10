package com.hi.module.locale.ui.local;

import com.android.ruifeng.hi.R;
import com.hi.common.PARAMS;
import com.hi.utils.ViewHandleUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * @author MM_Zerui
 *	显示用户照片墙图像的页面
 */
@SuppressLint("ValidFragment")
public class UserImgFragment extends Fragment{
	private View view;
	private String imgUrl;
	private ImageView photoView;
	// 图像下载工具
	protected ImageLoader imageLoader;
	protected DisplayImageOptions loadOptions;
	public UserImgFragment() {
		// TODO Auto-generated constructor stub
	}
	public UserImgFragment(String imgUrl) {
		// TODO Auto-generated constructor stub
		this.imgUrl=imgUrl;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view=inflater.inflate(R.layout.local_photo_fragment, null);
		initImgLoadUtil();
		photoView=(ImageView)view.findViewById(R.id.user_wall_photos);
//		System.out.println("imgURL:"+imgUrl);
		imageLoader.displayImage(imgUrl, photoView, loadOptions);
		return view;
	}
	private void initImgLoadUtil() {
		// TODO Auto-generated method stub
		loadOptions = new DisplayImageOptions.Builder() // 设置图片下载期间显示的图片
				.showImageForEmptyUri(R.drawable.app_icon) // 设置图片Uri为空或是错误的时候显示的图片
				.showImageOnFail(R.drawable.app_icon) // 设置图片加载或解码过程中发生错误显示的图片
				.cacheInMemory(true) // 设置下载的图片是否缓存在内存中
				.cacheOnDisc(true).build(); // 创建配置过得DisplayImageOption对象
		imageLoader = ImageLoader.getInstance();
	}
}
