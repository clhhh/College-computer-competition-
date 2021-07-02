package com.example.demo.Service;

import com.example.demo.result.Result;

public interface MatchService {
    Result GetAllMatchMes();
    Result fastGetAllMatchMes();
    Result queryContentMesByMid(int mid);
}
