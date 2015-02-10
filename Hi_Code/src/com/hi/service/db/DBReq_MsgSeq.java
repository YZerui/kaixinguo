package com.hi.service.db;

import java.util.List;

import com.format.utils.DataValidate;
import com.hi.common.param.Enum_ListLimit;
import com.hi.dao.model.T_Msg;
import com.hi.dao.model.T_MsgSeq;
import com.hi.dao.supImpl.Dao_MsgSeq;
import com.hi.service.base.Call_DBListData;
import com.hi.service.base.DBSuperClass;
import com.lidroid.xutils.exception.DbException;
import com.thread.RunnableService;
import com.thread.callBack.runCallBack;

/**
 * 搭讪记录的处理服务
 * 
 * @author MM_Zerui
 * 
 */
public class DBReq_MsgSeq extends DBSuperClass<T_MsgSeq> {
	public DBReq_MsgSeq(Call_DBListData<T_MsgSeq> callBack) {
		// TODO Auto-generated constructor stub
		this.call_list = callBack;
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
		call_list.onStart();
		try {
			if(isIfInit()){
				List<T_MsgSeq> msgGroups = Dao_MsgSeq
						.getMsgSeqList_Reverse(beginInit());
				if (DataValidate.checkDataValid(msgGroups)) {
					call_list.onInit(msgGroups);
				}else {
					call_list.onFail();
				}
			}else {
				List<T_MsgSeq> msgGroups = Dao_MsgSeq
						.getMsgSeqList_Reverse(beginLoad(Enum_ListLimit.MSG_LIST.value()));
				if (DataValidate.checkDataValid(msgGroups)) {
					call_list.onLoad(msgGroups);
				}else {
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
