package com.base.myweb.Tools;

public class Charset {

    public static String nullToEmpty(String params){
        if("null".equals(params)||"".equals(params)||"undefined".equals(params)){
            params = "";
        }
        return params;
    }


}
