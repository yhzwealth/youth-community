package com.chuang.bootplus.base.utils;

/**
 * @author lcc
 * @create 2021-05-28
 * @注意 本内容仅限于dev414内部传阅，禁止外泄以及用于其他的商业目的
 */

import cn.hutool.core.bean.copier.CopyOptions;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class BeanUtil {
    public BeanUtil() {
    }

    public static <A, B> B beanA2beanB(A beanA, Class<B> bClass, String... ignoreProperties) {
        try {
            B b = bClass.newInstance();
            cn.hutool.core.bean.BeanUtil.copyProperties(beanA, b, CopyOptions.create().setIgnoreProperties(ignoreProperties).ignoreError().ignoreNullValue());
            return b;
        } catch (Exception var4) {
            var4.printStackTrace();
            return (B) new Object();
        }
    }

    public static <A, B> List<B> listA2ListB(Collection<A> listA, Class<B> bClass, String... ignoreProperties) {
        List<B> listB = new ArrayList();
        if (ObjectUtils.isEmpty(listA)) {
            return listB;
        } else {
            try {
                Iterator var4 = listA.iterator();

                while(var4.hasNext()) {
                    A a = (A) var4.next();
                    listB.add(beanA2beanB(a, bClass, ignoreProperties));
                }
            } catch (Exception var6) {
                var6.printStackTrace();
            }

            return listB;
        }
    }
}
