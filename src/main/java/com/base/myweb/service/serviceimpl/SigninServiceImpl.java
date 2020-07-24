package com.base.myweb.service.serviceimpl;

import com.alibaba.fastjson.JSONObject;
import com.base.myweb.core.tools.Charset;
import com.base.myweb.mapper.SigninMapper;
import com.base.myweb.pojo.Signin;
import com.base.myweb.service.SigninService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Service
public class SigninServiceImpl implements SigninService {


    @Autowired
    SigninMapper signinMapper;

    @Override
    public JSONObject insertSignInfo(Model model, HttpSession session) {
        JSONObject jo = new JSONObject();
        String userId = (String) session.getAttribute("userId");
        if (!"".equals(Charset.nullToEmpty(userId))){
            Signin signin = new Signin();
            signin.setUserId(userId);
            signin.setSignTime(new Date());
            signin.setIntegral(5);
            signin.setOrigin("00");
            signinMapper.insert(signin);
            jo.put("userId",userId);
            jo.put("result","success");
        }else{
            jo.put("result","fail");
            jo.put("message","请先登录");
        }
        return jo;
    }
}
