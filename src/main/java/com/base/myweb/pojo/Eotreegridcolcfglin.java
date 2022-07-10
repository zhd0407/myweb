package com.base.myweb.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * author zhanghaidong
 * createTime 2022-07-09 17:18
 */
@Data
public class Eotreegridcolcfglin {


    @TableId(type = IdType.AUTO)
    private String fieldNo;
    private int gridNo;
    private String fieldId;
    private String fieldName;
    private String fieldTyp;
    private String formatSht;
    private String widthCnt;
    private String canEdit;
    private String optSht;
    private String leftCol;
    private String isShow;
    private String isGantt;
    private String xnTyp;
    private String pkFieldFlg;
    private String parentFieldFlg;
    private String seqNum;
    private String orgNo;
    private String fstusrId;
    private String fstusrDtm;
    private String lstusrId;
    private String lstusrDtm;
    private String validSta;
    private String enumOpt;
    private String staCode;
    private String formulaSht;
}
