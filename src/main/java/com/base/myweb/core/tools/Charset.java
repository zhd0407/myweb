package com.base.myweb.core.tools;

public class Charset {

    public static String nullToEmpty(String params){
        String n = "null";
        String und = "undefined";
        if(params==null||params.equals(n)||params.equals("")||params.equals(und)){
            params = "";
        }
        return params;
    }


}
