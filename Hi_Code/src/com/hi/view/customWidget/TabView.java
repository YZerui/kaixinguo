package com.hi.view.customWidget;

import com.android.ruifeng.hi.R;
import com.hi.utils.DeviceUtils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.StateListDrawable;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class TabView extends LinearLayout {
	ImageView imageView;

	public TabView(Context c, int drawable, int drawableselec) {
		super(c);
		imageView = new ImageView(c);
		StateListDrawable listDrawable = new StateListDrawable();
		listDrawable.addState(SELECTED_STATE_SET, this.getResources()
				.getDrawable(drawableselec));
		listDrawable.addState(ENABLED_STATE_SET, this.getResources()
				.getDrawable(drawable));
		imageView.setImageDrawable(listDrawable);
//        imageView.setBackgroundDrawable(c.getResources().getDrawable(R.drawable.img_corner_round));
		// imageView.setBackgroundColor(Color.TRANSPARENT);
		// imageView.setPadding(15,15,15,15);
		// DeviceUtils.dip2px(c, 30);
//		LayoutParams params = new LayoutParams(new LayoutParams(
//				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
//		// imageView.set
		imageView.setLayoutParams(new LayoutParams(
                LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
        imageView.setPadding(7,12,7,12);
		setGravity(Gravity.CENTER);
		// set
		// setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
		// LayoutParams.WRAP_CONTENT));
		// setLayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		addView(imageView);
	}
}
