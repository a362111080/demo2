package com.zw.service;

import com.zw.beans.Bill;
import com.zw.beans.BillDetails;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zw.tool.Message;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yzj
 * @since 2019-11-20
 */
public interface IBillDetailsService extends IService<BillDetails> {

    Message getbilldetsils(Bill bill);

}
