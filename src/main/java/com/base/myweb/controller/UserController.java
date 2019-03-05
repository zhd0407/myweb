package com.base.myweb.controller;

import com.alibaba.fastjson.JSONObject;
import com.base.myweb.client.OrderClient;
import com.base.myweb.core.exception.R;
import com.base.myweb.pojo.Userinfo;
import com.base.myweb.service.serviceImpl.MailServiceImpl;
import com.base.myweb.service.serviceImpl.SubjectServiceImpl;
import com.base.myweb.service.serviceImpl.UserInfoServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
/*import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;*/
/*import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;*/
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
/*import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestTemplate;*/
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
    @Autowired
    SubjectServiceImpl subjectService;
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

    @RequestMapping(value = "/user/regInfo" , method = RequestMethod.POST , produces="application/json;charset=utf-8")
    public ModelAndView regInfo(@RequestParam(name = "email",required = true)String email, @RequestParam(name = "username",required = true)String username,
                                @RequestParam(name = "pass",required = true)String pass,HttpSession session ){
        Userinfo currUser = userService.regInfo(email,username,pass,session);
        return  new ModelAndView("user/index","userInfo",currUser);
    }

    /**
     * @param username 邮箱，登录标志
     * @param password 密码
     * */
    @ResponseBody
    @RequestMapping(value = "/user/auth" , method = RequestMethod.POST , produces="application/json;charset=utf-8")
    public JSONObject auth(@RequestParam(name = "username",required = true)String username, @RequestParam(name = "password",required = true)String password,HttpSession session){
        JSONObject authJo = userService.LoginAuth(username,password,session);
       return  authJo;
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

    @RequestMapping("/user/model")
    public String model(Map<String ,Object> map){

        return "/user/model";
    }

    @ResponseBody
    @RequestMapping(value = "/user/table/subject" , method = RequestMethod.GET , produces="application/json;charset=utf-8")
    public JSONObject getModelInfo(){
        JSONObject subjectJo = subjectService.getSubjectInfoFormatTable();
        return subjectJo;
    }

    @ResponseBody
    @RequestMapping(value = "/sign/in", method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public R signIn(HttpSession session){
        if(userService.signIn(session)){
            return R.succ("插入数据成功");
        }else {
            return R.fail("插入数据失败");
        }
    }

    @Autowired
    MailServiceImpl mailService;
    @ResponseBody
    @RequestMapping(value = "/user/sendVerCodeToMail" , method = RequestMethod.POST , produces="application/json;charset=utf-8")
    public JSONObject setVerCodeToMail(@RequestParam(name = "email",required = true)String email){
        JSONObject jo = new JSONObject();
        String code = mailService.sendVerCodeToMail(email);
        jo.put("result","200");
        jo.put("varCode",code);
        return jo;
    }
   /* @Autowired
    LoadBalancerClient loadBalancerClient;
    @ResponseBody
    @RequestMapping(value = "test" )
    public String test(){
        RestTemplate restTemplate = new RestTemplate();
        ServiceInstance serviceInstance = loadBalancerClient.choose("ORDER");
        String url = String.format("http://%s:%s/order/1",serviceInstance.getHost(),serviceInstance.getPort());
        return restTemplate.getForObject(url,String.class);
    }
*/

  /* @Autowired
    OrderClient orderClient;
   @ResponseBody
   @GetMapping("/test1")
   public String test(){
       return orderClient.findOrderById(4l);
   }
*/
}
