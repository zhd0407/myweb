package com.base.myweb.controller;


import com.base.myweb.pojo.movie.Moviecol;
import com.base.myweb.service.serviceImpl.MovieServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

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
        return "movie/index";
    }

    @RequestMapping("/play")
    public String play( Model model){
        List<Moviecol> list = movieService.getMovieColInfo();
        model.addAttribute("MovieColInfo",list);
        return "movie/play";
    }

    @RequestMapping("/movieList")
    public String movieList(Model model, @RequestParam(name = "code",required = true)String code){
        List<Moviecol> list = movieService.getMovieColInfo();
        model.addAttribute("MovieColInfo",list);
        model.addAttribute("code",code);
        return "movie/movieList";
    }
}
