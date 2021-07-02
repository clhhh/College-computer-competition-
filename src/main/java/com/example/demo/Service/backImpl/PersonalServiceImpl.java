package com.example.demo.Service.backImpl;

import com.example.demo.Service.PersonalService;

import com.example.demo.dao.mapper.MatchMapper;
import com.example.demo.dao.mapper.UserMapper;
import com.example.demo.dao.mapper.WorksMapper;
import com.example.demo.pojo.Match;
import com.example.demo.pojo.Userdomain.CurrentUser;

import com.example.demo.pojo.Userdomain.User;
import com.example.demo.pojo.Workdomain.Works;
import com.example.demo.result.Result;
import com.example.demo.result.ResultCode;
import com.example.demo.util.CookieUtil;
import com.example.demo.util.GetRequestTokenUtil;
import com.example.demo.util.TokenSubjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

@Transactional(isolation = Isolation.REPEATABLE_READ)
@Service
public class PersonalServiceImpl implements PersonalService {
    @Autowired
    GetRequestTokenUtil getRequestTokenUtil;
    @Autowired
    MatchMapper matchMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    WorksMapper worksMapper;
    @Override
    public Result getUserMesByToken(HttpServletRequest request, HttpServletResponse response){

        String token = getRequestTokenUtil.getToken(request,response);
        CurrentUser user = new TokenSubjectUtil().getUserByToken(token);

        /*通过 学号 获取参赛项目*/
        List<Match> matches = matchMapper.queryMatchMesByUid(user.getNumber());
        /*通过 uid 获取个人信息*/
        List<User> users = userMapper.queryUserMesByUid(user.getPid());
        /*通过 学号 获取参赛作品--无奖状*/
        List<Works> works = worksMapper.queryWorkMesByNumber(user.getNumber());
        HashMap<String, Object> map = new HashMap<>();

//        for (User user1: users) {
//            System.out.println(user1);
//            map.put("username",user1.getUsername());
//            map.put("passsword",user1.getPassword());
//            map.put("phone",user1.getPhone());
//            map.put("college", user1.getCollege());
//            map.put("email", user1.getEmail());
//            map.put("number", user1.getNumber());
//            map.put("major", user1.getMajor());
//        }
        map.put("userList",users);
        map.put("matchMes",matches);
        map.put("works",works);
        System.out.println("返回个人比赛和作品信息");
        System.out.println("作品个数"+works.size());
        return new Result(ResultCode.DBC_SUCCESS,map);

    }
    public Result getUserWorkMesByToken(HttpServletRequest request, HttpServletResponse response){

        String token = getRequestTokenUtil.getToken(request,response);
        CurrentUser user = new TokenSubjectUtil().getUserByToken(token);

        /*通过 uid 获取个人信息*/
        List<User> users = userMapper.queryUserMesByUid(user.getPid());
        /*通过 学号 获取参赛作品--无奖状*/
        List<Works> works = worksMapper.queryWorkMesByNumber(user.getNumber());
        HashMap<String, Object> map = new HashMap<>();
        map.put("userList",users);
        map.put("works",works);
        System.out.println("返回个人作品信息");
        return new Result(ResultCode.DBC_SUCCESS,map);

    }
    public Result getUserMatchMesByToken(HttpServletRequest request, HttpServletResponse response){

        String token = getRequestTokenUtil.getToken(request,response);
        CurrentUser user = new TokenSubjectUtil().getUserByToken(token);

        /*通过 学号 获取参赛项目*/
        List<Match> matches = matchMapper.queryMatchMesByUid(user.getNumber());
        /*通过 uid 获取个人信息*/
        List<User> users = userMapper.queryUserMesByUid(user.getPid());
        HashMap<String, Object> map = new HashMap<>();
        map.put("userList",users);
        map.put("matchMes",matches);

        System.out.println("返回个人比赛信息");
        return new Result(ResultCode.DBC_SUCCESS,map);

    }
    @Override
    public Result EditUser(HttpServletRequest request, HttpServletResponse response,String number, String school,String username, String college,String major,String email, String phone) {
        String token = getRequestTokenUtil.getToken(request,response);
        CurrentUser currentUser = new TokenSubjectUtil().getUserByToken(token);
        User user = new User();
        user.setUid(currentUser.getPid());
        user.setNumber(number);
        user.setUsername(username);
        user.setSchool(school);
        user.setCollege(college);
        user.setMajor(major);
        user.setEmail(email);
        user.setPhone(phone);
        try {
            userMapper.updateUser(user);
            return new Result(ResultCode.DBC_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(ResultCode.DBC_FALIURE);
        }

    }
}
