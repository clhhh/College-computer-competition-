package com.example.demo.util;


import com.example.demo.pojo.Userdomain.CurrentUser;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class GetRequestTokenUtil {
    Logger logger = LoggerFactory.getLogger(getClass());
    public String getToken(HttpServletRequest request, HttpServletResponse response) {
        //从请求头查看有无token

        String token = request.getHeader(JwtUtil.getHeader());

        System.out.println("token is "+token);
        if (StringUtils.isBlank(token)) {
            //从请求参数查看有无token
            token = request.getParameter(JwtUtil.getHeader());
            if (StringUtils.isBlank(token)){
                logger.info("没有携带Token");
            }else{
                CurrentUser user = new TokenSubjectUtil().getUserByToken(token);
                if (user != null) {
                    if (!JwtUtil.verify(token,user.getUsername(),user.getPwd())){
                        logger.info("token无效,请重新登录");
                        CookieUtil.remove(request,response,"token");
                        new TokenSubjectUtil().deleteSubject(token);
                    }else{
                        logger.info("token授权成功");
                    }
                }else{
                    logger.info("CurrentUser为空");
                    CookieUtil.remove(request,response,"token");
                    new TokenSubjectUtil().deleteSubject(token);
                }

            }
        }
        return token;
    }
}
