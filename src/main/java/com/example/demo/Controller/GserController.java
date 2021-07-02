package com.example.demo.Controller;

import com.example.demo.Service.GserService;
import com.example.demo.result.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping(value = "/api",method = RequestMethod.POST)
public class GserController {
    @Autowired
    GserService gserServiceImpl;

    /**
     * 查询角色列表，以及相应的权限
     * @return
     */
    @PostMapping("/getRoles")
    @ResponseBody
    public Result GetRole(){
        return gserServiceImpl.GetRoles();
    }
    /**
     * 查询管理员信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    @PostMapping("/getGserMesList")
    @ResponseBody
    public Result GetGserMesList(@RequestParam(value = "pageNum")String pageNum,
                                @RequestParam(value = "pageSize")String pageSize){
       return gserServiceImpl.GetGserMesList(pageNum,pageSize);
    }

    /**
     * 获取管理员权限
     * @param request
     * @return
     */
    @PostMapping("/getGserMesByToken")
    @ResponseBody
    public Result GetGserMesByToken(HttpServletRequest request, HttpServletResponse response){
        return gserServiceImpl.GetGserMesByToken(request,response);
    }

    /**
     * 通过id获取管理员信息
     * @param gid
     * @return
     */
    @PostMapping ("/getGserMesByid")
    @ResponseBody
    public Result GetGserMesByid(@RequestParam(value = "gid",required = false)Integer gid){
       return gserServiceImpl.GetGserMesByid(gid);
    }

    /**
     * 检索管理员信息
     * @param pageNum
     * @param pageSize
     * @param realname
     * @param username
     * @return
     */
    @PostMapping("/getGserMesByString")
    @ResponseBody
    public Result GetGserMesByString(@RequestParam(value = "pageNum")String pageNum,
                                     @RequestParam(value = "pageSize")String pageSize,
                                     @RequestParam(value = "realname",required = false)String realname,
                                     @RequestParam(value = "username",required = false)String username){
       return gserServiceImpl.GetGserMesByString(pageNum,pageSize,realname,username);
    }

    /**
     * 新增管理员操作
     * @param realname
     * @param username
     * @param password
     * @param roleId 角色ID
     * @return
     */
    @PostMapping("/AddGser")
    @ResponseBody
    public Result addGser(@RequestParam(value = "realname",required = false)String realname,
                          @RequestParam(value = "username",required = false)String username,
                          @RequestParam(value = "password",required = false)String password,
                          @RequestParam(value = "roleId",required = false)Integer roleId,
                          @RequestParam(value = "cid",required = false)Integer cid){
        return gserServiceImpl.addGser(realname,username,password,roleId,cid);
    }


    /**
     * 修改管理员信息
     * @param realname
     * @param gid
     * @param username
     * @param password
     * @param roleId
     * @return
     */
    @PostMapping("/EditGser")
    @ResponseBody
    public Result EditGser(@RequestParam(value = "realname",required = false)String realname,
                           @RequestParam(value = "gid",required = false)Integer gid,
                           @RequestParam(value = "username",required = false)String username,
                           @RequestParam(value = "password",required = false)String password,
                           @RequestParam(value = "roleId",required = false)Integer roleId,
                           @RequestParam(value = "cid",required = false)Integer cid){
       return gserServiceImpl.EditGser(realname,gid,username,password,roleId,cid);
    }

    /**
     * 删除管理员信息
     * @param gid
     * @return
     */
    @PostMapping("/DeleteGser")
    @ResponseBody
    public Result DeleteGser(@RequestParam(value = "gid",required = false)int gid){
       return gserServiceImpl.DeleteGser(gid);
    }

}
