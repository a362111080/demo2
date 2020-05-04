package com.zw.requestDTO;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class BillDetailsGoodsRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String categoryId;

    private String specificationId;

    private BigDecimal price;

    private BigDecimal quantity;

    private BigDecimal subTotal;


}
