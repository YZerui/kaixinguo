package com.hi.module.global;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.os.Bundle;
import android.text.Html;
import android.webkit.WebView;
import android.widget.TextView;

import com.android.ruifeng.hi.R;
import com.customview.callBack.topBarCallBack;
import com.customview.view.CustomTopbarView;
import com.exception.utils.P;
import com.hi.module.base.superClass.NormalActivity;
import com.hi.utils.AnimationUtil;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 用户须知页面
 * @author MM_Zerui
 *
 */
public class ProtocolPage extends NormalActivity{
	@ViewInject(R.id.topBar)
	private CustomTopbarView topBar;
	@ViewInject(R.id.webView)
	private WebView webView;
	@ViewInject(R.id.text)
	private TextView text;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.app_protocol_page);
		ViewUtils.inject(this);
		super.onCreate(savedInstanceState);
	}



	@Override
	public void obtainIntentValue() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void initResource() {
		// TODO Auto-generated method stub
		webView.loadUrl("file:///android_asset/protocol.html");
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

	@Override
	public void outFinish() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
		AnimationUtil.finishOut2Right(context);
	}


	private String getHtml(String fileName) {
		InputStreamReader inputReader;
		try {
			inputReader = new InputStreamReader(getResources().getAssets()
					.open(fileName));
			BufferedReader bufReader = new BufferedReader(inputReader);
			String line = "";
			String Result = "";
			while ((line = bufReader.readLine()) != null)
				Result += line;
			return Result;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

}
