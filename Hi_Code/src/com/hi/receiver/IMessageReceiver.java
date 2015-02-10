package com.hi.receiver;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;

import com.android.ruifeng.hi.R;
import com.avos.avoscloud.AVMessage;
import com.avos.avoscloud.AVMessageReceiver;
import com.avos.avoscloud.Session;
import com.exception.utils.P;
import com.hi.common.PARAMS;
import com.hi.common.db.E_DB_ImgUrlMode;
import com.hi.common.db.E_DB_MsgSource;
import com.hi.common.db.E_DB_MsgType;
import com.hi.common.http.E_Http_StoreType;
import com.hi.common.param.Enum_Page;
import com.hi.dao.model.T_IMsg;
import com.hi.dao.supImpl.Dao_Friends;
import com.hi.dao.supImpl.Dao_IMsg;
import com.hi.dao.supImpl.Dao_SelfIfo;
import com.hi.module.base.application.TabBarActivity;
import com.hi.module.msg.ui.MsgDetailActivity;
import com.hi.receiver.model.IMMessageBean;
import com.hi.receiver.model.IMMessageBean.IMMessage;
import com.hi.service.image.Enum_ImgScaleType;
import com.hi.utils.AnimationUtil;
import com.hi.utils.BroadcastUtil;
import com.hi.utils.DeviceUtils;
import com.hi.utils.FunUtils;

/**
 * 即时通讯的消息回调
 * 
 * @author MM_Zerui
 * 
 */
public class IMessageReceiver extends AVMessageReceiver {
	/**
	 * 消息接口
	 * 
	 * @author MM_Zerui
	 * 
	 */
	public interface MessageListener {
		// 成功发送消息
		public void onIMSendMessage(String sendTime);

		// 成功收到消息
		public void onIMRecvMessage(IMMessage msgBean, String recvTime);

		// 消息发送失败
		public void onIMFail();
		
		//上线通知
		public void onOnLine(List<String> peerIds);
		
		//上线通知
		public void onOffLine(List<String> peerIds);
	}

	@Override
	public void onSessionOpen(Context context, Session session) {
		P.v("成功登录即时通讯服务");
	}

	@Override
	public void onSessionPaused(Context context, Session session) {
		P.v("这里掉线了");

	}

	@Override
	public void onSessionResumed(Context context, Session session) {
		P.v("重新连接上了");
	}

	/*
	 * 收到消息 (non-Javadoc)
	 * 
	 * @see
	 * com.avos.avoscloud.SessionListener#onMessage(android.content.Context,
	 * com.avos.avoscloud.Session, com.avos.avoscloud.AVMessage)
	 */
	@Override
	public void onMessage(Context context, Session session, AVMessage msg) {
		P.v("onMessageReceived " + msg.getMessage());
		P.v("消息接收时间：" + msg.getTimestamp());
		
		try {
			IMMessage iBean = IMMessageBean.fromAVMessage(msg);
			if(!iBean.getU_animID().equals(Dao_SelfIfo.getInstance().getMid())){
				return;
			}
			MessageListener listener = sessionMessageDispatchers.get(iBean
					.getU_sendID());
			
			T_IMsg msgBean = new T_IMsg();
			msgBean.setObjectId(iBean.getObjectId());
			msgBean.setHead(iBean.getU_head());
			msgBean.setIdentity(E_DB_MsgSource.USER.value());// 身份为对方
			msgBean.setMid(Dao_SelfIfo.getInstance().getMid());
			msgBean.setMsg(iBean.getM_content());
			msgBean.setMsgFormat(iBean.getM_formate());
			msgBean.setMsgType(E_DB_MsgType.ACCOST.value());
//			msgBean.setTime(iBean.getM_timestamp());
			msgBean.setName(iBean.getU_name());
			msgBean.setUid(iBean.getU_sendID());
			msgBean.setImageUrlMode(E_DB_ImgUrlMode.URL.value());
			msgBean.setWifiMac(DeviceUtils.getWifiMac());
			if (listener != null) {
				// 如果listener不为空，说明当前界面为当前用户，直接传递消息到聊天界面
				listener.onIMRecvMessage(iBean,
						String.valueOf(msg.getTimestamp()));
				// 将记录保存在本地
				Dao_IMsg.addMessage(msgBean, false,true);
			
			} else {
				Dao_IMsg.addMessage(msgBean, true,true);
				// 此时，弹出通知栏
				brocastMethod(msgBean.getMsg(),msgBean.getUid(),
						msgBean.getName(),msgBean.getHead(),context);
			}
			//更新搭讪状况
			Dao_Friends.updateAccostNum(msgBean.getUid(), E_DB_MsgSource.USER.value());

		} catch (Exception e) {
			// TODO: handle exception
			P.v("接收消息错误:" + e.getMessage());
		}

	}
	private void brocastMethod(String msg,String sendId,String name,String head,Context context) {
		// TODO Auto-generated method stub
		/*
		 * @tip_1 当在应用界面时，不显示通知栏，只震动
		 * 
		 * @tip_2 当在对话框界面时，震动、刷新消息列表，不显示通知栏
		 * 
		 * @tip_3 当不在本应用时，震动、弹出通知栏
		 */
		if (FunUtils.getRunningActivity()
				.equals(TabBarActivity.class.getName())) {
			System.out.println("Receiver TarBarActivity...");
			// 震动
			FunUtils.AcquireWakeLock(context);
			BroadcastUtil.sendBroadCast(context, Enum_Page.TAPBAR.name(),sendId);
			BroadcastUtil.sendBroadCast(context, Enum_Page.MSG.name(),sendId);
		} else if (FunUtils.getRunningActivity().equals(
				MsgDetailActivity.class.getName())) {
			System.out.println("Receiver MsgDetailActivity...");
			FunUtils.AcquireWakeLock(context);
			// 发送刷新列表的广播
//			Intent intent2 = new Intent();
//			intent2.setAction(PARAMS.MSG_CHAT_RECEIVE);
//			intent2.putExtra(PARAMS.MSG_CHAT_DATA_EXTRA, sendId);
//			context.sendBroadcast(intent2);
			
		} else {
			FunUtils.setNotification(context, "嗨", "有人向你搭讪了", msg,
					sendId.getBytes().length, R.drawable.app_icon,
					Notification.DEFAULT_ALL, sendId,name,head);
		}
	};
	/*
	 * 发送消息 (non-Javadoc)
	 * 
	 * @see
	 * com.avos.avoscloud.SessionListener#onMessageSent(android.content.Context,
	 * com.avos.avoscloud.Session, com.avos.avoscloud.AVMessage)
	 */
	@Override
	public void onMessageSent(Context context, Session session, AVMessage msg) {

		P.v("message fromPeerId=" + msg.getFromPeerId());
		P.v("message to peerIds=" + msg.getToPeerIds());
		P.v("message sent timestamp=" + msg.getTimestamp());// 发送的时间
		try {
			IMMessage iBean = IMMessageBean.fromAVMessage(msg);
			MessageListener listener = sessionMessageDispatchers.get(msg
					.getToPeerIds().get(0));
			
			if (listener != null) {
				listener.onIMSendMessage(String.valueOf(msg.getTimestamp()));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/*
	 * 发送消息失败 (non-Javadoc)
	 * 
	 * @see
	 * com.avos.avoscloud.SessionListener#onMessageFailure(android.content.Context
	 * , com.avos.avoscloud.Session, com.avos.avoscloud.AVMessage)
	 */
	@Override
	public void onMessageFailure(Context context, Session session, AVMessage msg) {
		P.v("message failed :" + msg.getMessage());
		try {
			IMMessage iBean = IMMessageBean.fromAVMessage(msg);
			MessageListener listener = sessionMessageDispatchers.get(msg
					.getToPeerIds().get(0));
			if (listener != null) {
				listener.onIMFail();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.avos.avoscloud.SessionListener#onStatusOnline(android.content.Context
	 * , com.avos.avoscloud.Session, java.util.List)
	 */
	@Override
	public void onStatusOnline(Context context, Session session,
			List<String> peerIds) {
		Collection<MessageListener> collection=sessionMessageDispatchers.values();
		Iterator<MessageListener> iterator=collection.iterator();
		while (iterator.hasNext()) {
			MessageListener listener=iterator.next();
			listener.onOnLine(peerIds);
		}
	}

	@Override
	public void onStatusOffline(Context context, Session session,
			List<String> peerIds) {
		P.v("status offline :" + peerIds.toString());
		Collection<MessageListener> collection=sessionMessageDispatchers.values();
		Iterator<MessageListener> iterator=collection.iterator();
		while (iterator.hasNext()) {
			MessageListener listener=iterator.next();
			listener.onOffLine(peerIds);
		}
	}

	@Override
	public void onError(Context context, Session session, Throwable e) {
		P.v("session error:" + (Exception) e);
	}

	public static void registerSessionListener(String peerId,
			MessageListener listener) {
		sessionMessageDispatchers.put(peerId, listener);
	}

	public static void unregisterSessionListener(String peerId) {
		sessionMessageDispatchers.remove(peerId);
	}

	static HashMap<String, MessageListener> sessionMessageDispatchers = new HashMap<String, MessageListener>();

	@Override
	public void onPeersWatched(Context context, Session session,
			List<String> peerIds) {
		P.v(peerIds.size() + "  watched");
	}

	@Override
	public void onPeersUnwatched(Context context, Session session,
			List<String> peerIds) {
		P.v(peerIds + " unwatched");
	}

	@Override
	public void onMessageDelivered(Context context, Session session,
			AVMessage msg) {
		P.v(msg.getMessage() + "delivered at " + msg.getReceiptTimestamp());
	}
}
