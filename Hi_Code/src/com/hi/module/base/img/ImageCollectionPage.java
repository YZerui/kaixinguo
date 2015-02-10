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
import com.hi.module.self.ui.ImgClipPage;
import com.hi.utils.AnimationUtil;
import com.imagefetch.model.ImageItem;
import com.imagefetch.util.IntentConstants;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * Í¼Æ¬¼¯Ò³Ãæ
 * 
 * @author MM_Zerui
 * 
 */
public class ImageCollectionPage extends SuperActivity {
	@ViewInject(R.id.topBar)
	private CustomTopbarView topBar;
	@ViewInject(R.id.gridview)
	private GridView gridView;

	private ImageGridAdapter adapter;
	private List<ImageItem> mDataList = new ArrayList<ImageItem>();
	private List<String> mStrList=new ArrayList<String>();
	private String mBucketName;

	public static boolean ifTurnToClip=false;//ÊÇ·ñÌø×ªµ½Í¼Æ¬¼ôÇÐÒ³Ãæ
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.img_collection_layout);
		ViewUtils.inject(this);
		super.onCreate(savedInstanceState);
		intentValue();
		init();
	}

	@SuppressWarnings("unchecked")
	private void intentValue() {
		// TODO Auto-generated method stub
		mDataList = (List<ImageItem>) getIntent().getSerializableExtra(
				IntentConstants.EXTRA_IMAGE_LIST);
		if (mDataList == null)
			mDataList = new ArrayList<ImageItem>();
		for(ImageItem item:mDataList){
			mStrList.add(item.sourcePath);
		}
		mBucketName = getIntent().getStringExtra(
				IntentConstants.EXTRA_BUCKET_NAME);
		if (TextUtils.isEmpty(mBucketName)) {
			mBucketName = "ÇëÑ¡Ôñ";
		}
	}

	private void init() {
		// TODO Auto-generated method stub
		adapter=new ImageGridAdapter(context, mStrList);
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
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				if(ifTurnToClip){
					//Ìø×ªµ½Í¼Ïñ¼ôÇÐÒ³Ãæ
					AnimationUtil.in2LeftIntent(context, 
							ImgClipPage.class,mDataList.get(position).sourcePath);
					
					return;
				}
				AnimationUtil.in2LeftIntent(context, 
						ImageSelectPage.class,mDataList.get(position).thumbnailPath
						,mDataList.get(position).sourcePath);
				
			}
		});
	}
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
		AnimationUtil.finishOut2Right(context);
	}
	
}
