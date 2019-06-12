package com.base.myweb.pojo;


import lombok.Data;

import java.util.List;

@Data
public class NoteDetail {

    private Noteinfo noteinfo;
    private Userinfo userinfo;
    private List<Message> msgList;



}
