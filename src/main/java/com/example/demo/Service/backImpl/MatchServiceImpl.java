package com.example.demo.Service.backImpl;

import com.example.demo.Service.MatchService;
import com.example.demo.dao.mapper.MatchMapper;
import com.example.demo.pojo.Match;
import com.example.demo.result.Result;
import com.example.demo.result.ResultCode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@Transactional(isolation = Isolation.REPEATABLE_READ)
public class MatchServiceImpl implements MatchService {

    @Autowired
    MatchMapper matchMapper;
    @Override
    public Result GetAllMatchMes() {
        try{

            List<Match> matches = matchMapper.queryMatchMes();
            PageInfo<Match> page = new PageInfo<>(matches);
            HashMap<String, Object> pageMes = new HashMap<>();
            pageMes.put("pageTotal",page.getTotal());
            pageMes.put("questionList",matches);
            System.out.println("matchmes return ok");
            return new Result(ResultCode.DBC_SUCCESS,pageMes);

        }catch (Exception e){
            e.printStackTrace();
            return new Result(ResultCode.DBC_FALIURE);
        }
    }
    @Override
    public Result fastGetAllMatchMes() {
        try{

            List<Match> matches = matchMapper.fastqueryMatchMes();
            PageInfo<Match> page = new PageInfo<>(matches);
            HashMap<String, Object> pageMes = new HashMap<>();
            pageMes.put("pageTotal",page.getTotal());
            pageMes.put("questionList",matches);
            System.out.println("matchmes return ok");
            return new Result(ResultCode.DBC_SUCCESS,pageMes);

        }catch (Exception e){
            e.printStackTrace();
            return new Result(ResultCode.DBC_FALIURE);
        }
    }

    @Override
    public Result queryContentMesByMid(int mid) {
        try {
            List<Match> matches = matchMapper.queryMatchContentByMid(mid);
            HashMap<String, Object> content = new HashMap<>();
            content.put("content", matches);
            return new Result(ResultCode.DBC_SUCCESS, content);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCode.DBC_FALIURE);
        }
    }
}
