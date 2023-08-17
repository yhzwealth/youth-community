package com.chuang.bootplus.base.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author hsy
 * @create 2021-07-19
 * @注意
 */
public class StringUtil {

    /**
     * 生成编码
     * @param prefix 前缀
     * @return
     */
    public static String createCode(String prefix) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String s = simpleDateFormat.format(new Date());
        String s1 = sixCode();
        return prefix + s + s1;
    }

    /**
     * 生成 6 位验证码
     * @return 字符串
     */
    public static String sixCode() {
        Random random = new Random();
        String randoms = "";
        for (int i = 0; i < 6; i++ ) {
            randoms += random.nextInt(10);
        }
        return randoms;
    }

    /**
     * 生成 6 位验证码
     * @return 数组
     */
    public static String[] sixCodeArray() {
        Random random = new Random();
        String randoms = "";
        for (int i = 0; i < 6; i++) {
            randoms += random.nextInt(10);
        }
        return randoms.split(",");
    }


    /**
     * 判断字符串是否为空
     *
     * @param str null、“ ”、“null”都返回true
     * @return
     */
    public static boolean isNullString(String str) {
        return (null == str || StringUtils.isBlank(str.trim()) || "null".equals(str.trim().toLowerCase())) ? true : false;
    }

}
