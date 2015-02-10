package com.hi.view.customClipLayout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import java.lang.reflect.Type;

/**
 * User: MM_Zerui
 * Time: 2015/1/22 15:09
 */
public class ClipImageBorderView extends View{
    private final String outerColor="#aa000000";
    /**
     * 水平方向与view的边距
     */
    private int mHorizontalPadding=30;

    /**
     * 垂直方向与view的边距
     */
    private int mVerticalPadding;

    /**
     * 绘制的矩形宽度
     */
    private int mWidth;

    /**
     * 边框的颜色，默认为橙色
     */
    private int mBorderColor= Color.parseColor("#fffa723a");

    /**
     * 边框的宽度 单位dp
     */
    private int mBorderWidth=1;

    private Paint mPaint;



    public ClipImageBorderView(Context context) {
       this(context, null);
    }

    public ClipImageBorderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ClipImageBorderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //计算padding的px
//        mHorizontalPadding=(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP
//            ,mHorizontalPadding,getResources().getDisplayMetrics());
        mBorderWidth=(int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP
            ,mBorderWidth,getResources().getDisplayMetrics());
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //计算矩形区域的宽度
        mWidth=getWidth()-2*mHorizontalPadding;
        //计算距离屏幕顶端和底段边界的边距
        mVerticalPadding=(getHeight()-mWidth)/2;
        mPaint.setColor(Color.parseColor(outerColor));
        mPaint.setStyle(Paint.Style.FILL);

        //绘制左边
        canvas.drawRect(0,0,mHorizontalPadding,getHeight(),mPaint);
        //绘制右边
        canvas.drawRect(getWidth()-mHorizontalPadding,0,getWidth(),getHeight(),mPaint);
        //绘制上边
        canvas.drawRect(mHorizontalPadding,0,getWidth()-mHorizontalPadding,
                mVerticalPadding,mPaint);
        //绘制下边
        canvas.drawRect(mHorizontalPadding,getHeight()-mVerticalPadding,
                getWidth()-mHorizontalPadding,getHeight(),mPaint);
        //绘制外边框
        mPaint.setColor(mBorderColor);
        mPaint.setStrokeWidth(mBorderWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(mHorizontalPadding,mVerticalPadding,
                getWidth()-mHorizontalPadding,getHeight()-mVerticalPadding,mPaint);
    }
    public void setHorizontalPadding(int mHorizontalPadding){
        this.mHorizontalPadding=mHorizontalPadding;
    }
}
