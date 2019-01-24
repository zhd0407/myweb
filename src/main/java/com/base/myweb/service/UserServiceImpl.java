package com.base.myweb.service;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.base.myweb.mapper.UserInfoMapper;
import com.base.myweb.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Service
public class UserServiceImpl {
    @Autowired
    private UserInfoMapper usermapper;

    public String checkEmailIsExist(String email){
        UserInfo user = new UserInfo();
        user.setEmail(email);
        QueryWrapper qw = new QueryWrapper();
        qw.eq("EMAIL", user.getEmail());
        int mailCount = usermapper.selectCount(qw);

        JSONObject ja = new JSONObject();
        String result = "success";
        String msg = "";
        if (mailCount>0){
            result = "fail";
            msg = "该邮箱已经被使用";
        }
        ja.put("result",result);
        ja.put("msg",msg);
        return ja.toString();
    }

    public UserInfo regInfo(String email, String username, String pass, HttpSession session){
        UserInfo user = new UserInfo();
        user.setEmail(email);
        QueryWrapper qw = new QueryWrapper();
        qw.eq("EMAIL", user.getEmail());
        int mailCount = usermapper.selectCount(qw);
        UserInfo currUser = null;
        if (mailCount<=0){
            user.setUsernam(username);
            user.setPassword(pass);
            user.setIdentityType("customer");
            user.setCreateTime(new Date());
            usermapper.insert(user);
            currUser = usermapper.selectOne(qw);
            session.setAttribute("userInfo",user);
            session.setAttribute("userId",user.getUserno()+"");
        }else{
            System.out.println("该邮箱已经被使用");
        }
        return currUser;
    }


}
