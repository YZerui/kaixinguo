package com.hi.service.sync;

import com.format.utils.BeanUtils;
import com.hi.dao.model.T_SelfIfo;
import com.hi.dao.supImpl.Dao_SelfIfo;
import com.hi.http.base.Call_httpData;
import com.hi.http.member.model.Recv_UserIfo;
import com.hi.http.member.model.Req_UserIfo;
import com.hi.http.member.req.Http_GetUserIfo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * 同步个人信息的服务
 * @author MM_Zerui
 *
 */
public class SyncInfoService extends Service{

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}


	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		method();
	}

	private void method() {
		// TODO Auto-generated method stub
		Req_UserIfo reqBean=new Req_UserIfo();
		reqBean.setId(Dao_SelfIfo.getInstance().getMid());
		reqBean.setUid(Dao_SelfIfo.getInstance().getMid());
		new Http_GetUserIfo(new Call_httpData<Recv_UserIfo>(){

			@Override
			public void onStart() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Recv_UserIfo datas) {
				// TODO Auto-generated method stub
				try {
					Dao_SelfIfo.save((T_SelfIfo)BeanUtils.copyProperties(new T_SelfIfo(), datas));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void onFail() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onFinally() {
				// TODO Auto-generated method stub
				stopSelf();
			}}).onParams(reqBean).onAction();
	}


	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
}
