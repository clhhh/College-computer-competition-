package com.example.demo.Service.backImpl;

import com.example.demo.Service.GserService;
import com.example.demo.dao.mapper.GserMapper;
import com.example.demo.pojo.Userdomain.CurrentUser;
import com.example.demo.pojo.Userdomain.Gser;
import com.example.demo.pojo.Userdomain.Role;
import com.example.demo.result.Result;
import com.example.demo.result.ResultCode;

import com.example.demo.util.GetRequestTokenUtil;
import com.example.demo.util.TokenSubjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional(isolation = Isolation.REPEATABLE_READ)
@Service
public class GserServiceImpl implements GserService {
    @Autowired
    GserMapper gserMapper;
    @Autowired
    GetRequestTokenUtil getRequestTokenUtil;

    @Override
    public Result GetGserMesByToken(HttpServletRequest request, HttpServletResponse response) {
        String token = getRequestTokenUtil.getToken(request,response);
        if (token == null){
            return new Result(ResultCode.NOPERSON_ERROR);
        }
        try{
            CurrentUser gser = new TokenSubjectUtil().getUserByToken(token);
            System.out.println("gser_id :"+gser.getPid());
            List<Gser> gsers = gserMapper.queryGserByGid(gser.getPid());
            Map<String, Object> map = new HashMap<>();
            for (Gser gser1 : gsers) {
                System.out.println(gser1);
                map.put("username",gser1.getUsername());
                map.put("realname",gser1.getRealname());
                map.put("passsword",gser1.getPassword());
            }
            List<String> gserRole = gserMapper.queryGserRole(gser.getPid());
            map.put("role",gserRole);
            return new Result(ResultCode.DBC_SUCCESS,map);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(ResultCode.DBC_FALIURE);
        }

    }

    @Override
    public Result GetGserMesByid(Integer gid) {
        try{
            List<Gser> gsers = gserMapper.queryGserByGid(gid);
            return new Result(ResultCode.DBC_SUCCESS,gsers);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(ResultCode.DBC_FALIURE);
        }
    }
    @Override
    public Result GetGserMesList(String pageNum, String pageSize) {
        try{
            PageHelper.startPage(Integer.parseInt(pageNum),Integer.parseInt(pageSize));
            List<Gser> gsers = gserMapper.queryGserList();
            PageInfo<Gser> page = new PageInfo<>(gsers);
            HashMap<String, Object> pageMes = new HashMap<>();
            pageMes.put("pageTotal",page.getTotal());
            pageMes.put("gserList",gsers);
            return new Result(ResultCode.DBC_SUCCESS,pageMes);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(ResultCode.DBC_FALIURE);
        }
    }
    @Override
    public Result GetGserMesByString(String pageNum, String pageSize, String realname, String username) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("realname",realname);
        map.put("username",username);
        try{
            PageHelper.startPage(Integer.parseInt(pageNum),Integer.parseInt(pageSize));
            List<Gser> gsers = gserMapper.queryGserByString(map);
            PageInfo<Gser> page = new PageInfo<>(gsers);
            HashMap<String, Object> pageMes = new HashMap<>();
            pageMes.put("pageTotal",page.getTotal());
            pageMes.put("gserList",gsers);
            return new Result(ResultCode.DBC_SUCCESS,pageMes);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(ResultCode.DBC_FALIURE);
        }
    }

    @Override
    public Result addGser(String realname, String username, String password, Integer roleId,Integer cid) {
        Gser gser = new Gser();
        gser.setUsername(username);
        gser.setRealname(realname);
        gser.setPassword(password);
        gser.setCode(cid);
        try{
            int i = gserMapper.insertGser(gser);
            int i1 = gserMapper.insertRoleByid(roleId,gser.getGid());
            if (i1==0){
                return new Result(ResultCode.INSERT_ERROR);
            }
            return new Result(ResultCode.DBC_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(ResultCode.DBC_FALIURE);
        }
    }

    @Override
    public Result EditGser(String realname, Integer gid, String username, String password, Integer roleId,Integer cid) {
        Gser gser = new Gser();
        gser.setUsername(username);
        gser.setRealname(realname);
        gser.setPassword(password);
        gser.setGid(gid);
        gser.setCode(cid);
        try{
            int i = gserMapper.updateGser(gser);
            if (i == 0){
                //更新教师管理员
                int i1 = gserMapper.updateTeacherGser(gser);
                if (i1 == 0){
                    return new Result(ResultCode.DBC_FALIURE);
                }else{
                    gserMapper.updateTeacherGserRole(gid,roleId);
                }
            }else{
                //更新普通管理员
                gserMapper.updateGserRole(gid,roleId);
            }
            return new Result(ResultCode.DBC_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(ResultCode.DBC_FALIURE);
        }
    }

    @Override
    public Result DeleteGser(int gid) {
        try{
            int i = gserMapper.deleteGser(gid);
            if (i == 0){
                //删除的是教师的管理员角色
                //通过gid删除是教师的管理员的管理员角色
                int i1 = gserMapper.updateTeacherGserRole(gid,-1);
            }else{
                int i2 = gserMapper.deleteGserRole(gid);
            }
            return new Result(ResultCode.DBC_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(ResultCode.DBC_FALIURE);
        }
    }

    @Override
    public Result GetRoles() {
        try{
           List<Role> roleList = gserMapper.queryRole();
            return new Result(ResultCode.DBC_SUCCESS,roleList);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(ResultCode.DBC_FALIURE);
        }
    }
}
