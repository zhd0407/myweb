package com.base.myweb.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/marry")
public class MarryController {

    @RequestMapping(value = "/marry" )
    public String marry(){
        return "marry/marry";
    }
}
