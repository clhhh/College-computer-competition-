package com.example.demo.pojo.Workdomain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorksPrize {
    private Integer wid;
    private Integer uid;
    private String prize_file_name;
    private String author_name;
    private String worksname;
    private String match_name;
    private String match_type;
    private String prize_name;
    private Integer number;
    private String year;
    private String month;
    private String day;
}
