package com.chuang.bootplus.po.dynamic;

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
@ApiModel(value = "发布动态", description = "")
public class DynamicSendPO {
    @ApiModelProperty(value = "发送人ID")
    private Long userId;

    @ApiModelProperty(value = "发送内容")
    private String content;

    @ApiModelProperty(value = "配图URL")
    private String pic;

    @ApiModelProperty(value = "何种动态类型")
    private Integer type;

    @ApiModelProperty(value = "是否可以被评论")
    private Integer comment;

    @ApiModelProperty(value = "可见类型")
    private Integer privacy;
}
