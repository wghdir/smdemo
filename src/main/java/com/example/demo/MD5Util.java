//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.demo;

import java.security.MessageDigest;

public class MD5Util {
    private static char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public MD5Util() {
    }

    public static String md5_(String str) {
        MessageDigest md5 = null;

        try {
            md5 = MessageDigest.getInstance("MD5");
            if (md5 != null) {
                md5.update(str.getBytes("utf-8"));
                byte[] encodeBytes = md5.digest();
                char[] encodeStr = new char[encodeBytes.length * 2];
                int k = 0;

                for(int i = 0; i < encodeBytes.length; ++i) {
                    byte encodeByte = encodeBytes[i];
                    encodeStr[k++] = hexDigits[encodeByte >> 4 & 15];
                    encodeStr[k++] = hexDigits[encodeByte & 15];
                }

                return (new String(encodeStr)).toUpperCase();
            }
        } catch (Exception var7) {
            var7.printStackTrace();
        }

        return null;
    }
}
