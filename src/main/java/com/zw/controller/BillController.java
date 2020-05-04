package com.zw.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zw.beans.Bill;
import com.zw.beans.User;
import com.zw.enums.TaskEnums;
import com.zw.requestDTO.BillRequestDTO;
import com.zw.requestDTO.BillRequestGoodsDTO;
import com.zw.responseDTO.BillReport;
import com.zw.service.IBillService;
import com.zw.tool.Message;
import com.zw.tool.UtilConstants;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yzj
 * @since 2019-11-20
 */
@Controller
@RequestMapping("/bill")
public class BillController {

    @Autowired
    private IBillService billService;
    @Autowired
    private HttpServletRequest request;

    @ApiOperation(value = "查询账单列表", notes = "含多条件查询")
    @RequestMapping(value = "/getBilllist", method = RequestMethod.POST)
    @ResponseBody
    public Message GetBillList(BillRequestDTO model) {
        Message ms = new Message();
        User user = (User) request.getSession().getAttribute("user");
        model.setShopId(user.getShopId());
        PageHelper.startPage(model.getCurrent().intValue(), model.getSize().intValue());
        PageHelper.startPage(model.getCurrent().intValue(), model.getSize().intValue());
        if (null != model.getEndtime() && model.getEndtime() != "") {
            model.setEndtime(model.getEndtime() + " 23:59:59");
        }
        List<Bill> BillListList = billService.getBilllist(model);
        PageInfo<Bill> pageInfo = new PageInfo<>(BillListList);
        ms.setData(pageInfo);

        List<Bill> BillList = billService.getBilllist(model);
        BillReport report = new BillReport();
        for (int i = 0; i < BillList.size(); i++) {
            if (BillList.get(i).getStatus().equals("0")) {
                //1未结清  0 已结清
                report.setCompleteCount(report.getCompleteCount().add(BigDecimal.ONE));
            } else {
                report.setUnCompleteCount(report.getUnCompleteCount().add(BigDecimal.ONE));
            }
            if (BillList.get(i).getType().equals(TaskEnums.Type.Unload.index().toString())) {
                //支出
                report.setInCount(report.getInCount().add(BillList.get(i).getPrice()));
                report.setInPcs(report.getInPcs().add(BillList.get(i).getQuantity()));
            }
            if (!BillList.get(i).getType().equals(TaskEnums.Type.Unload.index().toString())) {
                //收入
                if (null != BillList.get(i).getPrice()) {
                    report.setOutCount(report.getOutCount().add(BillList.get(i).getPrice()));
                }
                if (null != BillList.get(i).getQuantity()) {
                    report.setOutPcs(report.getOutPcs().add(BillList.get(i).getQuantity()));
                }
            }
        }
        ms.setTotaldata(report);
        ms.setState(UtilConstants.ResponseCode.SUCCESS_HEAD);
        ms.setMessage(UtilConstants.ResponseMsg.SUCCESS);
        return ms;
    }
    @RequestMapping(value = "/addbillandbilldetails", method = RequestMethod.POST)
    @ResponseBody
    public Message addBillAndbillDetails(BillRequestGoodsDTO model) {
        Message message = new Message();
        User user = (User) request.getSession().getAttribute("user");
        try {
            if (model != null && user != null&&checkShopAndCompanyExist(model,user)) {
                return billService.addBillAndbillDetails(model, user);
            } else {
                message.setState(UtilConstants.ResponseCode.SUCCESS_HEAD);
                message.setMessage(UtilConstants.ResponseMsg.PARAM_MISSING);
                return message;
            }

        } catch (Exception e) {
            message.setState(UtilConstants.ResponseCode.EXCEPTION_HEAD);
            message.setMessage(UtilConstants.ResponseMsg.FAILED);
            return message;
        }

    }
    private boolean checkShopAndCompanyExist(BillRequestGoodsDTO model ,User user) {
        boolean flag = (null != user.getShopId() &&null!=user.getName()
                &&null!=model.getType()&&model.getBillDetailsGoodsRequestDTOList().size()>0) ? true : false;
        return flag;
    }


}



