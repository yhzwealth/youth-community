package com.chuang.bootplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chuang.bootplus.base.database.EntityBase;
import com.chuang.bootplus.base.enumerate.HttpStatusEnum;
import com.chuang.bootplus.base.exception.BusException;
import com.chuang.bootplus.base.utils.ApiResponse;
import com.chuang.bootplus.base.utils.BeanUtil;
import com.chuang.bootplus.entity.Comment;
import com.chuang.bootplus.entity.User;
import com.chuang.bootplus.mapper.CommentMapper;
import com.chuang.bootplus.po.comment.CommentListPO;
import com.chuang.bootplus.po.comment.CommentSendPO;
import com.chuang.bootplus.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chuang.bootplus.service.UserService;
import com.chuang.bootplus.vo.comment.CommentListVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
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
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    private final UserService userService;

    @Override
    public ApiResponse<Void> commentsSend(CommentSendPO po) {
        if(userService.list(new LambdaQueryWrapper<User>().eq(User::getId,po.getUserId())).isEmpty()){
            throw new BusException("该用户不存在");
        }
        save(BeanUtil.beanA2beanB(po,Comment.class));
        return new ApiResponse<>();
    }

    @Override
    public ApiResponse<CommentListVO> commentsList(CommentListPO po) {
        Page<Comment> pages = new Page<>(po.getPageNumber(), po.getPageSize());

        Page<Comment> commentsPage = page(pages, new LambdaQueryWrapper<Comment>()
                .eq(Comment::getDynamicId,po.getDynamicId())
                .eq(po.getPid()!=null,Comment::getPid,po.getPid())
                .isNull(po.getPid()==null,Comment::getPid)
                .orderByDesc(Comment::getGmtCreate));
        if (commentsPage.getRecords().isEmpty()) {
            return new ApiResponse<>(HttpStatusEnum.RE_CODE_NO_HAVE_OBJECT);
        }

        List<CommentListVO> vos = BeanUtil.listA2ListB(commentsPage.getRecords(), CommentListVO.class);
        List<Long> collect = vos.stream().map(CommentListVO::getUserId).collect(Collectors.toList());
        List<User> users = userService.listByIds(collect);
        Map<Long,String> nicknames = users.stream().collect(Collectors.toMap(EntityBase::getId, User::getNickname));
        Map<Long,String> urls = users.stream().collect(Collectors.toMap(EntityBase::getId, User::getAvatar));

        List<CommentListVO> commentsList = vos.stream().peek(k -> {
            k.setGmdCreate(k.getGmtCreate().toLocalDate());
            k.setNickname(nicknames.get(k.getUserId()));
            k.setAvatar(urls.get(k.getUserId()));
        }).collect(Collectors.toList());

        return new ApiResponse(commentsList,commentsPage.getTotal());
    }
}
