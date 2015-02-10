package com.hi.module.global;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.android.ruifeng.hi.R;
import com.customview.callBack.topBarCallBack;
import com.customview.view.CustomTopbarView;
import com.hi.common.http.E_Http_Port;
import com.hi.module.base.superClass.NormalActivity;
import com.hi.utils.AnimationUtil;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.smoothprogressbar.SmoothProgressBar;

/**
 * 关于我们
 * 
 * @author MM_Zerui
 * 
 */
public class AboutUsPage extends NormalActivity {
	@ViewInject(R.id.topBar)
	private CustomTopbarView topBar;
	@ViewInject(R.id.webView)
	private WebView webView;
	@ViewInject(R.id.lineProgress)
	private SmoothProgressBar lineProgress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.app_about_page);
		ViewUtils.inject(this);
		super.onCreate(savedInstanceState);
	}



	
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
		AnimationUtil.finishOut2Right(context);
	}


	@Override
	public void obtainIntentValue() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void outFinish() {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void initResource() {
		// TODO Auto-generated method stub
		webView.getSettings().setJavaScriptEnabled(true);
		webView.clearCache(true);
		webView.loadUrl(E_Http_Port.ABOUT_US.value());
		webView.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageFinished(WebView view, String url) {
				// TODO Auto-generated method stub
				super.onPageFinished(view, url);
				lineProgress.setVisibility(View.GONE);
			}

			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				// TODO Auto-generated method stub
				super.onPageStarted(view, url, favicon);
				
			}

			@Override
			public void onReceivedError(WebView view, int errorCode,
					String description, String failingUrl) {
				// TODO Auto-generated method stub
				super.onReceivedError(view, errorCode, description, failingUrl);
				lineProgress.setVisibility(View.GONE);
			}
		});
	}




	@Override
	public void onClickListener() {
		// TODO Auto-generated method stub
		topBar.setCallBack(new topBarCallBack() {
			@Override
			public void call_backBtnListener() {
				// TODO Auto-generated method stub
				super.call_backBtnListener();
				finish();
			}
		});
	}

}
