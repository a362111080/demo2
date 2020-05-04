package com.zw.beans;

import java.math.BigDecimal;
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
 * @since 2019-11-20
 */
@TableName("tbz_bill_details")
@Data
public class BillDetails extends Model<BillDetails> {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.UUID)
    private String id;

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

    private String categoryId;

    @ApiModelProperty(value = "金额",required=false)
    private BigDecimal amount;

    private String specificationId;

    private Boolean dr;



}
