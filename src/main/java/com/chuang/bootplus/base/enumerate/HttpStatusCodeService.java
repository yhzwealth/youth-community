package com.chuang.bootplus.base.enumerate;

/**
 * @author lcc
 * @create 2021-07-04
 * @注意 本内容仅限于dev414内部传阅，禁止外泄以及用于其他的商业目的
 */
public interface HttpStatusCodeService {
    /**
     * 获取状态码
     * @return 状态码
     */
    String getReCode();
    /**
     * 获取状态信息
     * @return 状态信息
     */
    String getReMsg();
}