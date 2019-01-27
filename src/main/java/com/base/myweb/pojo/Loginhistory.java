package com.base.myweb.pojo;

import lombok.Data;
import java.util.Date;

@Data
public class Loginhistory {
    private int historyNo;
    private String userId;
    private Date loginTime;
    private String ip;
}
