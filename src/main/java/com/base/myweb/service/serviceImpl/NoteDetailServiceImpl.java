package com.base.myweb.service.serviceImpl;

import com.base.myweb.pojo.Message;
import com.base.myweb.pojo.NoteDetail;
import com.base.myweb.pojo.Noteinfo;
import com.base.myweb.pojo.Userinfo;
import com.base.myweb.service.NoteDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteDetailServiceImpl implements NoteDetailService {


    @Autowired
    UserInfoServiceImpl userInfoService;
    @Autowired
    NoteinfoServiceImpl noteinfoService;
    @Autowired
    MessageServiceImpl messageService;

    public NoteDetail getNoteDetailInfoByNoteId(String noteNo){
        NoteDetail noteDetail = new NoteDetail();
        Noteinfo noteinfo = noteinfoService.getNoteDetailByNoteNo(noteNo);

        if(noteinfo!=null){
            noteDetail.setNoteinfo(noteinfo);
            String userId = noteinfo.getUserId();
            Userinfo userinfo = userInfoService.getUserInfoByUserId(userId);
            if (userinfo!=null){
                noteDetail.setUserinfo(userinfo);
            }
            List<Message> msgList = messageService.getMessageByNoteNo(noteNo);
            noteDetail.setMsgList(msgList);
        }
        return noteDetail;
    }
}
