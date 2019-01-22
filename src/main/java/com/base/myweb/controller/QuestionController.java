package com.base.myweb.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("")
public class QuestionController {


    @RequestMapping("/question")
    public String question(Map<String ,Object> map){

        return "question/index";
    }

    @RequestMapping("/question/add")
    public String add(Map<String ,Object> map){

        return "question/add";
    }

    @RequestMapping("/question/detail")
    public String detail(Map<String ,Object> map){

        return "question/detail";
    }

}