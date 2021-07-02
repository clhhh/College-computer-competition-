package com.example.demo.pojo.Userdomain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseJudge {
    private Integer cj_id;
    private Integer club_id;
    private Integer mid;
    private String username;
    private String realname;
    private String password;
    private String major;
    private String college;
    private String school;
    private String phone;
    private String email;
    private String match_name;
    private String match_project;
    private String match_type;
}
