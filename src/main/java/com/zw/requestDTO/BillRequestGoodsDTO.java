package com.zw.requestDTO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
public class BillRequestGoodsDTO extends PageDTO {

    private static final long serialVersionUID = 1L;

    private String status;

    private String remark;

    private String creator;

    private String partnerId;

    private String type;

    private BigDecimal amount;

    @ApiModelProperty(value = "实收金额",required=false)
    private BigDecimal realAmount;

    private List<BillDetailsGoodsRequestDTO> billDetailsGoodsRequestDTOList;








}
