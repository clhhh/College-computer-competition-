package com.example.demo.Service.backImpl;


import com.example.demo.Service.TeacherService;
import com.example.demo.dao.mapper.TeacherMapper;
import com.example.demo.pojo.Userdomain.Teacher;
import com.example.demo.pojo.Userdomain.TeacherJudge;
import com.example.demo.result.Result;
import com.example.demo.result.ResultCode;
import com.example.demo.util.POIExcelUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Transactional(isolation = Isolation.REPEATABLE_READ)
@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    TeacherMapper teacherMapper;

    @Value("${file.path}")
    private String filePath;
    @Value("${location.path}")
    private String locationPath;
    @Override
    public Result GetTeacherMes(String pageNum, String pageSize) {
        try{
            PageHelper.startPage(Integer.parseInt(pageNum),Integer.parseInt(pageSize));
            List<Teacher> teachers = teacherMapper.queryTeachMes();
            PageInfo<Teacher> page = new PageInfo<>(teachers);
            HashMap<String, Object> pageMes = new HashMap<>();
            pageMes.put("pageTotal",page.getTotal());
            pageMes.put("teachList",teachers);
            return new Result(ResultCode.DBC_SUCCESS,pageMes);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(ResultCode.DBC_FALIURE);
        }
    }

    @Override
    public Result GetTeacherMesByid(Integer tid) {
        try{
            List<Teacher> teachers = teacherMapper.queryTeachMesByid(tid);
            return new Result(ResultCode.DBC_SUCCESS,teachers);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(ResultCode.DBC_FALIURE);
        }
    }

    @Override
    public Result AddTeacherMes(Teacher teacher) {
        try{
            int i = teacherMapper.insertTeacherMes(teacher);
            int i1 = teacherMapper.insertTeacher_role(teacher.getTid(),-1);
            return new Result(ResultCode.DBC_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(ResultCode.DBC_FALIURE);
        }
    }

    @Override
    public Result UpdateTeacherMes(Teacher teacher) {
        try{
            int i = teacherMapper.updateTeacherMes(teacher);
            return new Result(ResultCode.DBC_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(ResultCode.DBC_FALIURE);
        }
    }

    @Override
    public Result DeleteTeacherMes(int tid) {
        try{
            int i = teacherMapper.deleteTeacherMes(tid);
            return new Result(ResultCode.DBC_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(ResultCode.DBC_FALIURE);
        }
    }

    @Override
    public Result GetTeacherMesByString(String pageNum, String pageSize, String realname, String college, String school) {
        try{
            HashMap<String, Object> map = new HashMap<>();
            map.put("realname",realname);
            map.put("college",college);
            map.put("school",school);
            PageHelper.startPage(Integer.parseInt(pageNum),Integer.parseInt(pageSize));
            List<Teacher> teachers = teacherMapper.queryTeachMesByString(map);
            PageInfo<Teacher> page = new PageInfo<>(teachers);

            HashMap<String, Object> pageMes = new HashMap<>();
            pageMes.put("pageTotal",page.getTotal());
            pageMes.put("teachList",teachers);
            return new Result(ResultCode.DBC_SUCCESS,pageMes);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(ResultCode.DBC_FALIURE);
        }
    }

    @Override
    public Result TobeJudge(Integer tid) {
        try{
            int i = teacherMapper.updateTeacher_role(tid,5);
            int i1 = teacherMapper.updateTeacherCode(tid);
            return new Result(ResultCode.DBC_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(ResultCode.DBC_FALIURE);
        }
    }

    @Override
    public Result TonotbeJudge(Integer tid) {
        try{
            //int i = teacherMapper.updateTeacher_role(tid,-1);
            int i1 = teacherMapper.cancelTeacherCode(tid);
            return new Result(ResultCode.DBC_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(ResultCode.DBC_FALIURE);
        }
    }

    @Override
    public Result GetJudgeMes() {
        try{
            List<TeacherJudge> teachers = teacherMapper.queryJudge();
            return new Result(ResultCode.DBC_SUCCESS,teachers);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(ResultCode.DBC_FALIURE);
        }
    }

    @Override
    public Result GetJudgeMesByid(Integer tid) {
        try{
            List<Teacher> teachers = teacherMapper.queryJudgeByid(tid);
            return new Result(ResultCode.DBC_SUCCESS,teachers);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(ResultCode.DBC_FALIURE);
        }
    }
    @Override
    public Result GetJudgeMesByName(String pageNum, String pageSize, String realname, String college, String school) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("realname",realname);

        map.put("college",college);
        map.put("school",school);
        try{
            PageHelper.startPage(Integer.parseInt(pageNum),Integer.parseInt(pageSize));
            List<Teacher> teachers = teacherMapper.queryJudgeMesByString(map);
            PageInfo<Teacher> page = new PageInfo<>(teachers);
            HashMap<String, Object> pageMes = new HashMap<>();
            pageMes.put("pageTotal",page.getTotal());
            pageMes.put("teachList",teachers);
            return new Result(ResultCode.DBC_SUCCESS,pageMes);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(ResultCode.DBC_FALIURE);
        }
    }

    @Override
    public Result DownloadTeacherMes() {
        try{
            List<Teacher> teachers = teacherMapper.queryTeachMes();
            boolean b = POIExcelUtil.readTeacherMesExcelData(teachers,filePath+"dataList/teacher.xlsx");
            if (b){
                return new Result(ResultCode.DBC_SUCCESS,locationPath+"dataList/teacher.xlsx");
            }else{
                return new Result(ResultCode.DBC_FALIURE);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new Result(ResultCode.DBC_FALIURE);
        }
    }

    @Override
    public Result SetTeacherGRole(Integer tid,Integer roleId) {
        try{
            //取消管理员身份
            if (roleId == -1){
                int i = teacherMapper.updateTeacher_role(tid,-1);
                return new Result(ResultCode.DBC_SUCCESS);
            }else{
                //设置管理员身份
                int i = teacherMapper.updateTeacher_role(tid,roleId);
                return new Result(ResultCode.DBC_SUCCESS);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new Result(ResultCode.DBC_FALIURE);
        }
    }
}
