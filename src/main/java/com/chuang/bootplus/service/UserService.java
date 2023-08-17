package com.chuang.bootplus.service;

import com.chuang.bootplus.base.utils.ApiResponse;
import com.chuang.bootplus.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chuang.bootplus.po.user.*;
import com.chuang.bootplus.vo.user.UserInfoVO;

public interface UserService extends IService<User> {

    ApiResponse<UserInfoVO> login(LoginPO po);

    ApiResponse<Void> signIn(SignInPO po);

    ApiResponse<Void> checkName(CheckNamePO po);

    ApiResponse<Void> setInfo(UserInfoPO po);

    ApiResponse<UserInfoVO> getInfo(UserIdPO po);

    ApiResponse<Void> changeAvatar(UserURLPO po);

    ApiResponse<Void> changeBackground(UserURLPO po);
}