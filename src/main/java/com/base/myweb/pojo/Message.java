package com.base.myweb.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class Message {

    @TableId(type = IdType.AUTO)
    private Long msgNo;
    private int noteNo;
    private String userId;
    private int upMsgNo;
    private String msgDsc;
    private int praise;
    private int bad;
    private Date msgTime;
    private String validSta;

    public String getMsgTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return  sdf.format(this.msgTime);
    }
}
