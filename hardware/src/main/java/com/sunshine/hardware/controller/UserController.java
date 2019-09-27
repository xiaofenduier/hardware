package com.sunshine.hardware.controller;

import com.sunshine.hardware.model.User;
import com.sunshine.hardware.service.UserService;
import com.sunshine.hardware.util.RandomValidateCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author liujie
 * @version 创建时间：2017年8月28日 下午5:09:33
 * 类说明  登录类
 */

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("getRandomValidateCode")
    @ResponseBody
    public void getRandomValidateCode(HttpServletRequest request, HttpServletResponse response){
        //设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        RandomValidateCodeUtil randomValidateCode = new RandomValidateCodeUtil();
        try {
            //输出图片方法
            String randomValidateCodeStr = randomValidateCode.getRandcode(request, response);
            request.getSession().setAttribute("randomValidateCodeStr", randomValidateCodeStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ResponseBody
    @RequestMapping("login")
    public int loginIn(User user, String login_randomValidateCode, HttpServletRequest request){
        String randomValidateCodeStr = (String) request.getSession().getAttribute("randomValidateCodeStr");
        if(randomValidateCodeStr.compareToIgnoreCase(login_randomValidateCode) != 0){
            return -1;
        }
        User loginUser = userService.login(user.getUsername(), user.getPassword());
        if(loginUser != null){
            request.getSession().setAttribute("username", loginUser.getUsername());
            return 1;
        }else{
            return 0;
        }
    }

    @ResponseBody
    @RequestMapping("getLoginUser")
    public String getLoginUser(HttpServletRequest request){
        if(request.getSession().getAttribute("username") == null){
            return "--";
        }else{
            return request.getSession().getAttribute("username").toString();
        }
    }

    @ResponseBody
    @RequestMapping("logout")
    public int logout(HttpServletRequest request){
        request.getSession().removeAttribute("username");
        return 1;
    }
}
