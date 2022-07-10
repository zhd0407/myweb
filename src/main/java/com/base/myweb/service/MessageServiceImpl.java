package com.base.myweb.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.base.myweb.core.SessionInfo;
import com.base.myweb.core.tools.Charset;
import com.base.myweb.mapper.MessageMapper;
import com.base.myweb.mapper.NoteInfoMapper;
import com.base.myweb.pojo.Message;
import com.base.myweb.pojo.Noteinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Service
public class MessageServiceImpl   {

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
        Message message = messageMapper.selectById(msgNo);
        if(message!=null){
            message.setValidSta("N");
            int count = messageMapper.updateById(message);
            if(count>0){
                tmpObj.put("result","success");
                tmpObj.put("msg","");
            }else{
                tmpObj.put("result","faile");
                tmpObj.put("msg","操作失败，请重试或联系系统管理员");
            }
        }else{
            tmpObj.put("result","faile");
            tmpObj.put("msg","未获取到消息记录");
        }
    }

    public void acceptMsg(String msgNo,JSONObject tmpObj){
        String success = "success";
        Message message = messageMapper.selectById(msgNo);
        if(message!=null){
            message.setAcceptFlag("Y");
            int count = messageMapper.updateById(message);
            if(count<1){
                success = "fail";
            }
        }

        Noteinfo noteinfo = noteInfoMapper.selectById(message.getNoteNo());
        if(noteinfo!=null){
            noteinfo.setComplete("Y");
            int count = noteInfoMapper.updateById(noteinfo);
            if(count<1){
                success = "fail";
            }
        }
        tmpObj.put("result",success);
        tmpObj.put("msg","");
    }
}
