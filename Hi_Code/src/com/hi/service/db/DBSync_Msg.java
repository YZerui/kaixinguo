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
 * ��Ϣ��ı���ͬ������
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
	 * 	3.��MsgCache���и���ʱ���¼�����򣩴���List<MsgCache>����
	4.List<MsgCache>��ֱ�Ӷ�T_Msg����в������
		4.1 ÿ�β��� ���ID�Ƿ���� 
				4.1.1 ���������ȡ�����β���
				4.1.2 ��������������
						4.1.2.1 ÿ�β��붼����ͷ����������и��²���
	5.T_Msg��Ĳ�����ֱ�Ӷ�T_MsgGroup����и��£�����δ������
		5.1 ÿ�θ��¼��ID�Ƿ����T_MsgGroup���е�ID
				5.1.1 ������ڣ������ δ����+1
				5.1.2 ��֮��ȡ�����β���
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
			//TABBARС������
			BroadcastUtil.sendBroadCast(context, Enum_Page.TAPBAR.name());
			//��Ϣ����ҳ����
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
