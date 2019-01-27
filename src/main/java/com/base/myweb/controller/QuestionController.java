package com.base.myweb.controller;

import com.alibaba.fastjson.JSONObject;
import com.base.myweb.service.QuestionService;
import com.base.myweb.service.serviceImpl.QuestionServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("")
public class QuestionController {

    @Autowired
    QuestionServiceImpl questionServiceImpl;

    @RequestMapping("/question")
    public String question(Map<String ,Object> map){

        return "question/index";
    }

    @RequestMapping("/question/add")
    public String add(Model model, HttpSession session){
        questionServiceImpl.addNote(model,session);
        return"question/add";
    }

    @RequestMapping("/question/detail")
    public String detail(Map<String ,Object> map){

        return "question/detail";
    }

    @RequestMapping("/question/index")
    public String index(Map<String ,Object> map){

        return "question/index";
    }

}