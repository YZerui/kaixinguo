package com.hi.dao.supImpl;

import java.util.List;

import com.hi.dao.model.T_MsgCache;
import com.lidroid.xutils.exception.DbException;


/**
 * ��Ϣ�����
 * @author MM_Zerui
 *
 */
public class Dao_MsgCache extends SuperDaoImpl {
	//��ռ�¼
	public static void clear(){
		try {
			db.deleteAll(T_MsgCache.class);
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * �����б��¼
	 * @param list
	 */
	public static void saveList(List<T_MsgCache> list){
		try {
			db.saveAll(list);
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * ��ȡ���м�¼
	 * @return
	 */
	public static List<T_MsgCache> getAll(){
		try {
			return db.findAll(T_MsgCache.class);
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
