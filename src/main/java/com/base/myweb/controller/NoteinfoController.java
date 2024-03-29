package com.base.myweb.controller;

import com.base.myweb.core.tools.Charset;
import com.base.myweb.pojo.NoteDetail;
import com.base.myweb.pojo.Noteinfo;
import com.base.myweb.service.NoteDetailServiceImpl;
import com.base.myweb.service.NoteinfoServiceImpl;
import com.base.myweb.service.QuestionServiceImpl;
import com.base.myweb.service.UserInfoServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("")
public class NoteinfoController {

    @Autowired
    QuestionServiceImpl questionService;

    @Autowired
    NoteinfoServiceImpl noteinfoServiceImpl;

    @Autowired
    UserInfoServiceImpl userInfoService;

    @Autowired
    NoteDetailServiceImpl noteDetailService;

    @RequestMapping(value = {"/question","/question/index"})
    public String question(Map<String ,Object> map){

        return "question/index";
    }

    @RequestMapping("/question/add")
    public String add(Model model, HttpSession session){
        questionService.addNote(model,session);
        return"question/add";
    }

    @RequestMapping("/question/detail")
    public String detail(Model model,@RequestParam(name = "noteId",required = true)String noteId,HttpSession httpSession){
        NoteDetail noteDetail = null;
        if(!"".equals(Charset.nullToEmpty(noteId))){
           noteDetail = noteDetailService.getNoteDetailInfoByNoteId(noteId);
           if(noteDetail!=null){
               noteDetailService.getOtherInfoByNoteDetail(model,noteDetail,httpSession);
           }
        }
        model.addAttribute("NoteDetail",noteDetail);
        return "question/detail";
    }

    @RequestMapping("/noteinfo/add")
    public String noteinfoAdd(@RequestParam(name = "theme",required = true)String theme, @RequestParam(name = "noteType",required = true)String noteType,
                                  @RequestParam(name = "noteBody",required = true)String noteBody,@RequestParam(name = "keys",required = true)String keys,
                                  @RequestParam(name = "modelType",required = true)String modelType,@RequestParam(name = "integrate",required = false)int integrate,
                                  HttpSession session){
        String accessSta = "";
        String noteId = "";
        String originType = "origin";
        if("".equals(Charset.nullToEmpty(String.valueOf(integrate)))){
            integrate = 0;
        }
        Noteinfo noteinfo = new Noteinfo();
        noteinfo.setNoteId(noteId);     //编号
        noteinfo.setTheme(theme);       //主题、标题
        noteinfo.setNoteBody(noteBody);     //正文
        noteinfo.setModelType(modelType);       //模块类型
        noteinfo.setNoteKeys(keys);     //关键字
        noteinfo.setNoteType(noteType);     //文章类型，提问或悬赏
        noteinfo.setUserId((String) session.getAttribute("userId"));        //发帖人
        noteinfo.setCreateTime(new Date());             //发帖时间
        noteinfo.setAccessSta(accessSta);               //审核状态
        noteinfo.setOriginType(originType);        //性质，原创、转载
        noteinfo.setIntegrate(integrate);
        noteinfo.setSkinNum(0);
        noteinfoServiceImpl.insertNoteInfo(noteinfo);
        return "question/detail";
    }

}