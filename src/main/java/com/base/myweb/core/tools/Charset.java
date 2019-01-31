package com.base.myweb.core.tools;

public class Charset {

    public static String nullToEmpty(String params){
        if(params==null||"null".equals(params)||"".equals(params)||"undefined".equals(params)){
            params = "";
        }
        return params;
    }


}
