package com.chuang.bootplus.po.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode()
@ApiModel(value = "用户信息修改PO", description = "")
public class UserInfoPO {

    @ApiModelProperty(value = "用户id")
    private Long id;

    @ApiModelProperty(value = "电话号码")
    private String phone;

    @ApiModelProperty(value = "用户昵称")
    private String nickname;

    @ApiModelProperty(value = "性别")
    private String gender;

    @ApiModelProperty(value = "生日")
    private Date birth;

    @ApiModelProperty(value = "QQ号")
    private String qq;

    @ApiModelProperty(value = "微信号")
    private String WeChat;

    @ApiModelProperty(value = "电子邮箱")
    private String email;

    @ApiModelProperty(value = "个人主页")
    private String blog;

    @ApiModelProperty(value = "个人简介")
    private String introduction;

    @ApiModelProperty(value = "是否公开个人信息")
    private Integer privacy;
}
