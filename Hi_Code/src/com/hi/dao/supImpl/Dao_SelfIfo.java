package com.hi.dao.supImpl;

import com.hi.common.db.E_DB_SelfIfo;
import com.hi.dao.model.T_SelfIfo;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;

/**
 * 管理个人信息
 * @author MM_Zerui
 *
 */
public class Dao_SelfIfo extends SuperDaoImpl{
	public static T_SelfIfo getInstance() {
		try {
			return db.findFirst(Selector.from(T_SelfIfo.class));
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new T_SelfIfo();
	}
	public static boolean checkExist(){
		try {
			if(db.findFirst(T_SelfIfo.class)!=null){
				return true;
			}
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public static void clear(){
		try {
			db.deleteAll(T_SelfIfo.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void save(T_SelfIfo bean){
		try {
			
			if(checkExist()){
				db.deleteAll(T_SelfIfo.class);
			}
			db.save(bean);
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void setParams(E_DB_SelfIfo paramName,String paramValue){
		T_SelfIfo bean = getInstance();
		switch (paramName) {
		case accostedEffe:
			bean.setAccostedEffe(paramValue);
			break;
		case birthDay:
			bean.setBirthDay(paramValue);
			break;
		case currentState:
			bean.setCurrentState(paramValue);
			break;
		case head:
			bean.setHead(paramValue);
			break;
		case interest:
			bean.setInterest(paramValue);
			break;
		case locationEffe:
			bean.setLocationEffe(paramValue);
			break;
		case mid:
			bean.setMid(paramValue);
			break;
		case nickName:
			bean.setNickName(paramValue);
			break;
		case note:
			bean.setNote(paramValue);
			break;
		case occupation:
			bean.setOccupation(paramValue);
			break;
		case phone:
			bean.setPhone(paramValue);
			break;
		case remark:
			bean.setRemark(paramValue);
			break;
		case sex:
			bean.setSex(paramValue);
			break;
		case photos_1:
			bean.setPhotos_1(paramValue);
			break;
		case photos_2:
			bean.setPhotos_2(paramValue);
			break;
		case photos_3:
			bean.setPhotos_3(paramValue);
			break;
		default:
			return;
		}
		try {
			db.update(bean, paramName.name());
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
