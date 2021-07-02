package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Dynamic {

    private int id;
    private String content;
    private String short_content;
    private String title;
    private String name;
    private String time;
    private String match_name;
    private String match_project;
    private String image1=null;
    private String image2=null;
    private String image3=null;
    private String image4=null;
}