package com.hi.module.base.img;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.android.ruifeng.hi.R;
import com.customview.callBack.topBarCallBack;
import com.customview.view.CustomTopbarView;
import com.format.utils.DataValidate;
import com.hi.module.base.application.AppManager;
import com.hi.module.base.img.adapter.ImageBucketAdapter;
import com.hi.module.base.superClass.SuperActivity;
import com.hi.module.locale.ui.leavenote.ImgSelectDialog;
import com.hi.module.self.ui.ImgSetDialog;
import com.hi.utils.AnimationUtil;
import com.hi.utils.Benchmark;
import com.hi.utils.network.NetAsyncTask;
import com.imagefetch.model.ImageBucket;
import com.imagefetch.util.ImageFetcher;
import com.imagefetch.util.IntentConstants;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 相册列表
 * @author MM_Zerui
 *
 */
public class ImageAlbumList extends SuperActivity{
	@ViewInject(R.id.listView)
	private ListView listView;
	@ViewInject(R.id.topBar)
	private CustomTopbarView topBar;
	
	private ImageBucketAdapter adapter;
	private ImageFetcher mHelper;
	private List<ImageBucket> mDataList = new ArrayList<ImageBucket>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.img_album_list_layout);
		ViewUtils.inject(this);
		super.onCreate(savedInstanceState);
        getAlbumList();
	}

    private void getAlbumList() {
        new NetAsyncTask(context,true){

            @Override
            protected void doInBack() throws Exception {
                mHelper = ImageFetcher.getInstance(getApplicationContext());
                mDataList=mHelper.getImagesBucketList(false);
            }

            @Override
            protected void onPost(Exception e) {
                onInit();
            }
        }.execute();
    }

    private void onInit() {
		// TODO Auto-generated method stub


		if(!DataValidate.checkDataValid(mDataList)){
			return;
		}
		topBar.setTitle("选择一张图片");
		adapter=new ImageBucketAdapter(context, mDataList);
		listView.setAdapter(adapter);
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				//跳转到某相册图片集
				Intent intent = new Intent(context,
						ImageCollectionPage.class);
				intent.putExtra(IntentConstants.EXTRA_IMAGE_LIST,
						(Serializable) mDataList.get(position).imageList);
				intent.putExtra(IntentConstants.EXTRA_BUCKET_NAME,
						mDataList.get(position).bucketName);
//				intent.putExtra(IntentConstants.EXTRA_CAN_ADD_IMAGE_SIZE,
//						availableSize);

				startActivity(intent);
				overridePendingTransition(R.anim.in_right_to_left_page, 0);
			}
		});
		topBar.setCallBack(new topBarCallBack() {
			@Override
			public void call_backBtnListener() {
				// TODO Auto-generated method stub
				super.call_backBtnListener();
				finish();
			}
			@Override
			public void call_leftTextBtnListener() {
				// TODO Auto-generated method stub
				super.call_leftTextBtnListener();
				finish();
			}
		});
	}
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
		AppManager.getAppManager().finishActivity(ImgSelectDialog.class);

		AppManager.getAppManager().finishActivity(ImgSetDialog.class);
		AnimationUtil.finishOut2Bottom(context);
		//设定是否截图的标识
		ImageCollectionPage.ifTurnToClip=false;
		
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
	}
}
