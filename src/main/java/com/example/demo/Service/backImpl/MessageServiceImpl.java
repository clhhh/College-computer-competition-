package com.example.demo.Service.backImpl;

import com.example.demo.Service.MessageService;
import com.example.demo.dao.mapper.MessageMapper;
import com.example.demo.pojo.Message;
import com.example.demo.result.Result;
import com.example.demo.result.ResultCode;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    MessageMapper messageMapper;

    @Override
    public Result GetMessage() {
        try {
            List<Message> messages= messageMapper.getMessage();
            HashMap<String, Object> map = new HashMap<>();
            map.put("messageList",messages);

            return new Result(ResultCode.DBC_SUCCESS,map);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(ResultCode.DBC_FALIURE);
        }
    }
}
