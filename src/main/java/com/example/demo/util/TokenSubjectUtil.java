package com.example.demo.util;


import com.example.demo.pojo.Userdomain.CurrentUser;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
public class TokenSubjectUtil {

    Logger log = LoggerFactory.getLogger(this.getClass());


   @Autowired
   RedisUtil redisUtil;

   private static TokenSubjectUtil tokenSubjectUtil;


   @PostConstruct
   public void init(){
       tokenSubjectUtil = this;
   }
    public void saveSubject(String token,Subject sub) {
        CurrentUser user = (CurrentUser) sub.getPrincipal();
        //无论是否存在key，插入跟新即可
        log.info("正在保存用户:"+user.getUsername()+"token令牌");
        if (token == null){
            log.debug("token is null");
        }else{
            log.info("token is not null");
        }
        if (sub == null){
            log.debug("sub is null");
        }else{
            log.info("sub is not null");
        }
        try{
            boolean setSubject = tokenSubjectUtil.redisUtil.hset_Subject("user_token", token, user, 60 * 60 * 1000);
            if (setSubject){
                log.info("用戶:"+String.valueOf(user.getPid())+" 成功保存主体对象");
            }else{
                log.debug("用戶:"+String.valueOf(user.getPid())+" 失敗保存主体对象");
            }
        }catch (Exception e){
            e.printStackTrace();
            log.debug("用戶:"+String.valueOf(user.getPid())+" 失敗保存主体对象");
        }
    }
    public  CurrentUser getUserByToken(String token) {
        try{
            CurrentUser user = tokenSubjectUtil.redisUtil.hget_Subject("user_token", token);
            log.info("成功获取user:"+user.getUsername());
            return user;
        }catch (Exception e){
            e.printStackTrace();
            log.debug("失败获取");
            return null;
        }
    }
    public  void deleteSubject(String token){
       try{
           boolean isdelSuccess = tokenSubjectUtil.redisUtil.hdel_Subject("user_token", token);
           if (isdelSuccess){
               log.info("成功删除token");
           }else{
               log.debug("不成功删除token");
           }
       }catch (Exception e){
           log.debug("不成功删除token");
          e.printStackTrace();
       }
    }
}
