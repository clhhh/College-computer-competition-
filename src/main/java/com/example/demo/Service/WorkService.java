package com.example.demo.Service;


import com.example.demo.result.Result;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface WorkService {
    Result uploadSubmit(String worksname, String teamname, Integer mid, String teacher, String unit,
String introduce, String design, String author2_name, String author2_number, String author3_name,
String author3_number, String author4_name, String author4_number, String author5_name,
String author5_number,HttpServletRequest request, HttpServletResponse response);


}
