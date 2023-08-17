package com.chuang.bootplus.po.follow;


import com.chuang.bootplus.base.database.Page;
import com.chuang.bootplus.base.utils.ApiResponse;
import com.chuang.bootplus.vo.user.UserInfoVO;
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
@ApiModel(value = "获取所有关注的用户/粉丝", description = "")
public class FollowListPO extends Page {
    @ApiModelProperty(value = "用户id")
    private Long userId;
}
