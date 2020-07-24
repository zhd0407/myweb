package com.base.myweb.pojo.movie;

import lombok.Data;

import java.util.Date;

@Data
public class Movieinfo {

    private String movieCol;
    private String movieGroup;
    private String movieId;
    private String movieName;
    private String author;
    private String title;
    private Date time;
    private String path;
    private String skinNum;
    private String typ;

}
