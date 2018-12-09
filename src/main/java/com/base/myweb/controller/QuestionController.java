package com.base.myweb.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/page")
public class QuestionController {


    @RequestMapping("/question")
    public String question(Map<String ,Object> map){
        log.info("------------>question------------>"+new Date());
        return "question/index";
    }

    @RequestMapping("/question/add")
    public String add(Map<String ,Object> map){
        log.info("------------>question/add------------>"+new Date());
        return "question/add";
    }

    @RequestMapping("/question/detail")
    public String detail(Map<String ,Object> map){
        log.info("------------>question/detail------------>"+new Date());
        return "question/detail";
    }

}