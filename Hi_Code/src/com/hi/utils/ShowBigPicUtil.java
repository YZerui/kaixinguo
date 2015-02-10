package com.hi.utils;

import java.lang.reflect.Field;

import com.nostra13.universalimageloader.core.ImageLoader;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;


/**
 * @author 王训龙
 * 
 *         2013-9-6下午12:22:29
 */
public class ShowBigPicUtil {
    private final static int GO = 1;
    private final static int BACK = 2;
    private final static int DURATION = 300;
    // ////activity传过来的参数
    private Activity activity;
    private ImageView imageView;
    private ImageView oldImageView;
    private RelativeLayout parent;

    // //计算过程中需要用到的值
    private int startY;
    private int startX;
    private int top;// 状态栏的高度
    private float scaleX;// width上面的放大倍数
    private float scaleY;// 高度上的放大倍数
    private float scale;// 取最小的
    private boolean canback = true;// 防止多次点击 产生多个缩小的动画
    private int moveWidth;// x位移
    private int moveHeght;// y位移
    private int height;
    private int width;
    private int parentWidth;
    private int parentHeight;

    // //显示小图 和大图 的路径
    private String smallPic;
    private String bigPic;

    /**
     * 
     * @param activity 传过来再说
     * @param oldImageView 需要放大的图片
     * @param parent 这个是一层不满整个屏幕的布局 背景需要时黑色的 这里面写的是用relativelayout
     * @param smallPic 小图的地址
     * @param bigPic 大图的地址
     * @category 注意：这个里面用到了开源的图片下载的工具ImageLoader
     */
    public ShowBigPicUtil(Activity activity, ImageView oldImageView, RelativeLayout parent, String smallPic,
            String bigPic) {
        this.activity = activity;
        this.oldImageView = oldImageView;
        this.parent = parent;
        this.smallPic = smallPic;
        this.bigPic = bigPic;
    }

    public void show() {
        // 这里：我不明白 如果在看不见的情况下 执行了 动画 执行完成之后 让它可见
        // <br/>这样的话它就疯了 就一直占着焦点 一直可以点击
        // <br/> 所以以后注意 要先可见 再执行动画
        parent.setVisibility(View.VISIBLE);
        changeBackGround(0.5f, 1.0f, DURATION);
        parent.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (canback) {
                    changeBackGround(1.0f, 0.5f, DURATION);
                    animationSetBack();
                }
            }
        });
        calcuData();
        addImageView();
        startAnimation();
    }

    /**
     * 开始动画
     */
    private void startAnimation() {
        animationset();
    }

    /**
     * 改变背景的深度
     */
    private void changeBackGround(float from, final float to, long durationMillis) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(from, to);
        alphaAnimation.setDuration(durationMillis);
        alphaAnimation.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (to != 1.0f) {
                    parent.setVisibility(View.GONE);
                } else {
                    parent.setVisibility(View.VISIBLE);
                }
            }
        });
        parent.startAnimation(alphaAnimation);
    }

    /**
     * 使用系统的存在问题： 动画在播放过程中轨迹偏转了<br/>
     * 原因：大学矩阵变换里面讲过 先乘矩阵后乘矩阵得到的结果是不一样的 在这里 应该先去缩放<br/>
     * 然后相对自己再据对位移才行 在自定义的动画里面<br/>
     * 同理 pre的意思是放在前面 post的意思是放在后面 所以 在这里 应该先pre位移动画 再pre缩放动画 才会达到效果
     */
    private void animationset() {
        AnimationSet animationSet = new AnimationSet(false);
        if (hasBigPic()) {

            ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, scale, 1.0f, scale, Animation.RELATIVE_TO_SELF,
                    0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            scaleAnimation.setFillAfter(true);
            animationSet.addAnimation(scaleAnimation);

            TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.ABSOLUTE, moveWidth, Animation.RELATIVE_TO_SELF, 0.0f, Animation.ABSOLUTE, moveHeght);
            translateAnimation.setFillAfter(true);
            animationSet.addAnimation(translateAnimation);
        } else {
            animationSet.addAnimation(new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.5f, Animation.ABSOLUTE,
                    moveWidth, Animation.RELATIVE_TO_SELF, 0.5f, Animation.ABSOLUTE, moveHeght));
        }
        animationSet.setInterpolator(new AccelerateInterpolator());
        animationSet.setAnimationListener(new AnimationSetOver(GO));
        animationSet.setDuration(DURATION);
        animationSet.setFillAfter(true);
        imageView.startAnimation(animationSet);
    }

    /**
     * @return
     */
    private boolean hasBigPic() {
        return true;
    }

    /**
     * 为空的界面添加一个imagview
     */
    private void addImageView() {
        if (parent.getChildCount() == 0) {
            imageView = new ImageView(activity);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(width, height);
            layoutParams.setMargins(startX, startY, 0, 0);
            imageView.setScaleType(ScaleType.CENTER_CROP);
            imageView.setLayoutParams(layoutParams);
            parent.addView(imageView);
        } else {
            imageView = (ImageView) parent.getChildAt(0);
        }
        // TODO 显示小图
        // oldImageView.setDrawingCacheEnabled(true);
        // Bitmap drawingCache = oldImageView.getDrawingCache();
        ImageLoader.getInstance().displayImage(smallPic, imageView);
        // imageView.setImageBitmap(drawingCache);
    }

    /**
	 * 
	 */
    private void animationSetBack() {
        canback = false;
        AnimationSet animationSet = new AnimationSet(false);
        ScaleAnimation scaleAnimation = new ScaleAnimation(scale, 1.0f, scale, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setFillAfter(true);
        animationSet.addAnimation(scaleAnimation);

        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.ABSOLUTE, moveWidth,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.ABSOLUTE, moveHeght, Animation.RELATIVE_TO_SELF, 0.0f);
        translateAnimation.setFillAfter(true);
        animationSet.addAnimation(translateAnimation);
        animationSet.setAnimationListener(new AnimationSetOver(BACK));
        animationSet.setDuration(DURATION);
        animationSet.setFillAfter(true);
        imageView.startAnimation(animationSet);
    }

    private void calcuData() {
        int[] location = new int[2];
        oldImageView.getLocationInWindow(location);
        startX = location[0];
        startY = location[1];
        width = oldImageView.getWidth();
        height = oldImageView.getHeight();
        top = getStatuTop();
        startY -= top;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        parentWidth = displayMetrics.widthPixels;
        parentHeight = displayMetrics.heightPixels;
        parentHeight -= top;
        moveWidth = parentWidth / 2 - (startX + width / 2);
        moveHeght = parentHeight / 2 - (startY + height / 2);
        scaleX = (float) parentWidth / (float) width;
        scaleY = (float) parentHeight / (float) height;
        // 和最小的边相等 不应该是各放大各的
        if (scaleX > scaleY)
            scale = scaleY;
        else {
            scale = scaleX;
        }
    }

    /**
     * 这里是计算出来最上面的标题栏有多高
     * 
     * @return
     */
    private int getStatuTop() {
        @SuppressWarnings("rawtypes")
        Class clazz = null;
        try {
            clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            Field field = clazz.getField("status_bar_height");
            int x = Integer.parseInt(field.get(object).toString());
            return activity.getResources().getDimensionPixelSize(x);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    class AnimationSetOver implements AnimationListener {
        private int what;

        public AnimationSetOver(int what) {
            this.what = what;
        }

        @Override
        public void onAnimationStart(Animation animation) {
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            canback = true;
            if (what == GO) {
                // TODO 显示大图
                ImageLoader.getInstance().displayImage(bigPic, imageView);
            } else if (what == BACK) {
                activity.finish();
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }
    }
}
