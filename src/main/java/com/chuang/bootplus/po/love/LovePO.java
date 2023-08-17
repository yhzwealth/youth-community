package com.chuang.bootplus.po.love;

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
@ApiModel(value = "用户点赞", description = "")
public class LovePO {
    @ApiModelProperty(value = "被点赞的动态的ID")
    private Long dynamicId;

    @ApiModelProperty(value = "点赞人ID")
    private Long userId;
}
