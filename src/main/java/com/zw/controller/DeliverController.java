package com.zw.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.zw.beans.Deliver;
import com.zw.beans.Repertory;
import com.zw.beans.User;
import com.zw.requestDTO.DeliverRequestDTO;
import com.zw.requestDTO.RepertoryDTO;
import com.zw.service.IDeliverService;
import com.zw.service.IRepertoryService;
import com.zw.tool.Message;
import com.zw.tool.UtilConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yzj
 * @since 2019-11-19
 */
@Controller
@RequestMapping("/deliver")
public class DeliverController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private IDeliverService deliverService;

    @Autowired
    private IRepertoryService repertoryService;

    @RequestMapping(value = "/listdeliverbycondition", method = RequestMethod.POST)
    @ResponseBody
    public Message<IPage<Deliver>> listRepertoryByCondition(DeliverRequestDTO dto) {
        Message<IPage<Deliver>> message = new Message<IPage<Deliver>>();
        try {
            User user = (User) request.getSession().getAttribute("user");
            if (null == user || null == user.getShopId() || "" == user.getShopId()) {
                message = new Message();
                message.setState(UtilConstants.ResponseCode.EXCEPTION_HEAD);
                message.setMessage(UtilConstants.ResponseMsg.NOT_LOGGED);
                return message;
            }
            IPage<Deliver> list = deliverService.listDeliverByCondition(dto, user);
            message.setData(list);
            message.setState(UtilConstants.ResponseCode.SUCCESS_HEAD);
            message.setMessage(UtilConstants.ResponseMsg.SUCCESS);
        } catch (Exception e) {
            message.setState(UtilConstants.ResponseCode.EXCEPTION_HEAD);
            message.setMessage(UtilConstants.ResponseMsg.FAILED);
        }
        return message;
    }

    @RequestMapping(value = "/adddeliver", method = RequestMethod.POST)
    @ResponseBody
    public Message addDeliver(Deliver model) {
        Message message = new Message();
        try {
            User user = (User) request.getSession().getAttribute("user");
            if (null == user || null == user.getShopId() || "" == user.getShopId()) {
                message = new Message();
                message.setState(UtilConstants.ResponseCode.EXCEPTION_HEAD);
                message.setMessage(UtilConstants.ResponseMsg.NOT_LOGGED);
                return message;
            }
            if (null != model && null != model.getStaffId() && null != model.getCategoryId() && null != model.getSpecificationId() && null != model.getSum()) {
                message = deliverService.addDeliver(model, user);
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

