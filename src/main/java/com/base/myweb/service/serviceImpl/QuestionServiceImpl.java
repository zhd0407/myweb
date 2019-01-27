package com.base.myweb.service.serviceImpl;

import com.base.myweb.core.SessionInfo;
import com.base.myweb.pojo.Userinfo;
import com.base.myweb.service.QuestionService;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import javax.servlet.http.HttpSession;

@Service
public class QuestionServiceImpl implements QuestionService {


    public void addNote(Model model,HttpSession session){
        SessionInfo.setUserInfoToModelBySession(model,session);
       // Userinfo userinfo =



    }
}
