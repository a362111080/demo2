package com.zw.beans;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;


/**
 * <p>
 * 
 * </p>
 *
 * @author yzj
 * @since 2019-11-15
 */
@TableName("tbz_repertory")
@Data
public class Repertory extends Model<Repertory> {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.UUID)
    private String repertoryId;

    private String categoryId;

    private BigDecimal sum;

    private String specificationId;

    private Date createtime;

    private Date modifytime;

    private Boolean dr;

    private String shopId;

    @TableField(exist = false)//冗余字段
    private String specificationName;

    @TableField(exist = false)//冗余字段
    private String categoryName;



}
