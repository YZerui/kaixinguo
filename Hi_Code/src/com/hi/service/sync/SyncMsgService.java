package com.hi.service.sync;

import java.util.List;

import com.exception.utils.P;
import com.hi.common.param.Enum_Timestamp;
import com.hi.dao.model.T_Msg;
import com.hi.dao.model.T_MsgCache;
import com.hi.dao.supImpl.Dao_Msg;
import com.hi.dao.supImpl.Dao_MsgCache;
import com.hi.http.base.Call_httpListData;
import com.hi.http.sms.model.Req_Message;
import com.hi.http.sms.req.Http_Message;
import com.hi.service.db.DBSync_Msg;
import com.hi.utils.AnimationUtil;
import com.hi.utils.FormatUtils;
import com.lidroid.xutils.exception.DbException;
import com.thread.RunnableService;
import com.thread.callBack.runCallBack;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * 同步搭讪的服务
 * @备注 如果本地存在搭讪记录则不开启数据同步工作
 * @author MM_Zerui
 * 
 */
public class SyncMsgService extends Service {
	private Http_Message httpReq;

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
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		//开启消息表的处理服务
		AnimationUtil.startService(getApplicationContext(), DBSync_Msg.class);
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		
		new RunnableService(new runCallBack() {

			@Override
			public void start() {
				// TODO Auto-generated method stub
				if(Dao_Msg.checkExist()){
					//获取本地最新的消息ID
					syncLatest();
					P.v(getClass().getName()+"获取最新消息");
					return;
				}
				//同步10天内的消息
				syncAll();
				P.v(getClass().getName()+"同步全部消息");
			}

			@Override
			public void end() {
				// TODO Auto-generated method stub

			}
		}, true);
	}
	/**
	 * 同步最新信息
	 */
	private void syncLatest(){
		//获取本地最新的消息ID
		try {
			T_Msg msgBean=Dao_Msg.getLastAccostRecord();
			P.v("最大msgId："+msgBean.getMsgId());
			Req_Message reqBean=new Req_Message();
			reqBean.setID(String.valueOf(msgBean.getMsgId()));
//			//30天内
//			reqBean.setTime(String.valueOf(FormatUtils.getCurrentDateValue_long()
//				- Enum_Timestamp.DAY.value() * 30));
			httpReq = new Http_Message(new Call_httpListData<T_MsgCache>() {
				
				@Override
				public void onStart() {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onLoad(List<T_MsgCache> datas) {
					// TODO Auto-generated method stub
					Dao_MsgCache.saveList(datas);
					httpReq.onLoad();
				}
				
				@Override
				public void onInit(List<T_MsgCache> datas) {
					// TODO Auto-generated method stub
					Dao_MsgCache.clear();
					Dao_MsgCache.saveList(datas);
					httpReq.onLoad();
				}
				
				@Override
				public void onFinally() {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onFail() {
					// TODO Auto-generated method stub
					stopSelf();
				}
			});
			httpReq.onParams(reqBean).onInit();
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			P.v(e.getMessage());
		}
	}
	/**
	 * 完全同步
	 */
	private void syncAll() {
		Req_Message reqBean = new Req_Message();
		// 表明10天内
		reqBean.setTime(String.valueOf(FormatUtils.getCurrentDateValue_long()
				- Enum_Timestamp.DAY.value() * 10));
		httpReq = new Http_Message(new Call_httpListData<T_MsgCache>() {
			
			@Override
			public void onStart() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onLoad(List<T_MsgCache> datas) {
				// TODO Auto-generated method stub
				Dao_MsgCache.saveList(datas);
				httpReq.onLoad();
			}
			
			@Override
			public void onInit(List<T_MsgCache> datas) {
				// TODO Auto-generated method stub
				Dao_MsgCache.clear();
				Dao_MsgCache.saveList(datas);
				httpReq.onLoad();
			}
			
			@Override
			public void onFinally() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onFail() {
				// TODO Auto-generated method stub
				stopSelf();
			}
		});
		httpReq.onParams(reqBean).onInit();
	}

}
