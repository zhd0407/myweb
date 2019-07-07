package com.base.myweb.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import java.util.Date;

@Data
public class Loginhistory {

    @TableId(type = IdType.AUTO)
    private int historyNo;
    private String userId;
    private Date loginTime;
    private String ip;
}
