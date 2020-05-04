package com.zw.requestDTO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.zw.tool.PageDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
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
public class BillRequestDTO extends PageDTO {

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

    private String categoryname;

    private BigDecimal price;

    private Boolean dr;

    private String type;

    private String encoding;

    @ApiModelProperty(value = "实收金额",required=false)
    private BigDecimal realAmount;

    @ApiModelProperty(value = "查询关键字",required=true)
    private String keyword;

    @ApiModelProperty(value = "开始时间",required=true)
    private String begintime;

    @ApiModelProperty(value = "结束时间",required=true)
    private String endtime;




}
