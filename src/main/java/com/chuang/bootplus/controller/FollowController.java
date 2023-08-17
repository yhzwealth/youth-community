package com.chuang.bootplus.controller;


import com.chuang.bootplus.base.utils.ApiResponse;
import com.chuang.bootplus.po.follow.FollowListPO;
import com.chuang.bootplus.po.follow.FollowPO;
import com.chuang.bootplus.service.FollowService;
import com.chuang.bootplus.vo.follow.FollowListVO;
import com.chuang.bootplus.vo.follow.FollowVO;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@Api(tags = {"关注"})
@RequestMapping("/follow")
@CrossOrigin
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FollowController {

    private final FollowService followService;

    @PostMapping("follow")
    @ApiOperation(httpMethod = "POST", value = "关注")
    @ApiOperationSupport(includeParameters = {"FollowPO.userId", "FollowPO.fanId"})
    public ApiResponse<FollowVO> follow(@RequestBody FollowPO po){
        return followService.follow(po);
    }

    @PostMapping("unfollow")
    @ApiOperation(httpMethod = "POST", value = "取消关注")
    @ApiOperationSupport(includeParameters = {"UnFollowPO.userId", "UnFollowPO.fanId"})
    public ApiResponse<Void> unfollow(@RequestBody FollowPO po){
        return followService.unfollow(po);
    }

    @PostMapping("getFollowUser")
    @ApiOperation(value = "获取关注的用户",httpMethod = "POST")
    public ApiResponse<FollowListVO> getFollowUser(@RequestBody FollowListPO po){
        return followService.getFollowUser(po);
    }


    @PostMapping("getAllFans")
    @ApiOperation(value = "获取粉丝",httpMethod = "POST")
    public ApiResponse<FollowListVO> getFans(@RequestBody FollowListPO po){
        return followService.getFans(po);
    }



}

