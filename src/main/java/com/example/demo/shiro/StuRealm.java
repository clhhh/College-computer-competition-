package com.example.demo.shiro;


import com.example.demo.dao.mapper.UserMapper;
import com.example.demo.pojo.Userdomain.CurrentUser;
import com.example.demo.pojo.Userdomain.Permission;
import com.example.demo.pojo.Userdomain.Role;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class StuRealm extends AuthorizingRealm {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    UserMapper userMapper;
    /*授权*/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        CurrentUser user = (CurrentUser) principalCollection.getPrimaryPrincipal();
        logger.info(user.getUsername()+"登入进行授权");
        /*创建 权限字符列表 和 角色字符列表*/
        List<String> permissionList = new ArrayList<>();
        List<String> roleList = new ArrayList<>();
        /*得到User的角色*/
        Set<Role> roleSet = user.getRoles();
        /*封装权限和角色*/
        if (!CollectionUtils.isEmpty(roleSet)){
            for (Role role : roleSet) {
                Set<Permission> permissions = role.getPermissions();
                /*角色字符列表*/
                roleList.add(role.getRname());

                if (!CollectionUtils.isEmpty(permissions)){
                    for (Permission permission : permissions) {
                      /*权限字符列表*/
                        permissionList.add(permission.getName());
                    }
                }
            }
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissionList);
        info.addRoles(roleList);

        return info;
    }
    /*认证*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken Token) throws AuthenticationException {

        /*获取登录主体*/
        UserToken usertoken = (UserToken) Token;
        /*根据主体获取 用户输入的用户名*/
        String username = usertoken.getUsername();
        logger.info("用户 ："+username+"进行认证");
        /*验证登录*/
        CurrentUser user = userMapper.loginCheck(username);
        if (user == null){
            logger.info("不存在"+username+"用户");
        }else{
            logger.info("存在"+username+"用户");
        }
        return new SimpleAuthenticationInfo(user,user.getPwd(),this.getClass().getName());
    }
}
