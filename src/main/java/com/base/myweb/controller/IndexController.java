package com.base.myweb.controller;


import com.base.myweb.mapper.UserMapper;
import com.base.myweb.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/page")
public class IndexController {
    @Autowired
    public UserMapper usermapper;


    @RequestMapping("/index")
    public ModelAndView index(Map<String,Object> map){
        List<User> users = usermapper.selectList(null);
        log.info("------------>index.jsp------------>"+new Date());
        return  new ModelAndView("index");
    }

    @RequestMapping("/head")
    public String head(Map<String,Object> map){

        log.info("------------>head.html------------>"+new Date());
        return "head";
    }

    @RequestMapping("/advert")
    public String advert(Map<String,Object> map){

        log.info("------------>advert.html------------>"+new Date());
        return "advert";
    }

    @RequestMapping("/footer")
    public String footer(Map<String,Object> map){

        log.info("------------>footer.html------------>"+new Date());
        return "footer";
    }

    @RequestMapping("/friendlink")
    public String friendlink(Map<String,Object> map){

        log.info("------------>friendlink.html------------>"+new Date());
        return "friendlink";
    }

    @RequestMapping("/fastpassway")
    public String fastpassway(Map<String,Object> map){

        log.info("------------>fastpassway.html------------>"+new Date());
        return "fastpassway";
    }

    @RequestMapping("/setTop")
    public String setTop(Map<String,Object> map){

        log.info("------------>setTop.html------------>"+new Date());
        return "setTop";
    }

    @RequestMapping("/LAY_replyRank")
    public String LAY_replyRank(Map<String,Object> map){

        log.info("------------>LAY_replyRank.html------------>"+new Date());
        return "LAY_replyRank";
    }

    @RequestMapping("/attendance")
    public String attendance(Map<String,Object> map){

        log.info("------------>attendance.html------------>"+new Date());
        return "attendance";
    }

    @RequestMapping("/primary")
    public String primary(Map<String,Object> map){
        log.info("------------>primary.html------------>"+new Date());
        return "primary";
    }

    @RequestMapping("/header")
    public String header(Map<String,Object> map){
        log.info("------------>header.html------------>"+new Date());
        return "header";
    }

}
