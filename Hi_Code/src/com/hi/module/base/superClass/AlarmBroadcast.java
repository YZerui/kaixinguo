package com.hi.module.base.superClass;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

/**
 * @author ����_Zerui
 * �㲥��ĳ���
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
	    calendar.set(Calendar.HOUR_OF_DAY, hour); // ��������Сʱ��
		calendar.set(Calendar.MINUTE, minute); // �������ӵķ�����
		calendar.set(Calendar.SECOND, second); // �������ӵ�����
		calendar.set(Calendar.MILLISECOND, 0); // �������ӵĺ�����	
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
