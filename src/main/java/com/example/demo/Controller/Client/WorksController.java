package com.example.demo.Controller.Client;

import com.example.demo.Service.PersonalService;
import com.example.demo.Service.WorkService;
import com.example.demo.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/api",method = RequestMethod.POST)
public class WorksController {
    @Autowired
    WorkService workService;

    @CrossOrigin
    @PostMapping("/uploadSubmit")
    @ResponseBody
            public Result ShowPersonMatches(String worksname, String teamname, Integer mid, String teacher, String unit,
                                    String introduce, String design, String author2_name, String author2_number, String author3_name,
                                    String author3_number, String author4_name, String author4_number, String author5_name,
                                    String author5_number,HttpServletRequest request, HttpServletResponse response){
        return workService.uploadSubmit( worksname, teamname,  mid, teacher,unit,introduce, design,  author2_name,  author2_number,  author3_name,
                 author3_number,  author4_name, author4_number,  author5_name, author5_number,request,response);
    }
}
