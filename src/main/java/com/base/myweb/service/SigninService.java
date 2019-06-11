package com.base.myweb.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.ui.Model;
import javax.servlet.http.HttpSession;

public interface SigninService {

    public JSONObject insertSignInfo(Model model, HttpSession session);
}
