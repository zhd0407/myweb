package com.base.myweb.controller;


import com.alibaba.fastjson.JSONObject;
import com.base.myweb.pojo.Subject;
import com.base.myweb.service.SubjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class AdminController {

    @Autowired
    SubjectServiceImpl subjectService;

    @ResponseBody
    @RequestMapping(value = "/admin/setSubjectInfo" , method = RequestMethod.POST , produces="application/json;charset=utf-8")
    public JSONObject head(@RequestParam(name = "subject_code",required = true)String subjectCode,@RequestParam(name = "subject_nam",required = true)String subjectNam,
                       @RequestParam(name = "user_type",required = true)String userType,@RequestParam(name = "seq_num",required = false)int seqNum){
        Subject subject = new Subject();
        subject.setSubjectCode(subjectCode);
        subject.setSubjectNam(subjectNam);
        subject.setUserType(userType);
        subject.setSeqNum(seqNum);
        subjectService.insertOrUpdateSubjectInfo(subject);
        JSONObject jo = new JSONObject();
        jo.put("result","true");
        return jo;
    }

    @ResponseBody
    @RequestMapping(value = "/admin/delSubjectInfo" , method = RequestMethod.POST , produces="application/json;charset=utf-8")
    public JSONObject head(@RequestParam(name = "subject_no",required = true)int subjectNo){
        subjectService.delSubjectInfo(subjectNo);
        JSONObject jo = new JSONObject();
        jo.put("result","true");
        return jo;
    }

}
