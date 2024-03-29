package com.base.myweb.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Subject {
    @TableId(type = IdType.AUTO)
    private Subject subjectNo;
    private String subjectCode;
    private String subjectNam;
    private String userType;
    private Integer seqNum;
}
