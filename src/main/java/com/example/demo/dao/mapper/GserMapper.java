package com.example.demo.dao.mapper;

import com.example.demo.pojo.Userdomain.CurrentUser;
import com.example.demo.pojo.Userdomain.Gser;
import com.example.demo.pojo.Userdomain.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface GserMapper {
    CurrentUser loginCheck(String username);
    /*查询所有管理员*/
    List<Gser> queryGserList();
    /*通过id查询管理员*/
    List<Gser> queryGserByGid(int gid);
    /*通过真实姓名查询管理员*/
    List<Gser> queryGserByString(Map map);
    /*通过id查询管理员角色*/
    List<String> queryGserRole(int gid);
    /*通过gid添加到gser-role*/
    int insertRoleByid(int rid,int gid);
    //更新gser_role表数据通过gid
    int updateGserRole(Integer gid,Integer rid);
    /*添加新的管理员用户*/
    int insertGser(Gser gser);
    /*修改管理员用户*/
    int updateGser(Gser gser);
    /*删除管理员角色表*/
    int deleteGserRole(int gid);
    /*删除管理员用户*/
    int deleteGser(int gid);
    //通过Gid查询code的值
    int queryCodeByGid(int gid);
    //通过Gid查询cid名字
    String queryClubName(int gid);
    List<Role>  queryRole();

    int updateTeacherGser(Gser gser);
    //
    int updateTeacherGserRole(int gid,int rid);


}
