package com.chuang.bootplus.service;

import com.chuang.bootplus.base.utils.ApiResponse;
import com.chuang.bootplus.entity.Dynamic;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chuang.bootplus.po.dynamic.*;
import com.chuang.bootplus.vo.dynamic.DynamicListVO;

public interface DynamicService extends IService<Dynamic> {
    ApiResponse<Void> sendDynamic(DynamicSendPO po);
    ApiResponse<DynamicListVO> getUserDynamic(DynamicUserListPO po);
    ApiResponse<DynamicListVO> getFollowDynamic(DynamicFollowListPO po);
    ApiResponse<DynamicListVO> getTypeDynamic(DynamicTypeListPO po);
    ApiResponse<Void> setTop(DynamicTopPO po);
    ApiResponse<Void> cancelTop(DynamicTopPO po);
}
