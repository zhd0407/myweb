package com.base.myweb.controller;


import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TreeGridController {

    @RequestMapping("TreeGridInfo")
    public String getTreeGridInfo(){
        JSONObject treeGrid = new JSONObject();



        return "treeGrid";
    }
}
