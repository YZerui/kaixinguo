package com.hi.dao.supImpl;

import java.util.List;

import com.exception.utils.P;
import com.format.utils.BeanUtils;
import com.format.utils.DataValidate;
import com.hi.common.db.E_DB_IMsg;
import com.hi.common.db.E_DB_IMsgSeq;
import com.hi.common.db.E_DB_MsgSeq;
import com.hi.common.http.E_Http_SendState;
import com.hi.common.param.Enum_ListLimit;
import com.hi.dao.model.T_IMsg;
import com.hi.dao.model.T_IMsgSeq;
import com.hi.dao.model.T_MsgSeq;
import com.hi.utils.FormatUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.db.sqlite.WhereBuilder;
import com.lidroid.xutils.exception.DbException;

public class Dao_IMsgSeq extends SuperDaoImpl {
	private static boolean delMsg(String uid) {
		try {
			db.delete(
					T_IMsgSeq.class,
					WhereBuilder.b(E_DB_IMsgSeq.uid.name(), "=", uid).and(
							E_DB_IMsgSeq.mid.name(), "=",
							Dao_SelfIfo.getInstance().getMid()));
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	private static boolean hideMsg(String uid) {
		try {
			T_IMsgSeq seqBean = new T_IMsgSeq();
			seqBean.setSendState(E_Http_SendState.HIDE.value());
			db.update(
					seqBean,
					WhereBuilder.b(E_DB_IMsgSeq.uid.name(), "=", uid).and(
							E_DB_IMsgSeq.mid.name(), "=",
							Dao_SelfIfo.getInstance().getMid()),
					E_DB_IMsgSeq.sendState.name());
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	private static T_IMsgSeq getMsg(String uid) {
		try {
			T_IMsgSeq groupBean = db.findFirst(Selector
					.from(T_IMsgSeq.class)
					.where(E_DB_IMsgSeq.uid.name(), "=", uid)
					.and(E_DB_IMsgSeq.mid.name(), "=",
							Dao_SelfIfo.getInstance().getMid()));
			if (groupBean != null) {
				return groupBean;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	public static void clearUnRead(String uid) {
		try {
			T_IMsgSeq seqBean = new T_IMsgSeq();
			seqBean.setUnRead(0);
			db.update(
					seqBean,
					WhereBuilder.b(E_DB_IMsgSeq.uid.name(), "=", uid).and(
							E_DB_IMsgSeq.mid.name(), "=",
							Dao_SelfIfo.getInstance().getMid()),
					E_DB_IMsgSeq.unRead.name());
		} catch (Exception e) {
			// TODO: handle exception
			P.v(e.getMessage());
		}
	}

	/**
	 * ����һ����Ϣ��¼
	 * 
	 * @param iMsg
	 */
	public static void addMsg(T_IMsg iMsg, boolean unRead) {
		try {
			if (!DataValidate.checkDataValid(iMsg)) {
				return;
			}
			T_IMsgSeq seqBean = getMsg(iMsg.getUid());
			T_IMsgSeq tBean = (T_IMsgSeq) BeanUtils.copyProperties(
					new T_IMsgSeq(), iMsg);
			tBean.setMsgId(iMsg.getObjectId());
			if (DataValidate.checkDataValid(seqBean)) {
				if (unRead) {
					tBean.setUnRead(seqBean.getUnRead() + 1);
				} else {
					tBean.setUnRead(seqBean.getUnRead());
				}
			} else {
				if (unRead) {
					tBean.setUnRead(1);
				} else {
					tBean.setUnRead(0);
				}
			}
			delMsg(iMsg.getUid());
			db.save(tBean);
		} catch (Exception e) {
			// TODO: handle exception
			P.v(e.getMessage());
		}
	}

	/**
	 * ��ȡ��ĳ�û����δ����
	 * 
	 * @param uid
	 * @return
	 * @throws DbException
	 */
	public static int getMsgUnRead(String uid) throws DbException {
		// TODO Auto-generated method stub
		T_IMsgSeq msgBean = db.findFirst(Selector
				.from(T_IMsgSeq.class)
				.where(E_DB_IMsgSeq.uid.name(), "=", uid)
				.and(E_DB_IMsgSeq.mid.name(), "=",
						Dao_SelfIfo.getInstance().getMid()));
		if (msgBean == null) {
			return 0;
		}
		return msgBean.getUnRead();
	}

	/**
	 * ��ĳ�û����δ������1
	 * 
	 * @param uid
	 */
	public static void addMsgUnRead(String uid) {
		try {
			T_IMsgSeq seqBean = new T_IMsgSeq();
			seqBean.setTime(FormatUtils.getCurrentDateValue_long());
			seqBean.setUnRead(getMsgUnRead(uid) + 1);
			db.update(seqBean, WhereBuilder.b(E_DB_IMsgSeq.uid.name(),"=",uid)
					.and(E_DB_IMsgSeq.mid.name(),"=", Dao_SelfIfo.getInstance().getMid()),
					E_DB_IMsgSeq.unRead.name(),E_DB_IMsgSeq.time.name());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * ��ȡ�ܵ�δ����
	 * 
	 * @return
	 * @throws DbException
	 */
	public static int getTotalMsgUnRead() throws DbException {
		// TODO Auto-generated method stub
		int unRead = 0;
		List<T_IMsgSeq> group = db.findAll(Selector.from(T_IMsgSeq.class)
				.where("mid", "=", Dao_SelfIfo.getInstance().getMid()));
		if (group == null) {
			return 0;
		}
		for (T_IMsgSeq item : group) {
			unRead += item.getUnRead();
		}
		return unRead;
	}

	/**
	 * ��ȡ�б����ݣ���ʱ��˳��
	 * 
	 * @return
	 */
	public static List<T_IMsgSeq> getMsg(int index) {
		// TODO Auto-generated method stub
		try {
			List<T_IMsgSeq> seqList = db.findAll(Selector
					.from(T_IMsgSeq.class)
					.where(E_DB_IMsgSeq.mid.name(), "=",
							Dao_SelfIfo.getInstance().getMid())
					.and(E_DB_IMsgSeq.sendState.name(), "<>",
							E_Http_SendState.HIDE.value())
					.orderBy(E_DB_IMsgSeq.time.name(), true)
					.limit(Enum_ListLimit.MSG.value())
					.offset(index * Enum_ListLimit.MSG.value()));
			return seqList;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;

	}

	/**
	 * ɾ����ĳ�û���ĶԻ�
	 * 
	 * @param uid
	 */
	public static void delUserMsg(String uid) {
		delMsg(uid);
		Dao_IMsg.delMessage(uid);
	}

	/**
	 * ���غ�ĳ�û���ĶԻ�
	 * 
	 * @param uid
	 */
	public static void hideUserMsg(String uid) {
		hideMsg(uid);
		Dao_IMsg.hideMessage(uid);

	}

	/**
	 * ����ĳ����Ϣ�ķ���״̬
	 * 
	 * @param enumType
	 * @param objectId
	 */
	public static void updateMsgState(E_Http_SendState enumType, String msgId) {
		try {
			T_IMsgSeq iMsg = new T_IMsgSeq();
			iMsg.setSendState(enumType.value());
			db.update(iMsg,
					WhereBuilder.b(E_DB_IMsgSeq.msgId.name(), "=", msgId),
					E_DB_IMsg.sendState.name());
			// Dao_IMsgSeq.
		} catch (Exception e) {
		}
	}
	// public static void clearUnReadGroupMsg(String uid) {
	// // TODO Auto-generated method stub
	// try {
	// T_IMsgSeq group = new T_IMsgSeq();
	// group.setUnRead(0);
	// db.update(
	// group,
	// WhereBuilder.b(E_DB_IMsgSeq.uid.name(), "=",
	// uid).and(E_DB_IMsgSeq.mid.name(), "=",
	// Dao_SelfIfo.getInstance().getMid()),
	// E_DB_IMsgSeq.unRead.name());
	// } catch (Exception e) {
	// // TODO: handle exception
	// P.v(e.getMessage());
	// }
	// }
}
