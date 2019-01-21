package com.base.myweb.pojo;

import lombok.Data;
import java.util.Date;

@Data
public class User {
    private  int userno;
    private String userid;
    private String usernam;
    private String sex;
    private String phone;
    private String email;
    private String password;
    private String identitytype;
    private Date createdate;

}
