package com.zw.responseDTO;

import com.zw.beans.BillDetails;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class BillDetailsResponseDTO implements Serializable {
    private static final long serialVersionUID = 5549779329245608867L;

    private List<BillDetails> billDetailsList;

    /**
     * 实收金额
     */
    private BigDecimal realAmount;

    /**
     * 应收金额
     */
    private BigDecimal amount;
}
