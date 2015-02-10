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
 * @author MM_Zerui ͨѶ¼�ϴ����·���
 * @tip_1 �����жϱ������ݿ��Ƿ������ϵ�˼�¼
 * @tip_2 ��������ڣ����豸�л�ȡͨѶ¼��Ϣ�����뱾�����ݿ���
 * @tip_3 ������ڣ�ֱ�ӱ������ݿ�����ȡδע��ĺ����ϴ���������
 * @tip_4 �Է���˷��ص����ݽ��з���������˷��ص����ݾ�Ϊע����ģ�ͨ����Щ���ݶԱ������ݿ������Ϣ���Ĳ���
 * @tip_5 �÷��������֪ͨ�����б�ҳ�棬������Ӧ�ؼ��ĸ��²���
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
		// ����ǵ�һ������,��������ݼ���
		System.out.println("service 2...");
		if (!daoImpl.checkContactsExist()) {
			// ��ȡ�豸�е�ͨѶ¼����
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
	 * �ύ�ֻ����뵽����˵ķ���
	 * 
	 * @tip_1 ��ȡ�ֶ��ύ�ķ�ʽ
	 * @tip_2 ÿ���ύ��ȴ�����˷������ݲŽ��еڶ����ύ����
	 * @tip_3 ���������ݿ�����ݻ�ȡΪ��ʱ��ֹͣ�ϴ�����
	 * @tip_3 ֹͣ�ϴ������ٸ÷���
	 */
	private void upLoadPhones() {
		// TODO Auto-generated method stub

		// ��ȡ�����ύ������ֻ� ����
		List<T_Contacts> phones = daoImpl.getUnRegiPhones(index);
//		System.out.println("pones size:"+phones.size());
		// �������ͨ����Ϣ��ȡΪ��˵���Ѿ��ϴ���ϣ������ٸ÷���������ι���
		if (!DataValidate.checkDataValid(phones)) {
			// ���ٷ���
			onDestroy();
			
			return;
		}

		String upLoadPhones = FormatUtils.changeArrayPhoneType(phones);
		System.out.println("׼���ϴ��ĺ���:"+upLoadPhones);
		ReqPhoneCheckBean reqBean = new ReqPhoneCheckBean();
		reqBean.setUid(DBUtils.getUid());
		reqBean.setPhones(upLoadPhones);
		// �Ի�ȡ���ĺ�������ϴ�����
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
//			// ���ݿ��ѯλ��һ�����������´β�ѯ
//			index++;
//			// ������һ�ε��������
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
