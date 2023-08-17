package com.chuang.bootplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chuang.bootplus.base.database.EntityBase;
import com.chuang.bootplus.base.enumerate.HttpStatusEnum;
import com.chuang.bootplus.base.exception.BusException;
import com.chuang.bootplus.base.utils.ApiResponse;
import com.chuang.bootplus.base.utils.BeanUtil;
import com.chuang.bootplus.entity.*;
import com.chuang.bootplus.mapper.DynamicMapper;
import com.chuang.bootplus.po.dynamic.*;
import com.chuang.bootplus.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chuang.bootplus.vo.comment.CommentListVO;
import com.chuang.bootplus.vo.dynamic.DynamicListVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
public class DynamicServiceImpl extends ServiceImpl<DynamicMapper, Dynamic> implements DynamicService {

    private final UserService userService;
    private final FollowService followService;
    private final LoveService loveService;
    private final CommentService commentService;

    @Override
    public ApiResponse<Void> sendDynamic(DynamicSendPO po) {
        if (userService.list(new LambdaQueryWrapper<User>().eq(User::getId, po.getUserId())).isEmpty()) {
            throw new BusException("该用户不存在");
        }
        save(BeanUtil.beanA2beanB(po, Dynamic.class));
        return new ApiResponse<>();
    }

    @Override
    public ApiResponse<DynamicListVO> getUserDynamic(DynamicUserListPO po) {
        Page<Dynamic> pages = new Page<>(po.getPageNumber(), po.getPageSize());
        Page<Dynamic> dynamicPage;
        if (po.getUserId().equals(po.getHomeId())) {
            dynamicPage = page(pages, new LambdaQueryWrapper<Dynamic>()
                    .eq(Dynamic::getUserId, po.getUserId())
                    .orderByDesc(Dynamic::getTop)
                    .orderByDesc(Dynamic::getGmtCreate));
        } else if (followService.count(new LambdaQueryWrapper<Follow>().eq(Follow::getUserId, po.getHomeId())
                .eq(Follow::getFanId, po.getUserId())) > 0) {
            dynamicPage = page(pages, new LambdaQueryWrapper<Dynamic>()
                    .eq(Dynamic::getUserId, po.getHomeId())
                    .in(Dynamic::getPrivacy, 1, 2)
                    .orderByDesc(Dynamic::getTop)
                    .orderByDesc(Dynamic::getGmtCreate));
        } else {
            dynamicPage = page(pages, new LambdaQueryWrapper<Dynamic>()
                    .eq(Dynamic::getUserId, po.getHomeId())
                    .eq(Dynamic::getPrivacy, 2)
                    .orderByDesc(Dynamic::getTop)
                    .orderByDesc(Dynamic::getGmtCreate));
        }
        if (dynamicPage.getRecords().isEmpty()) {
            return new ApiResponse<>(HttpStatusEnum.RE_CODE_NO_HAVE_OBJECT);
        }
        return new ApiResponse(fullInfo(dynamicPage.getRecords(), po.getUserId()),dynamicPage.getTotal());
    }

    @Override
    public ApiResponse<DynamicListVO> getFollowDynamic(DynamicFollowListPO po) {
        List<Follow> follows = followService.list(new LambdaQueryWrapper<Follow>().eq(Follow::getFanId, po.getUserId()));
        if (follows.isEmpty()) {
            return new ApiResponse<>(HttpStatusEnum.RE_CODE_NO_HAVE_OBJECT);
        }
        List<Long> userIds = follows.stream().map(Follow::getUserId).collect(Collectors.toList());
        Page<Dynamic> pages = new Page<>(po.getPageNumber(), po.getPageSize());
        Page<Dynamic> dynamicPage = page(pages, new LambdaQueryWrapper<Dynamic>()
                .ne(Dynamic::getPrivacy,0)
                .in(Dynamic::getUserId,userIds)
                .orderByDesc(Dynamic::getGmtCreate));
        if (dynamicPage.getRecords().isEmpty()) {
            return new ApiResponse<>(HttpStatusEnum.RE_CODE_NO_HAVE_OBJECT);
        }
        return new ApiResponse(fullInfo(dynamicPage.getRecords(), po.getUserId()),dynamicPage.getTotal());
    }

    @Override
    public ApiResponse<DynamicListVO> getTypeDynamic(DynamicTypeListPO po) {
        Page<Dynamic> pages = new Page<>(po.getPageNumber(), po.getPageSize());
        Page<Dynamic> dynamicPage = page(pages, new LambdaQueryWrapper<Dynamic>()
                .eq(Dynamic::getPrivacy,2)
                .eq(Dynamic::getType,po.getType())
                .orderByDesc(Dynamic::getGmtCreate));
        if (dynamicPage.getRecords().isEmpty()) {
            return new ApiResponse<>(HttpStatusEnum.RE_CODE_NO_HAVE_OBJECT);
        }
        return new ApiResponse(fullInfo(dynamicPage.getRecords(), po.getUserId()),dynamicPage.getTotal());
    }

    @Override
    public ApiResponse<Void> setTop(DynamicTopPO po) {
        Dynamic top = baseMapper.selectOne(new LambdaQueryWrapper<Dynamic>()
                .eq(Dynamic::getUserId, baseMapper.selectOne(new LambdaQueryWrapper<Dynamic>()
                        .eq(Dynamic::getId, po.getDynamicId())).getUserId()).eq(Dynamic::getTop, 1));
        if(top != null) {
            baseMapper.setTop(top.getId());
        }
        baseMapper.setTop(po.getDynamicId());
        return new ApiResponse<>();
    }

    @Override
    public ApiResponse<Void> cancelTop(DynamicTopPO po) {
        baseMapper.setTop(po.getDynamicId());
        return new ApiResponse<>();
    }

    private List<DynamicListVO> fullInfo(List<Dynamic> records, Long userId) {
        List<DynamicListVO> vos = BeanUtil.listA2ListB(records, DynamicListVO.class);

        List<Long> userIds = vos.stream().map(DynamicListVO::getUserId).collect(Collectors.toList());
        List<Long> dynamicIds = vos.stream().map(DynamicListVO::getId).collect(Collectors.toList());

        List<User> users = userService.listByIds(userIds);
        Map<Long, String> nicknames = users.stream().collect(Collectors.toMap(EntityBase::getId, User::getNickname));
        Map<Long, String> urls = users.stream().collect(Collectors.toMap(EntityBase::getId, User::getAvatar));

        List<Love> loves = loveService.list(new LambdaQueryWrapper<Love>().in(Love::getDynamicId, dynamicIds));
        Map<Long, Long> loveNums = loves.stream().collect(Collectors.groupingBy(Love::getDynamicId, Collectors.counting()));

        List<Comment> comments = commentService.list(new LambdaQueryWrapper<Comment>().in(Comment::getDynamicId, dynamicIds));
        Map<Long, Long> commentNums = comments.stream().collect(Collectors.groupingBy(Comment::getDynamicId, Collectors.counting()));

        return vos.stream().peek(k -> {
            k.setGmdCreate(k.getGmtCreate().toLocalDate());
            k.setNickname(nicknames.get(k.getUserId()));
            k.setAvatar(urls.get(k.getUserId()));
            k.setLoveNum(loveNums.get(k.getId()));
            k.setLove(loves.stream().anyMatch(l -> l.getDynamicId().equals(k.getId()) && l.getUserId().equals(userId)) ? 1 : 0);
            k.setCommentNum(commentNums.get(k.getId()));
        }).collect(Collectors.toList());
    }
}
