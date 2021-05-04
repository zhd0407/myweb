package com.base.myweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@RequestMapping("")
@Controller
public class LevelController {

    @RequestMapping("/level")
    public ModelAndView level(Map<String,Object> map){


        return  new ModelAndView("level");
    }

    @RequestMapping("/level1/{path}")
    public String level_1(@PathVariable("path") String path){
        return "/level1/"+ path;
    }

    @RequestMapping("/level2/{path}")
    public String level_2(@PathVariable("path") String path){
        return "/level2/"+ path;
    }

    @RequestMapping("/level3/{path}")
    public String level_3(@PathVariable("path") String path){
        return "/level3/"+ path;
    }

}
