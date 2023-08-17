package com.chuang.bootplus.po.follow;

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
@ApiModel(value = "用户关注与否", description = "")
public class FollowPO {
    @ApiModelProperty(value = "被关注的用户的ID")
    private Long userId;

    @ApiModelProperty(value = "关注人ID")
    private Long fanId;
}