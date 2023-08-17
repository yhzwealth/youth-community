package com.chuang.bootplus.service;

import com.chuang.bootplus.base.utils.ApiResponse;
import com.chuang.bootplus.entity.Follow;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chuang.bootplus.po.follow.FollowListPO;
import com.chuang.bootplus.po.follow.FollowPO;
import com.chuang.bootplus.vo.follow.FollowListVO;
import com.chuang.bootplus.vo.follow.FollowVO;
import com.chuang.bootplus.vo.user.UserInfoVO;

public interface FollowService extends IService<Follow> {


    /***
     * @param po
     * @return 关注者的id
     */
    ApiResponse<FollowVO> follow(FollowPO po);

    ApiResponse<Void> unfollow(FollowPO po);

    ApiResponse<FollowListVO> getFollowUser(FollowListPO po);

    ApiResponse<FollowListVO> getFans(FollowListPO po);
}
