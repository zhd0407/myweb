package com.base.myweb.service.serviceimpl;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.base.myweb.mapper.MessageMapper;
import com.base.myweb.mapper.NoteInfoMapper;
import com.base.myweb.mapper.UserInfoMapper;
import com.base.myweb.pojo.Noteinfo;
import com.base.myweb.pojo.Userinfo;
import com.base.myweb.service.NoteinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;


@Service
public class NoteinfoServiceImpl implements NoteinfoService {

        @Autowired
        NoteInfoMapper noteInfoMapper;

        @Autowired
        UserInfoMapper userInfoMapper;

        @Autowired
        MessageMapper messageMapper;

        public JSONObject insertNoteInfo(Noteinfo noteinfo){
            JSONObject noteJo = new JSONObject();
            noteInfoMapper.insert(noteinfo);
            noteJo.put("result","success");
            noteJo.put("msg","发表成功");
            return noteJo;
        }

        public List<Noteinfo> getNoteList(HttpSession session){
            List<Noteinfo> noteList = noteInfoMapper.selectList(null);
            for(Noteinfo noteinfo:noteList){
                //获取发帖人名称
                QueryWrapper qw = new QueryWrapper();
                qw.eq("USER_ID",noteinfo.getUserId());
                Userinfo userinfo = userInfoMapper.selectOne(qw);
                noteinfo.setUserName(userinfo.getUserNam());

                //获取评论数
                qw = new QueryWrapper();
                qw.eq("NOTE_NO",noteinfo.getNoteNo());
                qw.eq("VALID_STA","Y");
                noteinfo.setMsgNum(messageMapper.selectCount(qw));
            }
            return noteList;
        }

        public Noteinfo getNoteDetailByNoteNo( String noteNo){
            return noteInfoMapper.selectById(noteNo);
        }




}
