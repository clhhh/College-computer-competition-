package com.example.demo.pojo.Workdomain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromoteWorks {
    private int mid;
    private String match_name;
    private String match_project;
    private String type;
    private int totalWork;//总共提交的作品数量
    private double point;
    private int promoteNum;//出线人数
}
