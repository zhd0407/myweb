package com.base.myweb.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Signin {
    private int signinNo;
    private String userId;
    private Date signTime;
    private int integral;
    private String origin;

}
