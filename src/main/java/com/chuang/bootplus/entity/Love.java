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
@ApiModel(value="Love对象", description="")
public class Love extends EntityBase {

    @ApiModelProperty(value = "被点赞的动态ID")
    private Long dynamicId;

    @ApiModelProperty(value = "点赞用户的用户名")
    private Long userId;

}
