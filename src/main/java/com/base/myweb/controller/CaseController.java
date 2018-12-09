package com.base.myweb.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Date;
import java.util.Map;


@Slf4j
@Controller
@RequestMapping("/page")
public class CaseController {

    @RequestMapping("/case")
    public String toCase(Map<String ,Object> map){
        log.info("------------>case------------>"+ new Date());
        return "case/case";
    }

}
