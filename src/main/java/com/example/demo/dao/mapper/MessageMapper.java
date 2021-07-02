package com.example.demo.dao.mapper;

import com.example.demo.pojo.Message;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MessageMapper {
    List<Message> getMessage();
}
