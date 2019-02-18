package com.base.myweb.core.codeBuilder;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.base.myweb.core.tools.Charset;
import com.base.myweb.mapper.UserInfoMapper;
import com.base.myweb.pojo.Userinfo;
import lombok.Synchronized;

import java.util.ArrayList;
import java.util.List;
/**
 * 该类用于生成用户id，用户注册时进行id生成，如果id没被占用则直接返回，如果被占用了，则重新生成并校验是否被占用
 * */
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
