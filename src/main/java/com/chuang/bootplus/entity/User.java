package com.chuang.bootplus.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;

import com.chuang.bootplus.base.database.EntityBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="User对象", description="")
public class User extends EntityBase {

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码(MD5加密)")
    private String password;

    @ApiModelProperty(value = "电话号码")
    private String phone;

    @ApiModelProperty(value = "用户昵称")
    private String nickname;

    @ApiModelProperty(value = "头像URL")
    private String avatar;

    @ApiModelProperty(value = "背景URL")
    private String background;

    @ApiModelProperty(value = "性别")
    private String gender;

    @ApiModelProperty(value = "生日")
    private Date birth;

    @ApiModelProperty(value = "QQ号")
    @TableField("QQ")
    private String qq;

    @ApiModelProperty(value = "微信号")
    @TableField("WeChat")
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
