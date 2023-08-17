package com.chuang.bootplus.base.constant;

import cn.hutool.core.date.DateUnit;


import java.time.ZoneId;

/**
 * @author lcc
 * @create 2021-07-08
 * @注意 本内容仅限于xinou内部传阅，禁止外泄以及用于其他的商业目的
 */
public class Constant {
    /**
     * dataList 常量值
     */
    public static final String DATA_LIST = "dataList";
    /**
     * dataInfo 常量值
     */
    public static final String DATA_INFO = "dataInfo";
    /**
     * total 常量值
     */
    public static final String TOTAL = "total";
    /**
     * 当 咨讯、活动等 Topic 处于关闭时的显示文本常量
     */
    public static final String TOPIC_CLOSE_TEXT = "抱歉，此{}已被删除。";
    /**
     * 当问价过大时常量
     */
    public static final String FILE_TO_BIG = "文件过大 限制为:[{}]MB";
    /**
     * 上海时区常量
     */
    public static final ZoneId SHANG_HAI = ZoneId.of("Asia/Shanghai");
    /**
     * aes key
     */
    public static final String AES_KEY = "xxxxxxxxxxxxxxxx";
    /**
     * web jwt 失效时间
     */
    public static final Integer WEB_JWT_TIME = 1;
    /**
     * sys jwt 失效时间
     */
    public static final Integer SYS_JWT_TIME = 1;
    /**
     * web jwt 失效时间单位
     */
    public static final DateUnit WEB_JWT_TIME_UNIT = DateUnit.DAY;
    /**
     * sys jwt 失效时间单位
     */
    public static final DateUnit SYS_JWT_TIME_UNIT = DateUnit.DAY;

    /**
     * 默认jwt有效时间
     */
    public static final String JWT_VALID_TIME = "7d";

    /**
     * jwt默认签证
     */
    public static final String JWT_AUDIENCE = "matrix";

    /**
     * 默认盐加密
     */
    public static final String DEFAULT_SALT = "matrix_plus";

    /*
    * followed参数  0代表当前用户未关注该用户
    * */
    public static final Integer FOLLOW_FALSE = 0;

    /*
     * followed参数  1代表当前用户关注该用户
     * */
    public static final Integer FOLLOW_TRUE = 1;


}
