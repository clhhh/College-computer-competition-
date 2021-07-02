package com.example.demo.shiro;

import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class ShiroConfigration {
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") SecurityManager securityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager);
        /*自定义拦截器*/
        Map<String, Filter> filterMap = new HashMap<String,Filter>(1);
        filterMap.put("user_role",new UserRoleFilter());
        filterMap.put("gser_role",new GserRoleFilter());
        bean.setFilters(filterMap);

        /*拦截器规则*/
        HashMap<String,String> filterMapAction = new HashMap<>();
        /*公用的静态文件打开*/
        filterMapAction.put("/static/**", "anon");
        filterMapAction.put("/public/**", "anon");
        filterMapAction.put("/resources/**","anon");




        /****************************************学生端的接口打开*******************************************************/
        /*学生端接口受限*/
        filterMapAction.put("/submit.html","user_role[student]");
//        filterMapAction.put("/like","user_role[student]");
//        filterMapAction.put("/unlike","user_role[student]");
        filterMapAction.put("/person.html","user_role[student]");
        filterMapAction.put("/revise.html","user_role[student]");
        filterMapAction.put("/ckecksubmit","user_role[student]");
        filterMapAction.put("/graded.html","user_role[judge,admin,courseAdmin,seniorAdmin,specialAdmin,anybody]");
        filterMapAction.put("/Getgrade.html","user_role[judge,admin,courseAdmin,seniorAdmin,specialAdmin,anybody]");
        filterMapAction.put("/pingFen","user_role[judge,admin,courseAdmin,seniorAdmin,specialAdmin,anybody]");




        /*管理员端的登录接口打开*/
        filterMapAction.put("/api/loginCheck","anon");




        /***************************************管理员端的其他接口受限制************************************************/

        /***************************************文章管理****************************************************/
        /*文章管理--轮播内容*/
        /*轮播图*/
        filterMapAction.put("/api/getlbPhotoMes","gser_role[admin,seniorAdmin]");
        filterMapAction.put("/api/getlbPhotoMesByid","gser_role[admin,seniorAdmin]");
        filterMapAction.put("/api/updatePhotoMes","gser_role[admin]");
        filterMapAction.put("/api/addPhotoMes","gser_role[admin]");
        filterMapAction.put("/api/deletePhotoMes","gser_role[admin]");
        /*轮播视频*/
        filterMapAction.put("/api/getlbVedioMes","gser_role[admin,seniorAdmin]");
        filterMapAction.put("/api/getlbVedioMesByid","gser_role[admin,seniorAdmin]");
        filterMapAction.put("/api/updateVedioMes","gser_role[admin]");
        filterMapAction.put("/api/addVedioMes","gser_role[admin]");
        filterMapAction.put("/api/deleteVedioMes","gser_role[admin]");

        /*文章管理--竞赛通知*/
        filterMapAction.put("/api/deleteNotice","gser_role[admin]");
        filterMapAction.put("/api/addNotice","gser_role[admin]");
        filterMapAction.put("/api/updateNotice","gser_role[admin]");
        filterMapAction.put("/api/queryNotice","gser_role[admin,seniorAdmin]");
        filterMapAction.put("/api/queryNoticeList","gser_role[admin,seniorAdmin]");
        filterMapAction.put("/api/getNoticeMesByString","gser_role[admin,seniorAdmin]");

        /*文章管理--竞赛动态*/
        filterMapAction.put("/api/deleteDynamic","gser_role[admin]");
        filterMapAction.put("/api/addDynamic","gser_role[admin]");
        filterMapAction.put("/api/updateDynamic","gser_role[admin]");
        filterMapAction.put("/api/queryDynamic","gser_role[admin,seniorAdmin]");
        filterMapAction.put("/api/queryDynamicList","gser_role[admin,seniorAdmin]");
        filterMapAction.put("/api/getDynamicMesByString","gser_role[admin,seniorAdmin]");

        /*文章管理--竞赛项目*/
        filterMapAction.put("/api/deleteMatchMes","gser_role[admin]");
        filterMapAction.put("/api/addMatchMes","gser_role[admin]");
        filterMapAction.put("/api/updateMatchMes","gser_role[admin]");
        filterMapAction.put("/api/getMatchMesById","gser_role[admin,seniorAdmin]");
        //filterMapAction.put("/api/getAllMatchMes","gser_role[admin,seniorAdmin]");
        filterMapAction.put("/api/getMatchMesByString","gser_role[admin,seniorAdmin]");
        filterMapAction.put("/api/isShowMatchMes","gser_role[admin]");
        filterMapAction.put("/api/isProjectMatch","gser_role[admin]");
        filterMapAction.put("/api/isCourseMatch","gser_role[admin]");
        filterMapAction.put("/api/getMatchID","gser_role[admin,seniorAdmin]");

        /*文章管理--竞赛归档*/
        filterMapAction.put("/api/getMatchMesByString_NOSort","gser_role[admin,seniorAdmin]");
        filterMapAction.put("/api/getMatchMesByString_Sort","gser_role[admin,seniorAdmin]");
        filterMapAction.put("/api/sortPackageByMid","gser_role[admin]");

        /*文章管理--关于我们*/
        filterMapAction.put("/api/deleteAboutUs","gser_role[admin]");
        filterMapAction.put("/api/addAboutUs","gser_role[admin]");
        filterMapAction.put("/api/queryAboutUsList","gser_role[admin,seniorAdmin]");
        /*文章管理--问答管理*/
        filterMapAction.put("/api/queryQuestionsList","gser_role[admin,seniorAdmin]");
        filterMapAction.put("/api/queryQuestionByString","gser_role[admin,seniorAdmin]");
        filterMapAction.put("/api/queryQuestions","gser_role[admin,seniorAdmin]");
        filterMapAction.put("/api/updateQuestions","gser_role[admin]");
        filterMapAction.put("/api/addQuestions","gser_role[admin]");
        filterMapAction.put("/api/deleteQuestions","gser_role[admin]");
        /*文章管理--奖状模板管理*/
        filterMapAction.put("/api/getPrizeModelMes","gser_role[admin,seniorAdmin]");
        filterMapAction.put("/api/getPrizeNameWithID","gser_role[admin,seniorAdmin]");
        filterMapAction.put("/api/addPrizeModel","gser_role[admin]");
        filterMapAction.put("/api/updatePrizeModel","gser_role[admin]");
        filterMapAction.put("/api/deletePrizeModel","gser_role[admin]");
        filterMapAction.put("/api/getCoursePrizeMes","gser_role[courseAdmin]");
        filterMapAction.put("/api/getCourseMatchMesByCid","gser_role[courseAdmin]");
        filterMapAction.put("/api/getModelNameWithID","gser_role[courseAdmin]");
        filterMapAction.put("/api/addCoursePrize","gser_role[courseAdmin]");
        filterMapAction.put("/api/updateCoursePrize","gser_role[courseAdmin]");
        filterMapAction.put("/api/deleteCoursePrize","gser_role[courseAdmin]");
        /*文章管理--评分表管理*/
        //数据管理员
        filterMapAction.put("/api/addMarktable","gser_role[admin]");
        filterMapAction.put("/api/getMarktableMes","gser_role[admin,seniorAdmin]");
        filterMapAction.put("/api/getMarkTableMesByid","gser_role[admin,seniorAdmin]");
        filterMapAction.put("/api/updateMarktable","gser_role[admin]");
        filterMapAction.put("/api/deleteMarktable","gser_role[admin]");


        /***************************************用户管理****************************************************/

        /*用户管理--选手管理*/
        filterMapAction.put("/api/getUserMes","gser_role[admin,seniorAdmin]");
        filterMapAction.put("/api/getUserMesByString","gser_role[admin,seniorAdmin]");
        filterMapAction.put("/api/delUserMesbyid","gser_role[admin]");
        filterMapAction.put("/api/updateUserMes","gser_role[admin]");
        filterMapAction.put("/api/getUserMesByid","gser_role[admin,seniorAdmin]");
        filterMapAction.put("/api/getUserList","gser_role[admin,seniorAdmin]");
        filterMapAction.put("/api/sendemail","gser_role[admin]");


        /*用户管理--教师评委管理*/
        filterMapAction.put("/api/getTeacherMes","gser_role[admin,seniorAdmin]");
        filterMapAction.put("/api/getTeacherMesByid","gser_role[admin,seniorAdmin]");
        filterMapAction.put("/api/tobeJudge","gser_role[admin]");
        filterMapAction.put("/api/addTeacherMes","gser_role[admin]");
        filterMapAction.put("/api/updateTeacherMes","gser_role[admin]");
        filterMapAction.put("/api/deleteTeacherMes","gser_role[admin]");
        filterMapAction.put("/api/downloadTeacherMes","gser_role[admin,seniorAdmin]");
        filterMapAction.put("/api/setTeacherGRole","gser_role[admin]");
        filterMapAction.put("/api/getTeacherMesByString","gser_role[admin,seniorAdmin]");
        filterMapAction.put("/api/getTeacherJudgeMes","gser_role[admin,seniorAdmin]");

        /*用户管理--课程评委管理*/
        filterMapAction.put("/api/getCourseJudgeMesByString","gser_role[admin,courseAdmin,seniorAdmin]");
        filterMapAction.put("/api/deleteCourseJudgeMes","gser_role[admin,courseAdmin]");
        filterMapAction.put("/api/updateCourseJudgeMes","gser_role[admin,courseAdmin]");
        filterMapAction.put("/api/addCourseJudgeMes","gser_role[admin,courseAdmin]");
        filterMapAction.put("/api/getCourseMatchMes","gser_role[admin,courseAdmin,seniorAdmin]");

        /*用户管理--管理员*/
        filterMapAction.put("/api/getGserMesByString","gser_role[admin,seniorAdmin]");
        filterMapAction.put("/api/DeleteGser","gser_role[admin]");
        filterMapAction.put("/api/EditGser","gser_role[admin]");
        filterMapAction.put("/api/AddGser","gser_role[admin]");
        filterMapAction.put("/api/getGserMesByid","gser_role[admin,seniorAdmin]");
        filterMapAction.put("/api/getGserMesList","gser_role[admin,seniorAdmin]");
        filterMapAction.put("/api/getRoles","gser_role[admin,seniorAdmin]");




        /***************************************赛事管理****************************************************/
        /*整体检索管理*/
        filterMapAction.put("/api/getWorksMesByClubID","gser_role[clubAdmin]");
        filterMapAction.put("/api/getWorksMesByString","gser_role[admin,seniorAdmin]");
        filterMapAction.put("/api/getWorksMesByCourseID","gser_role[courseAdmin]");
        /*赛事管理--全部作品*/
        filterMapAction.put("/api/getAllWorksMes","gser_role[admin,seniorAdmin]");
        filterMapAction.put("/api/getAllWorksByString","gser_role[admin,seniorAdmin]");
        filterMapAction.put("/api/overAcccess","gser_role[admin]");
        filterMapAction.put("/api/downloadAllWorksMes","gser_role[admin,seniorAdmin]");
        filterMapAction.put("/api/downLoadAllCourseWork","gser_role[courseAdmin]");


        /*赛事管理--待审核作品*/
        filterMapAction.put("/api/toTuiHui","gser_role[admin,clubAdmin]");
        filterMapAction.put("/api/toShenHe","gser_role[admin,clubAdmin]");
        filterMapAction.put("/api/toShenHeCourse","gser_role[courseAdmin]");
        filterMapAction.put("/api/toTuiHuiCourse","gser_role[courseAdmin]");
        /*赛事管理--待分配作品*/
        filterMapAction.put("/api/ToFenPei","gser_role[admin]");
        filterMapAction.put("/api/getCourseJudgeMesByCid","gser_role[courseAdmin]");
        filterMapAction.put("/api/toFenPeiCourseJudge","gser_role[courseAdmin]");




        /*赛事管理--未得分作品*/
        filterMapAction.put("/api/toTuiHuiPF","gser_role[admin]");
        filterMapAction.put("/api/toTuiHuiCourseJudge","gser_role[courseAdmin]");

        /*赛事管理--未制作出线名单*/
        filterMapAction.put("/api/toCXForMatch","gser_role[admin,seniorAdmin]");
        filterMapAction.put("/api/getCXWorksbyMatch","gser_role[admin,seniorAdmin]");
        filterMapAction.put("/api/getNotCXcourseMatchMes","gser_role[courseAdmin]");
        filterMapAction.put("/api/getCXcourseMatchMes","gser_role[courseAdmin]");
        filterMapAction.put("/api/toChuXian","gser_role[admin]");
        filterMapAction.put("/api/gotoCourseWorks","gser_role[courseAdmin]");
        /*赛事管理--已制作出线名单*/
        filterMapAction.put("/api/getHCXWorksbyMatch","gser_role[admin,seniorAdmin]");
        filterMapAction.put("/api/gobackCourseWorks","gser_role[courseAdmin]");
        filterMapAction.put("/api/getPrizeNameByCounrseID","gser_role[courseAdmin]");
        filterMapAction.put("/api/toTuihuiCXworks","gser_role[admin]");

        /*赛事管理--决赛名单*/
        filterMapAction.put("/api/toShenHeCXWorks","gser_role[admin]");
        filterMapAction.put("/api/toJSWorks","gser_role[admin]");
        filterMapAction.put("/api/updateCourseCertificate","gser_role[courseAdmin]");


        /*****************************************奖项管理**********************************/
        /*奖项管理--决赛获奖列表*/
        filterMapAction.put("/api/downloadJSWorksMes","gser_role[admin,specialAdmin,seniorAdmin]");
        filterMapAction.put("/api/getJSWorksByString","gser_role[admin,specialAdmin,seniorAdmin]");
        filterMapAction.put("/api/updateCertificate","gser_role[admin]");
        /*奖项管理--鼓励奖列表*/

        filterMapAction.put("/api/getGLWorksByString","gser_role[admin,seniorAdmin]");
        /*奖项管理--未发奖金列表*/

        filterMapAction.put("/api/getWorksNoRewardMesByString","gser_role[admin,seniorAdmin]");
        filterMapAction.put("/api/getReward","gser_role[admin]");
        /*奖项管理--已发奖金列表*/

        filterMapAction.put("/api/getWorksRewardMesByString","gser_role[admin,seniorAdmin]");
        filterMapAction.put("/api/downloadRewardMes","gser_role[admin,seniorAdmin]");


        /*****************************************协会管理********************************/
        filterMapAction.put("/api/getClubMes","gser_role[admin,seniorAdmin]");
        filterMapAction.put("/api/getClubName","gser_role[admin,seniorAdmin]");
        filterMapAction.put("/api/addClubMes","gser_role[admin]");
        filterMapAction.put("/api/updateClubMes","gser_role[admin]");
        filterMapAction.put("/api/deleteClub","gser_role[admin]");
        /*拦截全部的管理员接口作jwt验证*/



        bean.setFilterChainDefinitionMap(filterMapAction);
        return bean;
    }
    /*自定义的安全管理器*/
    @Bean("securityManager")
    public SecurityManager securityManager(@Qualifier("StuRealm") StuRealm stuRealm,
                                           @Qualifier("GserRealm") GserRealm gserRealm,
                                           @Qualifier("JudgeRealm") JudgeRealm judgeRealm,
                                           @Qualifier("modularRealmAuthenticator") UserModularRealmAuthenticator modularRealmAuthenticator,
                                           @Qualifier("modularRealmAuthorizer") UserModularRealmAuthorizer modularRealmAuthorizer){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        /*关闭session*/
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);
        /*添加自定义处理多个realm的认证方法*/
        securityManager.setAuthenticator(modularRealmAuthenticator);
        /*添加自定义处理多个realm的授权方法*/
       // securityManager.setAuthorizer(modularRealmAuthorizer);
        List<Realm> myrealm = new ArrayList<>();

        myrealm.add(gserRealm);/*GserRealm认证器*/
        myrealm.add(stuRealm);/*StuRealm认证器*/
        myrealm.add(judgeRealm);/*JudgeRealm认证器*/
        securityManager.setRealms(myrealm);


        return securityManager;
    }

    /*学生登录的认证器*/
    @Bean("StuRealm")
    public StuRealm Sturealm(@Qualifier("credentialMatcher") CredentialMatcher credentialMatcher){
        StuRealm stuRealm = new StuRealm();
        stuRealm.setCredentialsMatcher(credentialMatcher);
        return stuRealm;
    }
    /*管理员登录的认证器*/
    @Bean("GserRealm")
    public GserRealm Gserrealm(@Qualifier("credentialMatcher") CredentialMatcher credentialMatcher){
        GserRealm gserRealm = new GserRealm();
        gserRealm.setCredentialsMatcher(credentialMatcher);
        return gserRealm;
    }
    /*评委登录的认证器*/
    @Bean("JudgeRealm")
    public JudgeRealm Judgerealm(@Qualifier("credentialMatcher") CredentialMatcher credentialMatcher){
        JudgeRealm judgeRealm = new JudgeRealm();
        judgeRealm.setCredentialsMatcher(credentialMatcher);
        return judgeRealm;
    }
    /**
     * 系统自带的Realm管理，主要针对多realm 认证
     */
    @Bean("modularRealmAuthenticator")
    public UserModularRealmAuthenticator getmodularRealmAuthenticator() {

        UserModularRealmAuthenticator modularRealmAuthenticate = new UserModularRealmAuthenticator();
        modularRealmAuthenticate.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
        return modularRealmAuthenticate;
    }
    /**
     * 系统自带的Realm管理，主要针对多realm 授权
     */
    @Bean("modularRealmAuthorizer")
    public UserModularRealmAuthorizer modularRealmAuthorizer() {
        //自己重写的ModularRealmAuthorizer
        UserModularRealmAuthorizer modularRealmAuthorizer = new UserModularRealmAuthorizer();
        return modularRealmAuthorizer;
    }

    /*自定义的密码验证方式*/
    @Bean("credentialMatcher")
    public CredentialMatcher credentialMatcher(){
        return new CredentialMatcher();
    }
    /*一下两类定义securityManager与spring的关系*/
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager")SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }
}
