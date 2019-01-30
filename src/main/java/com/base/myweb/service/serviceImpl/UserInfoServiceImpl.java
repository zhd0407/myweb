package com.base.myweb.service.serviceImpl;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.base.myweb.Tools.UserIdBuilder;
import com.base.myweb.core.SessionInfo;
import com.base.myweb.mapper.LoginhistoryMapper;
import com.base.myweb.mapper.UserInfoMapper;
import com.base.myweb.pojo.Loginhistory;
import com.base.myweb.pojo.Userinfo;
import com.base.myweb.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoMapper usermapper;
    @Autowired
    LoginhistoryMapper loginhistoryMapper;

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

    public Userinfo regInfo(String email, String username, String pass, HttpSession session){

        Userinfo user = new Userinfo();
        user.setEmail(email);
        QueryWrapper qw = new QueryWrapper();
        qw.eq("EMAIL", user.getEmail());
        int mailCount = usermapper.selectCount(qw);
        Userinfo currUser = null;
        if (mailCount<=0){
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

    public JSONObject LoginAuth(String email,String pass,HttpSession session){
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

}