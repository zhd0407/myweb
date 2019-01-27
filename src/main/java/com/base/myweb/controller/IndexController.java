package com.base.myweb.controller;


import com.base.myweb.Tools.Charset;
import com.base.myweb.core.SessionInfo;
import com.base.myweb.mapper.UserInfoMapper;
import com.base.myweb.pojo.Userinfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("")
public class IndexController {
    @Autowired
    public UserInfoMapper usermapper;

    private Userinfo user;

    @RequestMapping("/index")
    public ModelAndView index(Map<String,Object> map){


        return  new ModelAndView("index");
    }

    @RequestMapping("/head")
    public String head(Model model,HttpSession session){
        SessionInfo.setUserInfoToModelBySession(model,session);
        return "common/column";
    }

    @RequestMapping("/advert")
    public String advert(Map<String,Object> map){


        return "advert";
    }

    @RequestMapping("/footer")
    public String footer(Map<String,Object> map){


        return "common/footer";
    }

    @RequestMapping("/friendlink")
    public String friendlink(Map<String,Object> map){

        return "friendlink";
    }

    @RequestMapping("/fastpassway")
    public String fastpassway(Map<String,Object> map){


        return "fastpassway";
    }

    @RequestMapping("/setTop")
    public String setTop(Map<String,Object> map){


        return "setTop";
    }

    @RequestMapping("/LAY_replyRank")
    public String LAY_replyRank(Map<String,Object> map){


        return "LAY_replyRank";
    }

    @RequestMapping("/attendance")
    public String attendance(Map<String,Object> map){


        return "attendance";
    }

    @RequestMapping("/primary")
    public String primary(Map<String,Object> map){

        return "primary";
    }

    @RequestMapping("/header")
    public String header(Model model, HttpSession session){
        SessionInfo.setUserInfoToModelBySession(model,session);
        return "common/header";
    }

    @RequestMapping("/currWeekTopic")
    public String currWeekTopic(Map<String,Object> map){

        return "currWeekTopic";
    }

}
