package com.example.demo.shiro;

import com.example.demo.pojo.Userdomain.CurrentUser;
import com.example.demo.pojo.Userdomain.Role;
import com.example.demo.util.GetRequestTokenUtil;
import com.example.demo.util.TokenSubjectUtil;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserRoleFilter extends BasicHttpAuthenticationFilter {

    GetRequestTokenUtil getRequestTokenUtil = new GetRequestTokenUtil();
    Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)  {
        logger.info("进入用户拦截器");
        String token = getRequestTokenUtil.getToken((HttpServletRequest)request,(HttpServletResponse) response);
        CurrentUser user = new TokenSubjectUtil().getUserByToken(token);
        if (user == null){
            return false;
        }else{
            String[] rolesArray = (String[]) mappedValue;
            if (rolesArray == null || rolesArray.length == 0) {
                //没有角色限制，有权限访问
                return true;
            }
            for (Role userRole : user.getRoles()) {
                for (String role : rolesArray){
                    if (userRole.getRname().equalsIgnoreCase(role)){
                        return true;
                    }
                }
            }

            logger.info("进入用户无该权限");
            return false;
        }
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        setLoginUrl("/login.html");
        saveRequestAndRedirectToLogin(request,response);/*重定向到前面设置的 bean.setLoginUrl的登录页*/
        return false;
    }

    /*@Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        AuthenticationToken token = createToken(request, response);
        System.out.println("executeLogin:"+token);
        if (token == null){
            throw new Exception();
        }
        try{
            Subject subject = getSubject(request, response);
            subject.login(token);
            return onLoginSuccess(token,subject,request,response);
        }catch (AuthenticationException e){
            return onLoginFailure(token,e,request,response);
        }
    }

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        issueSuccessRedirect(request,response);
        return false;
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        return true;
    }*/

}