package com.base.myweb.service.serviceImpl;

import java.util.List;
import com.base.myweb.mapper.MoviecolMapper;
import com.base.myweb.mapper.MoviegroupMapper;
import com.base.myweb.pojo.movie.Moviecol;
import com.base.myweb.pojo.movie.Moviegroup;
import com.base.myweb.service.MovieService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    MoviecolMapper moviecolMapper;
    @Autowired
    MoviegroupMapper moviegroupMapper;

    /**
     * 获取栏目和分类
     * */
    public List<Moviecol> getMovieColInfo(){
        List<Moviecol> movieColList = moviecolMapper.selectList(null);
        for (Moviecol moviecol:movieColList){
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("MCOL_CODE",moviecol.getMcolCode());
            List<Moviegroup> moviegroupList = moviegroupMapper.selectList(queryWrapper);
            moviecol.setMoviegroups(moviegroupList);
        }
        return movieColList;
    }


}
