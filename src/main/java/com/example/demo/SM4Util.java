//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.demo;


import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Security;

public class SM4Util {
    public SM4Util() {
    }

    public static String encrypt(String content, String password) {
        try {
            if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
                BouncyCastleProvider bp = new BouncyCastleProvider();
                Security.addProvider(bp); //注册BouncyCastleProvider
            }
            String passwordMD5 = MD5Util.md5_(password).toUpperCase().substring(8, 24);
            SecretKeySpec key = new SecretKeySpec(passwordMD5.getBytes(), "SM4");
            byte[] initParam = "abcdefghABCDEFGH".getBytes();
            IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);
            Cipher cipher;
            try {
                cipher = Cipher.getInstance("SM4/CBC/PKCS5Padding",Security.getProvider(BouncyCastleProvider.PROVIDER_NAME));//, BouncyCastleProvider.PROVIDER_NAME);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(1, key, ivParameterSpec);
            byte[] result = cipher.doFinal(byteContent);
            return Base64.encode(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decrypt(String content, String password) {
        try {
            if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
                BouncyCastleProvider bp = new BouncyCastleProvider();
                Security.addProvider(bp); //注册BouncyCastleProvider
            }
            String passwordMD5 = MD5Util.md5_(password).toUpperCase().substring(8, 24);
            SecretKeySpec key = new SecretKeySpec(passwordMD5.getBytes(), "SM4");
            Cipher cipher = null;
            try {
                cipher = Cipher.getInstance("SM4/CBC/PKCS5Padding",Security.getProvider(BouncyCastleProvider.PROVIDER_NAME));//, BouncyCastleProvider.PROVIDER_NAME);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
            byte[] initParam = "abcdefghABCDEFGH".getBytes();
            IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);
            cipher.init(2, key, ivParameterSpec);
            byte[] result = cipher.doFinal(Base64.decode(content));
            return new String(result, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
