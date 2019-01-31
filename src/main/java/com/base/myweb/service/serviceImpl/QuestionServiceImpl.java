package com.base.myweb.service.serviceImpl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.base.myweb.core.tools.Charset;
import com.base.myweb.core.SessionInfo;
import com.base.myweb.mapper.SubjectMapper;
import com.base.myweb.pojo.Subject;
import com.base.myweb.pojo.Userinfo;
import com.base.myweb.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    SubjectMapper subjectMapper;

    public void addNote(Model model,HttpSession session){
        SessionInfo.setUserInfoToModelBySession(model,session);
        String userId = Charset.nullToEmpty((String)session.getAttribute("userId"));
        if (!"".equals(userId)&&!"-1".equals(userId)){
            Userinfo userinfo = (Userinfo)session.getAttribute("userInfo");
            String userType = userinfo.getIdentityType();
            QueryWrapper queryWrapper = new QueryWrapper();
            if (!"".equals(Charset.nullToEmpty(userType))&&"customer".equals(userType)){
                queryWrapper.eq("user_type",userType);
            }
            List<Subject> subjects = subjectMapper.selectList(queryWrapper);
            JSONArray listJa = new JSONArray();
            JSONObject tmpJo = new JSONObject();
            tmpJo.put("code","");
            tmpJo.put("name","");
            listJa.add(tmpJo);
            for (Subject subject:subjects) {
                tmpJo = new JSONObject();
                tmpJo.put("code",subject.getSubjectCode());
                tmpJo.put("name",subject.getSubjectNam());
                listJa.add(tmpJo);
            }
            model.addAttribute("subjectInfo",listJa);
        }
    }
}
