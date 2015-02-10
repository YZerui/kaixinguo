package com.hi.receiver;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.ruifeng.hi.R;
import com.format.utils.DataValidate;
import com.hi.common.API;
import com.hi.common.PARAMS;
import com.hi.common.db.E_DB_MsgSource;
import com.hi.common.db.E_DB_MsgType;
import com.hi.common.param.Enum_Page;
import com.hi.dao.model.T_Msg;
import com.hi.dao.supImpl.Dao_Msg;
import com.hi.dao.supImpl.Dao_Params;
import com.hi.dao.supImpl.Dao_SelfIfo;
import com.hi.http.base.Call_httpData;
import com.hi.http.member.model.Recv_UserIfo;
import com.hi.http.member.model.Req_UserIfo;
import com.hi.http.member.req.Http_GetUserIfo;
import com.hi.module.base.application.TabBarActivity;
import com.hi.module.base.callBack.httpResultCallBack;
import com.hi.module.locale.model.RecvUserIfoBean;
import com.hi.module.locale.model.ReqUserIfoBean;
import com.hi.module.msg.ui.MsgDetailActivity;
import com.hi.service.HttpResultService;
import com.hi.service.sync.SyncMsgService;
import com.hi.utils.AnimationUtil;
import com.hi.utils.BroadcastUtil;
import com.hi.utils.FormatUtils;
import com.hi.utils.FunUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * ����������Ϣ��Receivier ����Խ��յ������ͽ����߼�����
 * 
 * @author MM_Zerui
 */
public class AvosPushReceiver extends BroadcastReceiver {
	T_Msg accostBean;
	private String sendId;
	private Context context;
	HttpUtils http;
	private String recvMsg = "";

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		this.context = context;
		http = new HttpUtils();
		try {
			String jsonStr = intent.getExtras().getString(
					"com.avos.avoscloud.Data");
			System.out.println("�յ����ͣ�" + jsonStr);
			if (jsonStr != null) {

				JSONObject json = new JSONObject(jsonStr);
				String msg = json.getString("alert");
				sendId = json.getString("sendId");
				if (DataValidate.checkDataValid(msg)) {
					recvMsg = msg;
				}
				// ����֪ͨ����ʾ
				brocastMethod(recvMsg);
				// �ӷ���˻�ȡ��Ϣ
				AnimationUtil.startService(context, SyncMsgService.class);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void brocastMethod(String msg) {
		// TODO Auto-generated method stub
		/*
		 * @tip_1 ����Ӧ�ý���ʱ������ʾ֪ͨ����ֻ��
		 * 
		 * @tip_2 ���ڶԻ������ʱ���𶯡�ˢ����Ϣ�б�����ʾ֪ͨ��
		 * 
		 * @tip_3 �����ڱ�Ӧ��ʱ���𶯡�����֪ͨ��
		 */
		if (FunUtils.getRunningActivity()
				.equals(TabBarActivity.class.getName())) {
			System.out.println("Receiver TarBarActivity...");
			// ��
			FunUtils.AcquireWakeLock(context);
		} else if (FunUtils.getRunningActivity().equals(
				MsgDetailActivity.class.getName())) {
			System.out.println("Receiver MsgDetailActivity...");
			FunUtils.AcquireWakeLock(context);
			// ����ˢ���б�Ĺ㲥
			Intent intent2 = new Intent();
			intent2.setAction(PARAMS.MSG_CHAT_RECEIVE);
			intent2.putExtra(PARAMS.MSG_CHAT_DATA_EXTRA, sendId);
			context.sendBroadcast(intent2);
		} else {
			FunUtils.setNotification(context, "��", "���������ڨ��", msg,
					R.drawable.notification_icon, R.drawable.app_icon,
					Notification.DEFAULT_ALL, sendId,null,null);
		}
	};
	// }

	/**
	 * ��ÿ�����͵���Ϣ�������ݵĸ���
	 */
	// private void DataUpdate(JSONObject json, final Context context) {
	// // TODO Auto-generated method stub
	// accostBean = new T_Msg();
	// try {
	// String msg = json.getString("alert");
	// sendId = json.getString("sendId");
	// // ע��Ҫ��ӱ����û����ID
	// accostBean.setMid(Dao_SelfIfo.getInstance().getMid());
	// accostBean.setMsg(msg);
	// accostBean.setUid(sendId);
	// accostBean.setTime(FormatUtils.getCurrentDateValue());
	// //��Ϣ��ԴΪ�û�
	// accostBean.setIdentity(E_DB_MsgSource.USER.value());
	// // �����ȶ���Ϣ����Ĭ��Ϊ��ڨ
	// accostBean.setMsgType(E_DB_MsgType.ACCOST.value());
	//
	// try {
	// T_Msg bean = Dao_Msg.getLastAccostRecord(sendId);
	// if(DataValidate.checkDataValid(bean)){
	// accostBean.setHead(bean.getHead());
	// accostBean.setName(bean.getName());
	// }
	// Dao_Msg.insertAccost(accostBean);
	// } catch (Exception e1) {
	// // TODO Auto-generated catch block
	// e1.printStackTrace();
	// }
	//
	// //���������Ϣ
	// Req_UserIfo reqBean=new Req_UserIfo();
	// reqBean.setId(sendId);
	// reqBean.setUid(Dao_SelfIfo.getInstance().getMid());
	// new Http_GetUserIfo(new Call_httpData<Recv_UserIfo>(){
	//
	// @Override
	// public void onStart() {
	//
	// }
	//
	// @Override
	// public void onSuccess(Recv_UserIfo datas) {
	// TODO Auto-generated method stub
	// try {
	// Dao_Msg.updateUserIfo(sendId, datas.getNickName(),
	// datas.getHead());
	// } catch (DbException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }

	// @Override
	// public void onFail() {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// @Override
	// public void onFinally() {
	// TODO Auto-generated method stub

	// }}).onParams(reqBean).onAction();

	// } catch (JSONException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }

}
