package com.hi.module.base.img.adapter;

import java.util.List;

import com.android.ruifeng.hi.R;
import com.exception.utils.P;
import com.hi.module.base.img.ImageLoader;
import com.hi.module.base.img.ImageLoader.Type;
import com.imagefetch.util.ImageDisplayer;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.utils.DiskCacheUtils;
import com.nostra13.universalimageloader.utils.MemoryCacheUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageGridAdapter extends BaseAdapter {
	protected DisplayImageOptions loadOptions;
	private Context mContext;
	private List<String> mDataList;
	private LayoutInflater inflater;
	com.nostra13.universalimageloader.core.ImageLoader imageLoader = com.nostra13.universalimageloader.core.ImageLoader
			.getInstance();

	public ImageGridAdapter(Context context, List<String> dataList) {
		this.mContext = context;
		inflater = LayoutInflater.from(context);
		this.mDataList = dataList;
		loadOptions = new DisplayImageOptions.Builder()
				.showImageForEmptyUri(R.drawable.default_img_head)
				.showImageOnFail(R.drawable.default_img_head).cacheOnDisc(true)
				.imageScaleType(ImageScaleType.IN_SAMPLE_INT)
				.bitmapConfig(Bitmap.Config.RGB_565).build();
	}

	@Override
	public int getCount() {
		return mDataList == null ? 0 : mDataList.size();
	}

	@Override
	public Object getItem(int position) {
		return mDataList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder mHolder;

		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item_image_list, null);
			mHolder = new ViewHolder();
			mHolder.imageIv = (ImageView) convertView
					.findViewById(R.id.image_gridview);
			convertView.setTag(mHolder);
		} else {
			mHolder = (ViewHolder) convertView.getTag();
		}

		final String item = mDataList.get(position);

		// P.v("url:"+item);
		// String url="/storage/emulated/0/hi/image/1420267946989.png";
		mHolder.imageIv.setImageResource(R.drawable.default_load_img);
		ImageLoader.getInstance(3, Type.FIFO).loadImage(item, mHolder.imageIv);
//		 imageLoader.displayImage("file://"+item, mHolder.imageIv,
//		 loadOptions);
		return convertView;
	}

	static class ViewHolder {
		private ImageView imageIv;
	}

}
