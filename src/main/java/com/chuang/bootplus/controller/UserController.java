package com.chuang.bootplus.controller;


import com.chuang.bootplus.base.utils.ApiResponse;
import com.chuang.bootplus.po.user.*;
import com.chuang.bootplus.service.UserService;
import com.chuang.bootplus.vo.user.UserInfoVO;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Api(tags = {"用户信息"})
@CrossOrigin
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserService userService;

    @PostMapping("login")
    @ApiOperation(httpMethod = "POST", value = "用户登陆")
    @ApiOperationSupport(includeParameters = {"LoginPO.username", "LoginPO.password"})
    public ApiResponse<UserInfoVO> userLogin(@RequestBody LoginPO po){
        return userService.login(po);
    }

    @PostMapping("signIn")
    @ApiOperation(httpMethod = "POST", value = "用户注册")
    @ApiOperationSupport(includeParameters = {"SignInPO.username", "SignInPO.password", "SignInPO.nickname"})
    public ApiResponse<Void> userSignIn(@RequestBody SignInPO po){
        return userService.signIn(po);
    }

    @PostMapping("checkName")
    @ApiOperation(httpMethod = "POST", value = "用户名检测")
    @ApiOperationSupport(includeParameters = {"CheckNamePO.username"})
    public ApiResponse<Void> checkName(@RequestBody CheckNamePO po){
        return userService.checkName(po);
    }

    @PostMapping("setInfo")
    @ApiOperation(httpMethod = "POST", value = "修改用户信息")
    public ApiResponse<Void> setInfo(@RequestBody UserInfoPO po){
        return userService.setInfo(po);
    }

    @PostMapping("getInfo")
    @ApiOperation(httpMethod = "POST", value = "获取用户信息")
    @ApiOperationSupport(includeParameters = {"UserIdPO.id"})
    public ApiResponse<UserInfoVO> getInfo(@RequestBody UserIdPO po){
        return userService.getInfo(po);
    }


    @PostMapping("avatar")
    @ApiOperation(httpMethod = "POST", value = "更换头像")
    @ApiOperationSupport(includeParameters = {"UserURLPO.id", "UserURLPO.url"})
    public ApiResponse<Void> changeAvatar(@RequestBody UserURLPO po){
        return userService.changeAvatar(po);
    }

    @PostMapping("bg")
    @ApiOperation(httpMethod = "POST", value = "更换背景")
    @ApiOperationSupport(includeParameters = {"UserURLPO.id", "UserURLPO.url"})
    public ApiResponse<Void> changeBackground(@RequestBody UserURLPO po){
        return userService.changeBackground(po);
    }
}