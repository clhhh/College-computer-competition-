package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prize {
    private Integer pid;
    private Integer mid;
    private Integer pr_id;
    private String path;
    private String prize_name;
    private Integer prize_type;
    private String file_name;
    private String match_name;
    private String match_project;
    private String match_type;
    private Integer number;
    private Integer level;
    private String level_name;
}
