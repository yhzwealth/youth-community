package com.chuang.bootplus.vo.dynamic;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class DynamicListVO implements Serializable {
    @ApiModelProperty(value = "动态id")
    private Long id;

    @ApiModelProperty(value = "发送人ID")
    private Long userId;

    @ApiModelProperty(value = "发送人昵称")
    private String nickname;

    @ApiModelProperty(value = "发送人头像URL")
    private String avatar;

    @ApiModelProperty(value = "动态内容")
    private String content;

    @ApiModelProperty(value = "配图URL")
    private String pic;

    @ApiModelProperty(value = "点赞数")
    private Long loveNum;

    @ApiModelProperty(value = "当前用户是否点赞")
    private Integer love;

    @ApiModelProperty(value = "评论数")
    private Long commentNum;

    @ApiModelProperty(value = "是否置顶")
    private Integer top;

    @ApiModelProperty(value = "何种动态类型")
    private Integer type;

    @ApiModelProperty(value = "是否可以被评论")
    private Integer comment;

    @ApiModelProperty(value = "可见类型")
    private Integer privacy;

    @ApiModelProperty(value = "留言创建时间")
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "上传时间(转换格式后)")
    private LocalDate gmdCreate;
}
