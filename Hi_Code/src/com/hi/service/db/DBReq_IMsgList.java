package com.hi.service.db;

import java.util.List;

import com.format.utils.DataValidate;
import com.hi.common.param.Enum_ListLimit;
import com.hi.dao.model.T_IMsg;
import com.hi.dao.supImpl.Dao_IMsg;
import com.hi.service.base.Call_DBListData;
import com.hi.service.base.DBSuperClass;
import com.thread.RunnableService;
import com.thread.callBack.runCallBack;

public class DBReq_IMsgList extends DBSuperClass<T_IMsg>{

	private String uid;

	public DBReq_IMsgList(Call_DBListData<T_IMsg> callBack) {
		// TODO Auto-generated constructor stub
		this.call_list = callBack;
	}

	public DBReq_IMsgList onUserId(String uid) {
		this.uid = uid;
		return this;
	}

	@Override
	protected void onAction() {
		// TODO Auto-generated method stub
		new RunnableService(new runCallBack() {
			
			@Override
			public void start() {
				// TODO Auto-generated method stub
				method();
			}
			
			@Override
			public void end() {
				// TODO Auto-generated method stub
				
			}
		}, true);
		
	}

	private void method() {
		try {
			call_list.onStart();
			if (isIfInit()) {
				List<T_IMsg> list = Dao_IMsg.getMessage(uid, beginInit());
				if (DataValidate.checkDataValid(list)) {
					call_list.onInit(list);
				} else {
					call_list.onFail();
				}
			} else {
				List<T_IMsg> list = Dao_IMsg.getMessage(uid,
						beginLoad(Enum_ListLimit.MSG_LIST.value()));
				if (DataValidate.checkDataValid(list)) {
					call_list.onLoad(list);
				} else {
					call_list.onFail();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		call_list.onFinally();
	}


}
