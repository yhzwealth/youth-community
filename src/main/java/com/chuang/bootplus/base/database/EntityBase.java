package com.chuang.bootplus.base.database;

/**
 * 包含 id、gmtCreate、gmtModified、isDelete
 * @author lcc
 * @create 2021-07-14
 * @注意 本内容仅限于xinou内部传阅，禁止外泄以及用于其他的商业目的
 */
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class EntityBase implements Serializable {

    @ApiModelProperty(value = "主键", example = "1", required = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @TableId(type = IdType.AUTO)
    @TableField("id")
    protected Long id;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间 yyyy-MM-dd HH:mm:ss", example = "2020-11-28 00:12:23", required = true)
    @JsonFormat(timezone = "GMT+8", shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_create")
    private LocalDateTime gmtCreate;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间 yyyy-MM-dd HH:mm:ss", example = "2020-11-28 00:12:23", required = true)
    @JsonFormat(timezone = "GMT+8", shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_modified")
    private LocalDateTime gmtModified;

    /**
     * 是否已删除
     * 0：未删除
     * 1：已删除
     */
    @TableLogic
    @JsonIgnore
    @TableField(value = "is_delete")
    private Integer isDelete;

}
