package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLike_Count {
    private Integer uid;
    //点赞时间
    private String like_time;
    //每日点赞总数
    private Integer like_daily_count;
}
