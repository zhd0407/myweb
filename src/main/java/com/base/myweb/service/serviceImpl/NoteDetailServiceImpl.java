package com.base.myweb.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.base.myweb.core.tools.Charset;
import com.base.myweb.pojo.Message;
import com.base.myweb.pojo.NoteDetail;
import com.base.myweb.pojo.Noteinfo;
import com.base.myweb.pojo.Userinfo;
import com.base.myweb.service.NoteDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
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
            for(Message message:msgList){
                QueryWrapper qw = new QueryWrapper();
                message.setUserinfo(userInfoService.getUserInfoByUserId(message.getUserId()));
            }
            noteDetail.setMsgList(msgList);


        }
        return noteDetail;
    }

    public void getOtherInfoByNoteDetail(Model model, NoteDetail noteDetail, HttpSession httpSession){
        String userId = (String) httpSession.getAttribute("userId");
        //获取当前登录人
        String signFlag = "true";       //登录状态
        String showAcceptBut = "false";        //是否显示采纳操作
        if("".equals(Charset.nullToEmpty(userId))){     //当前登录人为空，则设置标志位为未登录
            signFlag = "false";
        }
        //如果帖子完结，不显示采纳操作
        if("Y".equals(noteDetail.getNoteinfo().getComplete())){
            showAcceptBut = "false";
        }else{
            if (!"".equals(Charset.nullToEmpty(userId))&&userId.equals(noteDetail.getNoteinfo().getUserId())){
                //如果帖子完结，不显示采纳按钮
                showAcceptBut = "true";
            }else{      //如果不是发帖人，不显示采纳按钮
                showAcceptBut = "false";
            }
        }
        model.addAttribute("signFlag",signFlag);
        model.addAttribute("showAcceptBut",showAcceptBut);
        model.addAttribute("userId",userId);
    }
}
