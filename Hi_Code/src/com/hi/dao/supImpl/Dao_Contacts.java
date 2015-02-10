package com.hi.dao.supImpl;

import java.util.List;

import android.content.Context;

import com.hi.common.PARAMS;
import com.hi.dao.model.T_Contacts;
import com.hi.dao.sup.ContactsDao;
import com.hi.module.friend.model.RecvPhoneAuthorBean;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.db.sqlite.WhereBuilder;
import com.lidroid.xutils.exception.DbException;

/**
 * @author MM_Zerui
 *管理通讯录记录的数据库实现类
 */
public class Dao_Contacts extends SuperDaoImpl implements ContactsDao{
	@Override
	public boolean checkContactsExist() {
		// TODO Auto-generated method stub
		try {
			T_Contacts bean=db.findFirst(T_Contacts.class);
			if(bean!=null){
				return true;
			}
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void insertContactsRecord(List<T_Contacts> beanList) throws DbException {
		// TODO Auto-generated method stub
		db.saveAll(beanList);
	}

	@Override
	public List<T_Contacts> getAllContactsRecord() {
		// TODO Auto-generated method stub
		List<T_Contacts> list;
		try {
			list = db.findAll(T_Contacts.class);
			return list;
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<T_Contacts> getUnRegiPhones(int index) {
		// TODO Auto-generated method stub
		System.out.println("index:"+index);
		try {
			List<T_Contacts> list=db.findAll(Selector.from(T_Contacts.class).
					where("ifRegister","=",Integer.valueOf(PARAMS.CONTACT_UNREGISTER)).
					limit(PARAMS.CONTACT_INDEX_LENGTH).
					offset(index*PARAMS.CONTACT_INDEX_LENGTH));
		
//			System.out.println("contacts list size:"+list.size());
//			if(list==null){
//				return null;
//			}
			return list;
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updatePhoneFollow(String phone) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void updatePhoneIfo(RecvPhoneAuthorBean bean) throws DbException {
		// TODO Auto-generated method stub
		T_Contacts updateBean=new T_Contacts();
		updateBean.setUid(bean.getUid());
		updateBean.setIfRegister(Integer.valueOf(PARAMS.CONTACT_REGISTER));
		updateBean.setPhone(bean.getPhone());
		db.update(updateBean,WhereBuilder.b("phone","=", bean.getPhone()), new String[]{"uid","ifRegister"});
	}
	
	

}
