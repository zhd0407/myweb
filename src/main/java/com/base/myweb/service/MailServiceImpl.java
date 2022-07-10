package com.base.myweb.service;

import com.base.myweb.core.tools.SendMail;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl   {


    public String sendVerCodeToMail(String email) {
        SendMail themail =new SendMail("smtp.qq.com");
        String mailbody=String.valueOf((int)(Math.random()*10000));
        themail.setNeedAuth(true);
        themail.setSubject("验证码");
        themail.setBody(mailbody);
        themail.setTo(email);
        themail.setFrom("272754994@qq.com");
        themail.setNamePass("272754994@qq.com", "");
        themail.sendout();
        return mailbody;
    }
}
