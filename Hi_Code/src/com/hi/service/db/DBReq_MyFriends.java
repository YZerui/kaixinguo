package com.hi.service.db;

import java.util.List;

import com.format.utils.DataValidate;
import com.hi.common.param.Enum_ListLimit;
import com.hi.dao.model.T_MyFriends;
import com.hi.dao.supImpl.Dao_Friends;
import com.hi.service.base.Call_DBListData;
import com.hi.service.base.DBSuperClass;
import com.thread.RunnableService;
import com.thread.callBack.runCallBack;

/**
 * ��ϵ���б���Ϣ�Ļ�ȡ
 * @author MM_Zerui
 *
 */
public class DBReq_MyFriends extends DBSuperClass<T_MyFriends>{
	public DBReq_MyFriends(Call_DBListData<T_MyFriends> callBack){
		this.call_list=callBack;
	}
	@Override
	protected void onAction() {
		// TODO Auto-generated method stub
		new RunnableService(new runCallBack(){

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
				List<T_MyFriends> list = Dao_Friends.getAllFriends(beginInit());
				if (DataValidate.checkDataValid(list)) {
					call_list.onInit(list);
				} else {
					call_list.onFail();
				}
			} else {
				List<T_MyFriends> list = Dao_Friends.getAllFriends(
						beginLoad(Enum_ListLimit.FRIEND_LIST.value()));
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
