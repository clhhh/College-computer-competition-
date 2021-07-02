package com.example.demo.result;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum ResultCode {

    /*登录方面*/
    ACCESS_LIMIT (203,"请耐心等待"),
    LOGIN_SUCCESS(200,"登录成功"),
    NO_LOGIN(201,"没有登录"),
    PWDORUSER_ERROR(202,"用户名或密码错误"),
    NOPERSON_ERROR(203,"没有该用户记录"),
    UNKNOWN_ERROR(204,"系统错误"),
    USERNAME_EXIST(205,"用户名已经存在，请重新输入"),
    NUMBER_EXIST(206,"学号已存在，请重新输入"),
    REGISTER_SUCCESS(206,"注册成功"),
    REGISTER_FAILURE(207,"注册失败"),
    LOGINOUT_SUCCESS(200,"用户已注销"),
    REGISTER_RUN(200,"正常注册"),
    /*数据库操作方面*/
    DBC_SUCCESS(200,"数据库操作成功"),
    DBC_FALIURE(304,"数据库操作失败"),
    SELECT_NONE(307,"查不到该数据"),
    INSERT_ERROR(308,"数据已存在"),
    UPDATE_ERROR(309,"更新错误"),
    /*角色 权限配置方面*/
    ROLE_NOTEXIST(305,"角色不存在"),
    PERMISSION_NOTEXIST(306,"权限不存在"),
    AUTHORZE_SUCCESS(200,"授权成功"),
    AUTHORZE_FAILURE(300,"授权失败"),
    /*报名提交方面*/
    SUBMIT_SUCCESS(200,"报名表提交成功"),
    UPLOAD_SCUUESS(200,"作品提交成功"),
    SUBMIT_FAILURE(400,"报名表提交失败"),
    UPLOAD_FAILURE(400,"作品上传提交失败"),
    SUBMIT_READY(403,"已经报名"),
    UPLOAD_READY(400,"提交作品次数已经达到上限，如有问题请联系系统管理员或老师"),
    UPLOAD_NOREADY(200,"你在本次竞赛项目中只可以提交 1 次作品，请认真审核再提交"),
    WORK_TAKEPART_IN (400,"你的作品已进入评分阶段，不可在自此阶段更改作品"),
    SUBMIT_NOREADY(405,"还没有报名"),
    SUBMIT_OUTDATE(406,"报名时间已过"),
    SUBMIT_INDATE(407,"正常报名"),
    /*文件传输*/
    PHOTO_OPTION_ERROR(400,"图片传输失败"),
    PRIZE_OPTION_ERROR(400,"奖状模板传输失败"),
    PHOTO_OPTION_SUCCESS(200,"图片传输成功"),
    VEDIO_OPTION_ERROR(400,"视频传输失败"),
    VEDIO_OPTION_EMPTY(400,"视频文件为空"),
    VEDIO_OPTION_SUCCESS(200,"视频传输成功"),
    PROJECT_OPTION_ERROR(400,"工程文件传输失败"),
    DOCU_OPTION_ERROR(400,"文档传输失败"),
    /*评委评分*/
    JUDGE_SUCCESS(200,"评分成功"),
    JUDGE_FAILURE(400,"评分失败"),
    NO_MARKTABLE(507,"此项目还未设置评分表，请联系管理员设置"),
    JUDGE_ACCESS(200,"进入评分页，评发期间请勿管理浏览器"),
    JUDGE_NOACCESS(400,"未能获取选手作品信息，无法正常评分，请联系管理员"),
    /*修改个人信息*/
    REVISE_SUCCESS(600,"修改个人信息成功,请重新登录"),
    REVISE_FAILURE(601,"修改个人信息失败"),
    /*获取奖状信息*/
    PRIZE_DONE(700,"稍作等候，即将为您下载电子版获奖证书"),
    PRIZE_NONE(701,"您的作品没有进入评奖阶段，请关注我们的动态噢！！"),
    /*邮件发送*/
    MAILSEND_SUCCESS(200,"邮件发送成功"),
    MAILSEND_FAILURE(400,"邮件发送不成功"),


    //后台Code
    BACG_DOWNLOAD_SUCCESS(200,"下载成功"),
    BACG_DOWNLOAD_FAILURE(400,"下载失败"),
    //出线作品
    CX_WORKS_NOEXITS(400,"出线作品不存在"),
    MATCH_NONEED_SORT(400,"该竞赛项目无作品可被退回"),


    //作品点赞方面
    LIKE_CLIKE_NOLOGIN(302,"想点赞的话，要登陆自己的账号哦！"),
    LIKE_CLICK_SUCCESS(200,"谢谢你的点赞,这是对我最大的支持"),
    LIKE_CLICK_FIRSTONE(200,"你是第一个给我点赞呢！"),
    LIKE_CLICK_FAILURE(400,"由于网络原因,刚刚收不到你的点赞,你可以再试一次！"),
    LIKE_CLICK_MORETIME(400,"你今天的点赞次数用完了，每天记得再来哟！"),

    UNLIKE_CLICK_SUCCESS(200,"想留住你的喜欢，但是没关系，人各有所爱嘛"),
    UNLIKE_CLICK_LESS(200,"我没有赞啦"),
    UNLIKE_CLICK_NOWORK(200,"由于网络原因,暂时找不到该作品"),

    UNLIKE_CLICK_FAILURE(400,"如果你执意要取消，那你再试试");


    private int code;
    private String mesg;
     ResultCode(int code, String mesg){
        this.code = code;
        this.mesg = mesg;
    }
}
