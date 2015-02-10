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
     * ˮƽ������view�ı߾�
     */
    private int mHorizontalPadding=30;

    /**
     * ��ֱ������view�ı߾�
     */
    private int mVerticalPadding;

    /**
     * ���Ƶľ��ο��
     */
    private int mWidth;

    /**
     * �߿����ɫ��Ĭ��Ϊ��ɫ
     */
    private int mBorderColor= Color.parseColor("#fffa723a");

    /**
     * �߿�Ŀ�� ��λdp
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
        //����padding��px
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
        //�����������Ŀ��
        mWidth=getWidth()-2*mHorizontalPadding;
        //���������Ļ���˺͵׶α߽�ı߾�
        mVerticalPadding=(getHeight()-mWidth)/2;
        mPaint.setColor(Color.parseColor(outerColor));
        mPaint.setStyle(Paint.Style.FILL);

        //�������
        canvas.drawRect(0,0,mHorizontalPadding,getHeight(),mPaint);
        //�����ұ�
        canvas.drawRect(getWidth()-mHorizontalPadding,0,getWidth(),getHeight(),mPaint);
        //�����ϱ�
        canvas.drawRect(mHorizontalPadding,0,getWidth()-mHorizontalPadding,
                mVerticalPadding,mPaint);
        //�����±�
        canvas.drawRect(mHorizontalPadding,getHeight()-mVerticalPadding,
                getWidth()-mHorizontalPadding,getHeight(),mPaint);
        //������߿�
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
