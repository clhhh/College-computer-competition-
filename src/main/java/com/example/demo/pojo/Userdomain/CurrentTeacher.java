package com.example.demo.pojo.Userdomain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrentTeacher {
    private int tid;
    private String username;
    private String realname;
    private String college;
    private String password;
    private String school;
    private String phone;
    private String email;
    private int code;
    private String role;
}
