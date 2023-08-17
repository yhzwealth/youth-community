package com.chuang.bootplus.po.user;

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
@ApiModel(value = "URL更换", description = "")
public class UserURLPO {
    @ApiModelProperty(value = "用户id")
    private Long id;

    @ApiModelProperty(value = "URL")
    private String url;
}
