package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Distribute {
    private int wid;
    private int tid;
    private int scoreNum;
    private String worksname;
    private String type;
    private String project;
    private String match_name;
    private String end_date;
    private String markMes;
    private String last_time;
}
