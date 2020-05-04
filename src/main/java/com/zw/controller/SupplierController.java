package com.zw.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zw.beans.Supplier;
import com.zw.beans.User;
import com.zw.enums.SupplierEnum;
import com.zw.requestDTO.SupplierRequestDTO;
import com.zw.service.ISupplierService;
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
 * @since 2019-11-12
 */
@Controller
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    private ISupplierService supplierService;

    @Autowired
    private HttpServletRequest request;
    /**
     * 分页查询
     * @param
     * @param model
     * @return
     */
    @RequestMapping(value = "/getsupplierlist", method = RequestMethod.POST)
    @ResponseBody
    public Message getSupplierList(SupplierRequestDTO model) {
        Message ms = new Message();
        User user = (User) request.getSession().getAttribute("user");
        if (null != user && null != user.getShopId()) {
            PageHelper.startPage(model.getCurrent().intValue(), model.getSize().intValue());
            model.setShopId(user.getShopId());
            List<Supplier> suppliers = supplierService.GetSupplierList(model);
            PageInfo<com.zw.beans.Supplier> pageInfo = new PageInfo<>(suppliers);
            ms.setData(pageInfo);
            ms.setState(UtilConstants.ResponseCode.SUCCESS_HEAD);
            return ms;
        } else {
            ms.setState(UtilConstants.ResponseCode.EXCEPTION_HEAD);
            ms.setMessage(UtilConstants.ResponseMsg.NOT_LOGGED);
            return ms;
        }
    }


        /**
         * 新增供应商
         * @param model
         * @return
         */
    @RequestMapping(value = "/addsupplier", method = RequestMethod.POST)
    @ResponseBody
    public Message AddSupplier(Supplier model) {
        Message message = new Message();
        User user = (User) request.getSession().getAttribute("user");
        try {
            if (null != model && null != model.getName() && checkShopAndCompanyExist(user, model)) {
                model.setCreatetime(new Date());
                SupplierRequestDTO  dto=new  SupplierRequestDTO();
                dto.setShopId(user.getShopId());
                dto.setName(model.getName());
                List<Supplier> issup=supplierService.GetSupplierList(dto);
                if (issup.size()==0) {
                    int strval = supplierService.AddSupplier(model);
                    if (strval > 0) {
                        message.setState(UtilConstants.ResponseCode.SUCCESS_HEAD);
                        message.setMessage(UtilConstants.ResponseMsg.SUCCESS);
                    } else {
                        message.setState(UtilConstants.ResponseCode.EXCEPTION_HEAD);
                        message.setMessage(UtilConstants.ResponseMsg.FAILED);

                    }
                }
                else {
                    message.setState(UtilConstants.ResponseCode.EXCEPTION_HEAD);
                    message.setMessage("供应商名称重复");
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
    /**
     * @Description 更新供应商
     * @Return 是否成功
     **/

    @RequestMapping(value = "/updatesupplier", method = RequestMethod.POST)
    @ResponseBody
    public Message UpdateSupplier(Supplier model) {
        Message message = new Message();
        User user = (User) request.getSession().getAttribute("user");
        try {
            if (null != model && null != model.getName() && checkShopAndCompanyExist(user, model)) {
                model.setModifytime(new Date());
            }
            SupplierRequestDTO  dto=new  SupplierRequestDTO();
            dto.setShopId(user.getShopId());
            dto.setName(model.getName());
            List<Supplier> issup=supplierService.GetSupplierList(dto);
            if (issup.size()>0 ) {
                if (issup.get(0).getSupplierId().equals(model.getSupplierId())) {
                    int strval = supplierService.UpdateSupplier(model);
                    if (strval > 0) {
                        message.setState(UtilConstants.ResponseCode.SUCCESS_HEAD);
                        message.setMessage(UtilConstants.ResponseMsg.SUCCESS);
                    } else {
                        message.setState(UtilConstants.ResponseCode.EXCEPTION_HEAD);
                        message.setMessage(UtilConstants.ResponseMsg.FAILED);
                    }
                } else {
                    message.setState(UtilConstants.ResponseCode.EXCEPTION_HEAD);
                    message.setMessage("供应商名称重复");
                }
            }
            else
            {
                int strval = supplierService.UpdateSupplier(model);
                if (strval > 0) {
                    message.setState(UtilConstants.ResponseCode.SUCCESS_HEAD);
                    message.setMessage(UtilConstants.ResponseMsg.SUCCESS);
                } else {
                    message.setState(UtilConstants.ResponseCode.EXCEPTION_HEAD);
                    message.setMessage(UtilConstants.ResponseMsg.FAILED);
                }
            }
            return message;

        } catch (Exception e) {
            message.setState(UtilConstants.ResponseCode.EXCEPTION_HEAD);
            message.setMessage(UtilConstants.ResponseMsg.FAILED);
            //message.setMessage(e.getMessage());
            return message;
        }


    }
    /**
     * @Description 批量删除供应商
     * @Param [SupplierRequestDTO]
     * @Return java.lang.String
     **/
    @RequestMapping(value = "/delsupplier", method = RequestMethod.POST)
    @ResponseBody
    public Message batchDeleteEggType(SupplierRequestDTO supplier) {
        Message message = new Message();
        try {
            if (null != supplier.getIds()) {
                supplierService.DeleteSupplier(supplier);
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
            /**
             * 从当前登录用户信息中检查
             *
             */
            private boolean checkShopAndCompanyExist(User user, Supplier supplier) {
                boolean flag = (null != user.getShopId() &&null!=supplier.getCityId()) ? true : false;
                supplier.setShopId(user.getShopId());
                supplier.setModifytime(new Date());
                return flag;
            }
}


