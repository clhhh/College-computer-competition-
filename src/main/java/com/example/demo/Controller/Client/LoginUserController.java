package com.example.demo.Controller.Client;

import com.example.demo.Service.LoginUserService;
import com.example.demo.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping(value = "/api",method = RequestMethod.POST)
public class LoginUserController {
    @Autowired
    LoginUserService loginUserService;

    @CrossOrigin
    @PostMapping("/loginCheck")
    @ResponseBody
    public Result login(@RequestParam("username")String username,
                        @RequestParam("password")String password,
                        HttpServletResponse response){
        return loginUserService.login(username,password,response);
    }

    @CrossOrigin
    @PostMapping("/loginOut")
    public Result LoginOut(HttpServletRequest request, HttpServletResponse response){
        return loginUserService.LoginOut(request,response);
    }


    @PostMapping("/Show")
    public Result ShowPersonMatches(HttpServletRequest request, HttpServletResponse response){
        return loginUserService.LoginOut(request,response);
    }
    @PostMapping("/Register")
    @ResponseBody
    public Result Register(HttpServletRequest request,
                           HttpServletResponse response,
                           @RequestParam(value = "username",required = false)String username,
                           @RequestParam(value = "password",required = false)String password,
                           @RequestParam(value = "email",required = false)String email,
                           @RequestParam(value = "college",required = false)String college,
                           @RequestParam(value = "major",required = false)String major,
                           @RequestParam(value = "number",required = false)String number,
                           @RequestParam(value = "school",required = false)String school,
                           @RequestParam(value = "phone",required = false)String phone){
        return loginUserService.Register(
                request,response, email,number,   username, password, major,school,college, phone);
    }


}
