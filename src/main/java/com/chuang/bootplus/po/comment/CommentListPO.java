package com.chuang.bootplus.po.comment;

import com.chuang.bootplus.base.database.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "获取评论", description = "")
public class CommentListPO extends Page {

    @ApiModelProperty(value = "被评论的动态的ID")
    private Long dynamicId;

    @ApiModelProperty(value = "父级评论的ID")
    private Long pid;
}