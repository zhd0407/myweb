package com.base.myweb.service.serviceImpl;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.base.myweb.mapper.NoteInfoMapper;
import com.base.myweb.pojo.NoteDetail;
import com.base.myweb.pojo.Noteinfo;
import com.base.myweb.pojo.Userinfo;
import com.base.myweb.service.NoteinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.List;


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

        public List<Noteinfo> getNoteList(HttpSession session){
            List<Noteinfo> noteList = noteInfoMapper.selectList(null);
            return noteList;
        }

        public Noteinfo getNoteDetailByNoteNo( String noteNo){
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("NOTE_NO",noteNo);
            return noteInfoMapper.selectOne(queryWrapper);
        }




}
