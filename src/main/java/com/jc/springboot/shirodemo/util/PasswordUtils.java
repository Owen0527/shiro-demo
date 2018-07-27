package com.jc.springboot.shirodemo.util;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

public class PasswordUtils {
    /**
     *  此加密算法与 shiro验证登录算法保持一致
     * @param password
     * @param salt
     * @return
     */
    public static String simpleHashString(String password, String salt) {
        return simpleHashString(password, salt, 2);
    }


    public static String simpleHashString(String password, String salt, int times) {
        SimpleHash hash = new SimpleHash("MD5", password, salt, times);
        return hash.toString();
    }

    /**
     * 1 numBytes 得出的salt.length = 2
     * @param numBytes
     * @return
     */
    public static String getRandomSalt(int numBytes) {
        SecureRandomNumberGenerator secureRandom = new SecureRandomNumberGenerator();
        return secureRandom.nextBytes(numBytes).toHex();
    }
}
