package com.hi.dao.sup;

import java.util.List;

import com.hi.dao.model.T_Contacts;
import com.hi.module.friend.model.RecvPhoneAuthorBean;
import com.lidroid.xutils.exception.DbException;

/**
 * @author MM_Zerui
 * 定义通讯录信息管理的超类
 */
public interface ContactsDao {
	/**
	 * 检测本地是否存在通讯录信息
	 * @return
	 */
	public boolean checkContactsExist();
	
	/**
	 * 插入通讯录信息(初始化均为非注册)
	 * @throws DbException 
	 *
	 */
	public void insertContactsRecord(List<T_Contacts> beanList) throws DbException;
	
	/**
	 * 获得所有通讯录信息
	 * @return
	 */
	public List<T_Contacts> getAllContactsRecord();
	
	
	/**
	 *  获得未注册的名单(每次获取特定个数)
	 * @param index
	 * 		每次获取的个数
	 * @return
	 */
	public List<T_Contacts> getUnRegiPhones(int index);
	
	/**
	 * 将特定手机号更改为关注状态
	 * @param phone
	 */
	public void updatePhoneFollow(String phone);
	
	/**更新特定手机号的信息
	 * @param bean
	 * @throws DbException 
	 */
	public void updatePhoneIfo(RecvPhoneAuthorBean bean) throws DbException;
}
