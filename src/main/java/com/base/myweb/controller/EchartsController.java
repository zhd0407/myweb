package com.base.myweb.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EchartsController {


    @RequestMapping("/EchartsPage")
    public String goToEchartsPage(){

        return "/ignore/ECharts/EchartsPage";
    }

    @ResponseBody
    @RequestMapping("/ignore/getEchartsInfo")
    public JSONObject getEchartsInfo(){
        JSONObject jo = new JSONObject();
        JSONArray xAxisDataJa = new JSONArray();
        xAxisDataJa.add("衬衫");
        xAxisDataJa.add("羊毛衫");
        xAxisDataJa.add("雪纺衫");
        xAxisDataJa.add("裤子");
        xAxisDataJa.add("高跟鞋");
        xAxisDataJa.add("袜子");
        jo.put("xAxisData",xAxisDataJa);
        JSONArray varJa = new JSONArray();
        varJa.add("150");
        varJa.add("200");
        varJa.add("36");
        varJa.add("100");
        varJa.add("108");
        varJa.add("209");
        jo.put("xVal",varJa);

        JSONArray varJa1 = new JSONArray();
        varJa1.add("18");
        varJa1.add("15");
        varJa1.add("100");
        varJa1.add("50");
        varJa1.add("70");
        varJa1.add("60");
        jo.put("xVal1",varJa1);
        return jo;
    }

}
