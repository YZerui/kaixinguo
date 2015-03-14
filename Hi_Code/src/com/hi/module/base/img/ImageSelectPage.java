package com.hi.module.base.img;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.ruifeng.hi.R;
import com.exception.utils.P;
import com.format.utils.DataValidate;
import com.hi.module.base.application.AppManager;
import com.hi.module.base.img.ImageLoader.Type;
import com.hi.module.base.superClass.SuperActivity;
import com.hi.module.locale.ui.leavenote.ImgSelectDialog;
import com.hi.module.locale.ui.leavenote.LeaveNoteEditActivity;
import com.hi.utils.AnimationUtil;
import com.hi.utils.imgLoadUtil.ImageManager2;
import com.imagefetch.util.ImageDisplayer;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Handler;

/**
 * 图片选择页面
 *
 * @author MM_Zerui
 */
public class ImageSelectPage extends SuperActivity {
    /**
     * 图片点击事件
     */
    public interface ImageSelectListener{
        public void isImageSelect(boolean isSelect);
    }
    private static Set<ImageSelectListener> imageSelectListeners=new HashSet<>();
    public static void addImageSelect(ImageSelectListener selectListener){
        if(imageSelectListeners.contains(selectListener)){
            return;
        }
        imageSelectListeners.add(selectListener);
    }
    public static void removeSelectListener(ImageSelectListener selectListener){
        imageSelectListeners.remove(selectListener);
    }
    @ViewInject(R.id.back)
    private ImageView back;
    @ViewInject(R.id.select)
    private TextView select;
    @ViewInject(R.id.img)
    private ImageView img;

    public static String imgPath = new String();
//    public static boolean ifSelect = false;
//	protected static DisplayImageOptions loadOptions = new DisplayImageOptions.Builder()
//			.showImageForEmptyUri(R.drawable.default_load_img)
//			.showImageOnFail(R.drawable.default_load_img).cacheOnDisc(true)
//			.imageScaleType(ImageScaleType.IN_SAMPLE_INT)
//			.bitmapConfig(Bitmap.Config.RGB_565).build();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        setContentView(R.layout.img_select_page);
        ViewUtils.inject(this);
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        // TODO Auto-generated method stub
//        ifSelect = false;
        LeaveNoteEditActivity.sourcePath = getIntent().getStringExtra("DATA1");
        // LeaveNoteEditActivity.sourcePath =
        // getIntent().getStringExtra("DATA1");
        ImageSelectPage.imgPath = getIntent().getStringExtra("DATA0");
        // ImageDisplayer.getInstance(context).displayBmp(img,
        // LeaveNoteEditActivity.sourcePath, LeaveNoteEditActivity.sourcePath);
        if (DataValidate.checkDataValid(LeaveNoteEditActivity.sourcePath)) {
//			ImageLoader.getInstance(3,Type.FIFO).loadImage(LeaveNoteEditActivity.sourcePath, img);
            ImageManager2.from(context).displayImage(img, LeaveNoteEditActivity.sourcePath, R.drawable.default_load_img);
        } else {
//			ImageLoader.getInstance(3,Type.FIFO).loadImage(ImageSelectPage.imgPath, img);
            ImageManager2.from(context).displayImage(img, ImageSelectPage.imgPath, R.drawable.default_load_img);
        }

//		 imageLoader.displayImage("file://"+imgPath, img,
//				 loadOptions);

    }

    @OnClick(R.id.back)
    private void backClick(View v) {
        finish();
        AnimationUtil.finishOut2Right(context);
    }

    @OnClick(R.id.select)
    private void selectClick(View v) {
        try {
            AppManager.getAppManager().finishActivity(ImgSelectDialog.class);
            AppManager.getAppManager()
                    .finishActivity(ImageCollectionPage.class);
            AppManager.getAppManager().finishActivity(ImageAlbumList.class);
            AppManager.getAppManager().finishActivity(ImageTotalPage.class);
//			AppManager.getAppManager().finishActivity();


//            ifSelect = true;

            finish();
            AnimationUtil.finishOut2Bottom(context);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    for(ImageSelectListener listener:imageSelectListeners){
                        listener.isImageSelect(true);
                    }
                }
            },300);
        } catch (Exception e) {
            // TODO: handle exception
            toast.setText("图片数据出错");
            P.v(e.getMessage());
        }

    }

    @Override
    public void finish() {
        // TODO Auto-generated method stub
        super.finish();

    }
}
