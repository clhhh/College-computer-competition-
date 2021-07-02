package com.example.demo.pojo.Workdomain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorksPF {
    private int wid;
    private String worksname;
    private String date;
    private String match_name;
    private String match_project;
    private String type;

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
    private String phone;
    private String teacher;
    private String school;
    private String college;
    private String major;
    private String email;
    private String number;

    private int certificate;
    private String prize_name;
    private List<Map<String,Object>> judge = null;
    private String PWteacher;
    private double score;
    private int rank;
    private int normalization;
    private int reward;
    private int like_count;
}
