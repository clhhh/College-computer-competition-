package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Match {
    private int mid;
    private String name;   //大赛
    private String project;  //大赛里的项目
    private int now;
    private String type;
    private String start_date;
    private String end_date;
    private int club_id;
    private String clubname;
    private String number;
    private String content;
    private String short_content;
    private String poster;
    private String image;
    private int onTime;
    private int isProject;
    //判别是否是作品赛
    private int isCourse;
    //判别是否已经归档
    private int isSort;
}
