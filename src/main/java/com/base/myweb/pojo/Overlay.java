package com.base.myweb.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class Overlay {
    @TableId(type = IdType.AUTO)
    private  Integer id;
    private Integer radius;
    private String type;
    private String points;
    private String bzDesc;

}
