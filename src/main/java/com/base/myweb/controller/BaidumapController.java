package com.base.myweb.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.base.myweb.pojo.Overlay;

import com.base.myweb.service.OverlayServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("")
public class BaidumapController {

    @GetMapping(value = "/baiduMap")
    public String getBaiduMap(Model model, HttpSession session){


        return "/baiduMap";
    }
    @GetMapping(value = "/GaodeMap")
    public String getGaodeMap(Model model, HttpSession session){


        return "/GaodeMap";
    }

    @Autowired
    OverlayServiceImpl overlayService;

    @ResponseBody
    @PostMapping(value = "/insertMapAreaInfo")
    public JSONObject addBaiduMap(@RequestBody JSONObject obj){
        if(obj!=null){
            String type = obj.getString("type");
            JSONArray points = obj.getJSONArray("points");
            int radius = obj.getInteger("radius");
            Overlay overlay = new Overlay();
            overlay.setType(type);
            overlay.setRadius(radius);
            overlay.setPoints(points.toString());
            overlayService.insertOverlay(overlay);
        }
        return new JSONObject();
    }

    @ResponseBody
    @GetMapping(value = "/getOverlayInfo")
    public List<Overlay> getOverlayInfo(){
        return  overlayService.getOverlayInfo();
    }

    @GetMapping(value = "/gldVideo")
    public String gldVideo(Model model, HttpSession session){


        return "/gldVideo";
    }
}
