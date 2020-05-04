package com.zw.dao;

import com.zw.beans.Bill;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zw.requestDTO.BillRequestDTO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yzj
 * @since 2019-11-20
 */
public interface BillMapper extends BaseMapper<Bill> {

    /**
     * 根据主键id,店铺id,企业id查询账单,且账单未被删除
     * @param
     * @return
     */
    List<Bill> getBilllist(BillRequestDTO model);

    /**
     * 根据主键id,店铺id,且账单未被删除
     * @param bill
     * @return
     */
    Bill getOneByIdAndCompanyIdAndShopIdAndDr(Bill bill);

}
