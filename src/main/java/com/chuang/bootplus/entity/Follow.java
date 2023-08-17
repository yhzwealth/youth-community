package com.chuang.bootplus.entity;

import com.chuang.bootplus.base.database.EntityBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Follow对象", description="")
public class Follow extends EntityBase {

    @ApiModelProperty(value = "被关注人用户名")
    private Long userId;

    @ApiModelProperty(value = "粉丝用户名")
    private Long fanId;

    @ApiModelProperty(value = "备注")
    private String remark;

}
