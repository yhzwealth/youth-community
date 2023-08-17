package com.chuang.bootplus.po.comment;

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
@ApiModel(value = "发布评论", description = "")
public class CommentSendPO {

    @ApiModelProperty(value = "被评论的动态的ID")
    private Long dynamicId;

    @ApiModelProperty(value = "评论人ID")
    private Long userId;

    @ApiModelProperty(value = "父级评论的ID")
    private Long pid;

    @ApiModelProperty(value = "评论内容")
    private String content;
}
