package com.chuang.bootplus.mapper;

import com.chuang.bootplus.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chuang.bootplus.vo.user.UserInfoVO;

public interface UserMapper extends BaseMapper<User> {
    UserInfoVO getUserInfo(Long guest, Long id);
}
