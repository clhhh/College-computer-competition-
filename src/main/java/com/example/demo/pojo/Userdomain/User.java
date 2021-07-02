package com.example.demo.pojo.Userdomain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
//无参构造和有参构造
public class User {


    private int uid;
    private String password;
    private String number;
    private String username;
    private String school;
    private String college;
    private String major;
    private String email;
    private String phone;
    private String head;
    private String register_date;
    private String match_id;
    private String match_name;
    private String match_project;
    private String type;
    private String worksname;
    private String status;
    private String teacher;
    private String code;
    private Set<Role> roles = new HashSet<>();

}
