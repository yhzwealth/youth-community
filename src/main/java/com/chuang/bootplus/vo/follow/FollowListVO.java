package com.chuang.bootplus.vo.follow;

import com.chuang.bootplus.vo.user.UserInfoVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class FollowListVO {

    @ApiModelProperty(value = "用户信息")
    private UserInfoVO userInfoVO;

}
