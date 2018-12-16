package com.base.myweb.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.base.myweb.Tools.Charset;
import com.base.myweb.mapper.UserMapper;
import com.base.myweb.pojo.User;
import com.sun.org.apache.xerces.internal.util.EntityResolverWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 个人操作控制逻辑
 * 如登录、注册、查看修改个人信息等
 *
 *
 * */

@Controller
@Slf4j
@RequestMapping("/page")
public class UserController {
    @Autowired
    private UserMapper usermapper;

    @RequestMapping("/user/login")
    public String login(Map<String ,Object> map){
        log.info("------------>login------------>"+new Date());
        return "user/login";
    }

    @RequestMapping("/user/regisiter")
    public String regisiter(Map<String ,Object> map){
        log.info("------------>regisiter------------>" + new Date());
        return "user/regisiter";
    }

    @RequestMapping(value = "/user/regInfo" , method = RequestMethod.POST , produces="application/json;charset=utf-8")
    public ModelAndView regInfo(@RequestParam(name = "email",required = true)String email, @RequestParam(name = "username",required = true)String username,
                                @RequestParam(name = "pass",required = true)String pass, @RequestParam(name = "repass",required = true)String repass){
        User user = new User();
        user.setEmail(email);
        QueryWrapper qw = new QueryWrapper();
        qw.eq("EMAIL", user.getEmail());
        int mailCount = usermapper.selectCount(qw);
        if (mailCount<=0){
            user.setUser_nam(username);
            user.setPassword(pass);
            usermapper.insert(user);
        }else{
         System.out.println("该邮箱已经被使用");
        }
        return  new ModelAndView("user/personcenter","User",user);
    }

    @RequestMapping("/user/index")
    public String userIndex(Map<String ,Object> map){
        log.info("------------>user/index------------>" + new Date());
        return "user/index";
    }

    @RequestMapping("/user/index/collection")
    public String userCollection(Map<String ,Object> map){
        log.info("------------>/user/index/collection------------>" + new Date());
        return "/user/index/collection";
    }

    @RequestMapping("/user/message")
    public String message(Map<String ,Object> map){
        log.info("------------>/user/message------------>" + new Date());
        return "/user/message";
    }

    @RequestMapping("/user/home")
    public String home(Map<String ,Object> map){
        log.info("------------>/user/home------------>" + new Date());
        return "/user/home";
    }

}
