package com.zw.controller;


import com.zw.beans.Bill;
import com.zw.beans.User;
import com.zw.service.IBillDetailsService;
import com.zw.tool.Message;
import com.zw.tool.UtilConstants;
import io.swagger.annotations.ApiOperation;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author yzj
 * @since 2019-11-20
 */
@Controller
@RequestMapping("/billDetails")
public class BillDetailsController {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private IBillDetailsService billDetailsService;

    @RequestMapping(value = "/getbilldetsils", method = RequestMethod.POST)
    @ResponseBody
    public Message getByBillId(Bill bill) {
        Message ms = new Message();
        //当前登录用户
        User user = (User) request.getSession().getAttribute("user");
        try {
            bill.setShopId(user.getShopId());
            ms = billDetailsService.getbilldetsils(bill);
        } catch (Exception e) {
            ms.setState(UtilConstants.ResponseCode.EXCEPTION_HEAD);
            ms.setMessage(UtilConstants.ResponseMsg.FAILED);
            if (!(e instanceof ServiceException)) {
                ms.setState(UtilConstants.ResponseCode.EXCEPTION_HEAD);
                ms.setMessage(UtilConstants.ResponseMsg.FAILED);
                ms.setMessage(e.getMessage());
            }
        }
        return ms;
    }

}
