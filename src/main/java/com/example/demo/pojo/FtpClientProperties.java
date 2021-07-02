package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FtpClientProperties  {
    // ftp地址
    private String host = "sd.bnuz.edu.cn";
    // 端口号
    private Integer port = 21;
    // 登录用户
    private String username = "Administrator";
    // 登录密码
    private String password = "QAQsss123";
    // 被动模式
    private boolean passiveMode = false;
    // 编码
    private String encoding = "GBK";
    // 连接超时时间(秒)
    private Integer connectTimeout=30000;
    // 缓冲大小
    private Integer bufferSize = 1024;
}
