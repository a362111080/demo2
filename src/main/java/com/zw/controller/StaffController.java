package com.zw.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zw.beans.Staff;
import com.zw.beans.User;
import com.zw.requestDTO.StaffRequestDTO;
import com.zw.service.IStaffService;
import com.zw.tool.Message;
import com.zw.tool.UtilConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yzj
 * @since 2019-11-18
 */
@Controller
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private IStaffService staffService;

    /**
     * 添加员工
     */
    @RequestMapping(value = "/addstaff", method = RequestMethod.POST)
    @ResponseBody
    public Message AddSupplier(Staff model) {
        Message message = new Message();
        try {
            User user = (User) request.getSession().getAttribute("user");
            if (null != model && null != model.getName() && null != model.getPhone() && null != user.getShopId()) {
                model.setModifytime(new Date());
                model.setShopId(user.getShopId());
                model.setCreator(user.getName());
                model.setCreatetime(new Date());
                StaffRequestDTO dto = new StaffRequestDTO();
                dto.setName(user.getName());
                IPage<Staff> iPage = staffService.listRepertoryByCondition(dto, user);
                if (iPage.getTotal() == 0) {
                    int strval = staffService.addStaff(model);
                    if (strval > 0) {
                        message.setState(UtilConstants.ResponseCode.SUCCESS_HEAD);
                        message.setMessage(UtilConstants.ResponseMsg.SUCCESS);
                    } else {
                        message.setState(UtilConstants.ResponseCode.EXCEPTION_HEAD);
                        message.setMessage(UtilConstants.ResponseMsg.FAILED);
                    }
                } else {
                    message.setState(UtilConstants.ResponseCode.EXCEPTION_HEAD);
                    message.setMessage("员工名称重复");
                }
            } else {
                message.setState(UtilConstants.ResponseCode.EXCEPTION_HEAD);
                message.setMessage(UtilConstants.ResponseMsg.PARAM_ERROR);
            }
            return message;
        } catch (Exception e) {
            message.setState(UtilConstants.ResponseCode.EXCEPTION_HEAD);
            message.setMessage(UtilConstants.ResponseMsg.FAILED);
            return message;
        }
    }

    @RequestMapping(value = "/getstafflist", method = RequestMethod.POST)
    @ResponseBody
    public Message getSupplierList(StaffRequestDTO model) {
        Message message = new Message();
        try {
            User user = (User) request.getSession().getAttribute("user");
            if (null == user || null == user.getShopId() || "" == user.getShopId()) {
                message = new Message();
                message.setState(UtilConstants.ResponseCode.EXCEPTION_HEAD);
                message.setMessage(UtilConstants.ResponseMsg.NOT_LOGGED);
                return message;
            }
            IPage<Staff> iPage = staffService.listRepertoryByCondition(model, user);
            message.setData(iPage);
            message.setState(UtilConstants.ResponseCode.SUCCESS_HEAD);
            message.setMessage(UtilConstants.ResponseMsg.SUCCESS);
        } catch (Exception e) {
            message.setState(UtilConstants.ResponseCode.EXCEPTION_HEAD);
            message.setMessage(UtilConstants.ResponseMsg.FAILED);
        }
        return message;
    }

    @RequestMapping(value = "/updatestaff", method = RequestMethod.POST)
    @ResponseBody
    public Message UpdateStaff(Staff model) {
        Message message = new Message();
        User user = (User) request.getSession().getAttribute("user");
        try {
            if (null != model && null != model.getName() && null != user && null != user.getShopId()) {
                model.setModifytime(new Date());
                model.setShopId(user.getShopId());
                StaffRequestDTO dto = new StaffRequestDTO();
                dto.setName(model.getName());
                IPage<Staff> iPage = staffService.listRepertoryByCondition(dto, user);
                List<Staff> records = iPage.getRecords();
                if (records.size() > 0) {
                    if (records.get(0).getStaffId().equals(model.getStaffId())) {
                        int strval = staffService.updateStaff(model);
                        if (strval > 0) {
                            message.setState(UtilConstants.ResponseCode.SUCCESS_HEAD);
                            message.setMessage(UtilConstants.ResponseMsg.SUCCESS);
                        } else {
                            message.setState(UtilConstants.ResponseCode.EXCEPTION_HEAD);
                            message.setMessage(UtilConstants.ResponseMsg.FAILED);
                        }
                    } else {
                        message.setState(UtilConstants.ResponseCode.EXCEPTION_HEAD);
                        message.setMessage("已存在");
                    }
                } else {
                    int strval = staffService.updateStaff(model);
                    if (strval > 0) {
                        message.setState(UtilConstants.ResponseCode.SUCCESS_HEAD);
                        message.setMessage(UtilConstants.ResponseMsg.SUCCESS);
                    } else {
                        message.setState(UtilConstants.ResponseCode.EXCEPTION_HEAD);
                        message.setMessage(UtilConstants.ResponseMsg.FAILED);
                    }
                }
            } else {
                message.setState(UtilConstants.ResponseCode.EXCEPTION_HEAD);
                message.setMessage(UtilConstants.ResponseMsg.PARAM_MISSING);
            }
            return message;
        } catch (Exception e) {
            message.setState(UtilConstants.ResponseCode.EXCEPTION_HEAD);
            message.setMessage(UtilConstants.ResponseMsg.FAILED);
            return message;
        }
    }

    @RequestMapping(value = "/detelestaff", method = RequestMethod.POST)
    @ResponseBody
    public Message deteleStaff(StaffRequestDTO model) {
        Message message=new Message();
        try {
            if (null != model.getIds()) {
                staffService.deteleStaff(model);
                message.setState(UtilConstants.ResponseCode.SUCCESS_HEAD);
                message.setMessage(UtilConstants.ResponseMsg.SUCCESS);
            } else {
                message.setState(UtilConstants.ResponseCode.EXCEPTION_HEAD);
                message.setMessage(UtilConstants.ResponseMsg.ATLEAST_ONE);
            }
            return message;
        } catch (Exception e) {
            message.setState(UtilConstants.ResponseCode.EXCEPTION_HEAD);
            message.setMessage(UtilConstants.ResponseMsg.FAILED);
            return message;
        }
    }
}



