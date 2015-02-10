package com.hi.utils;



import com.hi.module.base.application.AppContextApplication;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author MM_Zerui
 *
 */
public class DBUtils {
	public static Boolean getSharedPreferences(Context context, String str_note) {
		return context.getSharedPreferences("data", Activity.MODE_PRIVATE)
				.getBoolean(str_note, false);

	}

	public static void setSharedPreferences(Context context, String str_note,
			Boolean bool_note) {
		SharedPreferences.Editor localEditor = context.getSharedPreferences(
				"data", Activity.MODE_PRIVATE).edit();
		localEditor.putBoolean(str_note, bool_note.booleanValue());
		localEditor.commit();
	}

	public static void setSharedPreInt(Context context, String str_note, int m_num) {
		SharedPreferences.Editor localEditor = context.getSharedPreferences(
				"data", Activity.MODE_PRIVATE).edit();
		localEditor.putInt(str_note, m_num);
		localEditor.commit();
	}

	public static int getSharedPreInt(Context context, String str_note) {
		return context.getSharedPreferences("data", Activity.MODE_PRIVATE)
				.getInt(str_note, 0);

	}

	public static void setSharedPreStr(Context context, String str_note, String str) {
		SharedPreferences.Editor localEditor = context.getSharedPreferences(
				"data", Activity.MODE_PRIVATE).edit();
		localEditor.putString(str_note, str);
		localEditor.commit();
	}

	public static String getSharedPreStr(Context context, String str_note,String strDefault) {
		return context.getSharedPreferences("data", Activity.MODE_PRIVATE)
				.getString(str_note, strDefault);
	}

	public static void deleteSharePreFer(Context context) {
		SharedPreferences.Editor localEditor = context.getSharedPreferences(
				"data", Activity.MODE_PRIVATE).edit();
		localEditor.clear();
		localEditor.commit();
	}
	
	/**
	 * @return
	 * 获得本地存储的用户Uid
	 */
	public static String getUid(){
		return DBUtils.getSharedPreStr(AppContextApplication.getInstance(), "mid", "未知ID");
	}
	
	/**
	 * @return
	 * 获得本地存储的个人头像地址
	 */
	public static String getHead(){
		return DBUtils.getSharedPreStr(AppContextApplication.getInstance(), "head", "未知Head");
	}
	
	/**
	 * @return
	 * 获得本人姓名
	 */
	public static String getName(){
		return DBUtils.getSharedPreStr(AppContextApplication.getInstance(), "nickName", "匿名");
	}
	
	/**
	 * @return
	 * 获得本人性别
	 */
	public static String getGender(){
		return DBUtils.getSharedPreStr(AppContextApplication.getInstance(), "gender", "未知性别");
	}
	public static String getBirthday(){
		return DBUtils.getSharedPreStr(AppContextApplication.getInstance(), "birthDay",null);
	}
	
	
	
	/**
	 * 是否允许被搭讪
	 * @return
	 */
	public static String getAccostedEffe(){
		return DBUtils.getSharedPreStr(AppContextApplication.getInstance(), "accostedEffe",null);
	}
	public static String getLocationEffe(){
		return DBUtils.getSharedPreStr(AppContextApplication.getInstance(), "locationEffe", null);
	}
}
