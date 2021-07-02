package com.example.demo.pojo.Userdomain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Gser {
    private Integer gid;
    private String realname;
    private String username;
    private String password;
    private Integer code;
    private String clubname;
    private Set<Role> roles = new HashSet<>();
}
