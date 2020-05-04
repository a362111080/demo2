package com.zw.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zw.beans.Partner;
import com.zw.beans.User;
import com.zw.dao.PartnerMapper;
import com.zw.requestDTO.PartnerRequestDTO;
import com.zw.service.IPartnerService;
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
@RequestMapping("/partner")
public class PartnerController {

    @Autowired
    private IPartnerService partnerService;
    @Autowired
    private HttpServletRequest request;
    /**
     * 分页查询
     * @param
     * @param model
     * @return
     */
    @RequestMapping(value = "/getPartnerlist", method = RequestMethod.POST)
    @ResponseBody
    public Message getPartnerList(PartnerRequestDTO model) {
        Message ms = new Message();
        User user = (User)request.getSession().getAttribute("user");
        if (null != user && null != user.getShopId()) {
            PageHelper.startPage(model.getCurrent().intValue(), model.getSize().intValue());
            model.setShopId(user.getShopId());
            List<Partner> partners = partnerService.GetPartnerList(model);
            PageInfo<com.zw.beans.Partner> pageInfo = new PageInfo<>(partners);
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
    @RequestMapping(value = "/addPartner", method = RequestMethod.POST)
    @ResponseBody
    public Message AddPartner(Partner model) {
        Message message = new Message();
        User user = (User) request.getSession().getAttribute("user");
        try {
            if (null != model && null != model.getName() && checkShopAndCompanyExist(user, model)) {
                model.setCreatetime(new Date());
                PartnerRequestDTO  dto=new  PartnerRequestDTO();
                dto.setShopId(user.getShopId());
                dto.setName(model.getName());
                List<Partner> issup=partnerService.GetPartnerList(dto);
                if (issup.size()==0) {
                    int strval = partnerService.AddPartner(model);
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

    @RequestMapping(value = "/updatePartner", method = RequestMethod.POST)
    @ResponseBody
    public Message UpdatePartner(Partner model) {
        Message message = new Message();
        User user = (User) request.getSession().getAttribute("user");
        try {
            if (null != model && null != model.getName() && checkShopAndCompanyExist(user, model)) {
                model.setModifytime(new Date());
            }
            PartnerRequestDTO  dto=new  PartnerRequestDTO();
            dto.setShopId(user.getShopId());
            dto.setName(model.getName());
            List<Partner> issup=partnerService.GetPartnerList(dto);
            if (issup.size()>0 ) {
                if (issup.get(0).getPartnerId().equals(model.getPartnerId())) {
                    int strval = partnerService.UpdatePartner(model);
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
                int strval = partnerService.UpdatePartner(model);
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
     * @Param [PartnerRequestDTO]
     * @Return java.lang.String
     **/
    @RequestMapping(value = "/delPartner", method = RequestMethod.POST)
    @ResponseBody
    public Message batchDeleteEggType(PartnerRequestDTO Partner) {
        Message message = new Message();
        try {
            if (null != Partner.getIds()) {
                partnerService.DeletePartner(Partner);
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
    private boolean checkShopAndCompanyExist(User user, Partner Partner) {
        boolean flag = (null != user.getShopId() &&null!=Partner.getCityId()) ? true : false;
        Partner.setShopId(user.getShopId());
        Partner.setModifytime(new Date());
        return flag;
    }

}

