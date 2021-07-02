package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DailyData {

    private Integer client_daily_count;
    private Integer admin_daily_count;
    private Integer works_daily_count;
    private Integer notice_daily_count;
    private Integer match_daily_count;
    private Integer share_daily_count;
    private Integer club_daily_count;
    private Integer person_daily_count;
    private Integer question_daily_count;
    private Integer submit_daily_count;
    private Integer grade_daily_count;
    private String date;
}
