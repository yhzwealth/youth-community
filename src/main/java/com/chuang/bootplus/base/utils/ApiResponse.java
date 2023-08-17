package com.chuang.bootplus.base.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chuang.bootplus.base.constant.Constant;
import com.chuang.bootplus.base.enumerate.HttpStatusCodeService;
import com.chuang.bootplus.base.enumerate.HttpStatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 信鸥 api 请求统一返回体
 *
 * @author lizhongyuan
 */
@Data
@ApiModel
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> implements Serializable {

    @ApiModelProperty(notes = "状态码", example = "200")
    private String reCode;

    @ApiModelProperty(notes = "状态信息", example = "成功")
    private String reMsg;

    @ApiModelProperty(notes = "返回数据")
    private Map<String, Object> body = new HashMap<>();

    @ApiModelProperty(notes = "返回实体类示例, 仅用于参考, 实际返回值中没有此字段")
    private T example;

    @ApiModelProperty(name = "前台Token")
    private String token = "";

    /**
     * 根据 枚举接口 构造返回体
     *
     * @param codeService 枚举接口
     * @see HttpStatusCodeService
     */
    public ApiResponse(HttpStatusCodeService codeService) {
        this.reCode = codeService.getReCode();
        this.reMsg = codeService.getReMsg();
    }

    /**
     * 根据状态码、状态信息 构造返回体
     *
     * @param reCode 状态码
     * @param reMsg  状态信息
     */
    public ApiResponse(String reCode, String reMsg) {
        this.reCode = reCode;
        this.reMsg = reMsg;
    }

    /**
     * 根据状态码枚举、状态信息 构造返回体
     *
     * @param codeService 状态码枚举
     * @param reMsg      状态信息
     * @see HttpStatusEnum
     */
    public ApiResponse(HttpStatusCodeService codeService, String reMsg) {
        this.reCode = codeService.getReCode();
        this.reMsg = reMsg;
    }

    /**
     * 返回请求成功状态码
     */
    public ApiResponse() {
        this.reCode = HttpStatusEnum.RE_CODE_SUCCESS.getCode();
        this.reMsg = HttpStatusEnum.RE_CODE_SUCCESS.getMessage();
    }

    /**
     * dataInfo 返回体
     *
     * @param dataInfo 单个对象
     */
    public ApiResponse(T dataInfo) {
        this.reCode = HttpStatusEnum.RE_CODE_SUCCESS.getCode();
        this.reMsg = HttpStatusEnum.RE_CODE_SUCCESS.getMessage();
        body.put(Constant.DATA_INFO, dataInfo);
    }

    /**
     * dataList 返回体
     * total 从 dataList 中获取
     *
     * @param dataList list 对象
     */
    public ApiResponse(Collection<T> dataList) {
        this.reCode = HttpStatusEnum.RE_CODE_SUCCESS.getCode();
        this.reMsg = HttpStatusEnum.RE_CODE_SUCCESS.getMessage();
        body.put(Constant.TOTAL, dataList.size());
        body.put(Constant.DATA_LIST, dataList);
    }

    /**
     * dataList 返回体
     *
     * @param dataList list 对象
     * @param total    Long 类型总数
     */
    public ApiResponse(Collection<T> dataList, Long total) {
        this.reCode = HttpStatusEnum.RE_CODE_SUCCESS.getCode();
        this.reMsg = HttpStatusEnum.RE_CODE_SUCCESS.getMessage();
        body.put(Constant.TOTAL, total);
        body.put(Constant.DATA_LIST, dataList);
    }

    /**
     * Page 返回体
     *
     * @param tPage page 对象
     */
    public ApiResponse(Page<T> tPage) {
        this.reCode = HttpStatusEnum.RE_CODE_SUCCESS.getCode();
        this.reMsg = HttpStatusEnum.RE_CODE_SUCCESS.getMessage();
        body.put(Constant.TOTAL, tPage.getTotal());
        body.put(Constant.DATA_LIST, tPage.getRecords());
    }

    /**
     * dataList 返回体
     *
     * @param dataList list 对象
     * @param total    Integer 类型总数
     */
    public ApiResponse(Collection<T> dataList, Integer total) {
        this.reCode = HttpStatusEnum.RE_CODE_SUCCESS.getCode();
        this.reMsg = HttpStatusEnum.RE_CODE_SUCCESS.getMessage();
        body.put(Constant.TOTAL, total);
        body.put(Constant.DATA_LIST, dataList);
    }

    /**
     * 自定义 map 返回体 为了兼容权限
     *
     * @param map map
     */
    @Deprecated
    public ApiResponse(Map map) {
        this.reCode = HttpStatusEnum.RE_CODE_SUCCESS.getCode();
        this.reMsg = HttpStatusEnum.RE_CODE_SUCCESS.getMessage();
        body.putAll(map);
    }

    /**
     * 自定义 body 的 key 和 value
     *
     * @param key   key
     * @param value value
     */
    public ApiResponse<T> put(String key, Object value) {
        body.put(key, value);
        return this;
    }

    /**
     * 按照key 获取 body value
     *
     * @param key key
     */
    public <V> V get(String key) {
        return (V) body.get(key);
    }

    /**
     * 获取 body dataList
     */
    @JsonIgnore
    public List<T> getDataList() {
        return (List<T>) body.get(Constant.DATA_LIST);
    }

    /**
     * 获取 body dataInfo
     */
    @JsonIgnore
    public T getDataInfo() {
        return (T) body.get(Constant.DATA_INFO);
    }
}
