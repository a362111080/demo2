package com.zw.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zw.beans.Specification;
import com.zw.beans.Supplier;
import com.zw.beans.User;
import com.zw.requestDTO.SpecificationRequestDTO;
import com.zw.service.ISpecificationService;
import com.zw.service.impl.SpecificationService;
import com.zw.tool.Message;
import com.zw.tool.UtilConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping("/specification")
public class SpecificationController {

    @Autowired
    private ISpecificationService specificationService;
    @Autowired
    private HttpServletRequest request;
    /**
     * 分页查询
     * @param
     * @param model
     * @return
     */
    @RequestMapping(value = "/getspecificationlist", method = RequestMethod.POST)
    @ResponseBody
    public Message getSupplierList(SpecificationRequestDTO model) {
        Message ms = new Message();
        User user = (User) request.getSession().getAttribute("user");
        if (null != user && null != user.getShopId() && null != model) {
            PageHelper.startPage(model.getCurrent().intValue(), model.getSize().intValue());
            model.setShopId(user.getShopId());
            List<Specification> specifications = specificationService.GetSpecificationList(model);
            PageInfo<com.zw.beans.Specification> pageInfo = new PageInfo<>(specifications);
            ms.setData(pageInfo);
            ms.setState(UtilConstants.ResponseCode.SUCCESS_HEAD);
            return ms;
        }else {
            ms.setState(UtilConstants.ResponseCode.EXCEPTION_HEAD);
            ms.setMessage(UtilConstants.ResponseMsg.NOT_LOGGED);
            return ms;
        }
    }
    /**
     * 新增规格
     * @param model
     * @return
     */
    @RequestMapping(value = "/addspecification", method = RequestMethod.POST)
    @ResponseBody
    public Message AddSpecification(Specification model) {
        Message message = new Message();
        User user = (User) request.getSession().getAttribute("user");
        try {
            if (null != model && null != model.getName() && model.getCategory().getName()!=null) {
                model.setCreatetime(new Date());
                model.setShopId(user.getShopId());
                model.setCreator(user.getName());
                SpecificationRequestDTO dto = new SpecificationRequestDTO();
                dto.setShopId(user.getShopId());
                dto.setCategoryId(model.getCategory().getCategoryId());
                dto.setName(model.getName());
                List<Specification> issup = specificationService.GetSpecificationList(dto);
                if (issup.size()==0) {
                    int strval = specificationService.AddSpecification(model);
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
                    message.setMessage("数据重复");
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

    /**
     * 更新
     */
    @RequestMapping(value = "/updatespecification", method = RequestMethod.POST)
    @ResponseBody
    public Message updateSpecification(Specification model) {
        Message message = new Message();
        User user = (User) request.getSession().getAttribute("user");
        try {
            if (null != model && null != model.getName() && null != model.getCategory() && null != model.getCategory().getCategoryId()
                    && checkShopAndCompanyExist(user)) {
                model.setShopId(user.getShopId());
                model.setModifytime(new Date());
                SpecificationRequestDTO dto = new SpecificationRequestDTO();
                dto.setShopId(user.getShopId());
                dto.setName(model.getName());
                dto.setCategoryId(model.getCategory().getCategoryId());
                List<Specification> spe = specificationService.GetSpecificationList(dto);
                if (spe.size() > 0) {
                    if (spe.get(0).getSpecificationId().equals(model.getSpecificationId())) {
                        int strval = specificationService.UpdateSpecification(model);
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
                } else {
                    int strval = specificationService.UpdateSpecification(model);
                    if (strval > 0) {
                        message.setState(UtilConstants.ResponseCode.SUCCESS_HEAD);
                        message.setMessage(UtilConstants.ResponseMsg.SUCCESS);
                    } else {
                        message.setState(UtilConstants.ResponseCode.EXCEPTION_HEAD);
                        message.setMessage(UtilConstants.ResponseMsg.FAILED);
                    }
                }
                return message;
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
    /**
     * @Description 批量删
     * @Param [SupplierRequestDTO]
     * @Return java.lang.String
     **/
    @RequestMapping(value = "/delspecification", method = RequestMethod.POST)
    @ResponseBody
    public Message batchDeleteEggType(SpecificationRequestDTO model) {
        Message message = new Message();
        try {
            if (null != model.getIds()) {
                specificationService.DeleteSpecification(model);
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
        private boolean checkShopAndCompanyExist(User user) {
            boolean flag = (null != user.getShopId() &&null!=user) ? true : false;
            return flag;
        }


}

