package com.chuang.bootplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chuang.bootplus.base.constant.Constant;
import com.chuang.bootplus.base.enumerate.HttpStatusEnum;
import com.chuang.bootplus.base.exception.BusException;
import com.chuang.bootplus.base.utils.ApiResponse;
import com.chuang.bootplus.base.utils.BeanUtil;
import com.chuang.bootplus.entity.Follow;
import com.chuang.bootplus.entity.User;
import com.chuang.bootplus.mapper.FollowMapper;

import com.chuang.bootplus.po.follow.FollowListPO;
import com.chuang.bootplus.po.follow.FollowPO;
import com.chuang.bootplus.service.FollowService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chuang.bootplus.service.UserService;

import com.chuang.bootplus.vo.follow.FollowListVO;
import com.chuang.bootplus.vo.follow.FollowVO;
import com.chuang.bootplus.vo.user.UserInfoVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
public class FollowServiceImpl extends ServiceImpl<FollowMapper, Follow> implements FollowService {

    private final UserService userService;

    @Override
    public ApiResponse<FollowVO> follow(FollowPO po) {
        if(userService.list(new LambdaQueryWrapper<User>().eq(User::getId,po.getUserId())).isEmpty()){
            throw new BusException("该用户不存在");
        }

        save(BeanUtil.beanA2beanB(po, Follow.class));
        Follow one = getOne(new LambdaQueryWrapper<Follow>().eq(Follow::getUserId, po.getUserId()).eq(Follow::getFanId, po.getFanId()));
        return new ApiResponse<FollowVO>(BeanUtil.beanA2beanB(one, FollowVO.class));
    }

    @Override
    public ApiResponse<Void> unfollow(FollowPO po) {
        if(userService.list(new LambdaQueryWrapper<User>().eq(User::getId,po.getUserId())).isEmpty()){
            throw new BusException("该用户不存在");
        }
        remove(new LambdaQueryWrapper<Follow>().eq(Follow::getUserId,po.getUserId()).eq(Follow::getFanId,po.getFanId()));
        return new ApiResponse<>();
    }



    @Override
    public ApiResponse<FollowListVO> getFollowUser(FollowListPO po) {
        Page<Follow> pages=new Page<>(po.getPageNumber(),po.getPageSize());
        Page<Follow> followPage=page(pages,new LambdaQueryWrapper<Follow>().eq(Follow::getFanId,po.getUserId()).orderByDesc(Follow::getGmtCreate));
        if (followPage==null){
            return new ApiResponse<>(HttpStatusEnum.RE_CODE_NO_HAVE_OBJECT);
        }

        List<FollowListVO> followListVOS1 = BeanUtil.listA2ListB(followPage.getRecords(), FollowListVO.class);

        List<Follow> list = list(new LambdaQueryWrapper<Follow>().eq(Follow::getFanId, po.getUserId()));
        if (list.isEmpty()){
            return new ApiResponse<>(HttpStatusEnum.RE_CODE_NO_FOLLOWS);
        }
        List<Long> userIdList = list.stream().map(t -> t.getUserId()).collect(Collectors.toList());

        List<User> userList = userService.listByIds(userIdList);
        List<UserInfoVO> userInfoVOS = BeanUtil.listA2ListB(userList, UserInfoVO.class);

        for (int i=0;i< userInfoVOS.size()&&i<followListVOS1.size();i++){
            followListVOS1.get(i).setUserInfoVO(userInfoVOS.get(i));
            userInfoVOS.get(i).setFollowed(1);
            userInfoVOS.get(i).setFan(count(new LambdaQueryWrapper<Follow>().eq(Follow::getUserId,userInfoVOS.get(i).getId())));
            userInfoVOS.get(i).setFollow(count(new LambdaQueryWrapper<Follow>().eq(Follow::getFanId,userInfoVOS.get(i).getId())));
            userInfoVOS.get(i).setGmdCreate(userInfoVOS.get(i).getGmtCreate().toLocalDate());
        }

        return new ApiResponse(followListVOS1,followPage.getTotal());
    }

    @Override
    public ApiResponse<FollowListVO> getFans(FollowListPO po) {
        Page<Follow> pages=new Page<>(po.getPageNumber(),po.getPageSize());
        Page<Follow> followPage=page(pages,new LambdaQueryWrapper<Follow>().eq(Follow::getUserId,po.getUserId()).orderByDesc(Follow::getGmtCreate));

        if (followPage==null){
            return new ApiResponse<>(HttpStatusEnum.RE_CODE_NO_HAVE_OBJECT);
        }
        List<FollowListVO> fansListVOS1 = BeanUtil.listA2ListB(followPage.getRecords(), FollowListVO.class);

        List<Follow> list = list(new LambdaQueryWrapper<Follow>().eq(Follow::getUserId, po.getUserId()));

        if (list.isEmpty()){
            return new ApiResponse<>(HttpStatusEnum.RE_CODE_NO_FANS);
        }
        List<Long> fansIdList = list.stream().map(t -> t.getFanId()).collect(Collectors.toList());

        List<User> userList = userService.listByIds(fansIdList);
        List<UserInfoVO> userInfoVOS = BeanUtil.listA2ListB(userList, UserInfoVO.class);

        for (int i=0;i< userInfoVOS.size()&&i<fansListVOS1.size();i++){

            fansListVOS1.get(i).setUserInfoVO(userInfoVOS.get(i));

            Follow one = getOne(new LambdaQueryWrapper<Follow>().eq(Follow::getFanId, po.getUserId()).eq(Follow::getUserId, userList.get(i).getId()));

            if (one!=null){
                userInfoVOS.get(i).setFollowed(1);
            }else {
                userInfoVOS.get(i).setFollowed(0);
            }

            userInfoVOS.get(i).setFan(count(new LambdaQueryWrapper<Follow>().eq(Follow::getUserId,userInfoVOS.get(i).getId())));
            userInfoVOS.get(i).setFollow(count(new LambdaQueryWrapper<Follow>().eq(Follow::getFanId,userInfoVOS.get(i).getId())));
            userInfoVOS.get(i).setGmdCreate(userInfoVOS.get(i).getGmtCreate().toLocalDate());
        }

        return new ApiResponse(fansListVOS1,followPage.getTotal());

    }



}
