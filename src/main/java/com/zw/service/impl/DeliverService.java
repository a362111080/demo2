package com.zw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zw.beans.Deliver;
import com.zw.beans.Repertory;
import com.zw.beans.User;
import com.zw.dao.DeliverMapper;
import com.zw.requestDTO.DeliverRequestDTO;
import com.zw.requestDTO.RepertoryDTO;
import com.zw.service.IDeliverService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zw.service.IRepertoryService;
import com.zw.tool.Message;
import com.zw.tool.UtilConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yzj
 * @since 2019-11-19
 */
@Service
public class DeliverService extends ServiceImpl<DeliverMapper, Deliver> implements IDeliverService {

    @Autowired
    private DeliverMapper mapper;

    @Autowired
    private IRepertoryService repertoryService;

    @Override
    public IPage<Deliver> listDeliverByCondition(DeliverRequestDTO dto, User user) {
        Page<Deliver> page = new Page<>();
        dto.setShopId(user.getShopId());
        page.setCurrent(dto.getCurrent());
        page.setSize(dto.getSize());
        QueryWrapper<DeliverRequestDTO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("re.dr", false);//查询未删除信息
        if (null != dto.getDeliverId()) {
            queryWrapper.eq("re.deliver_id", dto.getDeliverId());
        }
        if (null != dto.getStaffId()) {
            queryWrapper.eq("re.staff_id", dto.getStaffId());
        }
        if (null != dto.getCategoryId()) {
            queryWrapper.eq("re.category_id", dto.getCategoryId());
        }
        if (null != dto.getSpecificationId()) {
            queryWrapper.eq("re.specification_id", dto.getSpecificationId());
        }
        return mapper.listDeliverByCondition(page, queryWrapper);
    }


    @Transactional
    public Message addDeliver(Deliver model,User user) {
        Message message = new Message();
        RepertoryDTO dto = new RepertoryDTO();
        model.setShopId(user.getShopId());
        model.setCreatetime(new Date());
        model.setModifytime(new Date());
        model.setCreator(user.getName());
        model.setMender(user.getName());
        dto.setCategoryId(model.getCategoryId());
        dto.setSpecificationId(model.getSpecificationId());
        Repertory repertory = new Repertory();
        repertory.setCategoryId(model.getCategoryId());
        repertory.setSpecificationId(model.getSpecificationId());
        repertory.setShopId(user.getShopId());
        try {
            IPage<Repertory> list = repertoryService.listRepertoryByCondition(dto, user);
            //如果库存有相同的类别规格，数量相加
            if (list.getTotal() > 0) {
                repertory.setSum(model.getSum().add(list.getRecords().get(0).getSum()));
                int strval1 = repertoryService.UpdateRepertory(repertory);
                int strval2 = mapper.insert(model);
                if (strval1 > 0 && strval2 > 0) {
                    message.setState(UtilConstants.ResponseCode.SUCCESS_HEAD);
                    message.setMessage(UtilConstants.ResponseMsg.SUCCESS);
                } else {
                    message.setState(UtilConstants.ResponseCode.EXCEPTION_HEAD);
                    message.setMessage(UtilConstants.ResponseMsg.FAILED);
                }
            } else {
                //否则插入库存
                repertory.setCreatetime(new Date());
                repertory.setModifytime(new Date());
                repertory.setSum(model.getSum());
                int strval1 = repertoryService.addRepertory(repertory);
                int strval2 = mapper.insert(model);
                if (strval1 > 0 && strval2 > 0) {
                    message.setState(UtilConstants.ResponseCode.SUCCESS_HEAD);
                    message.setMessage(UtilConstants.ResponseMsg.SUCCESS);
                } else {
                    message.setState(UtilConstants.ResponseCode.EXCEPTION_HEAD);
                    message.setMessage(UtilConstants.ResponseMsg.FAILED);
                }
            }
            return message;
        } catch (Exception e) {
            message.setData(e.getMessage());
            message.setState(UtilConstants.ResponseCode.EXCEPTION_HEAD);
            message.setMessage(UtilConstants.ResponseMsg.FAILED);
            return message;
        }

    }
    }



