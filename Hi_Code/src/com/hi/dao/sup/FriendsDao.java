package com.hi.dao.sup;

import java.util.List;

import com.hi.dao.model.T_MyFriends;
import com.hi.module.friend.model.RecvFriendsBean;

/**
 * 管理我的朋友信息的dao超类
 * 
 * @author MM_Zerui
 * 
 */
public interface FriendsDao {
	/**
	 * 检查本地是否存在朋友记录
	 * 
	 * @return
	 */
	public boolean checkMyFriendsExist();

	/**
	 * 插入所有朋友信息
	 * @tip 插入前清除之前的记录
	 * @param listBean
	 */
	public void insertAllFriends(List<T_MyFriends> listBean);

	/**
	 * 获得所有朋友信息
	 * 
	 * @return
	 */
	public List<T_MyFriends> getAllFriends();

	/**
	 * 验证是否为对方的粉丝
	 * 
	 * @param uid
	 *            对方唯一标识
	 * @return
	 */
	public boolean checkFollowRelate(String uid);

	/**
	 * 更新朋友记录(根据自己mid和朋友uid更新两者关系信息)
	 * @tip_1 插入前检测该记录，如果该记录不存在则添加该关系
	 * @tip_2 如果该记录存在则修改该记录关系
	 * @param relate
	 *            两者关系
	 * @param uid
	 *            对方唯一标识
	 */
	public void updateFriendRelate(int relate,String uid);

	/**
	 * 更新某位朋友的信息
	 * 
	 * @param bean
	 */
	public void updateMyFriend(T_MyFriends bean);

//	/**
//	 * 删除所有朋友的信息
//	 */
//	public void deleteFriends();
	//
	// /**
	// * 获得指定更新时间前的朋友信息
	// * @param timeValue
	// * 指定的更新时间戳
	// * @return
	// */
	// public List<T_MyFriends> getAllFriends(String timeValue);
	//
	//
	//
	//
	// /**
	// * 获得某位朋友的信息
	// * @param uid
	// * 对方的唯一标识
	// * @return
	// */
	// public boolean getFriendIfo(String uid);
	//

}
