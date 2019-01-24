package com.base.myweb.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.base.myweb.pojo.UserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {
}
