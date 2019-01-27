package com.base.myweb.Tools;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.base.myweb.mapper.UserInfoMapper;
import com.base.myweb.pojo.Userinfo;
import lombok.Synchronized;

import java.util.ArrayList;
import java.util.List;

public class UserIdBuilder {

    public static List<String> userIdList = new ArrayList<>();

    public static List<String> tempUserIdList = new ArrayList<>();

    @Synchronized
    public static String createUserId(UserInfoMapper userInfoMapper){
        if(userIdList.size()<=0){
            List<Userinfo> users = userInfoMapper.selectList(new QueryWrapper<>());
            for ( Userinfo user:users) {
                String userId = Charset.nullToEmpty(user.getUserId());
                if (!"".equals(userId)&&!userIdList.contains(userId)){
                    userIdList.add(userId);
                }
            }
        }
        String newUserId = String.valueOf((int)(Math.random()*1000000000));
        while (userIdList.contains(newUserId)||tempUserIdList.contains(newUserId)){
            newUserId = String.valueOf((int)(Math.random()*1000000000));
        }
        UserIdBuilder.tempUserIdList.add(newUserId);
        return newUserId;
    }

    public static void addUserIdToList(String userId){
        userIdList.add(userId);
        tempUserIdList.remove(userId);
    }





}
