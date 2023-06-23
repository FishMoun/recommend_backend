package com.hhu.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
public class RatedMovie {
    private Integer id;
    private String name;
    private String publishedYear;
    private String type;
    private String posterUrl;
    private String introduction;

    private double avgRate;
    private int rateNums;
    private int curRate;
}
