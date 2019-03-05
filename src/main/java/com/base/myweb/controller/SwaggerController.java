package com.base.myweb.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.base.myweb.mapper.UserInfoMapper;
import com.base.myweb.pojo.Userinfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "集成测试", tags = "Swagger集成测试")
public class SwaggerController {

    @Autowired
    UserInfoMapper userInfoMapper;
    @ApiOperation(value="我是Swagger的接口简述", notes="Swagger接口详细描述信息")
    @ApiImplicitParam(name = "id", value = "用户ID", dataType = "String", paramType = "path" )
    @RequestMapping(value = "/swagger/{id}", method = RequestMethod.GET)
    public List<Userinfo> getUserById (@PathVariable(value = "id") String id){
        List<Userinfo> list = userInfoMapper.selectList(new QueryWrapper<Userinfo>());
        return list;
    }


}
