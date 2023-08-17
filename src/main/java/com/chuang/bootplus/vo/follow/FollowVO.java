package com.chuang.bootplus.vo.follow;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
public class FollowVO implements Serializable {

    @ApiModelProperty(value = "关注者的id")
    private Long id;

}