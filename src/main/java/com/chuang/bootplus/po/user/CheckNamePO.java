package com.chuang.bootplus.po.user;

import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.experimental.Accessors;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode()
@ApiModel(value = "用户名检测", description = "")
public class CheckNamePO {

    private String username;
}
