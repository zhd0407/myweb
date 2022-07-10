package com.base.myweb.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * author zhanghaidong
 * createTime 2022-07-09 17:06
 */
@Data
public class Eotreegridcfgmst {

    @TableId(type = IdType.AUTO)
    private int gridNo;
    private String gridId;
    private String gridName;
    private String pgmId;
    private String isTree;
    private String theme;
    private String canAdd;
    private String canEdit;
    private String canDelete;
    private String expandLev;
    private String tableName;
    private String seqColumnId;
    private String seqTyp;
    private String ganttTyp;
    private String psColumnId;
    private String pfColumnId;
    private String asColumnId;
    private String afColumnId;
    private String procressColumnId;
    private String orgNo;
    private String fstusrId;
    private Date fstusrDtm;
    private String lstusrId;
    private Date lstusrDtm;
    private String validSta;
    private String wfSta;
    private String mainColumnId;
    private String canSelect;
    private String canDrag;
    private String descendantsColumnId;
    private String mstColumnId;
    private String staCode;

}
