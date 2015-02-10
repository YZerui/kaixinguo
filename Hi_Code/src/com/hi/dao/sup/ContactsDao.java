package com.hi.dao.sup;

import java.util.List;

import com.hi.dao.model.T_Contacts;
import com.hi.module.friend.model.RecvPhoneAuthorBean;
import com.lidroid.xutils.exception.DbException;

/**
 * @author MM_Zerui
 * ����ͨѶ¼��Ϣ����ĳ���
 */
public interface ContactsDao {
	/**
	 * ��Ȿ���Ƿ����ͨѶ¼��Ϣ
	 * @return
	 */
	public boolean checkContactsExist();
	
	/**
	 * ����ͨѶ¼��Ϣ(��ʼ����Ϊ��ע��)
	 * @throws DbException 
	 *
	 */
	public void insertContactsRecord(List<T_Contacts> beanList) throws DbException;
	
	/**
	 * �������ͨѶ¼��Ϣ
	 * @return
	 */
	public List<T_Contacts> getAllContactsRecord();
	
	
	/**
	 *  ���δע�������(ÿ�λ�ȡ�ض�����)
	 * @param index
	 * 		ÿ�λ�ȡ�ĸ���
	 * @return
	 */
	public List<T_Contacts> getUnRegiPhones(int index);
	
	/**
	 * ���ض��ֻ��Ÿ���Ϊ��ע״̬
	 * @param phone
	 */
	public void updatePhoneFollow(String phone);
	
	/**�����ض��ֻ��ŵ���Ϣ
	 * @param bean
	 * @throws DbException 
	 */
	public void updatePhoneIfo(RecvPhoneAuthorBean bean) throws DbException;
}
