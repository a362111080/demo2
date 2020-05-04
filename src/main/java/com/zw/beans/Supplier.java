package com.zw.beans;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author yzj
 * @since 2019-11-12
 */
@TableName("tbz_supplier")
@Data
public class Supplier extends Model<Supplier> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键", required = false)
    @TableId(type = IdType.UUID)
    private String supplierId;

    private String shopId;

    private String name;

    private String status;

    private String remark;

    private String cityId;

    private String phone;

    private Date createtime;

    private Boolean dr;

    private Date modifytime;

    /**
     * 省市区一级id
     */
    @ApiModelProperty(value = "省市区一级id", required = false)
    @TableField(exist = false)
    private String city1;
    /**
     * 省市区二级id
     */
    @ApiModelProperty(value = "省市区二级id", required = false)
    @TableField(exist = false)
    private String city2;



}
