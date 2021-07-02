package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LBvedio {
    private int id;
    private String path;
    private String image;
    private String vedioName;
    private String vedioPhotoName;
}
