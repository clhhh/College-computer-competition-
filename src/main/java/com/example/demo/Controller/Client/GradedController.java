package com.example.demo.Controller.Client;

import com.example.demo.Service.GradeService;
import com.example.demo.Service.PersonalService;
import com.example.demo.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/api",method = RequestMethod.POST)
public class GradedController {

    @Autowired
    GradeService gradeService;

    @CrossOrigin
    @PostMapping("/checkMark")
    @ResponseBody
    public Result ShowPersonMatches(HttpServletRequest request, HttpServletResponse response){
        return gradeService.checkMark(request,response);
    }
}
