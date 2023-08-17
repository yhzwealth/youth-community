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
@ApiModel(value="Comment对象", description="")
public class Comment extends EntityBase {

    @ApiModelProperty(value = "被评论的动态的ID")
    private Long dynamicId;

    @ApiModelProperty(value = "评论人ID")
    private Long userId;

    @ApiModelProperty(value = "父级评论的ID")
    private Long pid;

    @ApiModelProperty(value = "评论内容")
    private String content;

}
