package com.format.utils;

import java.security.MessageDigest;
import java.util.Random;

public class Md5Util {
	private static MessageDigest md5 = null;
	static {
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * 用于获取一个String的md5值
	 * 
	 * @param string
	 * @return
	 */
	public static String getMd5(String str) {
		byte[] bs = md5.digest(str.getBytes());
		StringBuilder sb = new StringBuilder(40);
		for (byte x : bs) {
			if ((x & 0xff) >> 4 == 0) {
				sb.append("0").append(Integer.toHexString(x & 0xff));
			} else {
				sb.append(Integer.toHexString(x & 0xff));
			}
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(getMd5("hello world"));
	}

	public static String myUUID() {
		StringBuilder sb = new StringBuilder();
		int start = 48, end = 58;
		appendChar(sb, start, end);
		appendChar(sb, 65, 90);
		appendChar(sb, 97, 123);
		String charSet = sb.toString();
		StringBuilder sb1 = new StringBuilder();
		for (int i = 0; i < 24; i++) {
			int len = charSet.length();
			int pos = new Random().nextInt(len);
			sb1.append(charSet.charAt(pos));
		}
		return sb1.toString();
	}

	public static void appendChar(StringBuilder sb, int start, int end) {
		int i;
		for (i = start; i < end; i++) {
			sb.append((char) i);
		}
	}
}
