package com.hi.module.base.img;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.android.ruifeng.hi.R;
import com.customview.callBack.topBarCallBack;
import com.customview.view.CustomTopbarView;
import com.hi.module.base.img.adapter.ImageGridAdapter;
import com.hi.module.base.superClass.SuperActivity;
import com.hi.utils.AnimationUtil;
import com.imagefetch.model.ImageItem;
import com.imagefetch.util.ImageFetcher;
import com.imagefetch.util.IntentConstants;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.thread.HandlerExtend;
import com.thread.HandlerExtend.handleCallBack;
import com.thread.RunnableService;
import com.thread.callBack.runCallBack;

/**
 * 图片集页面
 * 
 * @author MM_Zerui
 * 
 */
public class ImageTotalPage extends SuperActivity {
	@ViewInject(R.id.topBar)
	private CustomTopbarView topBar;
	@ViewInject(R.id.gridview)
	private GridView gridView;

	private ImageGridAdapter adapter;
	// MyAdapter myAdapter;
	// private List<ImageItem> mDataList = new ArrayList<ImageItem>();
	private List<String> mStrList = new ArrayList<String>();
	private String mBucketName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.img_collection_layout);
		ViewUtils.inject(this);
		super.onCreate(savedInstanceState);
		intentValue();
		// init();
	}

	@SuppressWarnings("unchecked")
	private void intentValue() {
		// TODO Auto-generated method stub
		new RunnableService(new runCallBack() {

			@Override
			public void start() {
				// TODO Auto-generated method stub
				// mDataList =
				// ImageFetcher.getInstance(context).getImagesAllItemList(false);
				mStrList = ImageFetcher.getInstance(context)
						.getImagesAllStringList(false);
			}

			@Override
			public void end() {
				// TODO Auto-generated method stub
				handlerExtend.onFinally();
			}

		}, true);

	}

	private void init() {
		// TODO Auto-generated method stub
		adapter = new ImageGridAdapter(context, mStrList);
		// myAdapter = new MyAdapter(context, mStrList, R.layout.grid_item);
		topBar.setTitle(mBucketName);
		gridView.setAdapter(adapter);
		topBar.setCallBack(new topBarCallBack() {
			@Override
			public void call_backBtnListener() {
				// TODO Auto-generated method stub
				super.call_backBtnListener();
				finish();
			}

		});
		topBar.setBackText("返回");
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				AnimationUtil.in2LeftIntent(context, ImageSelectPage.class,
				// mDataList.get(position).thumbnailPath,
						mStrList.get(position));
			}
		});
	}

	HandlerExtend handlerExtend = new HandlerExtend(new handleCallBack() {

		@Override
		public void call_onInit() {
			// TODO Auto-generated method stub

		}

		@Override
		public void call_onLoad() {
			// TODO Auto-generated method stub

		}

		public void call_onFinally() {
			// if (mDataList == null)
			// mDataList = new ArrayList<ImageItem>();
			// mBucketName = getIntent().getStringExtra(
			// IntentConstants.EXTRA_BUCKET_NAME);
			// if (TextUtils.isEmpty(mBucketName)) {
			// mBucketName = "请选择";
			// }
			init();
		};

	});

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
		
		AnimationUtil.finishOut2Bottom(context);
	}
}
