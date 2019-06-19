package com.base.myweb.service.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.base.myweb.core.SessionInfo;
import com.base.myweb.core.tools.Charset;
import com.base.myweb.mapper.MessageMapper;
import com.base.myweb.mapper.NoteInfoMapper;
import com.base.myweb.pojo.Message;
import com.base.myweb.pojo.Noteinfo;
import com.base.myweb.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageMapper messageMapper;

    @Autowired
    NoteInfoMapper noteInfoMapper;

    public List<Message> getMessageByNoteNo(String noteNo){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("NOTE_NO",noteNo);
        queryWrapper.eq("VALID_STA","Y");
        return messageMapper.selectList(queryWrapper);
    }

    public void addNewMessage( String noteNo, String content, String upMsgNo, HttpSession httpSession, JSONObject tmpObj){
        Message message = new Message();
        String currUserId = SessionInfo.getUserIdFromSession(httpSession);
        if("".equals(currUserId)){
            tmpObj.put("result","fail");
            tmpObj.put("msg","请先登录");
        }else{
            String currUserName = SessionInfo.getUserNameFromSession(httpSession);
            message.setNoteNo(Integer.parseInt(noteNo));
            message.setUserId(currUserId);
            message.setBad(0);
            message.setPraise(0);
            message.setMsgDsc(content);
            message.setMsgTime(new Date());
            message.setValidSta("Y");
            if(!"".equals(Charset.nullToEmpty(upMsgNo))){
                message.setUpMsgNo(Integer.parseInt(upMsgNo));
            }
            messageMapper.insert(message);
            tmpObj.put("result","success");
            tmpObj.put("msgNo",message.getMsgNo());
            tmpObj.put("msgTime",message.getMsgTime());
            tmpObj.put("msg","");
        }
    }

    public void delMessage(String msgNo,JSONObject tmpObj){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("MSG_NO",msgNo);
        Message message = messageMapper.selectOne(queryWrapper);
        message.setValidSta("N");
        messageMapper.update(message,queryWrapper);

        tmpObj.put("result","success");
        tmpObj.put("msg","");
    }

    public void acceptMsg(String msgNo,JSONObject tmpObj){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("MSG_NO",msgNo);
        Message message = messageMapper.selectOne(queryWrapper);
        message.setAcceptFlag("Y");
        messageMapper.update(message,queryWrapper);

        QueryWrapper qw = new QueryWrapper();
        qw.eq("NOTE_NO",message.getNoteNo());
        Noteinfo noteinfo = noteInfoMapper.selectOne(qw);
        noteinfo.setComplete("Y");
        noteInfoMapper.update(noteinfo,qw);

        tmpObj.put("result","success");
        tmpObj.put("msg","");
    }
}
