package com.example.demo.pojo.Userdomain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrentUser {
    private int pid;
    private String pwd;
    private String username;
    private String number;
    private String school;
    private String college;
    private String major;
    private String email;
    private String phone;
    private HashSet<Role> roles = new HashSet<>();




}
