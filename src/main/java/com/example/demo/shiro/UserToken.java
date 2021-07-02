package com.example.demo.shiro;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.shiro.authc.UsernamePasswordToken;

@Data
@NoArgsConstructor
public class UserToken extends UsernamePasswordToken {
    private String usertype;
    public UserToken(final String username,final String password,final String usertype){
        super(username,password);
        this.usertype=usertype;
    }
}
