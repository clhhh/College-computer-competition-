package com.example.demo.Controller.Client;

import com.example.demo.Service.LoginUserService;
import com.example.demo.Service.PersonalService;
import com.example.demo.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@Controller
@RequestMapping(value = "/api",method = RequestMethod.POST)
public class PersonalController {

    @Autowired
    PersonalService personalService;

    @CrossOrigin
    @PostMapping("/getUserMesByToken")
    @ResponseBody
    public Result ShowPersonMatches(HttpServletRequest request, HttpServletResponse response){
        return personalService.getUserMesByToken(request,response);
    }

    @CrossOrigin
    @PostMapping("/getUserWorkMesByToken")
    @ResponseBody
    public Result ShowPersonWorkMes(HttpServletRequest request, HttpServletResponse response){
        return personalService.getUserWorkMesByToken(request,response);
    }
    @CrossOrigin
    @PostMapping("/getUserMatchMesByToken")
    @ResponseBody
    public Result ShowPersonMatchMes(HttpServletRequest request, HttpServletResponse response){
        return personalService.getUserMatchMesByToken(request,response);
    }

    @PostMapping("/EditUser")
    @ResponseBody
    public Result EditGser(HttpServletRequest request,
                           HttpServletResponse response,
                           @RequestParam(value = "username",required = false)String username,
                           @RequestParam(value = "email",required = false)String email,
                           @RequestParam(value = "college",required = false)String college,
                           @RequestParam(value = "major",required = false)String major,
                           @RequestParam(value = "number",required = false)String number,
                           @RequestParam(value = "school",required = false)String school,
                           @RequestParam(value = "phone",required = false)String phone){
        return personalService.EditUser(request,response, number,  school, username,  college, major, email,  phone);
    }
}
