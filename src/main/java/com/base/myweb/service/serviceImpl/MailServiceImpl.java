package com.base.myweb.service.serviceImpl;

import com.base.myweb.core.tools.SendMail;
import com.base.myweb.service.MailService;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

    @Override
    public String sendVerCodeToMail(String email) {
        SendMail themail =new SendMail("smtp.qq.com");
        String mailbody=String.valueOf((int)(Math.random()*10000));
        themail.setNeedAuth(true);
        themail.setSubject("验证码");
        themail.setBody(mailbody);
        themail.setTo(email);
        themail.setFrom("272754994@qq.com");
        themail.setNamePass("272754994@qq.com", ".4131izutnebevol");
        themail.sendout();
        return mailbody;
    }
}
