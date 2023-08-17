package com.chuang.bootplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.chuang.bootplus.base.exception.BusException;
import com.chuang.bootplus.base.utils.ApiResponse;
import com.chuang.bootplus.base.utils.BeanUtil;
import com.chuang.bootplus.entity.Comment;
import com.chuang.bootplus.entity.Love;
import com.chuang.bootplus.entity.User;
import com.chuang.bootplus.mapper.LoveMapper;
import com.chuang.bootplus.po.love.LovePO;
import com.chuang.bootplus.po.love.UnLovePO;
import com.chuang.bootplus.service.LoveService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chuang.bootplus.service.UserService;
import com.chuang.bootplus.vo.love.LoveVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2021-09-15
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LoveServiceImpl extends ServiceImpl<LoveMapper, Love> implements LoveService {

    private final UserService userService;

    @Override
    public ApiResponse<LoveVO> love(LovePO po) {
        if(userService.list(new LambdaQueryWrapper<User>().eq(User::getId,po.getUserId())).isEmpty()){
            throw new BusException("该用户不存在");
        }
        save(BeanUtil.beanA2beanB(po, Love.class));
        Love one = getOne(new LambdaQueryWrapper<Love>().eq(Love::getUserId, po.getUserId()).eq(Love::getDynamicId, po.getDynamicId()));
        return new ApiResponse(BeanUtil.beanA2beanB(one,LoveVO.class));
    }

    @Override
    public ApiResponse<Void> unLove(UnLovePO po) {
        removeById(po.getLoveId());
        return new ApiResponse<>();
    }


}
