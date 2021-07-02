package com.example.demo.dao.mapper;

import com.example.demo.pojo.Distribute;
import com.example.demo.pojo.Match;
import com.example.demo.pojo.Userdomain.CurrentUser;
import com.example.demo.pojo.Userdomain.Teacher;
import com.example.demo.pojo.Userdomain.TeacherJudge;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface TeacherMapper {
    CurrentUser loginCheck(String username);
    CurrentUser loginCheckTeacherGser(String username);
    CurrentUser loginCheckCJ(String username);
    /*查询所有老师的信息*/
    List<Teacher> queryTeachMes();
    /*通过id查询所有老师的信息*/
    List<Teacher> queryTeachMesByid(int tid);
    /*通过老师的真实姓名获取老师信息*/
    List<Teacher> queryTeachMesByString(Map map);
    /*添加教师*/
    int insertTeacherMes(Teacher teacher);
    /*更新教师*/
    int updateTeacherMes(Teacher teacher);
    /*删除老师信息*/
    int deleteTeacherMes(int tid);




    /*获取所有评委信息*/
    List<TeacherJudge> queryJudge();
    /*通过id获取评委信息*/
    List<Teacher> queryJudgeByid(int tid);
    /*通过评委的真实姓名获取评委信息*/
    List<Teacher> queryJudgeMesByString(Map map);
    /*选为评委*/
    int insertTeacher_role(int tid,int rid);
    int updateTeacher_role(int tid,int rid);
    int updateTeacherCode(int tid);
    /*取消成为评委*/
    int deleteTeacher_role_Judge(int tid,int rid);
    int cancelTeacherCode(int tid);
    /*取消成为管理员*/
    int deleteTeacher_role_Gser(Integer tid);
    /*根据评委ID获取所有被分配到比赛信息*/
    List<Match> queryMatchByTid(int tid);
    /*根据评委ID获取被分配到的作品*/
    List<Distribute> queryWorksByTid(int tid);
    //通过wid和tid 查询被分配作品信息
    Distribute queryDistributeWork(Integer wid, Integer tid);



}
