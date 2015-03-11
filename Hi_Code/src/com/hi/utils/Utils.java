package com.hi.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * User: Zerui
 * Time: 2015/3/9 19:55
 */
public class Utils {
    public static String uuid() {
        //return UUID.randomUUID().toString().substring(0, 24);
        return myUUID();
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
    /**
     * »ñÈ¡md5Âë
     * @param string
     * @return
     */
    public static String md5(String string) {
        byte[] hash = null;
        try {
            hash = string.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh,UTF-8 should be supported?", e);
        }
        return computeMD5(hash);
    }
    public static String computeMD5(byte[] input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input, 0, input.length);
            byte[] md5bytes = md.digest();

            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < md5bytes.length; i++) {
                String hex = Integer.toHexString(0xff & md5bytes[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
