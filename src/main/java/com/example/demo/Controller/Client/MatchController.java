package com.example.demo.Controller.Client;

import com.example.demo.Service.MatchService;
import com.example.demo.Service.PersonalService;
import com.example.demo.dao.mapper.MatchMapper;
import com.example.demo.result.Result;
import com.example.demo.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@CrossOrigin
@Controller
@RequestMapping(value = "/api",method = RequestMethod.GET)
public class MatchController {

    @Autowired
    MatchService matchServiceImpl;


    /*获取所有的竞赛项目信息*/

    @PostMapping("/getAllMatchMes")
    @ResponseBody
    public Result GetAllMatchMes(){
        return matchServiceImpl.GetAllMatchMes();
    }


    @PostMapping("/fastgetAllMatchMes")
    @ResponseBody
    public Result fastGetAllMatchMes(){
        return matchServiceImpl.fastGetAllMatchMes();
    }

    @PostMapping("/queryContentMesByMid")
    @ResponseBody
    public Result queryContentMesByMid(int mid){
        return matchServiceImpl.queryContentMesByMid(mid);
    }
}
