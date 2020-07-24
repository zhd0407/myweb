package com.base.myweb.service.serviceimpl;

import java.io.File;
import java.util.List;
import com.base.myweb.mapper.MoviecolMapper;
import com.base.myweb.mapper.MoviegroupMapper;
import com.base.myweb.mapper.MovieinfoMapper;
import com.base.myweb.pojo.movie.Moviecol;
import com.base.myweb.pojo.movie.Moviegroup;
import com.base.myweb.pojo.movie.Movieinfo;
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

    @Autowired
    MovieinfoMapper movieinfoMapper;

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

    /**
     * 获取栏目和分类
     * */
    public List<Movieinfo> getMovieList(String movieGroup){
        QueryWrapper queryWrapper = new QueryWrapper();
        if(!"".equals(movieGroup)){
            queryWrapper.eq("MOVIE_GROUP", movieGroup);
        }
        queryWrapper.orderByDesc("TIME");
        List<Movieinfo> movieList = movieinfoMapper.selectList(queryWrapper);
        return movieList;
    }

    public String getMoviePathByMovieId(String movieId){
        String moviePath = "";

        QueryWrapper queryWrapper = new QueryWrapper();
        if(!"".equals(movieId)){
            queryWrapper.eq("MOVIE_ID", movieId);
        }
        Movieinfo movieinfo = movieinfoMapper.selectOne(queryWrapper);
        if(movieinfo!=null){
            moviePath = movieinfo.getPath() + File.separator+movieinfo.getMovieId()+"."+movieinfo.getTyp();
        }
        return moviePath;
    }


}
