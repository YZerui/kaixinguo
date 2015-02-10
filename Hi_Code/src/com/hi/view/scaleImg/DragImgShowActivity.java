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
 * ֧����ק���ŵ�ҳ�棬����һ��Intent��ֵ
 * 
 * @author MM_Zerui
 * 
 */
public class DragImgShowActivity extends Activity {
	private int window_width, window_height;// �ؼ����
	private DragImageView dragImageView;// �Զ���ؼ�
	private int state_height;// ״̬���ĸ߶�
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
		// ��ȡIntent��ֵ
		Intent intent = getIntent();
		final String imgUrl = intent.getStringExtra("DATA0");
		/** ��ȡ��Ҋ����߶� **/
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
		
		dragImageView.setmActivity(this);// ע��Activity.
		/** ����״̬���߶� **/
		viewTreeObserver = dragImageView.getViewTreeObserver();
		viewTreeObserver
				.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

					@Override
					public void onGlobalLayout() {
						if (state_height == 0) {
							// ��ȡ״�����߶�
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
	 * ����ͼƬ�����صķ���
	 */
	private void saveImg(Drawable drawable){
		BitmapDrawable bitmapDrawable=(BitmapDrawable)drawable;
		if(bitmapDrawable==null){
			new CustomToast(getApplicationContext()).
				setText("ͼƬδ������ɣ��޷�����");
			return;
		}
		Bitmap bitmap=bitmapDrawable.getBitmap();
		FunUtils.savePhotoToSDCard(bitmap,
				Environment.getExternalStorageDirectory()+"/hi",
				FormatUtils.getCurrentDateValue());
		new CustomToast(getApplicationContext()).
			setText("ͼƬ����ɹ�!");
		save.setEnabled(false);
	}
	

}