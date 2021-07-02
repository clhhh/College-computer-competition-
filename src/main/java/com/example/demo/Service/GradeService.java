package com.example.demo.Service;

import com.example.demo.result.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface GradeService {
    Result checkMark(HttpServletRequest request, HttpServletResponse response);
}
