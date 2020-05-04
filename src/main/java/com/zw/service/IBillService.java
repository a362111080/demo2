package com.zw.service;

import com.zw.beans.Bill;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zw.beans.User;
import com.zw.requestDTO.BillRequestDTO;
import com.zw.requestDTO.BillRequestGoodsDTO;
import com.zw.tool.Message;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yzj
 * @since 2019-11-20
 */
public interface IBillService extends IService<Bill> {

    List<Bill> getBilllist(BillRequestDTO model);

    Message addBillAndbillDetails(BillRequestGoodsDTO dto, User user);

}
