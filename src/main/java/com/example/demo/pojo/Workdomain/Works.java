package com.example.demo.pojo.Workdomain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Works {
    private int wid;
    private int mid;
    private int user_id;
    private String worksname;
    private String teamname;
    private String match_name;
    private String match_project;
    private String introduce;
    private String design;
    private String date;
    private String category;
    private String teacher;
    private String PWteacher;
    private String unit;
    private String author1_name;
    private String author1_id;
    private String author2_name;
    private String author2_id;
    private String author3_name;
    private String author3_id;
    private String author4_name;
    private String author4_id;
    private String author5_name;
    private String author5_id;
    private String image1=null;
    private String image2=null;
    private String image3=null;
    private String image4=null;
    private String image5=null;
    private String video1=null;
    private String document=null;
    private String pdf=null;
    private String club_name;
    private double score;
    private int normalization;
    private int rank;
    private String prize_name;

    private int certificate;

    //奖金
    private int reward;
    private int like_count;
    private int visit_count;

    //评语

}