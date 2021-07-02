package com.example.demo.dao.mapper;

import com.example.demo.pojo.Userdomain.CurrentUser;
import com.example.demo.pojo.Userdomain.User;
import com.example.demo.pojo.Userdomain.User_Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

//这个注解表示了这是一个mybatis的mapper类；Dao
@Mapper
@Repository
public interface UserMapper {
    List<User> queryUserMes();

    String queryUserNameByNumber(String number);

    List<User> queryUserList();

    /*通过uid获取用户信息*/
    List<User> queryUserMesByUid(int uid);
    //通过uid获取参赛选手信息
    Map<String,Object> queryCUserByid(int uid);

    //通过uid获取参数选手的比赛信息
    List<Map<String,Object>> queryUserMatchWorksByUid(int uid);
    //检索选手信息
    List<User> queryUserByString(Map map);
    int addUser(User user);

    int addRole(User_Role ur);

    int updateUser(User user);

    int updatePwd(String email,String password);

    int updateCode(String email,String code);

    int deleteUser(int uid);

    CurrentUser loginCheck(String username);

    User codeCheck(String code);

    //获取选手管理的详细信息
    List<Map<String,Object>> queryUserMesDetail(Map map);

    //通过作品ID批量查询作者邮箱
    List<String> queryUserEmailByWid(List<String> widList);
}