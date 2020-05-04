package com.zw.tool;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 分页对象,给DTO用来继承
 *
 * @ClassName PageDTO
 * @Author
 * @Date
 **/
@Data
public class PageDTO {

    /**
     * 当前页
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "当前页",required = false,example = "1")
    private Long current = 1L;

    /**
     * 每页数量
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "每页数量",required = false,example = "10")
    private Long size = 10L;

    /**
     * 总条数
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "总条数",required = false)
    private Long total;

    /**
     * 总页数
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "总页数",required = false)
    private Long pages;


}
