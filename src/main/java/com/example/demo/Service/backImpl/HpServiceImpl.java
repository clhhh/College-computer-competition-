package com.example.demo.Service.backImpl;

import com.example.demo.Service.HpService;

import com.example.demo.dao.mapper.LbContentMapper;
import com.example.demo.pojo.LBphoto;
import com.example.demo.result.Result;
import com.example.demo.result.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class HpServiceImpl implements HpService {

    @Autowired
    LbContentMapper lbContentMapper;
    @Override
    public Result QueryLbpic() {
        try{
            HashMap<String, Object> map = new HashMap<>();
            List<LBphoto> lBphotos = lbContentMapper.querylbPhoto();
            map.put("lBphotos",lBphotos);
            return new Result(ResultCode.DBC_SUCCESS,map);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(ResultCode.DBC_FALIURE);
        }


    }
}
