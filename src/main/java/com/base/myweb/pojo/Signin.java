package com.base.myweb.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class Signin {
    @TableId(type = IdType.AUTO)
    private int signinNo;
    private String userId;
    private Date signTime;
    private int integral;
    private String origin;

}
