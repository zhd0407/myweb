package com.base.myweb.service;

import com.alibaba.fastjson.JSONObject;
import com.base.myweb.pojo.Userinfo;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;


public interface UserInfoService {

    String checkEmailIsExist(String email);

    Userinfo regInfo(String email, String username, String pass, HttpSession session);

    JSONObject LoginAuth(String email,String pass,HttpSession session);

    void checkSigned(Model model, HttpSession session);
}
