package com.base.myweb.service.serviceImpl;


import com.alibaba.fastjson.JSONObject;
import com.base.myweb.mapper.NoteInfoMapper;
import com.base.myweb.pojo.Noteinfo;
import com.base.myweb.service.NoteinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class NoteinfoServiceImpl implements NoteinfoService {

        @Autowired
        NoteInfoMapper noteInfoMapper;

        public JSONObject insertNoteInfo(Noteinfo noteinfo){
            JSONObject noteJo = new JSONObject();
            noteInfoMapper.insert(noteinfo);
            noteJo.put("result","success");
            noteJo.put("msg","发表成功");
            return noteJo;
        }




}
