package com.example.demo.Service;

import com.example.demo.result.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LoginUserService {
    Result login(String username, String password, HttpServletResponse response);
    Result LoginOut(HttpServletRequest request, HttpServletResponse response);
    Result Register(HttpServletRequest request, HttpServletResponse response,String email, String number, String username,
                    String password,  String major, String school,String college,String phone);
}
