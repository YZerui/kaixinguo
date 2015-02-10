package com.hi.view.customClipLayout;
import com.exception.utils.P;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewTreeObserver;
import android.widget.ImageView;


/**
 * User: MM_Zerui
 * Time: 2015/1/21 20:01
 */
public class ClipImageView extends ImageView implements ScaleGestureDetector.OnScaleGestureListener
        , View.OnTouchListener, ViewTreeObserver.OnGlobalLayoutListener {

    private static final String TAG = ClipImageView.class.getSimpleName();

    public static float SCALE_MAX = 4f;
    private static float SCALE_MID =2f;

    //初始化时的缩放比例
    private float initScale = 1.0f;

    //用于存放矩阵的9个值
    private final float[] matrixValues = new float[9];

    private boolean once = true;

    //缩放的手势检测
    private ScaleGestureDetector mScaleGestureDetector = null;

    //双击的手势监测
    private GestureDetector gestureDetector;
    private final Matrix mScaleMatrix = new Matrix();

    private int lastPointerCount;
    private float mLastX;
    private float mLastY;

    private boolean isCanDrag;
    private boolean isCheckTopAndBottom = true;
    private boolean isCheckLeftAndRight = true;

    private int mTouchSlop;
    private boolean isAutoScale;

    private int mHorizontalPadding = 30;
    private int mVerticalPadding;

    public ClipImageView(Context context) {
        this(context, null);
    }

    public ClipImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        super.setScaleType(ScaleType.MATRIX);
        mScaleGestureDetector = new ScaleGestureDetector(context, this);
        this.setOnTouchListener(this);
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                if (isAutoScale == true) {
                    return true;
                }
                float x = e.getX();
                float y = e.getY();
                if (getScale() < SCALE_MID) {
                    ClipImageView.this.postDelayed(new AutoScaleRunnable(SCALE_MID, x, y), 16);
                    isAutoScale = true;
                } else if (getScale() >= SCALE_MID && getScale() < SCALE_MAX) {
                    ClipImageView.this.postDelayed(
                            new AutoScaleRunnable(SCALE_MAX, x, y), 16);
                    isAutoScale = true;
                } else {
                    ClipImageView.this.postDelayed(
                            new AutoScaleRunnable(initScale, x, y), 16);
                    isAutoScale = true;
                }

                return true;
            }
        });
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        super.setScaleType(ScaleType.MATRIX);
        this.setOnTouchListener(this);
    }

    private class AutoScaleRunnable implements Runnable {
        static final float BIGGER = 1.07F;
        static final float SMALLER = 0.93F;
        private float mTargetScale;
        private float tmpScale;

        //缩放的中心
        private float x;
        private float y;

        /**
         * 传入目标缩放值，根据目标值和当前值，判断应该放大还是缩小
         *
         * @param targetScale
         * @param x
         * @param y
         */
        public AutoScaleRunnable(float targetScale, float x, float y) {
            this.mTargetScale = targetScale;
            this.x = x;
            this.y = y;
            if (getScale() < mTargetScale) {
                tmpScale = BIGGER;
            } else {
                tmpScale = SMALLER;
            }
        }

        @Override
        public void run() {
            mScaleMatrix.postScale(tmpScale, tmpScale, x, y);
            checkBorderAndCenterWhenScale();
            setImageMatrix(mScaleMatrix);

            final float currentScale = getScale();

            //如果视图还在放大或是缩小，则迭代下去
            if (((tmpScale > 1f) && (currentScale < mTargetScale)) ||
                    ((tmpScale < 1f) && (mTargetScale < currentScale))) {
                ClipImageView.this.postDelayed(this, 16);
            } else {     //否则，缩放至目标比例
                final float deltaScale = mTargetScale / currentScale;
                mScaleMatrix.postScale(deltaScale, deltaScale, x, y);
                checkBorderAndCenterWhenScale();
                setImageMatrix(mScaleMatrix);
                isAutoScale = false;
            }
        }
    }

    /**
     * 视图缩放时产生的回调
     *
     * @param detector
     * @return
     */
    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        float scale = getScale();
        float scaleFactor = detector.getScaleFactor();
        if (getDrawable() == null) {
            return true;
        }
        if ((scale < SCALE_MAX && scaleFactor > 1.0f) ||    //如果图片正在放大状态
                (scale > initScale && scaleFactor < 1.0f)) { //如果图片正在缩小状态
            //当缩小达到最小值时，设定缩放比例为默认的最小值
            if (scaleFactor * scale < initScale) {
                scaleFactor = initScale / scale;
            }
            //当放大比例达到最大值时，设定放大比例为最大值
            if (scaleFactor * scale > SCALE_MAX) {
                scaleFactor = SCALE_MAX / scale;
            }
            //设置缩放比例
            mScaleMatrix.postScale(scaleFactor, scaleFactor,
                    detector.getFocusX(), detector.getFocusY());
            checkBorderAndCenterWhenScale();
            setImageMatrix(mScaleMatrix);
        }
        return true;
    }

    /**
     * 在缩放时，对图片显示范围进行控制
     */
    private void checkBorderAndCenterWhenScale() {
        RectF rectF = getMatrixRectF();
        float deltaX = 0;
        float deltaY = 0;

        int width = getWidth();
        int height = getHeight();

        //如果宽或高大于屏幕，则控制范围
        if (rectF.width() >= width-2*mHorizontalPadding) {
            if (rectF.left > mHorizontalPadding) {   //如果向右偏移
                deltaX = -rectF.left+mHorizontalPadding;
            }
            if (rectF.right < width-mHorizontalPadding) {//如果向左偏移
                deltaX = width - rectF.right-mHorizontalPadding;
            }
        }
        if (rectF.height() >= height-2*mVerticalPadding) {
            if (rectF.top > mVerticalPadding) {    //如果向下偏移
                deltaY = -rectF.top+mVerticalPadding;
            }
            if (rectF.bottom < height-mVerticalPadding) { //如果向上偏移
                deltaY = height - rectF.bottom-mVerticalPadding;
            }
        }
//        width=width-2*mHorizontalPadding;
//        height=height-2*mVerticalPadding;
        //如果宽或高小于屏幕，则让其居中
//        if (rectF.width() < width) {
//            deltaX = width * 0.5f - rectF.right + 0.5f * rectF.width();
//        }
//        if (rectF.height() < height) {
//            deltaY = height * 0.5f - rectF.bottom + 0.5f * rectF.height();
//        }
        mScaleMatrix.postTranslate(deltaX, deltaY);
    }

    /**
     * 根据当前图片的Matrix获得图片的范围
     *
     * @return
     */
    private RectF getMatrixRectF() {
        Matrix matrix = mScaleMatrix;
        RectF rect = new RectF();
        Drawable d = getDrawable();
        if (null != d) {
            rect.set(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
            matrix.mapRect(rect);
        }
        return rect;
    }

    /**
     * 获取当前的缩放比例
     *
     * @return
     */
    public final float getScale() {
        mScaleMatrix.getValues(matrixValues);
        return matrixValues[Matrix.MSCALE_X];
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getViewTreeObserver().removeGlobalOnLayoutListener(this);
    }

    /**
     * 当布局变化或视图的可见性发生改变时触发此回调
     */
	@Override
    public void onGlobalLayout() {
        if (once) {
        	P.v("onGlobalLayout");
            Drawable d = getDrawable();
            if (d == null) {
                return;
            }
            Log.e(TAG, d.getIntrinsicWidth() + "," + d.getIntrinsicHeight());
            // 计算padding的px
//            mHorizontalPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
//                    mHorizontalPadding, getResources().getDisplayMetrics());
            // 垂直方向的边距
            mVerticalPadding = (getHeight() - (getWidth() - 2 * mHorizontalPadding)) / 2;

            int width = getWidth();
            int height = getHeight();
            //拿到图片的宽和高
            int dw = d.getIntrinsicWidth();
            int dh = d.getIntrinsicHeight();
            float scale = 1.0f;
            //如果宽为最小值且小于矩形宽
            if(dw<getWidth()-mHorizontalPadding*2&&dh>getHeight()-mVerticalPadding*2){
                scale=(getWidth()*1.0F-mHorizontalPadding*2)/dw;
            }
            //如果高为最小值且小于矩形高
            if(dh<getHeight()-mVerticalPadding*2&&dw>getWidth()-mHorizontalPadding*2){
                scale=(getHeight()-mVerticalPadding*2)/dh;
            }

            //如果高和宽都小于矩形高和宽
            if(dh<getHeight()-mVerticalPadding*2&&dw<getWidth()-mHorizontalPadding*2){
                float scaleX=(getWidth()*1.0F-mHorizontalPadding*2)/dw;
                float scaleY=(getHeight()-mVerticalPadding*2)/dh;
                scale=Math.max(scaleX,scaleY);
            }

//            //如果图片的宽或高大于屏幕，则缩放至屏幕的宽或高
            if (dw > width-mHorizontalPadding*2 && dh <= height-mVerticalPadding*2) {   //宽大 高小
                scale = (height-mVerticalPadding*2) * 1.0f / dh;
            }
            if (dw < width-mHorizontalPadding*2 && dh > height-mVerticalPadding*2) {    //宽小 高大
                scale = (width-mHorizontalPadding*2) * 1.0f / dw;
            }
            //如果宽和高都大于屏幕，则让其按比例适应屏幕大小
            if (dw > width-mHorizontalPadding*2 && dh > height-mVerticalPadding*2) {
                scale = Math.max((width-mHorizontalPadding*2) * 1.0f / dw, (height-mVerticalPadding*2) * 1.0f / dh);
            }
            initScale = scale;
            SCALE_MID=initScale*2f;
            SCALE_MAX=initScale*4f;
            //图片移至屏幕中心
            mScaleMatrix.postTranslate((width - dw) / 2, (height - dh) / 2);
            //设置缩放
            mScaleMatrix.postScale(scale, scale,getWidth()/2,getHeight()/2);
            setImageMatrix(mScaleMatrix);
            once = false;
        }
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {
        return true;
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (gestureDetector.onTouchEvent(event))
            return true;
        mScaleGestureDetector.onTouchEvent(event);

        float x = 0, y = 0;
        //拿到触摸点的个数
        final int pointerCount = event.getPointerCount();
        //得到多个触摸点的x与y均值
        for (int i = 0; i < pointerCount; i++) {
            y += event.getY(i);
            x += event.getX(i);
        }
        x = x / pointerCount;
        y = y / pointerCount;
        //每当触摸点发生变坏时，重置mLastX,mLastY
        if (pointerCount != lastPointerCount) {
            isCanDrag = false;
            mLastX = x;
            mLastY = y;
        }
        lastPointerCount = pointerCount;

        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                float dx = x - mLastX;
                float dy = y - mLastY;
                if (!isCanDrag) {
                    isCanDrag = isCanDrag(dx, dy);
                }
                if (isCanDrag) {
                    RectF rectF = getMatrixRectF();
                    if (getDrawable() != null) {
                        isCheckLeftAndRight = isCheckTopAndBottom = true;
                        //如果图像宽度小于屏幕宽度，则禁止左右移动
                        if (rectF.width() < getWidth()-2*mHorizontalPadding) {
                            dx = 0;
                            isCheckLeftAndRight = false;
                        }
                        //如果图像高度小于屏幕高度，则禁止上下移动
                        if (rectF.height() < getHeight()-2*mVerticalPadding) {
                            dy = 0;
                            isCheckTopAndBottom = false;
                        }
                        mScaleMatrix.postTranslate(dx, dy);
                        checkMatrixBounds();
                        setImageMatrix(mScaleMatrix);
                    }
                }
                mLastY = y;
                mLastX = x;
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                lastPointerCount = 0;
                break;
        }
        return true;
    }
    public Bitmap clip(){
        Bitmap bitmap=Bitmap.createBitmap(getWidth(),getHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(bitmap);
        draw(canvas);
        return Bitmap.createBitmap(bitmap,mHorizontalPadding,
                mVerticalPadding,getWidth()-2*mHorizontalPadding,
                getWidth()-2*mHorizontalPadding);
    }
    /**
     * 移动时进行边界判断，主要判断宽或高大于屏幕的
     */
    private void checkMatrixBounds() {

        RectF rectF = getMatrixRectF();
        float deltaX = 0, deltaY = 0;
        final float viewWidth = getWidth();
        final float viewHeight = getHeight();
        // 如果宽或高大于屏幕，则控制范围
        if(rectF.width()>=viewWidth-2*mHorizontalPadding){
            if(rectF.left>mHorizontalPadding){
                deltaX=-rectF.left+mHorizontalPadding;
            }
            if(rectF.right<viewWidth-mHorizontalPadding){
                deltaX=viewWidth-mHorizontalPadding-rectF.right;
            }
        }
        if(rectF.height()>=viewHeight-2*mVerticalPadding){
            if(rectF.top>mVerticalPadding){
                deltaY=-rectF.top+mVerticalPadding;
            }
            if(rectF.bottom<viewHeight-mVerticalPadding){
                deltaY=viewHeight-mVerticalPadding-rectF.bottom;
            }
        }
//        if (rectF.top > 0 && isCheckTopAndBottom) {
//            deltaY = -rectF.top;
//        }
//        if (rectF.bottom < viewHeight && isCheckTopAndBottom) {
//            deltaY = viewHeight - rectF.bottom;
//        }
//        if (rectF.left > 0 && isCheckLeftAndRight) {
//            deltaX = -rectF.left;
//        }
//        if (rectF.right < viewWidth && isCheckLeftAndRight) {
//            deltaX = viewWidth - rectF.right;
//        }
        System.out.println("delta X:" + deltaX + " delta Y:" + deltaY);
        mScaleMatrix.postTranslate(deltaX, deltaY);

    }

    private boolean isCanDrag(float dx, float dy) {
        return Math.sqrt((dx * dx) + (dy * dy)) >= mTouchSlop;
    }

    public void setHorizontalPadding(int mHorizontalPadding){
        this.mHorizontalPadding=mHorizontalPadding;
    }
}
