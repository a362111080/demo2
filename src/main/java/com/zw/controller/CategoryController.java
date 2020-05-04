package com.zw.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zw.beans.Category;
import com.zw.beans.User;
import com.zw.requestDTO.CategoryRequestDTO;
import com.zw.service.ICategoryService;
import com.zw.tool.Message;
import com.zw.tool.UtilConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;
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
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ICategoryService categoryService;

    @RequestMapping(value = "/getCategorylist", method = RequestMethod.POST)
    @ResponseBody
    public Message getCategorylist(CategoryRequestDTO model) {
        Message ms = new Message();
        User user = (User) request.getSession().getAttribute("user");
        if (null != user && null != user.getShopId()) {
            PageHelper.startPage(model.getCurrent().intValue(), model.getSize().intValue());
            model.setShopId(user.getShopId());
            List<Category> categories = categoryService.GetCategoryList(model);
            PageInfo<com.zw.beans.Category> pageInfo = new PageInfo<>(categories);
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
     * 新增品种
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/addCategory", method = RequestMethod.POST)
    @ResponseBody
    public Message AddPartner(Category model) {
        Message message = new Message();
        User user = (User) request.getSession().getAttribute("user");
        try {
            if (null != model && null != model.getName()) {
                model.setModifytime(new Date());
                model.setCreatetime(new Date());
                model.setCreator(user.getName());
                model.setShopId(user.getShopId());
                CategoryRequestDTO dto = new CategoryRequestDTO();
                dto.setShopId(user.getShopId());
                dto.setName(model.getName());
                List<Category> categories = categoryService.GetCategoryList(dto);
                if (categories.size() == 0) {
                    int adds = categoryService.AddCategory(model);
                    if (adds > 0) {
                        message.setState(UtilConstants.ResponseCode.SUCCESS_HEAD);
                        message.setMessage(UtilConstants.ResponseMsg.SUCCESS);
                    } else {
                        message.setState(UtilConstants.ResponseCode.EXCEPTION_HEAD);
                        message.setMessage(UtilConstants.ResponseMsg.FAILED);
                    }
                } else {
                    message.setState(UtilConstants.ResponseCode.EXCEPTION_HEAD);
                    message.setMessage("品种名称重复");
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
     * @Description 更新品种
     * @Return 是否成功
     **/

    @RequestMapping(value = "/updateCategory", method = RequestMethod.POST)
    @ResponseBody
    public Message UpdatePartner(Category model) {
        Message message = new Message();
        User user = (User) request.getSession().getAttribute("user");
        try {
            if (null != model && null != model.getName()&&null!=user&&null!=user.getShopId()) {
                model.setModifytime(new Date());
                model.setShopId(user.getShopId());
            }
            CategoryRequestDTO dto = new CategoryRequestDTO();
            dto.setShopId(user.getShopId());
            dto.setName(model.getName());
            List<Category> categories = categoryService.GetCategoryList(dto);
            if (categories.size() > 0) {
                if (categories.get(0).getCategoryId().equals(model.getCategoryId())) {
                    int strval = categoryService.UpdateCategory(model);
                    if (strval > 0) {
                        message.setState(UtilConstants.ResponseCode.SUCCESS_HEAD);
                        message.setMessage(UtilConstants.ResponseMsg.SUCCESS);
                    } else {
                        message.setState(UtilConstants.ResponseCode.EXCEPTION_HEAD);
                        message.setMessage(UtilConstants.ResponseMsg.FAILED);
                    }
                } else {
                    message.setState(UtilConstants.ResponseCode.EXCEPTION_HEAD);
                    message.setMessage("品种名称重复");
                }
            } else {
                int strval = categoryService.UpdateCategory(model);
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
     * @Description 批量删除品种
     * @Param [SupplierRequestDTO]
     * @Return java.lang.String
     **/
    @RequestMapping(value = "/delcategory", method = RequestMethod.POST)
    @ResponseBody
    public Message batchDeleteEggType(CategoryRequestDTO dto) {
        Message message = new Message();
        try {
            if (null != dto.getIds()) {
                categoryService.DeleteCategory(dto);
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




