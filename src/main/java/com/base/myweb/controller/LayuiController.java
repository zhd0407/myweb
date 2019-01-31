package com.base.myweb.controller;


import com.alibaba.fastjson.JSONObject;
import com.base.myweb.service.serviceImpl.LayuiServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LayuiController {

    @Autowired
    LayuiServiceImpl layuiService;


    @ResponseBody
    @RequestMapping(value = "/layIM/init" , method = RequestMethod.GET , produces="application/json;charset=utf-8")
    public JSONObject initLayIMByUserId(){
        return layuiService.initLayIM();
    }
}
