package com.hi.service.db;

import java.util.List;

import com.format.utils.DataValidate;
import com.hi.common.param.Enum_Page;
import com.hi.dao.model.T_MsgCache;
import com.hi.dao.supImpl.Dao_Msg;
import com.hi.dao.supImpl.Dao_MsgCache;
import com.hi.dao.supImpl.Dao_MsgSeq;
import com.hi.utils.BroadcastUtil;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

/**
 * 消息表的本地同步服务
 * @author MM_Zerui
 *
 */
public class DBSync_Msg extends Service{
	private Context context;
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		context=getApplicationContext();
		super.onCreate();
		
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		method();
	}
	
	/**
	 * 	3.从MsgCache表中根据时间记录（增序）存入List<MsgCache>表中
	4.List<MsgCache>将直接对T_Msg表进行插入操作
		4.1 每次插入 检测ID是否存在 
				4.1.1 如果存在则取消本次操作
				4.1.2 如果不存在则插入
						4.1.2.1 每次插入都将对头像和姓名进行更新操作
	5.T_Msg表的操作将直接对T_MsgGroup表进行更新（包括未读数）
		5.1 每次更新检测ID是否大于T_MsgGroup表中的ID
				5.1.1 如果大于，则插入 未读数+1
				5.1.2 反之，取消本次操作
	 */
	private void method() {
		// TODO Auto-generated method stub
		List<T_MsgCache> listDatas=Dao_MsgCache.getAll();
		if(!DataValidate.checkDataValid(listDatas)){
			return;
		}
		for(T_MsgCache item:listDatas){
			Dao_Msg.insertMsg(item);
		}
		if(DataValidate.checkDataValid(listDatas)){
			BroadcastUtil.sendBroadCast(context, Enum_Page.MSG.name());
			//TABBAR小红点更新
			BroadcastUtil.sendBroadCast(context, Enum_Page.TAPBAR.name());
			//消息详情页更新
			BroadcastUtil.sendBroadCast(context, Enum_Page.MSG_DETAIL.name(),listDatas.get(listDatas.size()-1).getUid());
		}
		stopSelf();
		
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	
	

}
