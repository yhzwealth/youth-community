package com.chuang.bootplus.base.utils;

/**
 * @author hsy
 * @create 2021-07-19
 * @注意
 */

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/**
 * 对象工具类
 *
 * @author hubin
 * @since 2018-06-05
 */
public class ObjectUtil {

    /**
     * 判断object是否为空,集合会校验size
     */
    public static boolean isNull(Object... objs) {
        for (Object obj : objs) {
            if (com.baomidou.mybatisplus.core.toolkit.ObjectUtils.isEmpty(obj)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断object是否不为空,集合会校验size
     */
    public static boolean isNotNull(Object... obj) {
        return !com.baomidou.mybatisplus.core.toolkit.ObjectUtils.isNull(obj);
    }

    /**
     * 对象非空判断
     */
    public static boolean isNotEmpty(Object obj) {
        return !com.baomidou.mybatisplus.core.toolkit.ObjectUtils.isEmpty(obj);
    }

    /**
     * 对象空判断
     */
    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }

        if (obj.getClass().isArray()) {
            return Array.getLength(obj) == 0;
        }
        if (obj instanceof CharSequence) {
            return ((CharSequence) obj).length() == 0;
        }
        if (obj instanceof Collection) {
            return ((Collection<?>) obj).isEmpty();
        }
        if (obj instanceof Map) {
            return ((Map<?, ?>) obj).isEmpty();
        }
        // else
        return false;
    }
}
