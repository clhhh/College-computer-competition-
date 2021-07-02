package com.example.demo.shiro;

import org.apache.shiro.authz.Authorizer;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.PrincipalCollection;

public class UserModularRealmAuthorizer extends ModularRealmAuthorizer {
    @Override
    public boolean isPermitted(PrincipalCollection principals, String permission) {
       // System.out.println("进入自定义授权器");
        assertRealmsConfigured();
        for (Realm realm : getRealms()) {
            if (!(realm instanceof Authorizer)){ continue;}
            //  todo 授权配置
            if (realm.getName().contains("Stu")){// 判断realm
                if (permission.contains("选手权限")) {
                    // 判断是否改realm的资源
                    return ((StuRealm) realm).isPermitted(principals, permission);    // 使用改realm的授权方法
                }
            }
            if (realm.getName().contains("Judge")){
                if (permission.contains("评改作品权限")) {
                    return ((JudgeRealm) realm).isPermitted(principals, permission);
                }
            }
            if (realm.getName().contains("Gser")){
                if (permission.contains("管理权限")) {
                    return ((GserRealm) realm).isPermitted(principals, permission);
                }
            }
        }
        return false;
    }
}
