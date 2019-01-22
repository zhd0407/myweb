package com.base.myweb.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.base.myweb.mapper.UserMapper;
import com.base.myweb.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;
import java.util.Date;
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
    private UserMapper usermapper;

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
        User user = new User();
        user.setEmail(email);
        QueryWrapper qw = new QueryWrapper();
        qw.eq("EMAIL", user.getEmail());
        int mailCount = usermapper.selectCount(qw);

        JSONObject ja = new JSONObject();
        String result = "success";
        String msg = "";
        if (mailCount>0){
         result = "fail";
         msg = "该邮箱已经被使用";
        }
        ja.put("result",result);
        ja.put("msg",msg);
        return ja.toString();
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
        User user = new User();
        user.setEmail(email);
        QueryWrapper qw = new QueryWrapper();
        qw.eq("EMAIL", user.getEmail());
        int mailCount = usermapper.selectCount(qw);
        User currUser = null;
        if (mailCount<=0){
            user.setUsernam(username);
            user.setPassword(pass);
            user.setIdentitytype("customer");
            user.setCreatedate(new Date());
            usermapper.insert(user);
            currUser = usermapper.selectOne(qw);
            session.setAttribute("userInfo",user);
            session.setAttribute("userId",user.getUserno()+"");
        }else{
         System.out.println("该邮箱已经被使用");
        }

        return  new ModelAndView("user/index","userInfo",currUser);
    }

    /**
     * @param email 邮箱，登录标志
     * @param pass 密码
     * */
    @ResponseBody
    @RequestMapping(value = "/user/auth" , method = RequestMethod.POST , produces="application/json;charset=utf-8")
    public ModelAndView auth(@RequestParam(name = "email",required = true)String email, @RequestParam(name = "pass",required = true)String pass,HttpSession session){
        QueryWrapper qw = new QueryWrapper();
        qw.eq("EMAIL", email);
        User user = usermapper.selectOne(qw);
        JSONObject jo = new JSONObject();
        if(user!=null&&user.getPassword().equals(pass)){
            jo.put("result","success");
            jo.put("userInfo",user);
            session.setAttribute("userInfo",user);
            session.setAttribute("userId",user.getUserno()+"");
            return  new ModelAndView("index","userInfo",jo);
        }else{
            jo.put("result","fail");
            jo.put("msg","用户名或密码错误");
            return  new ModelAndView("user/login","userInfo",jo);
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

}
