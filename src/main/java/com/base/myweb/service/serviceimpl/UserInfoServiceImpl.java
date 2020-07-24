package com.base.myweb.service.serviceimpl;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.base.myweb.core.RedisUtil;
import com.base.myweb.core.codebuilder.UserIdBuilder;
import com.base.myweb.core.SessionInfo;
import com.base.myweb.core.tools.Charset;
import com.base.myweb.mapper.LoginhistoryMapper;
import com.base.myweb.mapper.UserInfoMapper;
import com.base.myweb.pojo.Loginhistory;
import com.base.myweb.pojo.Userinfo;
import com.base.myweb.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    UserInfoMapper usermapper;
    @Autowired
    LoginhistoryMapper loginhistoryMapper;

    @Autowired
    RedisUtil redisUtil;

    @Override
    public String checkEmailIsExist(String email){
        Userinfo user = new Userinfo();
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

    @Override
    public Userinfo regInfo(String email, String username, String pass, HttpSession session){
        QueryWrapper qw = new QueryWrapper();
        qw.eq("EMAIL", email);
        int mailCount = usermapper.selectCount(qw);
        Userinfo currUser = null;
        if (mailCount<=0){
            Userinfo user = new Userinfo();
            user.setEmail(email);
            String userId = UserIdBuilder.createUserId(usermapper);
            user.setUserId(userId);
            user.setUserNam(username);
            user.setPassword(pass);
            user.setIdentityType("customer");
            user.setCreateTime(new Date());
            usermapper.insert(user);
            UserIdBuilder.addUserIdToList(userId);
            currUser = usermapper.selectOne(qw);

        }else{
            System.out.println("该邮箱已经被使用");
        }
        return currUser;
    }

    @Override
    public JSONObject loginAuth(String email,String pass,HttpSession session){
        JSONObject jo = new JSONObject();
        QueryWrapper qw = new QueryWrapper();
        //根据邮箱登录
        qw.eq("EMAIL", email);
        Userinfo user = usermapper.selectOne(qw);
        //根据邮箱获取数据不为空，且邮箱对应密码正确，则登录成功
        if(user!=null&&user.getPassword().equals(pass)){
            //登录历史记录
            Loginhistory hist = new Loginhistory();
            hist.setUserId(user.getUserId());
            hist.setLoginTime(new Date());
            //hist.setIp("");
            loginhistoryMapper.insert(hist);

            jo.put("result","success");
            jo.put("userInfo",user);
            SessionInfo.setUserInfoToSession(user,session);
        }else{
            jo.put("result","fail");
            jo.put("msg","用户名或密码错误");
        }
        return jo;
    }

    public Boolean signIn(HttpSession session){
        //  redisUtil

        return true;
    }

    public Userinfo getUserInfoByUserId(String userId){
        Userinfo userinfo = null;
        QueryWrapper qw = new QueryWrapper();
        qw.eq("USER_ID", userId);
        return usermapper.selectOne(qw);
    }


    @Override
    public void checkSigned(Model model, HttpSession session){
        String userId = (String) session.getAttribute("userId");
        if(!"".equals(Charset.nullToEmpty(userId))){




        }


    }
}
