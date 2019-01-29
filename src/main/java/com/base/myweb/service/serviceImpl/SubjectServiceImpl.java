package com.base.myweb.service.serviceImpl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.base.myweb.mapper.SubjectMapper;
import com.base.myweb.pojo.Subject;
import com.base.myweb.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    SubjectMapper subjectMapper;

    @Override
    public JSONObject getSubjectInfoFormatTable() {
        JSONObject subjectJo = new JSONObject();
        List<Subject> subjectList = subjectMapper.selectList(null);
        JSONArray dataArr = new JSONArray();
        for (Subject subject:subjectList ) {
            JSONObject subJo = new JSONObject();
            subJo.put("subjectNo",String.valueOf(subject.getSubjectNo()));
            subJo.put("subjectCode",subject.getSubjectCode());
            subJo.put("subjectNam",subject.getSubjectNam());
            subJo.put("userType",subject.getUserType());
            subJo.put("seqNum",String.valueOf(subject.getSeqNum()));
            dataArr.add(subJo);
        }
        subjectJo.put("data",dataArr);
        subjectJo.put("count",subjectList.size());
        subjectJo.put("msg","");
        subjectJo.put("code","0");
        return subjectJo;
    }
}
