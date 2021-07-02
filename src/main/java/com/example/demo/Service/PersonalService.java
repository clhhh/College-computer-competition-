package com.example.demo.Service;

import com.example.demo.result.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface PersonalService {
    Result getUserMesByToken(HttpServletRequest request, HttpServletResponse response);
    Result getUserWorkMesByToken(HttpServletRequest request, HttpServletResponse response);
    Result getUserMatchMesByToken(HttpServletRequest request, HttpServletResponse response);
    Result EditUser(HttpServletRequest request, HttpServletResponse response,String email, String number, String username, String major, String school,String college,String phone);
}
