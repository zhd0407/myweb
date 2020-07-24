package com.base.myweb.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.base.myweb.mapper.MenuMapper;
import com.base.myweb.pojo.Menu;
import com.base.myweb.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuMapper menuMapper;

    public List<Menu> getMenu(){
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("SEQ_NUM");

        return menuMapper.selectList(queryWrapper);
    }

}
