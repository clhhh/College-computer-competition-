package com.example.demo.Service;

import com.example.demo.result.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface GserService {
     Result GetGserMesList(String pageNum, String pageSize);
     Result GetGserMesByToken(HttpServletRequest request, HttpServletResponse response);
     Result GetGserMesByid(Integer gid);
     Result GetGserMesByString(String pageNum, String pageSize, String realname, String username);
     Result addGser(String realname, String username, String password, Integer roleId,Integer cid);
     Result EditGser(String realname, Integer gid, String username, String password, Integer roleId,Integer cid);
     Result DeleteGser(int gid);

     Result GetRoles();
}
