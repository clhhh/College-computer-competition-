package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarkTable {
    private int id;
    private int mid;
    private String name;
    private String matchname;
    private String project;
    private String type;
    private String content;
    private String path;

}
