package com.base.myweb.service.serviceimpl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.base.myweb.core.LayuiUtil;
import com.base.myweb.service.LayuiService;
import org.springframework.stereotype.Service;


@Service
public class LayuiServiceImpl implements LayuiService {


    @Override
    public  JSONObject initLayIm(){
        JSONObject layIm = LayuiUtil.instance();
        JSONObject data = new JSONObject();

        JSONObject myInfo = new JSONObject();
        myInfo.put("username","纸飞机");
        myInfo.put("id","10000");
        myInfo.put("status","online");
        myInfo.put("sign","在深邃的编码世界，做一枚轻盈的纸飞机");
        myInfo.put("avatar","http://cdn.firstlinkapp.com/upload/2016_6/1465575923433_33812.jpg");
        data.put("mine",myInfo);

        JSONArray friendArr = new JSONArray();
        for (int j=0;j<3;j++){
            JSONObject groupJo = new JSONObject();
            JSONArray tmpFriendArr = new JSONArray();
            for(int i=0;i<5;i++){
                JSONObject tmpFriendJo = new JSONObject();
                tmpFriendJo.put("username","贤心" + j + i);
                tmpFriendJo.put("id","1000" + j + i);
                tmpFriendJo.put("sign","我是贤心" + j + i + "号");
                tmpFriendJo.put("avatar","http://tp1.sinaimg.cn/1571889140/180/40030060651/"+i);
                tmpFriendArr.add(tmpFriendJo);
            }
            groupJo.put("list",tmpFriendArr);
            groupJo.put("groupname","前端码屌"+j);
            groupJo.put("id",j+"");
            groupJo.put("online",j+2);
            friendArr.add(groupJo);
        }
        data.put("friend",friendArr);

        JSONArray groupArr = new JSONArray();
        for (int i=0;i<3;i++){
            JSONObject groupJo = new JSONObject();
            groupJo.put("groupname","我就是" + i + "群");
            groupJo.put("id",i+100);
            groupJo.put("avatar","http://tp2.sinaimg.cn/5488749285/50/5719808192/"+i);
            groupArr.add(groupJo);
        }
        data.put("group",groupArr);
        layIm.put("data",data);
        return layIm;
    }



}
