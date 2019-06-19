package com.base.myweb.pojo;

import com.base.myweb.core.tools.Charset;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;
@Data
public class Noteinfo {

    private Integer noteNo;     //主键值
    private String noteId;      //编号
    private String theme;       //标题
    private String noteType;    //类型：分享/提问/求助/
    private String modelType;   //领域
    private String noteKeys;        //关键词

    private String noteBody;    //正文
    private String userId;      //发帖人
    private Date createTime;    //创建时间
    private Date changeTime;    //修改时间
    private String accessSta;   //审核状态
    private String privacy;     //私密帖
    private String draft;       //草稿
    private String originType;  //创作性质（原创/转载/收藏）
    private String oldNoteId;   //原贴编号
    private String cancomment;  //可以评论
    private Integer integrate;  //积分
    private Integer skinNum;    //浏览量
    private String complete;    //完结

    //重写返回时间格式
    public String getChangeTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        if(changeTime!=null){
            return sdf.format(this.changeTime);
        }else{
            return  sdf.format(this.createTime);
        }
    }
}
