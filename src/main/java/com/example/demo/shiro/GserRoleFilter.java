package com.example.demo.shiro;

import com.example.demo.pojo.Userdomain.CurrentUser;
import com.example.demo.pojo.Userdomain.Role;
import com.example.demo.result.Result;
import com.example.demo.result.ResultCode;
import com.example.demo.util.GetRequestTokenUtil;
import com.example.demo.util.TokenSubjectUtil;
import com.example.demo.util.WebUtil;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GserRoleFilter extends BasicHttpAuthenticationFilter {

    GetRequestTokenUtil getRequestTokenUtil = new GetRequestTokenUtil();
    Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)  {
        logger.info("进入管理员拦截器");
        String token = getRequestTokenUtil.getToken((HttpServletRequest)request,(HttpServletResponse) response);
        CurrentUser gser= new TokenSubjectUtil().getUserByToken(token);

        if (gser == null){
            return false;
        }else{
            String[] rolesArray = (String[]) mappedValue;
            if (rolesArray == null || rolesArray.length == 0) { //没有角色限制，有权限访问
                return true;
            }
            for (Role userRole : gser.getRoles()) {
                for (String role : rolesArray){
                    if (userRole.getRname().equalsIgnoreCase(role)){
                        return true;
                    }
                }
            }
            return false;
        }
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (this.isLoginRequest(request,response)){
            return true;
        }else{
            logger.info("管理员无权限");
            WebUtil.Send_AUTHORZE_HttpState((HttpServletResponse) response,new Result(ResultCode.AUTHORZE_FAILURE));
        }
        return false;
    }
}
