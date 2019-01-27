package com.base.myweb.pojo;

import lombok.Data;
import java.util.Date;

@Data
public class Userinfo {
    private  Integer userNo;
    private String userId;
    private String userNam;
    private String sex;
    private Integer age;
    private String phone;
    private String email;
    private String password;
    private Date brithday;
    private String identityType;
    private Date createTime;
    private String motto;
    private Integer country;
    private Integer province;
    private Integer city;
    private String address;
    private String emailSta;
    private String phoneSta;
    private String headImg;

}
