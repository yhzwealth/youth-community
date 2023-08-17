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
@ApiModel(value = "获取某个分类下的动态", description = "")
public class DynamicTypeListPO extends Page {
    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "动态类别")
    private Integer type;
}
