package com.chuang.bootplus.vo.user;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Accessors(chain = true)
public class UserInfoVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    private Long id;

    @ApiModelProperty(value = "用户名")
    private String username;

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
    private Boolean privacy;

    @ApiModelProperty(value = "是否已经关注")
    private Integer followed;

    @ApiModelProperty(value = "关注数")
    private Integer follow;

    @ApiModelProperty(value = "粉丝数")
    private Integer fan;

    @ApiModelProperty(value = "账号创建时间")
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "创建时间(转换格式后)")
    private LocalDate gmdCreate;
}
