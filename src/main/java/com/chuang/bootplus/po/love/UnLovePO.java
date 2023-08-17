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
@ApiModel(value = "用户取消点赞", description = "")
public class UnLovePO {
    @ApiModelProperty(value = "点赞ID")
    private Long loveId;
}
