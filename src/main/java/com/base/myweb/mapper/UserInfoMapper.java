package com.base.myweb.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.base.myweb.pojo.Userinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserInfoMapper extends BaseMapper<Userinfo> {

    @Select("select * from userinfo")
    List<Map<String,Object>> getUserSignSta(Page<Map<String,Object>> page, String userId);

}
