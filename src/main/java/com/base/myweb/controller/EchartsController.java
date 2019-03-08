package com.base.myweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EchartsController {


    @RequestMapping("EchartsPage")
    public String goToEchartsPage(){

        return "EchartsPage";
    }


}
