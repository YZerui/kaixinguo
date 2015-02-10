package com.hi.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.format.utils.DataValidate;
import com.hi.dao.model.T_Contacts;
import com.hi.module.base.application.AppContextApplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class FormatUtils {
	public static int paseBool2Int(Boolean bool) {
		if (bool) {
			return 1;
		}
		return 0;
	}

	public static String paseBool2Str(Boolean bool) {
		if (bool) {
			return "1";
		}
		return "0";
	}

	public static String getDateTime() {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm");
		return format.format(date);
	}

	/**
	 * ��ʱ��ת��Ϊʱ�����Ŀǰʱ�䣩
	 * 
	 * @param timeStr
	 * @return
	 */
	public static long getCurrentDateValue_long() {
		Date date = new Date();
		long value = date.getTime();
		return value;
	}

	/**
	 * ��ý�������ڣ��¡����գ�
	 * 
	 * @return
	 */
	public static String getTodayDateTime_M_D() {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("MM-dd");
		return format.format(date);
	}

	/**
	 * @param timeValue
	 * @return ��ʱ���ת��Ϊʱ�䣨�¡����գ�
	 */
	public static String getDateTime_M_D(String timeValue) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
		String date = sdf.format(new Date(Long.valueOf(timeValue)));
		return date;
	}

	/**
	 * @param timeValue
	 * @return ��ʱ���ת��Ϊʱ�䣨ʱ�����֣�
	 */
	public static String getDateTime_H_M(String timeValue) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		String date = sdf.format(new Date(Long.valueOf(timeValue)));
		return date;
	}

	/**
	 * @param timeValue
	 * @return ��ʱ���ת��Ϊʱ��
	 */
	public static String getDateTime(String timeValue) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
		String date = sdf.format(new Date(Long.valueOf(timeValue)));
		return date;
	}
	/**
	 * ��ȡ�б��е�ʱ��
	 *  @˵�� ��ʱ���ת��Ϊ�ض���ʽʱ��
	 * @return
	 */
	public static String getListItemTime(String timeValue){
		if(!DataValidate.checkDataValid(timeValue)){
			return "";
		}
		if(getDateTime_BIRTHDAY(timeValue).equals(getTodayDateTime_Y_M_D())){
			return getDateTime_H_m(timeValue);
		}
		return getDateTime_M_D(timeValue);
	}
	/**
	 * @param timeValue
	 * @return ��ʱ���ת��Ϊʱ�䣨ʱ�����֣�
	 */
	public static String getDateTime_H_m(String timeValue) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		String date = sdf.format(new Date(Long.valueOf(timeValue)));
		return date;
	}
	/**
	 * ��ý�������ڣ��ꡪ���¡����գ�
	 * @return
	 */
	public static String getTodayDateTime_Y_M_D(){
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
	}
	
	/**
	 * @param timeValue
	 * @return ��ʱ���ת��Ϊ���
	 */
	public static String getDateTime_YEAR(String timeValue) {
		if (timeValue == null || timeValue.equals("")) {
			return "0";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String date = sdf.format(new Date(Long.valueOf(timeValue)));
		return date;
	}

	public static String getAge(String timeValue) {
		try {
			int age = Integer.valueOf(getDateTime_YEAR(getCurrentDateValue()))
					- Integer.valueOf(getDateTime_YEAR(timeValue));
			return String.valueOf(age);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		

	}

	/**
	 * @param timeValue
	 * @return ��ʱ���ת��Ϊ����
	 */
	public static String getDateTime_BIRTHDAY(String timeValue) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String date = sdf.format(new Date(Long.valueOf(timeValue)));
			return date;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}

	}

	/**
	 * ��ʱ��ת��Ϊʱ������������ͣ�
	 * 
	 * @param timeStr
	 * @return
	 */
	public static String getDateValueBirthday(String timeStr) {
		if (timeStr == null || timeStr.equals("")) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = sdf.parse(timeStr);
			long value = date.getTime();
			return String.valueOf(value);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ��ʱ��ת��Ϊʱ�����Ŀǰʱ�䣩
	 * 
	 * @param timeStr
	 * @return
	 */
	public static String getCurrentDateValue() {
		Date date = new Date();
		long value = date.getTime();
		return String.valueOf(value);
	}

	/**
	 * ����������
	 * 
	 * @return
	 */
	public static String[] getYearArrays() {
		String[] array = new String[120];
		for (int i = 1900; i < 2020; i++) {
			array[i - 1900] = String.valueOf(i) + "��";
		}
		return array;
	}

	public static int getYearItem(int year) {
		return (year - 1900) < 0 ? 0 : (year - 1900);
	}

	public static int getMonthItem(int month) {
		return (month - 1) < 0 ? 0 : (month - 1);
	}

	public static int getDayItem(int day) {
		return (day - 1) < 0 ? 0 : (day - 1);
	}

	/**
	 * ����·�����
	 * 
	 * @return
	 */
	public static String[] getMonthArrays() {
		String[] array = new String[12];
		for (int i = 1; i <= 12; i++) {
			if (i < 10) {
				array[i - 1] = "0" + String.valueOf(i) + "��";
			} else {
				array[i - 1] = String.valueOf(i) + "��";
			}

		}
		return array;
	}

	/**
	 * ���������
	 * 
	 * @return
	 */
	public static String[] getDayArrays() {
		String[] array = new String[31];
		for (int i = 1; i <= 31; i++) {
			if (i < 10) {
				array[i - 1] = "0" + String.valueOf(i) + "��";
			} else {
				array[i - 1] = String.valueOf(i) + "��";
			}

		}
		return array;
	}

	/** drawable תΪ bitmap **/
	public static Bitmap drawableToBitamp(int drawable) {
		Bitmap bmp = BitmapFactory.decodeResource(AppContextApplication
				.getInstance().getResources(), drawable);
		return bmp;
	}

	/** ���ֻ������˳���ת��Ϊָ����ʽ���ַ��� **/
	public static String changeArrayPhoneType(List<T_Contacts> list) {
		String phone = new String();
		for (T_Contacts item : list) {
			phone += item.getPhone() + ";";
		}
		return phone;
	}
}
