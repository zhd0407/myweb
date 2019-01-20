package com.base.myweb.controller;


import com.base.myweb.Tools.Charset;
import com.base.myweb.mapper.UserMapper;
import com.base.myweb.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/page")
public class IndexController {
    @Autowired
    public UserMapper usermapper;

    private User user;

    @RequestMapping("/index")
    public ModelAndView index(Map<String,Object> map){


        return  new ModelAndView("index");
    }

    @RequestMapping("/head")
    public String head(Map<String,Object> map){


        return "head";
    }

    @RequestMapping("/advert")
    public String advert(Map<String,Object> map){


        return "advert";
    }

    @RequestMapping("/footer")
    public String footer(Map<String,Object> map){


        return "footer";
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
        String userId = (String) session.getAttribute("userId");

        if (!"".equals(Charset.nullToEmpty(userId))){
            user = (User) session.getAttribute("userInfo");
            model.addAttribute("userId",userId);
            model.addAttribute("userInfo",user);
        }
        return "header";
    }

    @RequestMapping("/currWeekTopic")
    public String currWeekTopic(Map<String,Object> map){

        return "currWeekTopic";
    }

}
