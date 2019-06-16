package com.base.myweb.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class Message {

    @TableId(type = IdType.AUTO)
    private Long msgNo;             //主键值
    private int noteNo;             //帖子主键值
    private String userId;          //发表用户id
    private int upMsgNo;            //上级评论
    private String msgDsc;          //评论内容
    private int praise;             //点赞数
    private int bad;                //差评数
    private Date msgTime;           //评论时间
    private String validSta;        //有效状态、逻辑删除
    private String acceptFlag;      //采纳标识

    //重写返回时间格式
    public String getMsgTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return  sdf.format(this.msgTime);
    }
}
