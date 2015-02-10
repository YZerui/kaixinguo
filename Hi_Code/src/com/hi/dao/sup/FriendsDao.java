package com.hi.dao.sup;

import java.util.List;

import com.hi.dao.model.T_MyFriends;
import com.hi.module.friend.model.RecvFriendsBean;

/**
 * �����ҵ�������Ϣ��dao����
 * 
 * @author MM_Zerui
 * 
 */
public interface FriendsDao {
	/**
	 * ��鱾���Ƿ�������Ѽ�¼
	 * 
	 * @return
	 */
	public boolean checkMyFriendsExist();

	/**
	 * ��������������Ϣ
	 * @tip ����ǰ���֮ǰ�ļ�¼
	 * @param listBean
	 */
	public void insertAllFriends(List<T_MyFriends> listBean);

	/**
	 * �������������Ϣ
	 * 
	 * @return
	 */
	public List<T_MyFriends> getAllFriends();

	/**
	 * ��֤�Ƿ�Ϊ�Է��ķ�˿
	 * 
	 * @param uid
	 *            �Է�Ψһ��ʶ
	 * @return
	 */
	public boolean checkFollowRelate(String uid);

	/**
	 * �������Ѽ�¼(�����Լ�mid������uid�������߹�ϵ��Ϣ)
	 * @tip_1 ����ǰ���ü�¼������ü�¼����������Ӹù�ϵ
	 * @tip_2 ����ü�¼�������޸ĸü�¼��ϵ
	 * @param relate
	 *            ���߹�ϵ
	 * @param uid
	 *            �Է�Ψһ��ʶ
	 */
	public void updateFriendRelate(int relate,String uid);

	/**
	 * ����ĳλ���ѵ���Ϣ
	 * 
	 * @param bean
	 */
	public void updateMyFriend(T_MyFriends bean);

//	/**
//	 * ɾ���������ѵ���Ϣ
//	 */
//	public void deleteFriends();
	//
	// /**
	// * ���ָ������ʱ��ǰ��������Ϣ
	// * @param timeValue
	// * ָ���ĸ���ʱ���
	// * @return
	// */
	// public List<T_MyFriends> getAllFriends(String timeValue);
	//
	//
	//
	//
	// /**
	// * ���ĳλ���ѵ���Ϣ
	// * @param uid
	// * �Է���Ψһ��ʶ
	// * @return
	// */
	// public boolean getFriendIfo(String uid);
	//

}
