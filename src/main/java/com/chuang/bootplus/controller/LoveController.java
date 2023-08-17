package com.chuang.bootplus.controller;


import com.chuang.bootplus.base.utils.ApiResponse;
import com.chuang.bootplus.po.comment.CommentSendPO;
import com.chuang.bootplus.po.love.LovePO;
import com.chuang.bootplus.po.love.UnLovePO;
import com.chuang.bootplus.service.LoveService;
import com.chuang.bootplus.vo.love.LoveVO;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/love")
@Api(tags = {"点赞"})
@CrossOrigin
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LoveController {

    private final LoveService loveService;

    @PostMapping("love")
    @ApiOperation(httpMethod = "POST", value = "点赞")
    @ApiOperationSupport(includeParameters = {"LovePO.userId", "LovePO.dynamicId"})
    public ApiResponse<LoveVO> love(@RequestBody LovePO po){
        return loveService.love(po);
    }

    @PostMapping("unLove")
    @ApiOperation(httpMethod = "POST", value = "取消点赞")
    @ApiOperationSupport(includeParameters = {"UnLovePO.loveId"})
    public ApiResponse<Void> unLove(@RequestBody UnLovePO po){
        return loveService.unLove(po);
    }
}

