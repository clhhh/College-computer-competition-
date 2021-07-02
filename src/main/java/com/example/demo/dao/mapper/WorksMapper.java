package com.example.demo.dao.mapper;

import com.example.demo.pojo.Author;
import com.example.demo.pojo.Similarity;
import com.example.demo.pojo.Workdomain.Works;
import com.example.demo.pojo.Workdomain.WorksMes;
import com.example.demo.pojo.Workdomain.WorksPF;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface WorksMapper {

    /*通过作品id查询单个作品信息--无奖项*/
    WorksMes queryWorksMesByWidNoPrize(int wid);
    /*通过作品id查询作品信息(不包含 图片 ，作者)*/
    WorksMes queryWorksMesByWid(int wid);
    /*通过作品id查询作品信息(图片 )*/
    Map<String,Object> queryWorksImgByWid(int wid);
    /*通过作品id查询作品信息(作者)*/
    Author queryWorksAuthorByWid(int wid);
    /*通过作品id查询作品获奖*/
    String queryWorksPrizeName(int wid);
    /*通过选手id查询作品信息*/
    List<Works> queryWorkMesByNumber(String number);
    //通过队长用户uid作品信息
    Works queryWorkMesByUid(int uid, int mid);

    //通过竞赛ID查找作品ID
    List<Integer> queryWidByMid(int mid);
    //搜索全部作品信息
    List<Works> queryWorksMes();

    /*查询所有作品的信息*/
    List<WorksPF> queryAllWorks();
    /*通过字符串查询全部作品*/
    List<WorksPF> queryAllWorksByString(Map map);


    /*查询所有 待审核 的作品信息*/
    List<WorksPF> queryWorksMesA();
    /* 待审核 作品的检索操作*/
    List<WorksPF> queryWorksMesAByString(Map map);
    /*审核或退回或分配 作品，根据r=1(审核) or -1（退回到用户）or 2（已经分配）or 3(退回到分配阶段)，可修改多行的nor*/
    int updateWorksNor(List<Map<String,Object>> list);
    int deleteUser_WorksByWid(List<Map<String,Object>> list);


    /*查询所有 待分配 的作品信息*/
    List<WorksPF> queryWorksMesB();
    /* 待分配 作品的检索操作*/
    List<WorksPF> queryWorksMesBByString(Map map);
    /*分配操作*/
    int insertIntoDistribute(List<Map<String,Object>> list);



    /*查询所有 已分配 的作品信息*/
    List<WorksPF> queryWorksMesC();
    /* 已分配 作品的检索操作*/
    List<WorksPF> queryWorksMesCByString(Map map);
    /*已分配作品的退回操作*/
    int deleteDistributeByWid(int wid);



    List<WorksPF> queryReturnWorksMes(Map map);




    List<Works> findallWork(Map map);

    //保存作品文件信息
    int updateWorksMes_NOVedio(Works works);
    int updateWorksVideo(String videoPath,Integer wid);
    //保存报名表信息
    int insertIntoSubmit(Works wk);
    //更新报名表信息
    int updateSubmit(Works wk);
    /*前台对已经分配好的作品进行评分操作*/
    int updateDistribute(int wid,String markMes,int score,int tid,String estimate);
    /*只修改一行作品的nor*/
    int updateWorksNorOne(int wid,int r);

    /*通过mid获取比赛时间*/
    Map<String,Object> ChecktoSubmit(int mid);
    /*将作者和比赛相连*/
    int insertIntoWorksUser(List<Map<String,Object>> list);
    /*通过mid和uid获取作品，看看用户是否已经参赛*/
    Integer ChecktoMatch(int uid,int mid);

    /*根据作品ID查询作品关联的pdf文件路径*/
    String queryWorksPDF(Integer wid);

    List<Works> queryNiceWorks();

    //查询作品赛的比赛作品
    List<Works> queryWorksProject(Map map);

    //通过作品id查询作品对应的比赛
    int queryMid(int wid);

    //通过作品id查找负责人信息
    int queryAuthorIdByWid(int wid);

    //查询高度相似作品信息
    List<WorksPF> querySimilarWorks(Map map);


    int insertIntoSimilar(List<Map<String, Object>> list);

    List<Similarity> queryFirstSimilarWorksByWid(int wid);

    List<Similarity> querySecondWorksByWid(int wid);

    //清空Similar表的数据
    int deleteSimilarTable();

    List<WorksPF> queryNoScoreWorksMes(Map map);

    int updateSimilarWorksNor(int nor);
    //从works表中所有字段进行查询
    List<Works> searchAllWorks(String sql);

    //检索课程赛作品
    List<Works> queryCourseWorks(Map map);

    //获取match中 isCourse的值，用于判断比赛是否为课程赛
    Integer queryIsCourse(Integer mid);

    int queryNormlizationByWid(int wid);
}
