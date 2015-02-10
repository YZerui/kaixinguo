package com.hi.utils;

import java.util.Comparator;

import com.hi.module.friend.model.RecvFriendsBean;


/**
 * 
 * @author xiaanming
 *
 */
public class PinyinComparator2 implements Comparator<RecvFriendsBean> {

	public int compare(RecvFriendsBean o1, RecvFriendsBean o2) {
		if (o1.getMid().equals("@")
				|| o2.getMid().equals("#")) {
			return -1;
		} else if (o1.getMid().equals("#")
				|| o2.getMid().equals("@")) {
			return 1;
		} else {
			return o1.getMid().compareTo(o2.getMid());
		}
	}

}
