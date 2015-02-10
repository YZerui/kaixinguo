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
 * @author ��ѵ��
 * 
 *         2013-9-6����12:22:29
 */
public class ShowBigPicUtil {
    private final static int GO = 1;
    private final static int BACK = 2;
    private final static int DURATION = 300;
    // ////activity�������Ĳ���
    private Activity activity;
    private ImageView imageView;
    private ImageView oldImageView;
    private RelativeLayout parent;

    // //�����������Ҫ�õ���ֵ
    private int startY;
    private int startX;
    private int top;// ״̬���ĸ߶�
    private float scaleX;// width����ķŴ���
    private float scaleY;// �߶��ϵķŴ���
    private float scale;// ȡ��С��
    private boolean canback = true;// ��ֹ��ε�� ���������С�Ķ���
    private int moveWidth;// xλ��
    private int moveHeght;// yλ��
    private int height;
    private int width;
    private int parentWidth;
    private int parentHeight;

    // //��ʾСͼ �ʹ�ͼ ��·��
    private String smallPic;
    private String bigPic;

    /**
     * 
     * @param activity ��������˵
     * @param oldImageView ��Ҫ�Ŵ��ͼƬ
     * @param parent �����һ�㲻��������Ļ�Ĳ��� ������Ҫʱ��ɫ�� ������д������relativelayout
     * @param smallPic Сͼ�ĵ�ַ
     * @param bigPic ��ͼ�ĵ�ַ
     * @category ע�⣺��������õ��˿�Դ��ͼƬ���صĹ���ImageLoader
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
        // ����Ҳ����� ����ڿ������������ ִ���� ���� ִ�����֮�� �����ɼ�
        // <br/>�����Ļ����ͷ��� ��һֱռ�Ž��� һֱ���Ե��
        // <br/> �����Ժ�ע�� Ҫ�ȿɼ� ��ִ�ж���
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
     * ��ʼ����
     */
    private void startAnimation() {
        animationset();
    }

    /**
     * �ı䱳�������
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
     * ʹ��ϵͳ�Ĵ������⣺ �����ڲ��Ź����й켣ƫת��<br/>
     * ԭ�򣺴�ѧ����任���潲�� �ȳ˾����˾���õ��Ľ���ǲ�һ���� ������ Ӧ����ȥ����<br/>
     * Ȼ������Լ��پݶ�λ�Ʋ��� ���Զ���Ķ�������<br/>
     * ͬ�� pre����˼�Ƿ���ǰ�� post����˼�Ƿ��ں��� ���� ������ Ӧ����preλ�ƶ��� ��pre���Ŷ��� �Ż�ﵽЧ��
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
     * Ϊ�յĽ������һ��imagview
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
        // TODO ��ʾСͼ
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
        // ����С�ı���� ��Ӧ���Ǹ��Ŵ����
        if (scaleX > scaleY)
            scale = scaleY;
        else {
            scale = scaleX;
        }
    }

    /**
     * �����Ǽ������������ı������ж��
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
                // TODO ��ʾ��ͼ
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
