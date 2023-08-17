package com.chuang.bootplus.base.annotation;

import com.chuang.bootplus.base.utils.ErrorCode;

import java.lang.annotation.*;

/**
 * 接口限流注解
 * @author hsy
 *
 */
@Inherited
@Documented
@Target({ElementType.FIELD,ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface VisitLimit {
    //标识 指定sec时间段内的访问次数限制
    int limit() default 30;
    //标识 时间段
    long sec() default 1000*60;
    //标识 错误信息
    String message() default ErrorCode.RE_MSG_OPERATE_FREQUENTLY;

}

