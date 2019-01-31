package com.base.myweb.controller;


import com.alibaba.fastjson.JSONObject;
import com.base.myweb.pojo.Subject;
import com.base.myweb.service.serviceImpl.SubjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class adminController {

    @Autowired
    SubjectServiceImpl subjectService;

    @ResponseBody
    @RequestMapping(value = "/admin/setSubjectInfo" , method = RequestMethod.POST , produces="application/json;charset=utf-8")
    public JSONObject head(@RequestParam(name = "subject_code",required = true)String subject_code,@RequestParam(name = "subject_nam",required = true)String subject_nam,
                       @RequestParam(name = "user_type",required = true)String user_type,@RequestParam(name = "seq_num",required = false)int seq_num){
        Subject subject = new Subject();
        subject.setSubjectCode(subject_code);
        subject.setSubjectNam(subject_nam);
        subject.setUserType(user_type);
        subject.setSeqNum(seq_num);
        subjectService.insertOrUpdateSubjectInfo(subject);
        JSONObject jo = new JSONObject();
        jo.put("result","true");
        return jo;
    }

    @ResponseBody
    @RequestMapping(value = "/admin/delSubjectInfo" , method = RequestMethod.POST , produces="application/json;charset=utf-8")
    public JSONObject head(@RequestParam(name = "subject_no",required = true)String subject_no){
        subjectService.delSubjectInfo(subject_no);
        JSONObject jo = new JSONObject();
        jo.put("result","true");
        return jo;
    }

}
