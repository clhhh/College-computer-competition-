package com.example.demo.shiro;

import com.example.demo.dao.mapper.GserMapper;
import com.example.demo.dao.mapper.TeacherMapper;
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

public class GserRealm extends AuthorizingRealm {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    GserMapper gserMapper;
    @Autowired
    TeacherMapper teacherMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        CurrentUser gser = (CurrentUser) principalCollection.getPrimaryPrincipal();
        logger.info(gser.getUsername()+"登入");
        /*创建 权限字符列表 和 角色字符列表*/
        List<String> permissionList = new ArrayList<>();
        List<String> roleList = new ArrayList<>();
        /*得到User的角色*/
        Set<Role> roleSet = gser.getRoles();
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
        /*Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("permission",permissionList);
        session.setAttribute("role",roleList);*/
     //   System.out.println("over");
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken Token) throws AuthenticationException {
        UserToken usertoken = (UserToken) Token;
        /*根据主体获取 用户输入的用户名*/
        String username = usertoken.getUsername();

        /*验证登录*/
        CurrentUser gser = gserMapper.loginCheck(username);
        if (gser == null){
            logger.info("teacherGser--->login in");
            gser = teacherMapper.loginCheckTeacherGser(username);
            System.out.println("gser_in:"+gser);
        }
        System.out.println("gser_out:"+gser);
        return new SimpleAuthenticationInfo(gser,gser.getPwd(),this.getClass().getName());
    }
}
