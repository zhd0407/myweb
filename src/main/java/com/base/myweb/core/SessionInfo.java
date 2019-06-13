package com.base.myweb.core;

import com.base.myweb.core.tools.Charset;
import com.base.myweb.pojo.Userinfo;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;


public class SessionInfo {

    /**
     * 从session中获取当前登录人信息并将信息设置到Model中返回到前台
     * @param model
     * @param session
     * */
    public static void setUserInfoToModelBySession(Model model,HttpSession session){
        String userId = (String) session.getAttribute("userId");
        Userinfo user = null;
        String userNam = "";
        if (!"".equals(Charset.nullToEmpty(userId))){
            user = (Userinfo)session.getAttribute("userInfo");
            if (user!=null){
                userNam = user.getUserNam();
            }
        }else{
            userId = "-1";
        }
        model.addAttribute("userId",userId);
        model.addAttribute("userNam",userNam);
        model.addAttribute("userInfo",user);
    }

    public static void setUserInfoToSession(Userinfo userInfo,HttpSession session){
        session.setAttribute("userInfo",userInfo);
        session.setAttribute("userNam",userInfo.getUserNam());
        session.setAttribute("userId",userInfo.getUserId());
    }

    public static Userinfo getUserInfoFromSession(String userId,HttpSession session){
        Userinfo userinfo = null;
        if (!"".equals(Charset.nullToEmpty(userId))){
            userinfo = (Userinfo) session.getAttribute("userInfo");
        }
        return userinfo;
    }

    public static String getUserIdFromSession(HttpSession httpSession){
        return Charset.nullToEmpty((String) httpSession.getAttribute("userId"));
    }

    public static String getUserNameFromSession(HttpSession httpSession){
        return Charset.nullToEmpty((String)httpSession.getAttribute("userNam"));
    }

}
