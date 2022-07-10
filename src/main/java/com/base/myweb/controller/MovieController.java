package com.base.myweb.controller;


import com.base.myweb.pojo.movie.Moviecol;
import com.base.myweb.pojo.movie.Movieinfo;
import com.base.myweb.service.MovieServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    MovieServiceImpl movieService;


    @RequestMapping("/index")
    public String index( Model model){
        List<Moviecol> list = movieService.getMovieColInfo();
        model.addAttribute("MovieColInfo",list);
        List<Movieinfo> movieList = movieService.getMovieList("");
        model.addAttribute("movieList",movieList);
        return "/ignore/movie/index";
    }

    @RequestMapping("/play")
    public String play( Model model,@RequestParam(name = "movieId",required = true)String movieId){
        String path = movieService.getMoviePathByMovieId(movieId);
        model.addAttribute("moviePath",path);
        return "/ignore/movie/play";
    }

}
