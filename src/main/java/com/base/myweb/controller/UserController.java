package com.base.myweb.controller;

import com.alibaba.fastjson.JSONObject;
import com.base.myweb.Tools.Charset;
import com.base.myweb.pojo.Userinfo;
import com.base.myweb.service.serviceImpl.UserInfoServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 个人操作控制逻辑
 * 如登录、注册、查看修改个人信息等
 *
 *
 * */

@Controller
@Slf4j
@RequestMapping("")
public class UserController {

    @Autowired
    UserInfoServiceImpl userService;
    /**
     * 登录界面
     *
     */

    @RequestMapping("/user/login")
    public String login(Map<String ,Object> map){

        return "user/login";
    }

    /**
     * 注册界面
     *
     */

    @RequestMapping("/user/regisiter")
    public String regisiter(Map<String ,Object> map){

        return "user/regisiter";
    }

    /**
     * @param email 邮箱
     * 校验邮箱是否被使用，一个邮箱被使用一次
     *
     */
    @RequestMapping(value = "/user/checkMailExist" , method = RequestMethod.POST , produces="application/json;charset=utf-8")
    @ResponseBody
    public String checkMailExist(@RequestParam(name = "email",required = true)String email){
        return userService.checkEmailIsExist(email);
    }

    /**
     * @param email 邮箱，登录标志
     * @param username  用户名
     * @param pass 密码
     * */
    @ResponseBody
    @RequestMapping(value = "/user/regInfo" , method = RequestMethod.POST , produces="application/json;charset=utf-8")
    public ModelAndView regInfo(@RequestParam(name = "email",required = true)String email, @RequestParam(name = "username",required = true)String username,
                                @RequestParam(name = "pass",required = true)String pass,HttpSession session ){
        Userinfo currUser = userService.regInfo(email,username,pass,session);
        return  new ModelAndView("user/index","userInfo",currUser);
    }

    /**
     * @param email 邮箱，登录标志
     * @param pass 密码
     * */
    @ResponseBody
    @RequestMapping(value = "/user/auth" , method = RequestMethod.POST , produces="application/json;charset=utf-8")
    public ModelAndView auth(@RequestParam(name = "email",required = true)String email, @RequestParam(name = "pass",required = true)String pass,HttpSession session){
        JSONObject authJo = userService.LoginAuth(email,pass,session);
        if(authJo!=null&&!"".equals(Charset.nullToEmpty((String) authJo.get("result")))){
            return  new ModelAndView("index","userInfo",authJo);
        }else{
            return  new ModelAndView("user/login","userInfo",authJo);
        }

    }

    @RequestMapping(value = "/user/personcenter" , method = RequestMethod.POST , produces="application/json;charset=utf-8")
    public ModelAndView personcenter(){
        return  new ModelAndView("user/personcenter");
    }

    @RequestMapping("/user/index")
        public String userIndex(Map<String ,Object> map){

        return "user/index";
    }

    @RequestMapping("/user/index/collection")
    public String userCollection(Map<String ,Object> map){

        return "/user/index/collection";
    }

    @RequestMapping("/user/message")
    public String message(Map<String ,Object> map){
        ;
        return "/user/message";
    }

    @RequestMapping("/user/home")
    public String home(Map<String ,Object> map){

        return "/user/home";
    }

    @RequestMapping("/user/set")
    public String set(Map<String ,Object> map){

        return "/user/set";
    }

}
