package com.example.demo.pojo.Workdomain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkID {
    private int id;
    private int rank;
    private double score;
    private String judge;
    private String estimate;
}
