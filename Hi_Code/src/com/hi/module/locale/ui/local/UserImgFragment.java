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
 *	��ʾ�û���Ƭǽͼ���ҳ��
 */
@SuppressLint("ValidFragment")
public class UserImgFragment extends Fragment{
	private View view;
	private String imgUrl;
	private ImageView photoView;
	// ͼ�����ع���
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
		loadOptions = new DisplayImageOptions.Builder() // ����ͼƬ�����ڼ���ʾ��ͼƬ
				.showImageForEmptyUri(R.drawable.app_icon) // ����ͼƬUriΪ�ջ��Ǵ����ʱ����ʾ��ͼƬ
				.showImageOnFail(R.drawable.app_icon) // ����ͼƬ���ػ��������з���������ʾ��ͼƬ
				.cacheInMemory(true) // �������ص�ͼƬ�Ƿ񻺴����ڴ���
				.cacheOnDisc(true).build(); // �������ù���DisplayImageOption����
		imageLoader = ImageLoader.getInstance();
	}
}
