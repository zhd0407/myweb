package com.base.myweb.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TreeGridController {


    @RequestMapping("/TreeGrid")
    public String getTreeGridInfo(){

        return "treeGrid";
    }



}
