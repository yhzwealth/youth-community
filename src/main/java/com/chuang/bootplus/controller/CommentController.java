package com.chuang.bootplus.controller;


import com.chuang.bootplus.base.utils.ApiResponse;
import com.chuang.bootplus.po.comment.CommentListPO;
import com.chuang.bootplus.po.comment.CommentSendPO;
import com.chuang.bootplus.service.CommentService;
import com.chuang.bootplus.vo.comment.CommentListVO;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@Api(tags = {"评论区"})
@CrossOrigin(origins = "*")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CommentController {

    private final CommentService commentService;

    @PostMapping("send")
    @ApiOperation(httpMethod = "POST", value = "发送留言")
    @ApiOperationSupport(includeParameters = {"CommentSendPO.userId", "CommentSendPO.content","CommentsSendPO.dynamicId","CommentsSendPO.pid"})
    public ApiResponse<Void> commentSend(@RequestBody CommentSendPO po){
        return commentService.commentsSend(po);
    }

    @PostMapping("list")
    @ApiOperation(httpMethod = "POST", value = "获取留言")
    @ApiOperationSupport(includeParameters = {"CommentListPO.pid","CommentsListPO.dynamicId", "CommentsListPO.pageSize", "CommentsListPO.pageNumber"},ignoreParameters = {"CommentsListPO.id"})
    public ApiResponse<CommentListVO> commentList(@RequestBody CommentListPO po){
        return commentService.commentsList(po);
    }

}

