package com.chuang.bootplus.vo.love;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class LoveVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "点赞id")
    private Long id;
}
