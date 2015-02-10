package com.customview.view;

import java.util.ArrayList;
import java.util.List;

import com.customview.callBack.pageCallBack;
import com.utils.Util;

import customview.library.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CustomPageView extends LinearLayout {
	private RelativeLayout bottomLayout, defaultLayout;
	private ImageView errorImg;
	private ImageView refreshBtn;
	private ProgressBar progressBar;
	private RelativeLayout overallLayout;
	private TextView errorText;
	private View pageLayout;
	private CustomTextView customProgress;
	private int[] res = { R.id.centerImg, R.id.centerImg_2, R.id.centerImg_3 };
	private List<ImageView> circleImgList=new ArrayList<ImageView>();
	private TextView refreshWord;
	public CustomPageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		callBack = new pageCallBack() {

			@Override
			public void onRefreshClick() {
				// TODO Auto-generated method stub

			}
		};
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.custom_view_default_page, null);
		overallLayout = (RelativeLayout) view.findViewById(R.id.overallLayout);
		bottomLayout = (RelativeLayout) view
				.findViewById(R.id.defalutBottomLayout);
		errorImg = (ImageView) view.findViewById(R.id.defalutErrorPage);
		refreshBtn = (ImageView) view.findViewById(R.id.defaultRefresh);
		progressBar = (ProgressBar) view.findViewById(R.id.defaultProgressBar);
		pageLayout = (View) view.findViewById(R.id.pageLayout);
		defaultLayout = (RelativeLayout) view.findViewById(R.id.defaultLayout);
		refreshWord=(TextView)view.findViewById(R.id.refreshWord);
		customProgress = (CustomTextView) view
				.findViewById(R.id.customProgress);
		errorText = (TextView) view.findViewById(R.id.defalutErrorText);
		TypedArray typedArray = context.obtainStyledAttributes(attrs,
				R.styleable.CustomPageView);
		int resourceId = -1;
		int n = typedArray.getIndexCount();
		for (int i = 0; i < n; i++) {
			int attr = typedArray.getIndex(i);
            if (attr == R.styleable.CustomPageView_page_bottom_refresh_img) {
                resourceId = typedArray.getResourceId(
                        R.styleable.CustomPageView_page_bottom_refresh_img, 0);
                refreshBtn.setImageResource(resourceId);

            } else if (attr == R.styleable.CustomPageView_page_bottom_visible) {
                resourceId = typedArray.getInt(
                        R.styleable.CustomPageView_page_bottom_visible, 0);
                if (resourceId == 1) {
                    bottomLayout.setVisibility(View.VISIBLE);
                } else {
                    bottomLayout.setVisibility(View.INVISIBLE);
                }

            } else if (attr == R.styleable.CustomPageView_page_error_img) {
                resourceId = typedArray.getResourceId(
                        R.styleable.CustomPageView_page_error_img, 0);
                errorImg.setImageResource(resourceId);

            } else if (attr == R.styleable.CustomPageView_page_visible) {
                resourceId = typedArray.getInt(
                        R.styleable.CustomPageView_page_visible, 0);
                if (resourceId == 1) {
                    overallLayout.setVisibility(View.VISIBLE);
                } else {
                    overallLayout.setVisibility(View.INVISIBLE);
                }

            } else if (attr == R.styleable.CustomPageView_page_error_img_visible) {
                resourceId = typedArray.getInt(
                        R.styleable.CustomPageView_page_error_img_visible, 0);
                if (resourceId == 1) {
                    errorImg.setVisibility(View.VISIBLE);
                } else {
                    errorImg.setVisibility(View.INVISIBLE);
                }

            } else if (attr == R.styleable.CustomPageView_page_error_text) {
                errorText.setVisibility(View.VISIBLE);
                resourceId = typedArray.getResourceId(
                        R.styleable.CustomPageView_page_error_text, 0);
                errorText.setText(resourceId > 0 ? typedArray.getResources()
                        .getText(resourceId) : typedArray
                        .getString(R.styleable.CustomPageView_page_error_text));

            } else if (attr == R.styleable.CustomPageView_page_error_text_size) {
                float size = typedArray.getDimension(
                        R.styleable.CustomPageView_page_error_text_size,
                        Util.dip2px(context, 20));
                errorText.setTextSize(size);

            }

		}
		refreshWord.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				callBack.onRefreshClick();
				progressRun();
			}
		});
		refreshBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				callBack.onRefreshClick();
				progressRun();
			}
		});
		addView(view);
		animatorRefreshBtn(refreshBtn);
		for (int i = 0; i < res.length; i++) {
			ImageView imageView = (ImageView) findViewById(res[i]);
			circleImgList.add(imageView);
//			imageView.setVisibility(View.VISIBLE);
			anim(imageView, i * 200);
		}
	}

	/**
	 * 设定刷新按钮的动画
	 * 
	 * @param refreshBtn2
	 */
	private void animatorRefreshBtn(ImageView refreshBtn) {
		// TODO Auto-generated method stub
		// float init_scale = 0.1f, to_scale = 1f;
		float init_alpha = 0f, to_alpha = 1f;
		float init_trans = -80f, to_trans = 0f;
		PropertyValuesHolder p_alpha = PropertyValuesHolder.ofFloat("alpha",
				init_alpha, to_alpha);
		// PropertyValuesHolder p_scale=PropertyValuesHolder.ofFloat("s",
		// values)
		PropertyValuesHolder p_trans = PropertyValuesHolder.ofFloat(
				"translationY", init_trans, to_trans);
		ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(
				refreshBtn, p_alpha, p_trans);
		animator.setDuration(1200);
		animator.setRepeatMode(ValueAnimator.RESTART);
		animator.setRepeatCount(ValueAnimator.INFINITE);
		animator.setStartDelay(300);
		animator.start();
	}

	/**
	 * 线圈的变化
	 */
	public void anim(final ImageView imageView, int delayLong) {
		float init_scale = 0.1f, to_scale = 1f;
		float init_alpha = 1f, to_alpha = 0.1f;
		PropertyValuesHolder p_alpha = PropertyValuesHolder.ofFloat("alpha",
				init_alpha, to_alpha);
		PropertyValuesHolder p_scaleX = PropertyValuesHolder.ofFloat("scaleX",
				init_scale, to_scale);
		PropertyValuesHolder p_scaleY = PropertyValuesHolder.ofFloat("scaleY",
				init_scale, to_scale);
		ObjectAnimator objAnimator = ObjectAnimator.ofPropertyValuesHolder(
				imageView, p_alpha, p_scaleX, p_scaleY);
		objAnimator.setStartDelay(delayLong);
		objAnimator.setDuration(800);
		objAnimator.setRepeatMode(ValueAnimator.RESTART);
		objAnimator.setRepeatCount(ValueAnimator.INFINITE);
		objAnimator.start();
		objAnimator.addListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationStart(Animator animation) {
				super.onAnimationStart(animation);
				imageView.setVisibility(View.VISIBLE);
			}

			@Override
			public void onAnimationEnd(Animator animation) {
				super.onAnimationEnd(animation);
			}
		});
	}

	/**
	 * 刷新状态
	 */
	public CustomPageView progressRun() {
		refreshWord.setVisibility(View.INVISIBLE);
//		refreshBtn.setVisibility(View.INVISIBLE);
		progressBar.setVisibility(View.VISIBLE);
		return this;
	}

	/**
	 * 正常状态
	 */
	public CustomPageView progressEnd() {
		refreshWord.setVisibility(View.VISIBLE);
//		refreshBtn.setVisibility(View.VISIBLE);
		progressBar.setVisibility(View.INVISIBLE);
		return this;
	}

	public CustomPageView hide() {
		overallLayout.setVisibility(View.GONE);
		return this;
	}

	public CustomPageView show() {
		overallLayout.setVisibility(View.VISIBLE);
		return this;
	}

	public CustomPageView setBottomLayoutVisible(boolean visible) {
		if (visible) {
			bottomLayout.setVisibility(View.VISIBLE);
		} else {
			bottomLayout.setVisibility(View.INVISIBLE);
		}
		return this;
	}

	private pageCallBack callBack;

	public void setCallBack(pageCallBack callBack) {
		this.callBack = callBack;
	}

	public CustomPageView setDefaultNoteImg(int img) {
		errorImg.setVisibility(View.VISIBLE);
		errorImg.setImageResource(img);
		return this;
	}

	public CustomPageView setProgress(String text) {
		pageLayout.setVisibility(View.INVISIBLE);
		defaultLayout.setVisibility(View.INVISIBLE);
		customProgress.setVisibility(View.VISIBLE);
		customProgress.setText(text);
		return this;
	}

	public CustomPageView onProgressOnly() {
		pageLayout.setVisibility(View.INVISIBLE);
		defaultLayout.setVisibility(View.INVISIBLE);
		customProgress.setVisibility(View.VISIBLE);
		customProgress.setProgressOnly();
		return this;
	}

	public CustomPageView setShadowPage() {
		customProgress.setVisibility(View.INVISIBLE);
		defaultLayout.setVisibility(View.INVISIBLE);
		pageLayout.setVisibility(View.VISIBLE);
		return this;
	}

	public CustomPageView setDefaultPage() {
		defaultLayout.setVisibility(View.VISIBLE);
		pageLayout.setVisibility(View.INVISIBLE);
		customProgress.setVisibility(View.INVISIBLE);
		return this;
	}

	public CustomPageView setErrorText(String text) {
		errorText.setVisibility(View.VISIBLE);
		errorText.setText(text);
		return this;
	}

	public CustomPageView setTextOnly(String text) {
		errorImg.setVisibility(View.GONE);
		setDefaultPage();
		setErrorText(text);
		return this;
	}

	public CustomPageView setErrorTextVisible(boolean visible) {
		if (visible) {
			errorText.setVisibility(View.VISIBLE);
		} else {
			errorText.setVisibility(View.INVISIBLE);
		}
		return this;

	}

}
