package com.hi.dao.supImpl;

import java.util.List;

import com.android.ruifeng.hi.R.id;
import com.hi.common.PARAMS;
import com.hi.common.db.E_DB_MsgSource;
import com.hi.common.db.E_DB_MyFriend;
import com.hi.common.param.Enum_ListLimit;
import com.hi.dao.model.T_MyFriends;
import com.hi.dao.sup.FriendsDao;
import com.hi.http.base.Call_httpData;
import com.hi.http.friends.model.Req_Follow;
import com.hi.http.friends.req.Http_Follow;
import com.hi.http.member.model.Recv_UserIfo;
import com.hi.http.member.model.Req_UserIfo;
import com.hi.http.member.req.Http_GetUserIfo;
import com.hi.module.friend.model.RecvFriendsBean;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.db.sqlite.WhereBuilder;
import com.lidroid.xutils.exception.DbException;

/**
 * 管理朋友信息的实现类
 * 
 * @author MM_Zerui
 * 
 */
public class Dao_Friends extends SuperDaoImpl {
	private static void addRecord(T_MyFriends tBean) {
		tBean.setMid(Dao_SelfIfo.getInstance().getMid());
		try {
			db.save(tBean);
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static boolean checkMyFriendsExist() {
		// TODO Auto-generated method stub
		T_MyFriends bean;
		try {
			bean = db.findFirst(T_MyFriends.class);
			if (bean != null) {
				return true;
			}
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * 检测某联系人是否存在
	 * 
	 * @param fid
	 * @return
	 */
	private static boolean checkMyFirendsExit(String fid) {
		if (getInfo(fid) == null) {
			return false;
		}
		return true;
	}

	/**
	 * 更新某用户的信息
	 * 
	 * @param tBean
	 */
	private static void updateInfo(T_MyFriends tBean) {
		try {
			db.update(
					tBean,
					WhereBuilder.b(E_DB_MyFriend.mid.name(), "=",
							Dao_SelfIfo.getInstance().getMid()).and(
							E_DB_MyFriend.fid.name(), "=", tBean.getFid()),
					E_DB_MyFriend.birthday.name(),
					E_DB_MyFriend.currentState.name(),
					E_DB_MyFriend.head.name(), E_DB_MyFriend.location.name(),
					E_DB_MyFriend.nickName.name(), E_DB_MyFriend.sex.name(),
					E_DB_MyFriend.type.name());
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 获取某联系人的信息
	 * 
	 * @param fid
	 * @return
	 */
	private static T_MyFriends getInfo(String fid) {
		Selector selector = Selector.from(T_MyFriends.class);
		selector.where(E_DB_MyFriend.mid.name(), "=",
				Dao_SelfIfo.getInstance().getMid()).and(
				E_DB_MyFriend.fid.name(), "=", fid);
		try {
			return db.findFirst(selector);
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 更新被搭讪次数信息
	 * 
	 * @param tBean
	 */
	private static void updateAccostNum_IN(T_MyFriends tBean) {
		try {
			db.update(
					tBean,
					WhereBuilder.b(E_DB_MyFriend.mid.name(), "=",
							Dao_SelfIfo.getInstance().getMid()).and(
							E_DB_MyFriend.fid.name(), "=", tBean.getFid()),
					E_DB_MyFriend.accost_in_num.name(),
					E_DB_MyFriend.accost_num.name());
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 更新主动搭讪次数信息
	 * 
	 * @param tBean
	 */
	private static void updateAccostNum_OUT(T_MyFriends tBean) {
		try {
			db.update(
					tBean,
					WhereBuilder.b(E_DB_MyFriend.mid.name(), "=",
							Dao_SelfIfo.getInstance().getMid()).and(
							E_DB_MyFriend.fid.name(), "=", tBean.getFid()),
					E_DB_MyFriend.accost_out_num.name(),
					E_DB_MyFriend.accost_num.name());
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void updateAccostNum(final String uid,final int identity) {
		if(uid.equals(Dao_SelfIfo.getInstance().getMid())){
			return;
		}
		if (Dao_Friends.checkMyFirendsExit(uid)) {
			if (identity == E_DB_MsgSource.SELF.value()) {
				// 如果是自己搭讪
				updateAccostNum_OUT(uid);
			} else {
				// 来自别人搭讪
				updateAccostNum_IN(uid);
			}
		} else {
			//
			//获取该用户信息同步本地
			Req_UserIfo reqBean=new Req_UserIfo();
			reqBean.setUid(uid);
			reqBean.setId(uid);
			new Http_GetUserIfo(new Call_httpData<Recv_UserIfo>(){

				@Override
				public void onStart() {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onSuccess(Recv_UserIfo datas) {
					// TODO Auto-generated method stub
					final T_MyFriends tBean=new T_MyFriends();
					tBean.setFid(datas.getMid());
					tBean.setBirthDay(datas.getBirthDay());
					tBean.setCurrentState(datas.getCurrentState());
					tBean.setHead(datas.getHead());
					tBean.setLocation(datas.getLocationEffe());
					tBean.setMid(Dao_SelfIfo.getInstance().getMid());
					tBean.setNickName(datas.getNickName());
					tBean.setSex(datas.getSex());
					if (identity == E_DB_MsgSource.SELF.value()) {
						tBean.setAccost_out_num(1);
						//关注该好友
						Req_Follow reqBean=new Req_Follow();
						reqBean.setBeUID(uid);
						reqBean.setUid(Dao_SelfIfo.getInstance().getMid());
						new Http_Follow(new Call_httpData<Class<?>>() {
							
							@Override
							public void onSuccess(Class<?> datas) {
								// TODO Auto-generated method stub
							
							}
							
							@Override
							public void onStart() {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void onFinally() {
								// TODO Auto-generated method stub
								addRecvRecord(tBean);
							}
							
							@Override
							public void onFail() {
								// TODO Auto-generated method stub
								
							}
						}).onParams(reqBean).onAction();
					}else {
						tBean.setAccost_in_num(1);
						addRecvRecord(tBean);
					}
					
//					tBean.setType(E_DB)
				}

				@Override
				public void onFail() {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onFinally() {
					// TODO Auto-generated method stub
					
				}
				
			}).onParams(reqBean).onAction();
		
		}
	}

	/**
	 * 增加和某用户间的被搭讪次数
	 * 
	 * @param uid
	 */
	public static void updateAccostNum_IN(String uid) {
		T_MyFriends tBean = new T_MyFriends();
		tBean.setAccost_in_num(getInfo(uid).getAccost_in_num() + 1);
		tBean.setAccost_num(getInfo(uid).getAccost_num()+1);
		tBean.setFid(uid);
		updateAccostNum_IN(tBean);
	}

	/**
	 * 增加和某用户间的主动搭讪次数
	 * 
	 * @param uid
	 */
	public static void updateAccostNum_OUT(String uid) {
		T_MyFriends tBean = new T_MyFriends();
		tBean.setAccost_out_num(getInfo(uid).getAccost_out_num() + 1);
		tBean.setAccost_num(getInfo(uid).getAccost_num()+1);
		tBean.setFid(uid);
		updateAccostNum_OUT(tBean);
	}

	/**
	 * 增加一条记录
	 * 
	 * @param tBean
	 */
	public static void addRecvRecord(T_MyFriends tBean) {
		if (checkMyFirendsExit(tBean.getFid())) {
			updateInfo(tBean);
			return;
		}
		addRecord(tBean);
	}

	/**
	 * 获取所有朋友信息，按时间顺序，搭讪次数
	 * 
	 * @param index
	 * @return
	 */
	public static List<T_MyFriends> getAllFriends(int index) {
		// TODO Auto-generated method stub
		Selector selector = Selector.from(T_MyFriends.class);
		selector.where(E_DB_MyFriend.mid.name(), "=", Dao_SelfIfo.getInstance()
				.getMid());
		selector.orderBy(E_DB_MyFriend.accost_num.name(), true);
		selector.limit(Enum_ListLimit.FRIEND_LIST.value());
		selector.offset(index * Enum_ListLimit.FRIEND_LIST.value());
		try {
			return db.findAll(selector);
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取所有朋友信息，按时间顺序，搭讪次数
	 * 
	 * @param index
	 * @return
	 */
	public static List<T_MyFriends> getAllFriends() {
		// TODO Auto-generated method stub
		Selector selector = Selector.from(T_MyFriends.class);
		selector.where(E_DB_MyFriend.mid.name(), "=", Dao_SelfIfo.getInstance()
				.getMid());
		// selector.orderBy(E_DB_MyFriend.accost_time.name(),true);
		// selector.limit(Enum_ListLimit.FRIEND_LIST.value());
		// selector.offset(index*Enum_ListLimit.FRIEND_LIST.value());
		try {
			return db.findAll(selector);
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
