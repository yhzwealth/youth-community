package com.chuang.bootplus.mapper;

import com.chuang.bootplus.entity.Dynamic;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chuang.bootplus.vo.dynamic.DynamicListVO;

import java.util.List;


public interface DynamicMapper extends BaseMapper<Dynamic> {
    List<DynamicListVO> getAllByGuest(Long guestName, Long username);
    List<DynamicListVO> getAllByFan(Long guestName,Long username);
    List<DynamicListVO> getAll(Long guestName,Long username);
    List<DynamicListVO> getFollow(Long username);
    List<DynamicListVO> getDynamicByType(Long username,Integer type);
    void setTop(Long id);
}
