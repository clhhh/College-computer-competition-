package com.example.demo.result;

import lombok.Getter;
import lombok.Setter;


@Setter@Getter
public class Result {
    private int code;
    private String msg;
    private Object obj;
    public Result(ResultCode resultCode){
        this.code = resultCode.getCode();
        this.msg = resultCode.getMesg();
    }
    public Result(ResultCode resultCode,Object obj){
        this.code = resultCode.getCode();
        this.msg = resultCode.getMesg();
        this.obj = obj;
    }
}
