package com.example.demo.Controller.Client;

import com.example.demo.Service.MessageService;
import com.example.demo.Service.PersonalService;
import com.example.demo.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/api",method = RequestMethod.GET)
public class MessageController {

    @Autowired
    MessageService messageServiceImpl;

    @CrossOrigin
    @PostMapping("/getMessage")
    @ResponseBody
    public Result getMessage(){
        return messageServiceImpl.GetMessage();
    }
}