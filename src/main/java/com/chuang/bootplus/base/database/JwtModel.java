package com.chuang.bootplus.base.database;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.Tolerate;

/**
 * @author hsy
 * @create 2021-07-19
 * @注意
 */
@Data
@Builder
@AllArgsConstructor
@Accessors(chain = true)
public class JwtModel {
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户账号、appId
     */
    private String account;

    /**
     * 用户名、nickName
     */
    private String userName;

    /**
     * appName、realName
     */
    private String appName;


    @Tolerate
    public JwtModel() {}
}

