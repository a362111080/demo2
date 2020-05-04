package com.zw.beans;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
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
 * @since 2019-11-18
 */
@TableName("tbz_staff")
@Data
public class Staff extends Model<Staff> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键", required = false)
    @TableId(type = IdType.UUID)
    private String staffId;

    private String name;

    private String phone;

    private String status;

    private String remark;

    private Boolean dr;

    private String shopId;

    private Date createtime;

    private Date modifytime;

    private String creator;

    private String cityId;

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
