package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AboutUs {

    private int id;
    private String content;//内容
    private String author;//作者
    private String content_short;
    private String time;
}
