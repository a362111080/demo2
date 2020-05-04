package com.zw.dao;

import com.zw.beans.Bill;
import com.zw.beans.BillDetails;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yzj
 * @since 2019-11-20
 */
public interface BillDetailsMapper extends BaseMapper<BillDetails> {
    List<BillDetails> getbilldetsils(Bill bill);

}
