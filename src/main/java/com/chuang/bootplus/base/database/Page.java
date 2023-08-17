package com.chuang.bootplus.base.database;

/**
 * @author lcc
 * @create 2021-07-14
 * @注意 本内容仅限于xinou内部传阅，禁止外泄以及用于其他的商业目的
 */
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * 信鸥 分页 基类对象<br>
 * 包含 id、gmtCreate、gmtModified、isDelete
 *
 *
 * @author lizhongyuan
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class Page extends BasePO {


    private static final Integer DEFAULT_PAGE_SIZE = 10;

    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页 默认 1", example = "1")
    private Integer pageNumber;

    /**
     * 页面大小
     */
    @ApiModelProperty(value = "页面大小 默认 10", example = "10")
    private Integer pageSize;

    public Page() {
        this(1, DEFAULT_PAGE_SIZE);
    }

    /**
     * 构造 Page
     *
     * @param pageNumber 页码
     * @param pageSize 每页结果数
     */
    public Page(Integer pageNumber, Integer pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = Math.max(pageSize, 0);
    }

}
