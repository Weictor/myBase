package com.base.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Create by 俞智威
 * on 2016-11-18
 * 15:52
 * Procedure Explain: MD5加密工具类
 */

public class MD5Util {

    /**
     * md5 16位 加密
     *
     * @param plainText
     * @return
     */
    public static String Md5_16(String plainText) {
        StringBuffer buf = new StringBuffer("");
        int i;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();

            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }


        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return buf.toString().substring(8, 24);
    }

    /**
     * md5 32位 加密
     *
     * @param plainText
     */
    public static String Md5_32(String plainText) {
        StringBuffer buf = new StringBuffer("");
        int i;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();

            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }


        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return buf.toString();
    }
}
