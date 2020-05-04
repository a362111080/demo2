package com.zw.service.impl;

import com.zw.beans.Bill;
import com.zw.beans.BillDetails;
import com.zw.dao.BillDetailsMapper;
import com.zw.dao.BillMapper;
import com.zw.responseDTO.BillDetailsResponseDTO;
import com.zw.service.IBillDetailsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zw.tool.Message;
import com.zw.tool.UtilConstants;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yzj
 * @since 2019-11-20
 */
@Service
public class BillDetailsService extends ServiceImpl<BillDetailsMapper, BillDetails> implements IBillDetailsService {


    @Autowired
    private BillDetailsMapper mapper;

    @Autowired
    private BillMapper billMapper;

    @Override
    public Message getbilldetsils(Bill bill) {
        Message message = new Message();
        BillDetailsResponseDTO billDetailsResponseDTO = new BillDetailsResponseDTO();
        try {
            Bill actualBill = billMapper.getOneByIdAndCompanyIdAndShopIdAndDr(bill);
            //如果没查出该账单信息则返回提示信息
            if (null != actualBill) {
                billDetailsResponseDTO.setAmount(actualBill.getPrice());
                billDetailsResponseDTO.setRealAmount(actualBill.getRealAmount());
                List<BillDetails> billDetailsList = mapper.getbilldetsils(bill);
                billDetailsResponseDTO.setBillDetailsList(billDetailsList);
                message.setData(billDetailsResponseDTO);
                message.setState(UtilConstants.ResponseCode.SUCCESS_HEAD);
                message.setMessage(UtilConstants.ResponseMsg.SUCCESS);
            } else {
                message.setState(UtilConstants.ResponseCode.EXCEPTION_HEAD);
                message.setMessage(UtilConstants.ResponseMsg.PARAM_ERROR);
            }
        } catch (Exception e) {
            throw new ServiceException("getbilldetsils error");
        }
        return message;
    }
}

