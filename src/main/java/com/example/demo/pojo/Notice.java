package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Notice {

    private int id;
    private String title;
    private String content;
    private String short_content;
    private String name;
    private String time;
    private String match_name;
    private String match_project;
    private String image=null;
}