package com.hi.dao.supImpl;

import java.util.List;

import com.exception.utils.P;
import com.format.utils.BeanUtils;
import com.hi.common.PARAMS;
import com.hi.common.db.E_DB_IMsgSeq;
import com.hi.common.db.E_DB_MsgSeq;
import com.hi.common.db.E_DB_MsgSource;
import com.hi.common.param.Enum_ListLimit;
import com.hi.dao.model.T_IMsgSeq;
import com.hi.dao.model.T_Msg;
import com.hi.dao.model.T_MsgSeq;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.db.sqlite.WhereBuilder;
import com.lidroid.xutils.exception.DbException;

/**
 * 消息顺序表
 * 
 * @author MM_Zerui
 * 
 */
public class Dao_MsgSeq extends SuperDaoImpl {

	public static List<T_MsgSeq> getMsgSeqList_Reverse(int i)
			throws DbException {
		// TODO Auto-generated method stub
		List<T_MsgSeq> groupList = db.findAll(Selector
				.from(T_MsgSeq.class)
				.where(E_DB_MsgSeq.mid.name(), "=",
						Dao_SelfIfo.getInstance().getMid()).orderBy("id", true)
				.limit(Enum_ListLimit.MSG.value())
				.offset(i * Enum_ListLimit.MSG.value()));
		return groupList;
	}

	public static void insertMSRecord(T_Msg bean) throws DbException {
		try {
			T_MsgSeq seqBean = (T_MsgSeq) BeanUtils.copyProperties(
					new T_MsgSeq(), bean);
			// 获取上一条的未读数
			int unRead = getMsgUnRead(seqBean.getUid());
			// 如果消息来自对方，则增加未读数
			if (seqBean.getIdentity() != E_DB_MsgSource.SELF.value()) {
				unRead++;
			}
			seqBean.setUnRead(unRead);
			if (checkRecordLatest(seqBean.getUid(), seqBean.getMsgId())) {
				// 覆盖原来的消息内容
				deleteMSRecord(seqBean.getUid(), seqBean.getMsgId());
				db.save(seqBean);
			} else {
				// 更新未读数
				updateUnRead(seqBean.getUid(), unRead);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * 更新某条消息的未读数
	 * 
	 * @param uid
	 * @param unRead
	 */
	private static void updateUnRead(String uid, int unRead) {
		T_MsgSeq seqBean = new T_MsgSeq();
		seqBean.setUnRead(unRead);
		try {
			db.update(
					seqBean,
					WhereBuilder.b(E_DB_MsgSeq.uid.name(), "=", uid).and(
							E_DB_MsgSeq.mid.name(), "=",
							Dao_SelfIfo.getInstance().getMid()),
					E_DB_MsgSeq.unRead.name());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			P.v(e.getMessage());
		}
	}

	public static void deleteMSRecord(String uid, long msgId)
			throws DbException {
		// TODO Auto-generated method stub
		db.delete(
				T_MsgSeq.class,
				WhereBuilder
						.b(E_DB_MsgSeq.uid.name(), "=", uid)
						.and(E_DB_MsgSeq.mid.name(), "=",
								Dao_SelfIfo.getInstance().getMid())
						.or(E_DB_MsgSeq.msgId.name(), "=", msgId));
	}

	/**
	 * 判断和某用户的消息记录是否存在
	 * 
	 * @param uid
	 * @return
	 * @throws DbException
	 */
	private static boolean checkRecordExist(String uid) throws DbException {
		// TODO Auto-generated method stub
		T_MsgSeq groupBean = db.findFirst(Selector
				.from(T_MsgSeq.class)
				.where(E_DB_MsgSeq.uid.name(), "=", uid)
				.and(E_DB_MsgSeq.mid.name(), "=",
						Dao_SelfIfo.getInstance().getMid()));
		if (groupBean != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获取某用户的消息记录
	 * 
	 * @param uid
	 * @return
	 * @throws DbException
	 */
	private static T_MsgSeq getMsgSeq(String uid) throws DbException {
		// TODO Auto-generated method stub
		T_MsgSeq groupBean = db.findFirst(Selector
				.from(T_MsgSeq.class)
				.where(E_DB_MsgSeq.uid.name(), "=", uid)
				.and(E_DB_MsgSeq.mid.name(), "=",
						Dao_SelfIfo.getInstance().getMid()));
		if (groupBean != null) {
			return groupBean;
		} else {
			return null;
		}
	}

	/**
	 * 检测某记录在消息顺序表中是否为最新
	 * 
	 * @param uid
	 * @param msgId
	 * @return
	 */
	private static boolean checkRecordLatest(String uid, long msgId) {
		try {
			T_MsgSeq seqBean = getMsgSeq(uid);
			if (seqBean == null) {
				return true;
			}
			if (seqBean.getMsgId() < msgId) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;

	}

	/**
	 * 更新表中的数据(昵称+头像)
	 * 
	 * @param uid
	 * @param name
	 * @param head
	 */
	public static void upadateInfo(String uid, String name, String head) {
		T_MsgSeq bean = new T_MsgSeq();
		bean.setUid(uid);
		bean.setName(name);
		bean.setHead(head);
		try {
			db.update(
					bean,
					WhereBuilder.b(E_DB_MsgSeq.mid.name(), "=",
							Dao_SelfIfo.getInstance().getMid()).and(
							E_DB_MsgSeq.uid.name(), "=", uid),
					E_DB_MsgSeq.name.name(), E_DB_MsgSeq.head.name());
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}

	public static int getMsgUnRead(String uid) throws DbException {
		// TODO Auto-generated method stub
		T_MsgSeq group = db.findFirst(Selector
				.from(T_MsgSeq.class)
				.where(E_DB_MsgSeq.uid.name(), "=", uid)
				.and(E_DB_MsgSeq.mid.name(), "=",
						Dao_SelfIfo.getInstance().getMid()));
		if (group == null) {
			return 0;
		}
		return group.getUnRead();
	}

	public static int getTotalMsgUnRead() throws DbException {
		// TODO Auto-generated method stub
		int unRead = 0;
		List<T_MsgSeq> group = db.findAll(Selector.from(T_MsgSeq.class).where(
				"mid", "=", Dao_SelfIfo.getInstance().getMid()));
		if (group == null) {
			return 0;
		}
		for (T_MsgSeq item : group) {
			unRead += item.getUnRead();
		}
		return unRead;
	}

	

}
