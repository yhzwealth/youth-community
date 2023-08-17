package com.chuang.bootplus.service;

import com.chuang.bootplus.base.utils.ApiResponse;
import com.chuang.bootplus.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chuang.bootplus.po.comment.CommentListPO;
import com.chuang.bootplus.po.comment.CommentSendPO;
import com.chuang.bootplus.vo.comment.CommentListVO;

public interface CommentService extends IService<Comment> {

    ApiResponse<Void> commentsSend(CommentSendPO po);

    ApiResponse<CommentListVO> commentsList(CommentListPO po);

}
