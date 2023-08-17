package com.chuang.bootplus.controller;


import com.chuang.bootplus.base.utils.ApiResponse;
import com.chuang.bootplus.po.dynamic.*;
import com.chuang.bootplus.service.DynamicService;
import com.chuang.bootplus.vo.dynamic.DynamicListVO;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/dynamic")
@Api(tags = {"动态"})
@CrossOrigin
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DynamicController {

    private final DynamicService dynamicService;

    @PostMapping("send")
    @ApiOperation(httpMethod = "POST", value = "发布动态")
    @ApiOperationSupport(includeParameters = {"DynamicSendPO.userId", "DynamicSendPO.content","DynamicSendPO.pic","DynamicSendPO.type","DynamicSendPO.comment","DynamicSendPO.privacy"})
    public ApiResponse<Void> dynamicSend(@RequestBody DynamicSendPO po){
        return dynamicService.sendDynamic(po);
    }

    @PostMapping("get")
    @ApiOperation(httpMethod = "POST", value = "获取某个用户动态")
    @ApiOperationSupport(includeParameters = {"DynamicUserListPO.userId","DynamicUserListPO.homeId"},ignoreParameters = {"DynamicUserListPO.id"})
    public ApiResponse<DynamicListVO> dynamicUserList(@RequestBody DynamicUserListPO po){
        return dynamicService.getUserDynamic(po);
    }

    @PostMapping("getFollow")
    @ApiOperation(httpMethod = "POST", value = "获取用户关注动态")
    @ApiOperationSupport(includeParameters = {"DynamicUserListPO.userId"},ignoreParameters = {"DynamicFollowListPO.id"})
    public ApiResponse<DynamicListVO> dynamicFollowList(@RequestBody DynamicFollowListPO po){
        return dynamicService.getFollowDynamic(po);
    }

    @PostMapping("getType")
    @ApiOperation(httpMethod = "POST", value = "获取某类别下的动态")
    @ApiOperationSupport(includeParameters = {"DynamicUserListPO.userId"},ignoreParameters = {"DynamicFollowListPO.id"})
    public ApiResponse<DynamicListVO> dynamicTypeList(@RequestBody DynamicTypeListPO po){
        return dynamicService.getTypeDynamic(po);
    }

    @PostMapping("top")
    @ApiOperation(httpMethod = "POST", value = "置顶动态")
    @ApiOperationSupport(includeParameters = {"DynamicTopPO.dynamicId"})
    public ApiResponse<Void> top(@RequestBody DynamicTopPO po){
        return dynamicService.setTop(po);
    }

    @PostMapping("unTop")
    @ApiOperation(httpMethod = "POST", value = "取消置顶动态")
    @ApiOperationSupport(includeParameters = {"DynamicTopPO.dynamicId"})
    public ApiResponse<Void> unTop(@RequestBody DynamicTopPO po){
        return dynamicService.cancelTop(po);
    }

}

