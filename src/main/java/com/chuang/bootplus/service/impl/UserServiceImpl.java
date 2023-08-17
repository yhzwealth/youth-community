package com.chuang.bootplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.chuang.bootplus.base.exception.BusException;
import com.chuang.bootplus.base.utils.ApiResponse;
import com.chuang.bootplus.base.utils.BeanUtil;
import com.chuang.bootplus.entity.User;
import com.chuang.bootplus.mapper.UserMapper;
import com.chuang.bootplus.po.user.*;
import com.chuang.bootplus.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chuang.bootplus.vo.user.UserInfoVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2021-09-15
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public ApiResponse<UserInfoVO> login(LoginPO po) {
        User user = getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, po.getUsername()));
        if (user == null) {
            throw new BusException("该用户不存在");
        }
        if (!user.getPassword().equals(po.getPassword())) {
            throw new BusException("密码错误");
        }
        UserInfoVO userInfoVO = getBaseMapper().getUserInfo(user.getId(), user.getId());
        userInfoVO.setGmdCreate(userInfoVO.getGmtCreate().toLocalDate());
        return new ApiResponse<>(userInfoVO);
    }

    @Override
    public ApiResponse<Void> signIn(SignInPO po) {
        if (getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, po.getUsername())) != null) {
            throw new BusException("该用户名已被注册");
        }
        User user = BeanUtil.beanA2beanB(po, User.class);
        user.setAvatar("http://122.9.10.73:8080/photos/1631699431719default-avatar.jpg")
                .setBackground("http://122.9.10.73:8080/photos/1631699454052default-background.png");
        save(user);
        return new ApiResponse<>();
    }

    @Override
    public ApiResponse<Void> checkName(CheckNamePO po) {
        if (getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, po.getUsername())) != null) {
            throw new BusException("该用户名已被注册");
        }
        return new ApiResponse<>();
    }

    @Override
    public ApiResponse<Void> setInfo(UserInfoPO po) {
        try {
            User user = BeanUtil.beanA2beanB(po, User.class);
            updateById(user);
        }catch (Exception e){
            throw new BusException("修改失败");
        }
        return new ApiResponse<>();
    }

    @Override
    public ApiResponse<UserInfoVO> getInfo(UserIdPO po) {
        UserInfoVO user = baseMapper.getUserInfo(po.getId(), po.getId());
        if(user==null){
            throw new BusException("用户ID有误");
        }
        user.setGmdCreate(user.getGmtCreate().toLocalDate());
        return new ApiResponse<>(user);
    }

    @Override
    public ApiResponse<Void> changeAvatar(UserURLPO po) {
        User user = new User();
        user.setId(po.getId());
        user.setAvatar(po.getUrl());
        updateById(user);
        return new ApiResponse<>();
    }

    @Override
    public ApiResponse<Void> changeBackground(UserURLPO po) {
        User user = new User();
        user.setId(po.getId());
        user.setBackground(po.getUrl());
        updateById(user);
        return new ApiResponse<>();
    }
}
