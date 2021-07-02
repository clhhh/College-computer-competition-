package com.example.demo.Controller;


import com.example.demo.Service.TeacherService;
import com.example.demo.pojo.Userdomain.Teacher;
import com.example.demo.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api",method = RequestMethod.POST)
public class TeacherController {

    @Autowired
    TeacherService teacherServiceImpl;
    /*下载所有老师信息*/
    @PostMapping("/downloadTeacherMes")
    public Result DownloadTeacherMes(){
        return teacherServiceImpl.DownloadTeacherMes();
    }
    /*获取所有老师信息*/
    @PostMapping("/getTeacherMes")
    public Result GetTeacherMes(@RequestParam(value = "pageNum")String pageNum,
                                @RequestParam(value = "pageSize")String pageSize){
        return teacherServiceImpl.GetTeacherMes(pageNum, pageSize);
    }
    /*通过id获取所有老师信息*/
    @PostMapping("/getTeacherMesByid")
    public Result GetTeacherMesByid(@RequestParam(value = "tid")Integer tid){
        return teacherServiceImpl.GetTeacherMesByid(tid);
    }
    /*增加老师信息*/
    @PostMapping("/addTeacherMes")
    public Result AddTeacherMes(@RequestParam(value = "realname",required = false)String realname,
                                @RequestParam(value = "username",required = false)String username,
                                @RequestParam(value = "password",required = false)String password,
                                @RequestParam(value = "major",required = false)String major,
                                @RequestParam(value = "college",required = false)String college,
                                @RequestParam(value = "school",required = false)String school,
                                @RequestParam(value = "email",required = false)String email,
                                @RequestParam(value = "phone",required = false)String phone,
                                @RequestParam(value = "code",required = false)Integer code
                                ){
        Teacher teacher = new Teacher();
        teacher.setCode(code);
        teacher.setCollege(college);
        teacher.setMajor(major);
        teacher.setEmail(email);
        teacher.setPassword(password);
        teacher.setRealname(realname);
        teacher.setUsername(username);
        teacher.setSchool(school);
        teacher.setPhone(phone);
        return teacherServiceImpl.AddTeacherMes(teacher);
    }
    /*更新老师信息*/
    @PostMapping("/updateTeacherMes")
    public Result UpdateTeacherMes(@RequestParam(value = "tid")Integer tid,
                                   @RequestParam(value = "realname",required = false)String realname,
                                   @RequestParam(value = "username",required = false)String username,
                                   @RequestParam(value = "password",required = false)String password,
                                   @RequestParam(value = "major",required = false)String major,
                                   @RequestParam(value = "college",required = false)String college,
                                   @RequestParam(value = "school",required = false)String school,
                                   @RequestParam(value = "email",required = false)String email,
                                   @RequestParam(value = "phone",required = false)String phone,
                                   @RequestParam(value = "code",required = false)Integer code){
        Teacher teacher = new Teacher();
        teacher.setTid(tid);
        teacher.setCode(code);
        teacher.setCollege(college);
        teacher.setMajor(major);
        teacher.setEmail(email);
        teacher.setPassword(password);
        teacher.setRealname(realname);
        teacher.setUsername(username);
        teacher.setSchool(school);
        teacher.setPhone(phone);
        return teacherServiceImpl.UpdateTeacherMes(teacher);
    } 
    /*删除老师信息*/
    @PostMapping("/deleteTeacherMes")
    public Result DeleteTeacherMes(@RequestParam("tid")int tid){
        return teacherServiceImpl.DeleteTeacherMes(tid);
    }
    /*通过老师的真实姓名获取老师信息*/
    @PostMapping("/getTeacherMesByString")
    public Result GetTeacherMesByString(@RequestParam(value = "pageNum")String pageNum,
                                        @RequestParam(value = "pageSize")String pageSize,
                                        @RequestParam(value = "realname",required = false)String realname,
                                        @RequestParam(value = "college",required = false)String college,
                                        @RequestParam(value = "school",required = false)String school){
        return teacherServiceImpl.GetTeacherMesByString(pageNum,pageSize,realname,college,school);
    }
    /*将老师选为管理员*/
    @PostMapping("/setTeacherGRole")
    public Result SetTeacherGRole(@RequestParam(value = "tid")Integer tid,
                                  @RequestParam(value = "roleId")Integer roleId){
        return teacherServiceImpl.SetTeacherGRole(tid,roleId);
    }
    /*将老师选为评委*/
    @PostMapping("/tobeJudge")
    public Result TobeJudge(@RequestParam(value = "tid")Integer tid){
        return teacherServiceImpl.TobeJudge(tid);
    }
    /*取消教师评委资格*/
    @PostMapping("/tonotbeJudge")
    public Result TonotbeJudge(@RequestParam(value = "tid")Integer tid){
        return teacherServiceImpl.TonotbeJudge(tid);
    }
    /*获取所有评委信息*/
    @PostMapping("/getTeacherJudgeMes")
    public Result GetJudgeMes(){
        return teacherServiceImpl.GetJudgeMes();
    }
}
