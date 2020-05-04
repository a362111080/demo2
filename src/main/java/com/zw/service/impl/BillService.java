package com.zw.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zw.beans.Bill;
import com.zw.beans.BillDetails;
import com.zw.beans.Repertory;
import com.zw.beans.User;
import com.zw.dao.BillDetailsMapper;
import com.zw.dao.BillMapper;
import com.zw.dao.CategoryMapper;
import com.zw.requestDTO.BillDetailsGoodsRequestDTO;
import com.zw.requestDTO.BillRequestDTO;
import com.zw.requestDTO.BillRequestGoodsDTO;
import com.zw.requestDTO.RepertoryDTO;
import com.zw.service.IBillService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zw.service.IRepertoryService;
import com.zw.tool.Message;
import com.zw.tool.UtilConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yzj
 * @since 2019-11-20
 */
@Service
public class BillService extends ServiceImpl<BillMapper, Bill> implements IBillService {

    @Autowired
    private BillMapper billMapper;
    @Autowired
    private BillDetailsMapper billDetailsMapper;
    @Autowired
    private IRepertoryService repertoryService;
    @Override
    public List<Bill> getBilllist(BillRequestDTO model) {
        return billMapper.getBilllist(model);
    }

    @Override
    @Transactional
    public Message addBillAndbillDetails(BillRequestGoodsDTO dto, User user) {
        Message message = new Message();
        try {
            List<BillDetailsGoodsRequestDTO> db = dto.getBillDetailsGoodsRequestDTOList();
            RepertoryDTO repertoryDTO = new RepertoryDTO();
            Repertory repertory = new Repertory();
            repertory.setShopId(user.getShopId());

            BillDetails billDetails = new BillDetails();
            String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
            billDetails.setShopId(user.getShopId());
            billDetails.setBillId(uuid);
            billDetails.setModifytime(new Date());
            billDetails.setCreatetime(new Date());
            billDetails.setPartnerId(dto.getPartnerId());
            billDetails.setCreator(user.getName());

            //整个账单应收金额和数量
            BigDecimal amount = BigDecimal.ZERO;
            BigDecimal totalAuatity = BigDecimal.ZERO;

            Bill bill = new Bill();
            bill.setBillId(uuid);
            bill.setShopId(user.getShopId());
            bill.setType(dto.getType());
            bill.setModifytime(new Date());
            bill.setCreatetime(new Date());
            bill.setCreator(user.getName());
            bill.setPartnerId(dto.getPartnerId());
            if (db.size() > 0) {
                for (int i = 0; i < db.size(); i++) {
                    BillDetailsGoodsRequestDTO bdgr = db.get(i);

                    BigDecimal subTotal = bdgr.getPrice().multiply(bdgr.getQuantity());
                    billDetails.setCategoryId(bdgr.getCategoryId());
                    billDetails.setSpecificationId(bdgr.getSpecificationId());
                    billDetails.setQuantity(bdgr.getQuantity());
                    billDetails.setPrice(bdgr.getPrice());
                    billDetails.setAmount(subTotal);
                    //总金额
                    amount.add(subTotal);
                    //总数量
                    totalAuatity.add(bdgr.getQuantity());

                    int s = billDetailsMapper.insert(billDetails);
                    if (s > 0) {
                        repertoryDTO.setCategoryId(bdgr.getCategoryId());
                        repertoryDTO.setSpecificationId(bdgr.getSpecificationId());
                        repertory.setCategoryId(bdgr.getCategoryId());
                        repertory.setSpecificationId(bdgr.getSpecificationId());
                        IPage<Repertory> list = repertoryService.listRepertoryByCondition(repertoryDTO, user);
                        //如果库存有相同的类别规格，数量相减
                        if (list.getTotal() > 0) {
                            if (list.getRecords().get(0).getSum().compareTo(bdgr.getQuantity()) > -1) {
                                repertory.setSum(list.getRecords().get(0).getSum().subtract(bdgr.getQuantity()));
                                int strval1 = repertoryService.UpdateRepertory(repertory);
                                if (strval1 > 0) {
                                    message.setState(UtilConstants.ResponseCode.SUCCESS_HEAD);
                                    message.setMessage(UtilConstants.ResponseMsg.SUCCESS);
                                } else {
                                    message.setState(UtilConstants.ResponseCode.EXCEPTION_HEAD);
                                    message.setMessage(UtilConstants.ResponseMsg.FAILED);
                                }
                            } else {
                                message.setState(UtilConstants.ResponseCode.EXCEPTION_HEAD);
                                message.setMessage("库存不足");
                            }
                        } else  {
                                message.setState(UtilConstants.ResponseCode.EXCEPTION_HEAD);
                                message.setMessage("库存中不存在");
                            }
                        }
                     else {
                        message.setState(UtilConstants.ResponseCode.EXCEPTION_HEAD);
                        message.setMessage(UtilConstants.ResponseMsg.FAILED);
                    }
                }
                bill.setQuantity(amount);
                bill.setPrice(totalAuatity);
                int bs = billMapper.insert(bill);
                if (bs > 0) {
                    message.setState(UtilConstants.ResponseCode.SUCCESS_HEAD);
                    message.setMessage(UtilConstants.ResponseMsg.SUCCESS);
                } else {
                    message.setState(UtilConstants.ResponseCode.EXCEPTION_HEAD);
                    message.setMessage(UtilConstants.ResponseMsg.FAILED);
                }
            } else {
                message.setState(UtilConstants.ResponseCode.EXCEPTION_HEAD);
                message.setMessage(UtilConstants.ResponseMsg.FAILED);
            }
            return message;

        } catch (Exception e) {
            message.setState(UtilConstants.ResponseCode.EXCEPTION_HEAD);
            message.setMessage(UtilConstants.ResponseMsg.FAILED);
            message.setMessage(e.getMessage());
            return message;
        }


        }
    }
