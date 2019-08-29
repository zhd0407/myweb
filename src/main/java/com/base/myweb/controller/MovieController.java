package com.base.myweb.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/movie")
public class MovieController {

    @RequestMapping("/index")
    public String toCase(Map<String ,Object> map){

        return "movie/index";
    }



}
