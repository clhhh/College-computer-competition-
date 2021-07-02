package com.example.demo.pojo.Userdomain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    private int tid;
    private String username;
    private String realname;
    private String password;
    private String major;
    private String college;
    private String school;
    private String phone;
    private String email;
    private int code;

}
