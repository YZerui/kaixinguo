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
 * ͬ����ڨ�ķ���
 * @��ע ������ش��ڴ�ڨ��¼�򲻿�������ͬ������
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
		//������Ϣ��Ĵ������
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
					//��ȡ�������µ���ϢID
					syncLatest();
					P.v(getClass().getName()+"��ȡ������Ϣ");
					return;
				}
				//ͬ��10���ڵ���Ϣ
				syncAll();
				P.v(getClass().getName()+"ͬ��ȫ����Ϣ");
			}

			@Override
			public void end() {
				// TODO Auto-generated method stub

			}
		}, true);
	}
	/**
	 * ͬ��������Ϣ
	 */
	private void syncLatest(){
		//��ȡ�������µ���ϢID
		try {
			T_Msg msgBean=Dao_Msg.getLastAccostRecord();
			P.v("���msgId��"+msgBean.getMsgId());
			Req_Message reqBean=new Req_Message();
			reqBean.setID(String.valueOf(msgBean.getMsgId()));
//			//30����
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
	 * ��ȫͬ��
	 */
	private void syncAll() {
		Req_Message reqBean = new Req_Message();
		// ����10����
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
