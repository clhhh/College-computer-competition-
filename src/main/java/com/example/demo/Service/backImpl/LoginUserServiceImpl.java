package com.example.demo.Service.backImpl;

import com.example.demo.Service.LoginUserService;
import com.example.demo.dao.mapper.UserMapper;
import com.example.demo.pojo.Userdomain.CurrentUser;
import com.example.demo.pojo.Userdomain.User;
import com.example.demo.pojo.Userdomain.User_Role;
import com.example.demo.result.Result;
import com.example.demo.result.ResultCode;
import com.example.demo.shiro.UserToken;
import com.example.demo.util.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Transactional(isolation = Isolation.REPEATABLE_READ)
                        @Service
public class LoginUserServiceImpl implements LoginUserService {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    GetRequestTokenUtil getRequestTokenUtil;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    UserMapper userMapper;

    @Override
    public Result login(@RequestParam("username") String userid,
                        @RequestParam("password") String password1,
                        HttpServletResponse response){
        if (userid !=null && password1!=null){
            /*将登录用户封装为Stutoken令牌*/
            UserToken usertoken = new UserToken(userid,password1,"Stu");
            /*获取登录主体*/
            Subject subject = SecurityUtils.getSubject();
            try{
                /*进行主体登录验证*/
                subject.login(usertoken);
                /*获取登录成功的主体*/
                CurrentUser user =(CurrentUser) subject.getPrincipal();
                logger.info("成功获得登录主体:"+user.getUsername());
                String token = JwtUtil.sign(user.getUsername(),user.getPwd(),30*60*1000);
                new TokenSubjectUtil().saveSubject(token,subject);
//                redisUtil.hset_Subject("user_token", token, user);
                CookieUtil.set(response,"token",token,true);
                CookieUtil.set(response,"role","s",true);
                return new Result(ResultCode.LOGIN_SUCCESS,token);
            }catch (IncorrectCredentialsException e){
                return new Result(ResultCode.PWDORUSER_ERROR);
            }catch (AuthenticationException e){
                return new Result(ResultCode.NOPERSON_ERROR);
            }
        }else{
            return new Result(ResultCode.NO_LOGIN);
        }
    }

    @Override
    public Result LoginOut(HttpServletRequest request, HttpServletResponse response) {
        String token = getRequestTokenUtil.getToken(request,response);
        CurrentUser user = new TokenSubjectUtil().getUserByToken(token);
        if (user!=null){
            logger.info("用户:"+user.getUsername()+"登出");
            logger.info("*********----************");

            CookieUtil.remove(request,response,"token");
            CookieUtil.remove(request,response,"role");
            new TokenSubjectUtil().deleteSubject(token);
        }/*else{
            if (token!=null) {
                CookieUtil.remove(request, response, "token");
                CookieUtil.remove(request, response, "role");
                logger.info("用户登出");
            }*/
            return new Result(ResultCode.LOGINOUT_SUCCESS);
    }

     @Override
     public Result Register(
             HttpServletRequest request, HttpServletResponse response,String email, String number, String username,
             String password,  String major, String school,String college,String phone
            ) {
         User user = new User();
         User_Role ur = new User_Role();
         Integer uid = new Random().nextInt(899999) + 100000;
         SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
         user.setUid(uid);
         user.setPassword(password);
         user.setUsername(username);
         user.setNumber(number);
         user.setSchool(school);
         user.setCollege(college);
         user.setMajor(major);
         user.setEmail(email);
         user.setPhone(phone);
         ur.setRid(1);
         ur.setUid(uid);
         try{
             int i = userMapper.addUser(user);
             int j = userMapper.addRole(ur);
             System.out.println(i);
             System.out.println(j);
             System.out.println("新注册用户uid为"+user.getUid());
             return new Result(ResultCode.REGISTER_SUCCESS);
         }catch(Exception e){
             e.printStackTrace();
             return new Result(ResultCode.REGISTER_FAILURE);
         }
     }


}
