package com.chuang.bootplus.vo.comment;

import com.chuang.bootplus.base.database.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class CommentListVO{

    @ApiModelProperty(value = "评论id")
    private Long id;

    @ApiModelProperty(value = "被评论的动态的ID")
    private Long dynamicId;

    @ApiModelProperty(value = "评论人ID")
    private Long userId;

    @ApiModelProperty(value = "评论人头像URL")
    private String avatar;

    @ApiModelProperty(value = "评论人昵称")
    private String nickname;

    @ApiModelProperty(value = "父级评论的ID")
    private Long pid;

    @ApiModelProperty(value = "评论内容")
    private String content;

    @ApiModelProperty(value = "留言创建时间")
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "上传时间(转换格式后)")
    private LocalDate gmdCreate;
}
