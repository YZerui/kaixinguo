package com.hi.utils;

import java.util.Comparator;

import com.hi.dao.model.T_Contacts;
import com.hi.module.friend.model.ContactSortBean;



/**
 * 
 * @author xiaanming
 *
 */
public class PinyinComparator implements Comparator<T_Contacts> {

	public int compare(T_Contacts o1, T_Contacts o2) {
		if (o1.getFirstLetter().equals("@")
				|| o2.getFirstLetter().equals("#")) {
			return -1;
		} else if (o1.getFirstLetter().equals("#")
				|| o2.getFirstLetter().equals("@")) {
			return 1;
		} else {
			return o1.getFirstLetter().compareTo(o2.getFirstLetter());
		}
	}

}
