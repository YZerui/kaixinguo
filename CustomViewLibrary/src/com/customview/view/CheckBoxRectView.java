package com.customview.view;

import com.utils.Util;

import customview.library.R;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * µãÔÞ¿Ø¼þ User: MM_Zerui Time: 2015/1/28 23:14
 */
public class CheckBoxRectView extends RelativeLayout {
	private CheckBox checkBox;
	private ImageView imageView;
	private TextView textView;

	public CheckBoxRectView(Context context) {
		super(context);
	}

	public CheckBoxRectView(Context context, AttributeSet attrs) {
		super(context, attrs);
		View view = View
				.inflate(context, R.layout.checkbox_custom_layout, null);
		checkBox = (CheckBox) view.findViewById(R.id.checkBox);
		imageView = (ImageView) view.findViewById(R.id.imageView);
		textView = (TextView) view.findViewById(R.id.textView);
//		textView.setText("0");
		int width = Util.dip2px(context, 60);
		int height = Util.dip2px(context, 25);
		LayoutParams lp = new LayoutParams(width, height);
		addView(view, lp);
		
		checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
//					textView.setText(zanNum+1+"");
					textView.setTextColor(0xffff8501);
					imageView.setImageDrawable(getResources().getDrawable(
							R.drawable.icon_zan_selected));
				} else {
//					textView.setText(zanNum+"");
					textView.setTextColor(0xff999999);
					imageView.setImageDrawable(getResources().getDrawable(
							R.drawable.icon_zan_normal));
				}
			}
		});
		checkBox.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(callBack==null){
					return;
				}
				callBack.onCheck(checkBox.isChecked());
				if(checkBox.isChecked()){
					textView.setText(zanNum+1+"");
					textView.setTextColor(0xffff8501);
					imageView.setImageDrawable(getResources().getDrawable(
							R.drawable.icon_zan_selected));
				}else {
					textView.setText(zanNum+"");
					textView.setTextColor(0xff999999);
					imageView.setImageDrawable(getResources().getDrawable(
							R.drawable.icon_zan_normal));
				}
			}
		});
	}
	private int zanNum;
	public void onNum(int num){
		this.zanNum=num;
		textView.setText(""+num);
	}
	private CallBack callBack;

	public void onCheckChangeListener(CallBack callBack) {
		this.callBack = callBack;
	}
	public void onChecked(boolean isCheck){
		checkBox.setChecked(isCheck);
	}
	public static abstract class CallBack {
		public abstract void onCheck(boolean isCheck);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

	}
}
