package com.hi.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 判断星座的工具
 * 
 * @author MM_Zerui
 * 
 */
public class XingZuo {
	public static String getXingZuo(String timeValue) {
		try {
			if (rangeInDefined(getDateValue(timeValue),
					getDateValueMD("03-21"), getDateValueMD("04-19"))) {
				return "白羊座";
			} else if (rangeInDefined(getDateValue(timeValue),
					getDateValueMD("04-20"), getDateValueMD("05-20"))) {
				return "金牛座";
			} else if (rangeInDefined(getDateValue(timeValue),
					getDateValueMD("05-21"), getDateValueMD("06-21"))) {
				return "双子座";
			} else if (rangeInDefined(getDateValue(timeValue),
					getDateValueMD("06-22"), getDateValueMD("07-22"))) {
				return "巨蟹座";
			} else if (rangeInDefined(getDateValue(timeValue),
					getDateValueMD("07-23"), getDateValueMD("08-22"))) {
				return "狮子座";
			} else if (rangeInDefined(getDateValue(timeValue),
					getDateValueMD("08-23"), getDateValueMD("09-22"))) {
				return "处女座";
			} else if (rangeInDefined(getDateValue(timeValue),
					getDateValueMD("09-23"), getDateValueMD("10-23"))) {
				return "天秤座";
			} else if (rangeInDefined(getDateValue(timeValue),
					getDateValueMD("10-24"), getDateValueMD("11-22"))) {
				return "天蝎座";
			} else if (rangeInDefined(getDateValue(timeValue),
					getDateValueMD("11-23"), getDateValueMD("12-21"))) {
				return "射手座";
			}

			else if (rangeInDefined(getDateValue(timeValue),
					getDateValueMD("01-20"), getDateValueMD("02-18"))) {
				return "水瓶座";
			} else if (rangeInDefined(getDateValue(timeValue),
					getDateValueMD("02-19"), getDateValueMD("03-20"))) {
				return "双鱼座";

			} else {
				return "魔蝎座";
			}
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	public static boolean rangeInDefined(Long current, Long min, Long max) {
		return Math.max(min, current) == Math.min(current, max);
	}

	public static Long getDateValue(String timeStr) {

		return getDateValueMD(getDateTime(timeStr));
	}

	/**
	 * @param timeValue
	 * @return 将时间戳转化为时间
	 */
	public static String getDateTime(String timeValue) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
		String date = sdf.format(new Date(Long.valueOf(timeValue)));
		return date;
	}

	/**
	 * 将时间转化为时间戳（生日类型）
	 * 
	 * @param timeStr
	 * @return
	 */
	public static Long getDateValueMD(String timeStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
		Date date;
		try {
			date = sdf.parse(timeStr);
			long value = date.getTime();
			return value;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
