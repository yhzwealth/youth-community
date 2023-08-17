package com.chuang.bootplus.po.dynamic;

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
@EqualsAndHashCode()
@ApiModel(value = "获取关注人动态", description = "")
public class DynamicFollowListPO extends Page {
    @ApiModelProperty(value = "用户ID")
    private Long userId;
}
