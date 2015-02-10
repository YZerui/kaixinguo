package com.hi.module.base.superClass;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

/**
 * @author 瑞泽_Zerui
 * 广播类的超类
 */
public class AlarmBroadcast {
	private Calendar calendar;
	private AlarmManager localAlarmManager;
	private PendingIntent pendingIntent;
	public void init(Context context, Intent intent, String intent_note, int hour, 
			int minute, int second){
		intent.setAction(intent_note);
	    pendingIntent = PendingIntent.getBroadcast(context, 0, intent,
	    		PendingIntent.FLAG_CANCEL_CURRENT);
	    localAlarmManager = (AlarmManager)context.getSystemService("alarm");
	    calendar = Calendar.getInstance();
	    calendar.setTimeInMillis(System.currentTimeMillis());
	    calendar.set(Calendar.HOUR_OF_DAY, hour); // 设置闹钟小时数
		calendar.set(Calendar.MINUTE, minute); // 设置闹钟的分钟数
		calendar.set(Calendar.SECOND, second); // 设置闹钟的秒数
		calendar.set(Calendar.MILLISECOND, 0); // 设置闹钟的毫秒数	
	}
	public void sendBroadCast(Context context, Intent intent, String str_note, int hour, 
			int minute, int second, Long long_interval)
	  {
	    init(context, intent, str_note, hour, minute, second);
	    localAlarmManager.setRepeating(0, calendar.getTimeInMillis(), long_interval, pendingIntent);
	  }
	public void sendBroadCastOnce(Context context, Intent intent, String str_note, int hour, 
			int minute, int second)
	  {
	    init(context, intent, str_note, hour, minute, second);
//	    localAlarmManager.setRepeating(0, calendar.getTimeInMillis(), long_interval, pendingIntent);
	    localAlarmManager.set(0,calendar.getTimeInMillis(),pendingIntent);
	  }
}
