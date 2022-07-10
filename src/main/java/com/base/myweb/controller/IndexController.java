package com.base.myweb.controller;


import com.base.myweb.service.NoteinfoServiceImpl;
import com.base.myweb.service.UserInfoServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("")
public class IndexController {

    @Autowired
    NoteinfoServiceImpl noteinfoServiceimpl;
    @Autowired
    UserInfoServiceImpl userInfoServiceimpl;

    @RequestMapping(value={"/","/index"})
    public String index(Model model ){
        noteinfoServiceimpl.getNoteList(model);
        return  "index";
    }

    @RequestMapping("/main")
    public String main(Model model ){

        return  "main";
    }

    @RequestMapping("/welcome")
    public String welcome(Model model ){

        return  "welcome";
    }
    @RequestMapping("/LAY_replyRank")
    public String layReplyRank(Map<String,Object> map){


        return "LAY_replyRank";
    }

    @RequestMapping("/attendance")
    public String attendance(Model model, HttpSession session){


        return "attendance";
    }

    @RequestMapping("/catalog")
    public String catalog(Model model, HttpSession session){


        return "/ignore/catalog";
    }

    @RequestMapping("/other/notice")
    public String notice(Model model, HttpSession session){


        return "/ignore/other/notice";
    }

    @RequestMapping("/other/404")
    public String notfound(Model model, HttpSession session){


        return "/ignore/other/404";
    }

    @RequestMapping("/other/tips")
    public String tips(Model model, HttpSession session){


        return "/ignore/other/tips";
    }


}
