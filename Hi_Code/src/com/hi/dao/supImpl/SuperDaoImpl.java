package com.hi.dao.supImpl;

import com.hi.module.base.application.AppContextApplication;
import com.lidroid.xutils.DbUtils;

/**
 * daoʵ�ֲ�ĳ��࣬������ݴ洢���ߵĳ�ʼ��
 * @author MM_Zerui
 *
 */
public class SuperDaoImpl {
	protected static DbUtils db;
	static{
		// TODO Auto-generated constructor stub
		db = DbUtils.create(AppContextApplication.getInstance());
		db.configAllowTransaction(true);
		db.configDebug(true);
	}
}
