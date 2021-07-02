package com.example.demo.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserModularRealmAuthenticator extends ModularRealmAuthenticator {

    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken){

        // 判断getRealms()是否返回为空
        assertRealmsConfigured();
        // 强制转换回自定义的CustomizedToken
        UserToken userToken = (UserToken) authenticationToken;
        // 登录类型
        String usertype = userToken.getUsertype();
        System.out.println(usertype);
        // 所有Realm
        Collection<Realm> realms = getRealms();
        // 登录类型对应的所有Realm
        List<Realm> typeRealms = new ArrayList<>();
        for (Realm realm : realms) {
            if (realm.getName().contains(usertype))/*判断所有的realm中是否包含以当前的usertype用户类型的字符串*/
            {
                typeRealms.add(realm);
                System.out.println(realm.getName());
            }

        }
        // 判断是单Realm还是多Realm
        if (typeRealms.size() == 1) {
            System.out.println("single realm");
            return doSingleRealmAuthentication(typeRealms.get(0), userToken);
        } else {
            System.out.println("more realm");
            return doMultiRealmAuthentication(typeRealms, userToken);
        }

    }
}
