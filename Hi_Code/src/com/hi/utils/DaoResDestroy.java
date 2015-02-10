package com.hi.utils;



import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;

public class DaoResDestroy{
	public static void destroyCurosr(Cursor cursor){
		if(cursor!=null){
			cursor.close();
		}
	}
	public static void destroyDb(SQLiteOpenHelper database){
		if(database!=null){
			database.close();
		}
	}
//	public void destroyCurosr(Cursor cursor){
//		if(cursor!=null){
//			cursor.close();
//		}
//	}
	
}
