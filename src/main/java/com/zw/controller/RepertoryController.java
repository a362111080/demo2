package com.zw.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zw.beans.Repertory;
import com.zw.beans.User;
import com.zw.requestDTO.RepertoryDTO;
import com.zw.responseDTO.RepertoryResponse;
import com.zw.service.IRepertoryService;
import com.zw.tool.Message;
import com.zw.tool.UtilConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yzj
 * @since 2019-11-15
 */
@Controller
@RequestMapping("/repertory")
public class RepertoryController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private IRepertoryService repertoryService;

    @ResponseBody
    @RequestMapping(value = "/listrepertorybycondition", method = RequestMethod.POST)
    public Message<IPage<Repertory>> listRepertoryByCondition(RepertoryDTO dto) {
        Message<IPage<Repertory>> message = new Message<IPage<Repertory>>();
        try {
            User user = (User) request.getSession().getAttribute("user");
            if (null == user || null == user.getShopId() || "" == user.getShopId()) {
                message = new Message();
                message.setState(UtilConstants.ResponseCode.EXCEPTION_HEAD);
                message.setMessage(UtilConstants.ResponseMsg.NOT_LOGGED);
                return message;
            }
            IPage<Repertory> list = repertoryService.listRepertoryByCondition(dto, user);
            message.setData(list);
            message.setState(UtilConstants.ResponseCode.SUCCESS_HEAD);
            message.setMessage(UtilConstants.ResponseMsg.SUCCESS);
        } catch (Exception e) {
            message.setState(UtilConstants.ResponseCode.EXCEPTION_HEAD);
            message.setMessage(UtilConstants.ResponseMsg.FAILED);
        }
        return message;
    }

}

