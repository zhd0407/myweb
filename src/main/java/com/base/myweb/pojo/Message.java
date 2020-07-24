package com.base.myweb.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class Message {

    @TableId(type = IdType.UUID)
    //主键值
    private Long msgNo;
    //帖子主键值
    private int noteNo;
    //发表用户id
    private String userId;
    //上级评论
    private int upMsgNo;
    //评论内容
    private String msgDsc;
    //点赞数
    private int praise;
    //差评数
    private int bad;
    //评论时间
    private Date msgTime;
    //有效状态、逻辑删除
    private String validSta;
    //采纳标识
    private String acceptFlag;
    @TableField(exist = false)
    private Userinfo userinfo;

    //重写返回时间格式
    public String getMsgTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return  sdf.format(this.msgTime);
    }
}
