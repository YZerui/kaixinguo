package com.hi.service.sync;

import java.util.List;

import com.hi.common.param.Enum_Page;
import com.hi.dao.model.T_MyFriends;
import com.hi.dao.supImpl.Dao_Friends;
import com.hi.dao.supImpl.Dao_SelfIfo;
import com.hi.http.base.Call_httpListData;
import com.hi.http.friends.model.Recv_Friends;
import com.hi.http.friends.model.Req_Friends;
import com.hi.http.friends.req.Http_Friends;
import com.hi.utils.BroadcastUtil;
import com.thread.RunnableService;
import com.thread.callBack.runCallBack;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * 同步朋友信息
 * @步骤 从服务端获取所有朋友信息，对本地的朋友信息进行更新
 * @步骤 如果本地存在该联系人，则更新用户信息，如果不存在，直接插入数据
 * @author MM_Zerui
 *
 */
public class SyncMyfreinds extends Service{
	private Http_Friends httpReq;
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}
	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		new RunnableService(new runCallBack(){

			@Override
			public void start() {
				// TODO Auto-generated method stub
				method();
			}

		

			@Override
			public void end() {
				// TODO Auto-generated method stub
				
			}},true);
	}
	private void method() {
		Req_Friends reqBean=new Req_Friends();
		reqBean.setUid(Dao_SelfIfo.getInstance().getMid());
		httpReq=new Http_Friends(new Call_httpListData<T_MyFriends>(){

			@Override
			public void onStart() {
				// TODO Auto-generated method stub
			}
			@Override
			public void onInit(List<T_MyFriends> datas) {
				// TODO Auto-generated method stub
				//更新朋友信息
				for(T_MyFriends item:datas){
					Dao_Friends.addRecvRecord(item);
				}
				httpReq.onLoad();
			}

			@Override
			public void onLoad(List<T_MyFriends> datas) {
				// TODO Auto-generated method stub
				//更新朋友信息
				for(T_MyFriends item:datas){
					Dao_Friends.addRecvRecord(item);
				}
				httpReq.onLoad();
			}

			@Override
			public void onFail() {
				// TODO Auto-generated method stub
				stopSelf();
				//通知开启消息同步服务
//				BroadcastUtil.sendBroadCast(getApplicationContext(), Enum_Page.TAPBAR.name(),"MSG");
			}

			@Override
			public void onFinally() {
				// TODO Auto-generated method stub
				
			}});
		httpReq.onParams(reqBean).onInit();
	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	
}
