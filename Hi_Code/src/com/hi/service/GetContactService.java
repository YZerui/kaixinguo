package com.hi.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.hi.common.EXCEPTION_CODE;
import com.hi.dao.model.T_Contacts;
import com.hi.module.base.application.AppContextApplication;
import com.hi.module.base.superClass.SuperRunnable;
import com.hi.module.friend.model.ContactSortBean;
import com.hi.utils.ConstactUtil;

import android.content.Context;
import android.os.Handler;


/**
 * @author MM_Zerui
 * 获得手机联系信息的服务类
 */
public class GetContactService extends SuperRunnable{
	private ArrayList<T_Contacts> beanList;
	private Context context;
	public GetContactService(Handler handler) {
		// TODO Auto-generated constructor stub
		context=AppContextApplication.getInstance();
		beanList=new ArrayList<T_Contacts>();
		this.handler=handler;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		//获得联系人信息（姓名+号码）
		beanList=ConstactUtil.getSortContactData();
		if(beanList!=null){
			msg.what=EXCEPTION_CODE.CONTACT_OBT_SUCCESS;
			msg.obj=beanList;
		}else {
			msg.what=EXCEPTION_CODE.CONTACT_OBT_FAIL;
		}
		handler.sendMessage(msg);
	}
	
}
