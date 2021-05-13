package com.base.myweb.service.serviceimpl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
            subJo.put("subject_no",String.valueOf(subject.getSubjectNo()));
            subJo.put("subject_code",subject.getSubjectCode());
            subJo.put("subject_nam",subject.getSubjectNam());
            subJo.put("user_type",subject.getUserType());
            subJo.put("seq_num",String.valueOf(subject.getSeqNum()));
            dataArr.add(subJo);
        }
        subjectJo.put("data",dataArr);
        subjectJo.put("count",subjectList.size());
        subjectJo.put("msg","");
        subjectJo.put("code",0);
        return subjectJo;
    }

   public void insertOrUpdateSubjectInfo(Subject subject){
        subjectMapper.insert(subject);
   }

    public void delSubjectInfo(int subjectNo){
        subjectMapper.deleteById(subjectNo);
    }

    public List<Subject> getSubject(){
        QueryWrapper qw = new QueryWrapper();
        qw.orderByAsc("SEQ_NUM");
        return subjectMapper.selectList(qw);
    }

    public void addSubject(Subject subject){
        subjectMapper.insert(subject);
    }

    public Subject getSubjectById(int id){
        return subjectMapper.selectById(id);
    }

    public void updateSubjectById(Subject subject) {
        subjectMapper.updateById(subject);
    }

}
