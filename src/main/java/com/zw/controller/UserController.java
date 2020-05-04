package com.zw.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zw.beans.User;
import com.zw.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yzj
 * @since 2019-11-12
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    //登录
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> checklogin(String loginname
            , String loginPwd, HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        if (loginname != null && loginPwd != null) {
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("loginname", loginname)
                    .eq("password", loginPwd);
//                    .eq("dr", true);
            User user = userService.getOne(queryWrapper);
            if (user != null) {
                //若能取到账号信息则登陆成功
                modelMap.put("success", true);
                //同时在session里设置用户信息
                request.getSession().setAttribute("user", user);

            } else {
                modelMap.put("success", false);
                modelMap.put("errMsg", "用户名或密码错误");
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "用户名和密码均不能为空");
        }
        return modelMap;
    }
}





