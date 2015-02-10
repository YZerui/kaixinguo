package com.hi.view.customClipLayout;

import com.android.ruifeng.hi.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.RelativeLayout;


/**
 * User: MM_Zerui
 * Time: 2015/1/22 19:33
 */
public class ClipImageLayout extends RelativeLayout {
    private ClipImageBorderView clipImageBorderView;
    private ClipImageView clipImageView;
    private int mHorizontalPadding=60;

    public ClipImageLayout(Context context) {
        super(context);
    }

    public ClipImageLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ClipImageLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        clipImageBorderView=new ClipImageBorderView(context);
        clipImageView=new ClipImageView(context);

        LayoutParams lp=new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
        setBackgroundColor(getResources().getColor(R.color.black));
        this.addView(clipImageView,lp);
        this.addView(clipImageBorderView,lp);

        // ¼ÆËãpaddingµÄpx
//              mHorizontalPadding = (int) TypedValue.applyDimension(
//                      TypedValue.COMPLEX_UNIT_DIP, mHorizontalPadding, getResources()
//                              .getDisplayMetrics());
//        clipImageView.setImageDrawable(getResources().getDrawable(R.drawable.app_bg));
        clipImageBorderView.setHorizontalPadding(mHorizontalPadding);
        clipImageView.setHorizontalPadding(mHorizontalPadding);

    }
    public void setHorizontalPadding(int mHorizontalPadding){
        this.mHorizontalPadding=mHorizontalPadding;
    }
    public Bitmap clip(){
        return clipImageView.clip();
    }
    public void setImageDrawable(int drawable){
        clipImageView.setImageDrawable(getResources().getDrawable(drawable));
    }
    public void setImageBitmap(Bitmap bitmap){
    	clipImageView.setImageBitmap(bitmap);
    }
    
    public ClipImageView getImageView(){
    	return clipImageView;
    }
}
