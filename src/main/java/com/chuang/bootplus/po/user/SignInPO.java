package com.chuang.bootplus.po.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode()
@ApiModel(value = "用户注册", description = "")
public class SignInPO {
    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码(MD5加密)")
    private String password;

    @ApiModelProperty(value = "用户昵称")
    private String nickname;
}
