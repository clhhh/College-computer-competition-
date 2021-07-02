package com.example.demo.pojo.Workdomain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * 作品-竞赛类别-阶段映射类
 */
public class WorkMatchNorinfo {
    private int mid;
    //根据竞赛类别记录不同阶段的作品个数
    private int counts;
}
