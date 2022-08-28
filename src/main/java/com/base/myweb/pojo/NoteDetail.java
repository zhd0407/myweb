package com.base.myweb.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.List;

@Data
public class NoteDetail {

    private Noteinfo noteinfo;
    private Userinfo userinfo;
    private List<Message> msgList;



}
