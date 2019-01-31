package com.base.myweb.core;


import com.alibaba.fastjson.JSONObject;

public class LayuiUtil {

        public static JSONObject instance(){
            JSONObject tmpJo = new JSONObject();
            tmpJo.put("code",0);
            tmpJo.put("msg","");
            return tmpJo;
        }

}
