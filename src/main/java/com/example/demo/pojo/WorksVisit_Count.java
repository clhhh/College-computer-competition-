package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorksVisit_Count {
    private Integer wid;
    private String worksname;
    private Integer visit_count;
}
