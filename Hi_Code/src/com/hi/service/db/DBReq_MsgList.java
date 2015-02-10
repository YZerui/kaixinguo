package com.hi.service.db;

import java.util.List;

import com.format.utils.DataValidate;
import com.hi.common.param.Enum_ListLimit;
import com.hi.dao.model.T_Msg;
import com.hi.dao.supImpl.Dao_Msg;
import com.hi.service.base.Call_DBListData;
import com.hi.service.base.DBSuperClass;
import com.lidroid.xutils.exception.DbException;
import com.thread.RunnableService;
import com.thread.callBack.runCallBack;

/**
 * 获取消息详情列表
 * 
 * @author MM_Zerui
 * 
 */
public class DBReq_MsgList extends DBSuperClass<T_Msg> {
	private String uid;

	public DBReq_MsgList(Call_DBListData<T_Msg> callBack) {
		// TODO Auto-generated constructor stub
		this.call_list = callBack;
	}

	public DBReq_MsgList onUserId(String uid) {
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
				List<T_Msg> list = Dao_Msg
						.getAccostList(uid, beginInit());
				if (DataValidate.checkDataValid(list)) {
					call_list.onInit(list);
				} else {
					call_list.onFail();
				}
			} else {
				List<T_Msg> list = Dao_Msg.getAccostList(uid,
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
