package com.zw.beans;

import java.math.BigDecimal;

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
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author yzj
 * @since 2019-11-20
 */
@TableName("tbz_bill")
@Data
public class Bill extends Model<Bill> {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.UUID)
    private String billId;

    private String shopId;

    private BigDecimal quantity;

    private String status;

    private String remark;

    private String creator;

    private Date createtime;

    private Date modifytime;

    private String partnerId;

    private BigDecimal price;

    private String categoryname;

    private Boolean dr;

    private String type;

    private String encoding;

    @ApiModelProperty(value = "实收金额",required=false)
    private BigDecimal realAmount;

    @TableField(exist = false)
    @ApiModelProperty(value = "勾选的账单id",hidden=true)
    private List<String> ids;



}
