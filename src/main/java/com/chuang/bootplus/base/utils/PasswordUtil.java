package com.chuang.bootplus.base.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author hsy
 * @create 2021-07-19
 * @注意
 */
public class PasswordUtil {
    /**
     * 密码加密
     * @param password 原始密码
     * @param salt 盐值（UUID.randomUUID().toString();）
     * @return 加密后的密码
     */
    public static String passwordEncoderByMd5(String password, String salt) {
        String value = salt + password + salt;
        return DigestUtils.md5Hex(value);
    }
}
