package com.hi.view.scaleImg;

import java.io.InputStream;
import java.text.Format;

import com.android.ruifeng.hi.R;
import com.hi.utils.FormatUtils;
import com.hi.utils.FunUtils;
import com.hi.view.customLayout.CustomToast;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.text.format.Time;
import android.util.Log;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * 支持拖拽缩放的页面，接收一个Intent传值
 * 
 * @author MM_Zerui
 * 
 */
public class DragImgShowActivity extends Activity {
	private int window_width, window_height;// 控件宽度
	private DragImageView dragImageView;// 自定义控件
	private int state_height;// 状态栏的高度
	private LinearLayout layout;
	private ViewTreeObserver viewTreeObserver;
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	private ImageView close,save;
	private DisplayImageOptions loadOptions;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
//		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.img_scale_page);
		// 获取Intent传值
		Intent intent = getIntent();
		final String imgUrl = intent.getStringExtra("DATA0");
		/** 获取可見区域高度 **/
		WindowManager manager = getWindowManager();
		window_width = manager.getDefaultDisplay().getWidth();
		window_height = manager.getDefaultDisplay().getHeight();

		dragImageView = (DragImageView) findViewById(R.id.dragImageView);

		loadOptions = new DisplayImageOptions.Builder()
				.showImageOnFail(R.drawable.default_load_img)
				.showImageForEmptyUri(R.drawable.default_load_img)
				.cacheInMemory(true)
				.cacheOnDisc(true).build();

		imageLoader.displayImage(imgUrl, dragImageView, loadOptions);
		
		dragImageView.setmActivity(this);// 注入Activity.
		/** 测量状态栏高度 **/
		viewTreeObserver = dragImageView.getViewTreeObserver();
		viewTreeObserver
				.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

					@Override
					public void onGlobalLayout() {
						if (state_height == 0) {
							// 获取状况栏高度
							Rect frame = new Rect();
							getWindow().getDecorView()
									.getWindowVisibleDisplayFrame(frame);
							state_height = frame.top;
							dragImageView.setScreen_H(window_height
									- state_height);
							dragImageView.setScreen_W(window_width);
						}

					}
				});
		// layout=(LinearLayout)findViewById(R.id.layout);
		// layout.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// // TODO Auto-generated method stub
		// finish();
		// }
		// });
		close = (ImageView) findViewById(R.id.closeImg);
		close.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		save=(ImageView)findViewById(R.id.saveImg);
		save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				saveImg(dragImageView.getDrawable());
			}
		});
	}
	/**
	 * 保存图片到本地的方法
	 */
	private void saveImg(Drawable drawable){
		BitmapDrawable bitmapDrawable=(BitmapDrawable)drawable;
		if(bitmapDrawable==null){
			new CustomToast(getApplicationContext()).
				setText("图片未加载完成，无法加载");
			return;
		}
		Bitmap bitmap=bitmapDrawable.getBitmap();
		FunUtils.savePhotoToSDCard(bitmap,
				Environment.getExternalStorageDirectory()+"/hi",
				FormatUtils.getCurrentDateValue());
		new CustomToast(getApplicationContext()).
			setText("图片保存成功!");
		save.setEnabled(false);
	}
	

}