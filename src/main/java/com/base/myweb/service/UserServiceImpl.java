package com.base.myweb.service;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.base.myweb.mapper.UserMapper;
import com.base.myweb.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Service
public class UserServiceImpl {
    @Autowired
    private UserMapper usermapper;

    public String checkEmailIsExist(String email){
        User user = new User();
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

    public  User regInfo(String email, String username, String pass, HttpSession session){
        User user = new User();
        user.setEmail(email);
        QueryWrapper qw = new QueryWrapper();
        qw.eq("EMAIL", user.getEmail());
        int mailCount = usermapper.selectCount(qw);
        User currUser = null;
        if (mailCount<=0){
            user.setUsernam(username);
            user.setPassword(pass);
            user.setIdentitytype("customer");
            user.setCreatedate(new Date());
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
