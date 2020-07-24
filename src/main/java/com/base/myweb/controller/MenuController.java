package com.base.myweb.controller;

import com.base.myweb.service.serviceimpl.MenuServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class MenuController {


    @Autowired
    MenuServiceImpl menuService;

    @RequestMapping("/menu")
    public String menu(Map<String,Object> map){
        map.put("MenuList", menuService.getMenu());
        return "Menu";
    }
}
