package com.example.demo.Service.backImpl;

import com.example.demo.Service.GradeService;
import com.example.demo.dao.mapper.TeacherMapper;
import com.example.demo.pojo.Distribute;
import com.example.demo.pojo.Userdomain.CurrentUser;
import com.example.demo.pojo.Userdomain.Teacher;
import com.example.demo.result.Result;
import com.example.demo.result.ResultCode;
import com.example.demo.util.GetRequestTokenUtil;
import com.example.demo.util.TokenSubjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

@Transactional(isolation = Isolation.REPEATABLE_READ)
@Service
public class GradeServiceImpl implements GradeService{
    @Autowired
    GetRequestTokenUtil getRequestTokenUtil;
    @Autowired
    TeacherMapper teacherMapper;
    @Override
    public Result checkMark(HttpServletRequest request, HttpServletResponse response){
        String token = getRequestTokenUtil.getToken(request,response);
        CurrentUser teacher = new TokenSubjectUtil().getUserByToken(token);

        /*获取评委的个人信息*/
        List<Teacher> teacherList = teacherMapper.queryJudgeByid(teacher.getPid());
        /*据评委ID获取被分配到的作品*/
//            PageHelper.startPage(Integer.parseInt(pageNum),11);
        List<Distribute> worksList = teacherMapper.queryWorksByTid(teacher.getPid());
//            PageInfo<Distribute> pageWorks = new PageInfo<>(worksList);
        HashMap<String, Object> map = new HashMap<>();
        map.put("teacherList",teacherList);
        map.put("worksList",worksList);
        return new Result(ResultCode.DBC_SUCCESS,map);

    }

}
