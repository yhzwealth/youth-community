package com.chuang.bootplus.base.database;

/**
 * @author lcc
 * @create 2021-07-14
 * @注意 本内容仅限于xinou内部传阅，禁止外泄以及用于其他的商业目的
 */
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BasePO implements Serializable {

    @ApiModelProperty(value = "主键id")
    private Long id;
}
