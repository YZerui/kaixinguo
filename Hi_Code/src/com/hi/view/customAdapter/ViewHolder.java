package com.hi.view.customAdapter;

import com.android.ruifeng.hi.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * User: MM_Zerui Time: 2015/1/23 19:25
 */
public class ViewHolder {
	private final SparseArray<View> mViews;
	private View mContentView;
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	protected DisplayImageOptions loadOptions = new DisplayImageOptions.Builder()
			.showImageForEmptyUri(R.drawable.default_img_load)
			.showImageOnFail(R.drawable.default_img_load)
			.showStubImage(R.drawable.photo_loading).cacheInMemory(true)
			.displayer(new FadeInBitmapDisplayer(300)).cacheOnDisc(true)
			.build();

	private ViewHolder(Context context, ViewGroup parent, int layoutId,
			int position) {
		this.mViews = new SparseArray<View>();
		mContentView = LayoutInflater.from(context).inflate(layoutId, parent,
				false);
		mContentView.setTag(this);
	}

	public static ViewHolder get(Context context, View contentView,
			ViewGroup parent, int layoutId, int position) {
		if (contentView == null) {
			return new ViewHolder(context, parent, layoutId, position);
		}
		return (ViewHolder) contentView.getTag();
	}

	public <T extends View> T getView(int viewId) {
		View view = mViews.get(viewId);
		if (view == null) {
			view = mContentView.findViewById(viewId);
			mViews.put(viewId, view);
		}
		return (T) view;
	}

	public View getContentView() {
		return mContentView;
	}

	public ViewHolder setText(int viewId, String text) {
		TextView textView = getView(viewId);
		textView.setText(text);
		return this;
	}

	public ViewHolder setHttpImg(int viewId, String imgUrl) {
		ImageView imageView = getView(viewId);
		imageLoader.displayImage(imgUrl, imageView, loadOptions);
		return this;
	}
}
