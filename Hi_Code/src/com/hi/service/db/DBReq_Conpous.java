package com.hi.service.db;

import java.util.List;

import com.format.utils.DataValidate;
import com.hi.common.param.Enum_ListLimit;
import com.hi.dao.model.T_Coupons;
import com.hi.dao.supImpl.Dao_Coupons;
import com.hi.service.base.Call_DBListData;
import com.hi.service.base.DBSuperClass;
import com.thread.RunnableService;
import com.thread.callBack.runCallBack;

/**
 * 获取优惠劵列表信息
 * @author MM_Zerui
 *
 */
public class DBReq_Conpous extends DBSuperClass<T_Coupons>{
	public DBReq_Conpous(Call_DBListData<T_Coupons> callBack) {
		// TODO Auto-generated constructor stub
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
	public void method(){
		try {
			call_list.onStart();
			if (isIfInit()) {
				List<T_Coupons> list = Dao_Coupons
						.getCoupons(beginInit());
				if (DataValidate.checkDataValid(list)) {
					call_list.onInit(list);
				} else {
					call_list.onFail();
				}
			} else {
				List<T_Coupons> list = Dao_Coupons.getCoupons(
						beginLoad(Enum_ListLimit.COUPONS.value()));
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
