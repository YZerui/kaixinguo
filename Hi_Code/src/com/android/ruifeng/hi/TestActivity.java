package com.android.ruifeng.hi;

import com.exception.utils.P;
import com.format.utils.BeanUtils;
import com.hi.dao.model.T_SelfIfo;
import com.hi.http.base.Call_httpData;
import com.hi.http.member.model.Recv_UserIfo;
import com.hi.http.member.model.Req_UserIfo;
import com.hi.http.member.req.Http_GetUserIfo;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class TestActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_layout);
		testHttp();
	}

	private void testHttp() {
		// TODO Auto-generated method stub
		Req_UserIfo reqBean=new Req_UserIfo();
		reqBean.setId("8a211dcc478743ce01478d6d2985001c");
		reqBean.setUid("8a211dcc478743ce01478d6d2985001c");
		new Http_GetUserIfo(new Call_httpData<Recv_UserIfo>() {
			
			@Override
			public void onSuccess(Recv_UserIfo datas) {
				// TODO Auto-generated method stub
				P.v("请求数据："+datas.getNickName());
				T_SelfIfo bean=new T_SelfIfo();
				try {
					BeanUtils.copyProperties(bean,datas);
					P.v("bean:"+bean.getNickName());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			@Override
			public void onStart() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onFinally() {
				// TODO Auto-generated method stub
				P.v("结束");
			}
			
			@Override
			public void onFail() {
				// TODO Auto-generated method stub
				P.v("请求失败");
			}
		}).onParams(reqBean).onAction();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.test, menu);
		return true;
	}

}
