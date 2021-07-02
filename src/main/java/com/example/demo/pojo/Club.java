package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Club {
    private int cid;
    private String clubname;
    private String leader;
    private String phone;
    private String date;
    private String college;
    private String email;
    private String content;
}
