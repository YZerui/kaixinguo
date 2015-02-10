package com.hi.adapter;

import java.util.ArrayList;

import com.android.ruifeng.hi.R;
import com.hi.common.PARAMS;
import com.hi.module.base.superClass.SuperAdapter;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
//import com.meimei.image.imgload.AsyncImageLoader;
//import com.meimei.image.imgload.ImageCacheManager;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class GalleryImageAdapter extends SuperAdapter {

	private String[] data;
	private LayoutInflater inflater;
	private int width;
//	private AsyncImageLoader imageLoader;

	// private AsyncBitmapLoader asyncBitmapLoader;

	public GalleryImageAdapter(Context context, String[] data,
			int width) {
		this.data = data;
		this.inflater = LayoutInflater.from(context);
		this.width = width;
		loadOptions= new DisplayImageOptions.Builder()			
		.showImageForEmptyUri(R.drawable.default_load_img)	
		.showImageOnFail(R.drawable.default_load_img)
		.showImageForEmptyUri(R.drawable.default_load_img)
		.showStubImage(R.drawable.default_load_img)
//		.showStubImage(R.drawable.app_icon)
		.cacheInMemory(true)						
		.cacheOnDisc(true)						
		.build();		
	}

	@Override
	public int getCount() {
		int count = 0;
		if (data != null) {
			count = data.length;
		}
		return count;
	}

	@Override
	public Object getItem(int position) {
		String item = null;
		if (data != null) {
			item = data[position];
		}
		return item;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.horizontal_gallery_img_cell, null);
		}
		ImageView iv = (ImageView) convertView.findViewById(R.id.iv);
		int height = width * 312 / 500;
		convertView.setLayoutParams(new Gallery.LayoutParams(width, height));
//		Bitmap bitmap = imageLoader.loadBitmap(iv, data.get(position), true,PARAMS.LIST_IMG);
		imageLoader.displayImage(data[position],iv, loadOptions);
		iv.setScaleType(ImageView.ScaleType.FIT_XY);
		
		// BitmapDrawable bitmapDrawable = asyncBitmapLoader.loadBitmap(
		// data.get(position), iv, new ImageCallBack() {
		//
		// @Override
		// public void imageLoad(ImageView imageView,
		// BitmapDrawable bitmap) {
		// imageView.setImageBitmap(bitmap.getBitmap());
		// }
		// });
//		if (bitmap != null) {
//			iv.setImageBitmap(bitmap);
//		} else {
//			iv.setImageResource(R.drawable.app_icon);
//		}
		return convertView;
	}
}
