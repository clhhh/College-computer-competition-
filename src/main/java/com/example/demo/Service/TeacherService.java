package com.example.demo.Service;

import com.example.demo.pojo.Userdomain.Teacher;
import com.example.demo.result.Result;

public interface TeacherService {
     Result GetTeacherMes(String pageNum, String pageSize);
     Result GetTeacherMesByid(Integer tid);
     Result AddTeacherMes(Teacher teacher);
     Result UpdateTeacherMes(Teacher teacher);
     Result DeleteTeacherMes(int tid);
     Result GetTeacherMesByString(String pageNum, String pageSize, String realname, String college, String school);
     Result TobeJudge(Integer tid);
     Result TonotbeJudge(Integer tid);
     Result GetJudgeMes();
     Result GetJudgeMesByid(Integer tid);
     Result GetJudgeMesByName(String pageNum, String pageSize, String realname, String college, String school);

    Result DownloadTeacherMes();

     Result SetTeacherGRole(Integer tid,Integer roleId);
}
