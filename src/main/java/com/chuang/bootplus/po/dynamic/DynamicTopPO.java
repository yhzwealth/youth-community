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
@ApiModel(value = "处理置顶动态", description = "")
public class DynamicTopPO {
    @ApiModelProperty(value = "动态ID")
    private Long dynamicId;
}
