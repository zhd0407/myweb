package com.base.myweb.controller;

import com.alibaba.fastjson.JSONObject;
import com.base.myweb.service.serviceImpl.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class MessageController {

    @Autowired
    MessageServiceImpl messageService;

    @ResponseBody
    @RequestMapping("/message/add")
    public JSONObject addMessage(@RequestParam(name = "noteNo",required = true)String noteNo, @RequestParam(name = "content",required = true)String content,
                                 @RequestParam(name = "upMsgNo",required = true)String upMsgNo, HttpSession session){
        JSONObject tmpObj = new JSONObject();
        messageService.addNewMessage( noteNo, content, upMsgNo, session, tmpObj);
        return tmpObj;
    }

    @ResponseBody
    @RequestMapping("/message/del")
    public JSONObject delMessage(@RequestParam(name = "msgNo",required = true)String msgNo){
        JSONObject tmpObj = new JSONObject();
        messageService.delMessage( msgNo, tmpObj);
        return tmpObj;
    }
}
