package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Certification {
    private int id;
    private int uid;
    private int mid;
    private int wid;
    private Date date;
    private String name;
    private String reward;
    private String certificate;
    private int review;
}
