package com.zw.beans;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author yzj
 * @since 2019-11-19
 */
@TableName("tbz_deliver")
@Data
public class Deliver extends Model<Deliver> {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.UUID)
    private String deliverId;

    private String staffId;

    private String categoryId;

    private String specificationId;

    private Date createtime;

    private Date modifytime;

    private String shopId;

    private String status;

    private String creator;

    private String mender;

    private BigDecimal sum;

    private Boolean dr;

    @TableField(exist = false)//冗余字段
    private String specificationName;

    @TableField(exist = false)//冗余字段
    private String categoryName;

    @TableField(exist = false)//冗余字段
    private String staffName;

}
