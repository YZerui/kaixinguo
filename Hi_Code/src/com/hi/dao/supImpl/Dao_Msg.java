package com.hi.dao.supImpl;

import java.util.List;

import com.exception.utils.P;
import com.format.utils.BeanUtils;
import com.hi.common.db.E_DB_Msg;
import com.hi.common.db.E_DB_MsgSource;
import com.hi.common.db.E_DB_MsgType;
import com.hi.common.param.Enum_ListLimit;
import com.hi.dao.model.T_Msg;
import com.hi.dao.model.T_MsgCache;
import com.hi.dao.model.T_MsgSeq;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.db.sqlite.WhereBuilder;
import com.lidroid.xutils.exception.DbException;

/**
 * ��ڨ��¼�Ĳ���
 * 
 * @author MM_Zerui
 * 
 */
public class Dao_Msg extends SuperDaoImpl {

	/**
	 * ÿ�β����ж��Ƿ��Ѿ����ڴ���ϢID����������ڣ�����
	 * 
	 * @param msgCache
	 */
	public static void insertMsg(T_MsgCache msgCache) {
		try {
			if (checkExist(msgCache.getMsgId())) {
				return;
			}
			T_Msg msg = new T_Msg();
			msg.setHead(msgCache.getHead());
			msg.setIdentity(msgCache.getIdentity());
			msg.setMid(Dao_SelfIfo.getInstance().getMid());
			msg.setMsg(msgCache.getMsg());
			msg.setMsgId(msgCache.getMsgId());
			msg.setMsgType(E_DB_MsgType.ACCOST.value());// ����Ĭ��Ϊ��ڨ
			msg.setName(msgCache.getNickname());
			msg.setTime(msgCache.getTime());
			msg.setUid(msgCache.getUid());
			db.save(msg);
			// ������Ϣ˳���
			Dao_MsgSeq.insertMSRecord(msg);
			// ���������Ϣ
			updateUserIfo(msg.getUid(), msg.getName(), msg.getHead());
			//�������Ѵ�ڨ�� uid identity 
			Dao_Friends.updateAccostNum(msg.getUid(), msgCache.getIdentity());
			
		} catch (Exception e) {
			// TODO: handle exception
			P.v(e.getMessage());
		}
	}

	public static void insertMsg(T_Msg msgBean) {
		try {
			if (checkExist(msgBean.getMsgId())) {
				return;
			}
			db.save(msgBean);
			// ������Ϣ˳���
			Dao_MsgSeq.insertMSRecord(msgBean);
			// ���������Ϣ
			updateUserIfo(msgBean.getUid(), msgBean.getName(),
					msgBean.getHead());
			//�������Ѵ�ڨ�� uid identity 
			Dao_Friends.updateAccostNum(msgBean.getUid(), msgBean.getIdentity());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * �����û���ͷ���������Ϣ
	 * 
	 * @param uid
	 * @param name
	 * @param head
	 * @throws DbException
	 */
	private static void updateUserIfo(String uid, String name, String head)
			throws DbException {
		// TODO Auto-generated method stub
		T_Msg accostBean = new T_Msg();
		accostBean.setName(name);
		accostBean.setHead(head);
		db.update(accostBean, WhereBuilder.b(E_DB_Msg.uid.name(), "=", uid),
				new String[] { E_DB_Msg.name.name(), E_DB_Msg.head.name() });
		// ������Ϣ˳����е�����
		Dao_MsgSeq.upadateInfo(uid, name, head);
	}

	/**
	 * ��ȡ��ĳ�û�������д�ڨ�б�
	 * 
	 * @param uid
	 * @param index
	 * @return
	 * @throws DbException
	 */
	public static List<T_Msg> getAccostList(String uid, int index)
			throws DbException {
		// TODO Auto-generated method stub
		int length = Enum_ListLimit.MSG_LIST.value();
		List<T_Msg> accostList = db.findAll(Selector
				.from(T_Msg.class)
				.where(E_DB_Msg.uid.name(), "=", uid)
				.and(E_DB_Msg.mid.name(), "=",
						Dao_SelfIfo.getInstance().getMid())
				.orderBy("CAST(time AS NUMERIC)", true).limit(length)
				.offset(index * length));
		return accostList;
	}

	/**
	 * ���ĳ��ڨ��¼�Ƿ����
	 * 
	 * @param uid
	 * @return
	 */
	public static boolean checkExist(long msgId) {
		if(msgId==-1){
			return false;
		}
		Selector selector = Selector.from(T_Msg.class);
		selector.where(E_DB_Msg.mid.name(), "=", Dao_SelfIfo.getInstance()
				.getMid());
		selector.and(E_DB_Msg.msgId.name(), "=", msgId);
		try {
			if (db.findFirst(selector) != null) {
				return true;
			}
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * ��鱾���Ƿ����ĳ�û��Ĵ�ڨ��¼
	 * 
	 * @param uid
	 * @return
	 */
	public static boolean checkExist() {
		Selector selector = Selector.from(T_Msg.class);
		selector.where(E_DB_Msg.mid.name(), "=", Dao_SelfIfo.getInstance()
				.getMid());
		try {
			if (db.findFirst(selector) != null) {
				return true;
			}
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * ��ȡĳId������м�¼
	 * @param uid
	 * @param msgId
	 * @return
	 */
	public static List<T_Msg> getRecordsById(String uid,long msgId){
		Selector selector=Selector.from(T_Msg.class);
		selector
			.where(E_DB_Msg.uid.name(), "=", uid)
			.and(E_DB_Msg.mid.name(),"=",Dao_SelfIfo.getInstance().getMid())
			.and(E_DB_Msg.msgId.name(),">",msgId)
			.orderBy(E_DB_Msg.msgId.name(), true);
		try {
			return db.findAll(selector);
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

	/**
	 * ��ȡ��ĳ�û������µĴ�ڨ��¼
	 * 
	 * @param uid
	 * @return
	 * @throws DbException
	 */
	public static T_Msg getLastAccostRecord(String uid) throws DbException {
		T_Msg accostBean = db
				.findFirst(Selector
						.from(T_Msg.class)
						.where(E_DB_Msg.uid.name(), "=", uid)
						.and(E_DB_Msg.mid.name(), "=",
								Dao_SelfIfo.getInstance().getMid())
						.expr("and CAST(msgId AS NUMERIC)=(select max(CAST(msgId AS NUMERIC)) from t_accost where uid="
								+ "\'"
								+ uid
								+ "\' and mid="
								+ "\'"
								+ Dao_SelfIfo.getInstance().getMid() + "\')"));
		return accostBean;
	}
	/**
	 * ��ȡ�������µĴ�ڨ��¼
	 * 
	 * @param uid
	 * @return
	 * @throws DbException
	 */
	public static T_Msg getLastAccostRecord() throws DbException {
		T_Msg accostBean = db
				.findFirst(Selector
						.from(T_Msg.class)
						.where(E_DB_Msg.mid.name(), "=",
								Dao_SelfIfo.getInstance().getMid())
								.expr("and CAST(msgId AS NUMERIC)=(select max(CAST(msgId AS NUMERIC)) from T_Msg where mid="
										+ "\'"
										+ Dao_SelfIfo.getInstance().getMid() + "\')"));
		return accostBean;
	}

}
