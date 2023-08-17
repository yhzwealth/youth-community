package com.chuang.bootplus.service;

import com.chuang.bootplus.base.utils.ApiResponse;
import com.chuang.bootplus.entity.Love;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chuang.bootplus.po.love.LovePO;
import com.chuang.bootplus.po.love.UnLovePO;
import com.chuang.bootplus.vo.love.LoveVO;

public interface LoveService extends IService<Love> {
    ApiResponse<LoveVO> love(LovePO po);
    ApiResponse<Void> unLove(UnLovePO po);
}
