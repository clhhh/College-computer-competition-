package com.example.demo.Service.backImpl;

import com.example.demo.Service.WorkService;
import com.example.demo.dao.mapper.WorksMapper;
import com.example.demo.pojo.Userdomain.CurrentUser;
import com.example.demo.pojo.Workdomain.Works;
import com.example.demo.result.Result;
import com.example.demo.result.ResultCode;
import com.example.demo.util.GetRequestTokenUtil;
import com.example.demo.util.TokenSubjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

@Transactional(isolation = Isolation.REPEATABLE_READ)
@Service
public class WorkServiceImpl implements WorkService {

    @Autowired
    GetRequestTokenUtil getRequestTokenUtil;
    @Autowired
    WorksMapper worksMapper;

    @Override
    public Result uploadSubmit(@RequestParam(value = "worksname",required = false)String worksname,
                               @RequestParam(value = "teamname",required = false)String teamname,
                               @RequestParam(value = "mid",required = false)Integer mid,
                               @RequestParam(value = "teacher",required = false)String teacher,
                               @RequestParam(value = "unit",required = false)String unit,
                               @RequestParam(value = "introduce",required = false)String introduce,
                               @RequestParam(value = "design",required = false)String design,
                               @RequestParam(value = "author2_name",required = false)String author2_name,
                               @RequestParam(value = "author2_number",required = false)String author2_number,
                               @RequestParam(value = "author3_name",required = false)String author3_name,
                               @RequestParam(value = "author3_number",required = false)String author3_number,
                               @RequestParam(value = "author4_name",required = false)String author4_name,
                               @RequestParam(value = "author4_number",required = false)String author4_number,
                               @RequestParam(value = "author5_name",required = false)String author5_name,
                               @RequestParam(value = "author5_number",required = false)String author5_number,
                               HttpServletRequest request, HttpServletResponse response){

        String token = getRequestTokenUtil.getToken(request,response);
        CurrentUser user = new TokenSubjectUtil().getUserByToken(token);
        /*进行表单验证*/
        Date d = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        /*定义作者列表*/
        List<String> authorList = new ArrayList<>();
        authorList.add(user.getNumber());

        /*保存报名表信息*/
        Works wk = new Works();
        wk.setUser_id(user.getPid());
        wk.setMid(mid);
        wk.setDate(dateFormat.format(d));
        wk.setDesign(design);
        wk.setIntroduce(introduce);
        wk.setTeacher(teacher);
        wk.setWorksname(worksname);
        wk.setTeamname(teamname);
        wk.setUnit(unit);
        wk.setAuthor1_name(user.getUsername());
        wk.setAuthor1_id(user.getNumber());
        Integer isCourse = worksMapper.queryIsCourse(mid);
        if (isCourse == 0){
            wk.setCertificate(0);
        }else{
            wk.setCertificate(-1);
        }
        if ( !"".equals(author2_name)){
            wk.setAuthor2_id(author2_number);
            wk.setAuthor2_name(author2_name);
            authorList.add(author2_number);

        }else{
            wk.setAuthor2_id(null);
            wk.setAuthor2_name(null);
        }
        if ( !"".equals(author3_name)){
            wk.setAuthor3_id(author3_number);
            wk.setAuthor3_name(author3_name);
            authorList.add(author3_number);
        }else{
            wk.setAuthor3_id(null);
            wk.setAuthor3_name(null);
        }
        if ( !"".equals(author4_name)){
            wk.setAuthor4_id(author4_number);
            wk.setAuthor4_name(author4_name);
            authorList.add(author4_number);
        }else{
            wk.setAuthor4_id(null);
            wk.setAuthor4_name(null);
        }
        if ( !"".equals(author5_name)){
            wk.setAuthor5_id(author5_number);
            wk.setAuthor5_name(author5_name);
            authorList.add(author5_number);
        }else{
            wk.setAuthor5_id(null);
            wk.setAuthor5_name(null);
        }
        try{
            //查询报名表是否已经存在
            int insertSubmitMes=0;int updateSubmit=0;
            //存在：更新；不存在：插入
            Works works = worksMapper.queryWorkMesByUid(user.getPid(), mid);
            if (works == null){
                //报名表不存在，插入
                insertSubmitMes = worksMapper.insertIntoSubmit(wk);
                System.out.println("报名表新增情况"+insertSubmitMes);

                List<Map<String,Object>> list = new ArrayList<>();
                Map<String, Object> map = new HashMap<>();
                map.put("number",user.getNumber());
                map.put("wid",wk.getWid());
                map.put("mid",mid);
                list.add(map);
                int i=0;
                i=worksMapper.insertIntoWorksUser(list);
                System.out.println("作者表新增情况"+i);
                return new Result(ResultCode.SUBMIT_SUCCESS,wk.getWid());

            }else{
                //报名表已经存在，更新
                wk.setWid(works.getWid());
                updateSubmit = worksMapper.updateSubmit(wk);
                System.out.println("报名表更新情况"+updateSubmit);

                List<Map<String,Object>> list = new ArrayList<>();
                Map<String, Object> map = new HashMap<>();
                map.put("number",user.getNumber());
                map.put("wid",wk.getWid());
                map.put("mid",mid);
                list.add(map);
                int i=0;
                i=worksMapper.insertIntoWorksUser(list);
                System.out.println("作者表新增情况"+i);

                return new Result(ResultCode.SUBMIT_SUCCESS,wk.getWid());
            }

//            if (insertSubmitMes!=0 || updateSubmit!=0){
//                //先批量删除作品-作者-比赛映射关系
//                List<Map<String,Object>> user_workslist = new ArrayList<>();
//                HashMap<String, Object> user_worksmap = new HashMap<>();
//                user_worksmap.put("wid",wk.getWid());
//                user_workslist.add(user_worksmap);
//                worksMapper.deleteUser_WorksByWid(user_workslist);
//                /*批量生成作品——作者——比赛映射表*/
//                List<Map<String,Object>> list = new ArrayList<>();
//                System.out.println(authorList.size());
//                for (String authorID : authorList) {
//                    Map<String, Object> map = new HashMap<>();
//                    map.put("number",authorID);
//                    map.put("wid",wk.getWid());
//                    map.put("mid",mid);
//                    list.add(map);
//                }
//                int i1 = worksMapper.insertIntoWorksUser(list);
//                return new Result(ResultCode.SUBMIT_SUCCESS,wk.getWid());
//            }else{
//                return new Result(ResultCode.SUBMIT_FAILURE);
//            }
        }catch (Exception e){
            e.printStackTrace();
            return new Result(ResultCode.SUBMIT_FAILURE);
        }
    }



}
