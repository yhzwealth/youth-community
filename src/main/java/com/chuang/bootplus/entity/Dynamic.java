package com.chuang.bootplus.entity;

import com.chuang.bootplus.base.database.EntityBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Dynamic对象", description="")
public class Dynamic extends EntityBase {

    @ApiModelProperty(value = "发送人用户名")
    private Long userId;

    @ApiModelProperty(value = "发送内容")
    private String content;

    @ApiModelProperty(value = "配图URL")
    private String pic;

    @ApiModelProperty(value = "是否置顶")
    private Integer top;

    @ApiModelProperty(value = "何种动态类型")
    private Integer type;

    @ApiModelProperty(value = "是否可以被评论")
    private Integer comment;

    @ApiModelProperty(value = "可见类型")
    private Integer privacy;

}
