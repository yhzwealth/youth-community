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
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "获取某个用户全部可见动态", description = "")
public class DynamicUserListPO extends Page {
    @ApiModelProperty(value = "用户ID(一般为登陆者本人)")
    private Long userId;

    @ApiModelProperty(value = "被访问人用户ID")
    private Long homeId;
}
