package com.example.demo.Controller.Client;

import com.example.demo.Service.HpService;
import com.example.demo.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/api",method = RequestMethod.POST)
public class hpController {
    @Autowired
    HpService hpServiceImpl;

    @CrossOrigin
    @PostMapping("/queryLbpic")
    @ResponseBody
    public Result queryLbpic(){
        return hpServiceImpl.QueryLbpic();
    }
}
