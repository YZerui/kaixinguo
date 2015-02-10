package com.hi.dao.supImpl;

import com.hi.module.base.application.AppContextApplication;
import com.lidroid.xutils.DbUtils;

/**
 * dao实现层的超类，完成数据存储工具的初始化
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
