package com.hi.view.customWidget;

import com.android.ruifeng.hi.R;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * 性别显示的ImageView
 * @author MM_Zerui
 *
 */
public class ImageView_Gender extends ImageView{
	
	private boolean isMan=false;
	public ImageView_Gender(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		drawChange();
	}
	public void setGender(boolean isMan){
		this.isMan=isMan;
		drawChange();
	}
	private void drawChange(){
		if(isMan){
			setImageDrawable(getResources().getDrawable(R.drawable.ic_sex_boy));
		}else {
			setImageDrawable(getResources().getDrawable(R.drawable.ic_sex_girl));
		}
	}
}
