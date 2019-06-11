package com.base.myweb.controller;

import com.alibaba.fastjson.JSONObject;
import com.base.myweb.service.serviceImpl.SigninServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class SigninController {

    @Autowired
    SigninServiceImpl signinServiceImpl;

    @RequestMapping(value = "/user/signin" , method = RequestMethod.POST , produces="application/json;charset=utf-8")
    @ResponseBody
    public JSONObject signin(Model model, HttpSession session){
        return signinServiceImpl.insertSignInfo(model,session);
    }


}
