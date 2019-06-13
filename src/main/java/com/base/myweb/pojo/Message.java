package com.base.myweb.pojo;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class Message {

    private int msgNo;
    private int noteNo;
    private String userId;
    private int upMsgNo;
    private String msgDsc;
    private int praise;
    private int bad;
    private Date msgTime;

    public String getMsgTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return  sdf.format(this.msgTime);
    }
}
