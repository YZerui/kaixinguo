package com.hi.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.format.utils.DataValidate;
import com.hi.common.API;
import com.hi.common.PARAMS;
import com.hi.dao.model.T_Contacts;
import com.hi.dao.supImpl.Dao_Contacts;
import com.hi.module.friend.model.ContactSortBean;
import com.hi.module.friend.model.RecvPhoneAuthorBean;
import com.hi.module.friend.model.ReqPhoneCheckBean;
import com.hi.utils.ConstactUtil;
import com.hi.utils.DBUtils;
import com.hi.utils.FormatUtils;
import com.lidroid.xutils.exception.DbException;


import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

/**
 * @author MM_Zerui 通讯录上传更新服务
 * @tip_1 首先判断本地数据库是否存在联系人记录
 * @tip_2 如果不存在，从设备中获取通讯录信息并存入本地数据库中
 * @tip_3 如果存在，直接本地数据库中提取未注册的号码上传到服务器
 * @tip_4 对服务端返回的数据进行分析，服务端返回的数据均为注册过的，通过这些数据对本地数据库进行信息更改操作
 * @tip_5 该服务结束后通知朋友列表页面，进行相应控件的更新操作
 */
public class ContactsUploadService extends Service {

	private Dao_Contacts daoImpl;
	private int index = 0;

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		System.out.println("service 1...");

		super.onCreate();
	}

	private void initResource() {
		// TODO Auto-generated method stub
		daoImpl = new Dao_Contacts();
	}

	private void initContactsData() {
		// TODO Auto-generated method stub
		// 如果是第一次运行,则进行数据加载
		System.out.println("service 2...");
		if (!daoImpl.checkContactsExist()) {
			// 获取设备中的通讯录号码
			ArrayList<T_Contacts> list = ConstactUtil.getSortContactData();
			// daoImpl.insetContacts(list);
			try {
				daoImpl.insertContactsRecord(list);
			} catch (DbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("service 3...");
		}
	}

	/**
	 * 提交手机号码到服务端的方法
	 * 
	 * @tip_1 采取分段提交的方式
	 * @tip_2 每次提交后等待服务端返回数据才进行第二次提交操作
	 * @tip_3 当本地数据库的数据获取为空时，停止上传操作
	 * @tip_3 停止上传后，销毁该服务
	 */
	private void upLoadPhones() {
		// TODO Auto-generated method stub

		// 获取首批提交检验的手机 号码
		List<T_Contacts> phones = daoImpl.getUnRegiPhones(index);
//		System.out.println("pones size:"+phones.size());
		// 如果本地通信信息获取为空说明已经上传完毕，则销毁该服务结束本次过程
		if (!DataValidate.checkDataValid(phones)) {
			// 销毁服务
			onDestroy();
			
			return;
		}

		String upLoadPhones = FormatUtils.changeArrayPhoneType(phones);
		System.out.println("准备上传的号码:"+upLoadPhones);
		ReqPhoneCheckBean reqBean = new ReqPhoneCheckBean();
		reqBean.setUid(DBUtils.getUid());
		reqBean.setPhones(upLoadPhones);
		// 对获取到的号码进行上传操作
//		HttpServiceUtil upLoadService = new HttpServiceUtil(
//				API.CONTACT_PHONE_CHECK, reqBean);
//		upLoadService.setHttpRecvListener(recvListener);
//		new Thread(upLoadService).start();

	}

//	private httpRecvListener recvListener = new httpRecvListener() {
//
//		@Override
//		public void requestSuccess(int params, Object obj) {
//			// TODO Auto-generated method stub
//			if (obj != null) {
//				JSONObject jsonObject = (JSONObject) obj;
//				JSONArray array;
//				try {
//					array = jsonObject.getJSONArray("data");
//					ArrayList<RecvPhoneAuthorBean> phoneList = new ArrayList<RecvPhoneAuthorBean>();
//
//					try {
//						for (int i = 0; i < array.length(); i++) {
//							JSONObject object = array.getJSONObject(i);
//							RecvPhoneAuthorBean authorBean = new RecvPhoneAuthorBean();
//							authorBean.setPhone(object.getString("phone"));
//							authorBean.setUid(object.getString("mid"));
//							phoneList.add(authorBean);
//						}
//						for (RecvPhoneAuthorBean item : phoneList) {
////							daoImpl.updatePhoneRegister(item);
//							daoImpl.updatePhoneIfo(item);
//						}
//					} catch (JSONException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					} catch (DbException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				} catch (JSONException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//
//			}
//			// 数据库查询位增一操作，方便下次查询
//			index++;
//			// 进行下一次的请求操作
//			upLoadPhones();
//
//		}
//
//		@Override
//		public void requestFail(int params) {
//			// TODO Auto-generated method stub
//			onDestroy();
//		}
//	};

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		initResource();
//		initContactsData();
		new Thread(contactsRunnable).start();
//		upLoadPhones();
		super.onStart(intent, startId);
	}
	Runnable contactsRunnable=new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			initContactsData();
			upLoadPhones();
		}
	};
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		Intent intent=new Intent();
		intent.setAction(PARAMS.CONTACTS_DONE_RECEIVE);
		sendBroadcast(intent);
		stopSelf();
		super.onDestroy();
	}

}
