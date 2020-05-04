package com.zw.beans;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author yzj
 * @since 2019-11-12
 */
@TableName("tbz_specification")
@Data
public class Specification extends Model<Specification> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键", required = false)
    @TableId(type = IdType.UUID)
    private String specificationId;

    private String name;

    private Date createtime;

    private Date modifytime;

    private String status;

    private Category category;

    private String shopId;

    private String creator;

    private Boolean dr;
//    @TableField(exist = false)
//    private String categoryName;
}


