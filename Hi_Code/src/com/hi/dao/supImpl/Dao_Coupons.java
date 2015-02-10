package com.hi.dao.supImpl;

import java.util.List;

import com.format.utils.DataValidate;
import com.hi.common.db.E_DB_Conpous;
import com.hi.common.db.E_DB_ConpousUse;
import com.hi.common.db.E_DB_ConpousUsing;
import com.hi.common.param.Enum_ListLimit;
import com.hi.dao.model.T_Coupons;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.db.sqlite.WhereBuilder;
import com.lidroid.xutils.exception.DbException;

/**
 * �Ż݄�����
 * 
 * @author MM_Zerui
 * 
 */
public class Dao_Coupons extends SuperDaoImpl {
	/**
	 * �����Ż݄��Ƿ����
	 * 
	 * @param cid
	 * @return
	 */
	public static boolean checkCouponsExist(String cid) {
		Selector selector = Selector.from(T_Coupons.class);
		selector.where(E_DB_Conpous.cid.name(), "=", cid);
		selector.and(E_DB_Conpous.mid.name(), "=", Dao_SelfIfo.getInstance()
				.getMid());
		try {
			if (DataValidate.checkDataValid(db.findFirst(selector))) {
				return true;
			}

		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * ��ȡĳ���Ż݄�
	 * @param cId
	 * @return
	 */
	public static T_Coupons getCoupon(String cId){
		Selector selector = Selector.from(T_Coupons.class);
		selector.where(E_DB_Conpous.cid.name(), "=", cId);
		selector.and(E_DB_Conpous.mid.name(), "=", Dao_SelfIfo.getInstance()
				.getMid());
		try {
			return db.findFirst(selector);
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * ����һ���Ż݄���¼
	 * @param tBean
	 */
	public static void addCoupons(T_Coupons tBean){
		tBean.setMid(Dao_SelfIfo.getInstance().getMid());
		tBean.setIntervalTime(100);//Ĭ�ϻ���ʱ��
		if(checkCouponsExist(tBean.getCid())){
			return;
		}
		try {
			db.save(tBean);
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * ��ȡ������Ч�Ż݄�
	 * @return
	 */
	public static List<T_Coupons> getCoupons(int index){
		Selector selector=Selector.from(T_Coupons.class);
		selector.where(E_DB_Conpous.mid.name(), "=", Dao_SelfIfo.getInstance().getMid());
		selector.and(E_DB_Conpous.isUse.name(), "=", E_DB_ConpousUse.UNUSE.value());
		selector.limit(Enum_ListLimit.COUPONS.value());
		selector.offset(index*Enum_ListLimit.COUPONS.value());
		try {
			return db.findAll(selector);
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * ����ĳ�Ż݄��Ƿ�ʹ�õ�״̬
	 * @param cId
	 * @param isUse
	 */
	public static void updateCouponsUse(String cId,boolean isUse){
		T_Coupons tBean=new T_Coupons();
		if(isUse){
			tBean.setIsUse(E_DB_ConpousUse.USE.value());
		}else {
			tBean.setIsUse(E_DB_ConpousUse.UNUSE.value());
		}
		try {
			db.update(tBean, WhereBuilder
					.b(E_DB_Conpous.mid.name(), "=", Dao_SelfIfo.getInstance().getMid())
					.and(E_DB_Conpous.cid.name(), "=", cId),
					E_DB_Conpous.isUse.name());
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * ����ĳ�Ż݄��Ƿ�����ʹ�õ�״̬
	 * @param cId
	 * @param isUse
	 */
	public static void updateCouponsUsing(String cId,boolean isUsing){
		T_Coupons tBean=new T_Coupons();
		if(isUsing){
			tBean.setIsUsing(E_DB_ConpousUsing.USING.value());
		}else {
			tBean.setIsUsing(E_DB_ConpousUsing.UNUSING.value());
		}
		try {
			db.update(tBean, WhereBuilder
					.b(E_DB_Conpous.mid.name(), "=", Dao_SelfIfo.getInstance().getMid())
					.and(E_DB_Conpous.cid.name(), "=", cId),
					E_DB_Conpous.isUsing.name());
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * ����ĳ�Ż݄�ʹ��ʱ��
	 * @param cId
	 * @param isUse
	 */
	public static void updateCouponsUseTime(String time,String cId){
		T_Coupons tBean=new T_Coupons();
		tBean.setUseTime(time);
		try {
			db.update(tBean, WhereBuilder
					.b(E_DB_Conpous.mid.name(), "=", Dao_SelfIfo.getInstance().getMid())
					.and(E_DB_Conpous.cid.name(), "=", cId),
					E_DB_Conpous.useTime.name());
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
