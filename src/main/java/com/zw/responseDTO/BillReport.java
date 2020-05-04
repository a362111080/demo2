package com.zw.responseDTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class BillReport {

    @ApiModelProperty(value = "总收入")
    private BigDecimal InCount=BigDecimal.ZERO;

    @ApiModelProperty(value = "总进货数")
    private BigDecimal InPcs=BigDecimal.ZERO;

    @ApiModelProperty(value = "总支出")
    private BigDecimal OutCount=BigDecimal.ZERO;

    @ApiModelProperty(value = "总出货数")
    private BigDecimal OutPcs=BigDecimal.ZERO;


    @ApiModelProperty(value = "已结清数量")
    private BigDecimal CompleteCount=BigDecimal.ZERO;

    @ApiModelProperty(value = "未结清数量")
    private BigDecimal UnCompleteCount=BigDecimal.ZERO;

    @ApiModelProperty(value = "未生成数量")
    private BigDecimal UnCreateCount=BigDecimal.ZERO;

    @ApiModelProperty(value = "零售账单数量")
    private BigDecimal RetailBillCount=BigDecimal.ZERO;
}


