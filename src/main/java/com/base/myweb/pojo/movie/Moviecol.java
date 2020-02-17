package com.base.myweb.pojo.movie;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.List;
@Data
public class Moviecol {

    private int mcolId;
    private String mcolCode;
    private String mcolName;
    private String mcolType;
    private String bzSht;
    @TableField(exist = false)
    private List<Moviegroup> moviegroups = null;
}
