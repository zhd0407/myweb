package com.base.myweb.mapper;

import com.base.myweb.pojo.Message;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

@Mapper
public interface MessageMapper extends BaseMapper <Message> {

}
