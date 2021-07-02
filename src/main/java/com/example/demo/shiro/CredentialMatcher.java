package com.example.demo.shiro;


import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*密码校验规则*/
public class CredentialMatcher extends SimpleCredentialsMatcher {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken userToken = (UsernamePasswordToken)token;
        /*获取用户输入的密码*/
        String password = new String (userToken.getPassword());
        /*获取数据库密码*/
        String dbpassword = (String) info.getCredentials();
        if (equals(password,dbpassword)){
            logger.info("用户凭证验证成功");
            return true;
        }else{
            logger.info("用户凭证验证失败");
            return false;
        }
    }
}
