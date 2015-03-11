package com.hi.dao.supImpl;

import java.util.ArrayList;
import java.util.List;

import com.exception.utils.P;
import com.format.utils.DataValidate;
import com.hi.common.db.E_DB_IMsg;
import com.hi.common.db.E_DB_ImgUrlMode;
import com.hi.common.db.E_DB_Msg;
import com.hi.common.http.E_Http_SendState;
import com.hi.common.param.Enum_ListLimit;
import com.hi.dao.model.T_IMsg;
import com.hi.dao.model.T_Msg;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.db.sqlite.WhereBuilder;
import com.lidroid.xutils.exception.DbException;

/**
 * ��ʱͨѶ��Ϣ��
 * 
 * @author MM_Zerui
 * 
 */
public class Dao_IMsg extends SuperDaoImpl {
	private static boolean checkExist(String itemId) {
		try {
			// if(!DataValidate.checkDataValid(itemId)){
			// return false;
			// }
			Selector selector = Selector.from(T_IMsg.class);
			selector.where(E_DB_IMsg.objectId.name(), "=", itemId);
			if (db.findFirst(selector) != null) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	/**
	 * �����û���ͷ���������Ϣ
	 * 
	 * @param uid
	 * @param name
	 * @param head
	 * @throws DbException
	 */
	private static void updateUserIfo(String uid, String name, String head) {
		// TODO Auto-generated method stub
		try {
			if (!DataValidate.checkDataValid(name)
					|| !DataValidate.checkDataValid(head)) {
				return;
			}
			T_IMsg iMsg = new T_IMsg();
			iMsg.setName(name);
			iMsg.setHead(head);
			db.update(
					iMsg,
					WhereBuilder.b(E_DB_IMsg.uid.name(), "=", uid),
					new String[] { E_DB_IMsg.name.name(), E_DB_IMsg.head.name() });
			// ������Ϣ˳����е�����
			Dao_MsgSeq.upadateInfo(uid, name, head);
		} catch (Exception e) {
			// TODO: handle exception
			P.v(e.getMessage());
		}
	}

    /**
     * ����ĳ����Ϣ�ķ���״̬
     * @param objectId
     * @param sendState
     */
    public static void updateMsgState(String objectId,E_Http_SendState sendState){
        T_IMsg iMsg=new T_IMsg();
        iMsg.setSendState(sendState.value());
        try {
            db.update(iMsg,WhereBuilder.b(E_DB_IMsg.objectId.name(),"=",objectId),E_DB_IMsg.sendState.name());
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    public static void updateImageMode(String objectId,String imgUrl,E_DB_ImgUrlMode urlMode){
        T_IMsg iMsg=new T_IMsg();
        iMsg.setImageUrlMode(urlMode.value());
        iMsg.setMsg(imgUrl);
        try {
            db.update(iMsg,WhereBuilder.b(E_DB_IMsg.objectId.name(),"=",objectId),
                    E_DB_IMsg.msg.name(),E_DB_IMsg.ImageUrlMode.name());
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
	/**
	 * ɾ����ĳ�û�������м�¼
	 * 
	 * @param uid
	 */
	public static void delMessage(String uid) {
		try {
			db.delete(
					T_IMsg.class,
					WhereBuilder.b(E_DB_IMsg.uid.name(), "=", uid).and(
							E_DB_IMsg.mid.name(), "=",
							Dao_SelfIfo.getInstance().getMid()));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * ���غ�ĳ�û�������жԻ�
	 * 
	 * @param uid
	 */
	public static void hideMessage(String uid) {
		try {
			T_IMsg iMsg = new T_IMsg();
			iMsg.setSendState(E_Http_SendState.HIDE.value());
			db.update(
					iMsg,
					WhereBuilder.b(E_DB_IMsg.uid.name(), "=", uid).and(
							E_DB_IMsg.mid.name(), "=",
							Dao_SelfIfo.getInstance().getMid()),
					E_DB_IMsg.sendState.name());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * ����һ����Ϣ��¼
	 * 
	 * @param iMsg
	 * @param unRead
	 */
	public static synchronized void addMessage(T_IMsg iMsg, boolean unRead,
			boolean isRefresh) {
        P.v("addMessage...");
		try {
			if (checkExist(iMsg.getObjectId())) {
				return;
			}
			db.save(iMsg);
			// ������Ϣ˳���
			Dao_IMsgSeq.addMsg(iMsg, unRead);
			if (isRefresh) {
				// ���±��жԷ���ͷ���������Ϣ
				updateUserIfo(iMsg.getUid(), iMsg.getName(), iMsg.getHead());
			}
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * ����ĳ����Ϣ�ķ���״̬
	 * 
	 * @param enumType
	 * @param objectId
	 */
	public static void updateMsgState(E_Http_SendState enumType,
			String objectId, String uid) {
		try {
			T_IMsg iMsg = new T_IMsg();
			iMsg.setSendState(enumType.value());
			db.update(iMsg,
					WhereBuilder.b(E_DB_IMsg.objectId.name(), "=", objectId),
					E_DB_IMsg.sendState.name());
			// Dao_IMsgSeq.updateMsgState(enumType, objectId);
			// ���Ϊɾ��״̬
			if (enumType == E_Http_SendState.HIDE) {
				Dao_IMsgSeq.addMsg(getNewestMessage(uid), false);
			}

		} catch (Exception e) {
			// TODO: handle exception

		}
	}

	/**
	 * ��ȡ��ĳ�û���ĶԻ�
	 * 
	 * @param uid
	 * @param index
	 * @return
	 */
	public static List<T_IMsg> getMessage(String uid, int index) {
		// TODO Auto-generated method stub
		try {
			int length = Enum_ListLimit.MSG_LIST.value();
			List<T_IMsg> msgList = db.findAll(Selector
					.from(T_IMsg.class)
					.where(E_DB_IMsg.uid.name(), "=", uid)
					.and(E_DB_IMsg.mid.name(), "=",
							Dao_SelfIfo.getInstance().getMid())
					.and(E_DB_IMsg.sendState.name(), "<>",
							E_Http_SendState.HIDE.value())
					.orderBy(E_DB_IMsg.time.name(), true).limit(length)
					.offset(index * length));
			return msgList;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	/**
	 * ��ȡ��ĳ�û���ĶԻ�
	 *
	 * @return
	 */
	public static List<T_IMsg> getMessage2(String convid, int msgSize) {
		// TODO Auto-generated method stub
		try {
			int length = Enum_ListLimit.MSG_LIST.value();
			List<T_IMsg> msgList = db.findAll(Selector
					.from(T_IMsg.class)
					.where(E_DB_IMsg.convid.name(), "=", convid)
					.and(E_DB_IMsg.sendState.name(), "<>",
                            E_Http_SendState.HIDE.value())
					.orderBy(E_DB_IMsg.time.name(),true).limit(msgSize))
					;
			return msgList;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return new ArrayList<T_IMsg>();
	}

	/**
	 * ��ȡ��ĳ�û������µ�N���Ի�
	 * 
	 * @param uid
	 * @return
	 */
	public static List<T_IMsg> getMessageNum(String uid, int num) {
		// TODO Auto-generated method stub
		try {
			List<T_IMsg> msgList = db.findAll(Selector
					.from(T_IMsg.class)
					.where(E_DB_IMsg.uid.name(), "=", uid)
					.and(E_DB_IMsg.mid.name(), "=",
							Dao_SelfIfo.getInstance().getMid())
					.orderBy(E_DB_IMsg.time.name(), true).limit(num));
			return msgList;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	/**
	 * ��ȡ��ĳ�û������µĶԻ�
	 * 
	 * @param uid
	 * @return
	 */
	public static T_IMsg getNewestMessage(String uid) {
		// TODO Auto-generated method stub
		try {
			T_IMsg msgList = db.findFirst(Selector
					.from(T_IMsg.class)
					.where(E_DB_IMsg.uid.name(), "=", uid)
					.and(E_DB_IMsg.mid.name(), "=",
							Dao_SelfIfo.getInstance().getMid())
					.and(E_DB_IMsg.sendState.name(), "<>",
							E_Http_SendState.HIDE.value())
					.orderBy(E_DB_IMsg.time.name(), true));
			return msgList;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

}
