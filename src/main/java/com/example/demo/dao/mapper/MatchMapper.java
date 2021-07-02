package com.example.demo.dao.mapper;

import com.example.demo.pojo.Dynamic;
import com.example.demo.pojo.Match;
import com.example.demo.pojo.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface MatchMapper {
    List<Match> queryMatchMes();
    List<Match> fastqueryMatchMes();
//    List<Match> queryContentMes();
    List<Map<String,Object>> queryMatchID();
    List<Match> queryMatchMesByUid(String number);
    List<Match> queryMatchMesByMid(int mid);
    List<Match> queryMatchContentByMid(int mid);
    List<Match> queryMatchMesByString(Map map);
    int updateMatchMes(Match match);
    int addMatchMes(Match match);
    int deleteMatchMes(int mid);

    List<Notice> queryNoticeByMname(String match_name);

    List<Dynamic> queryDynamicByMname(String match_name);

    List<Match> queryMatchMesOrderByname();

    List<Dynamic>  queryDynamicImgByMname(String match_name);

    int UpdateOnTimeByMid(Integer mid, Integer onTime);
    //查询上线的比赛
    List<Match> queryMatchMesonTime();
    //查询作品赛
    List<Match> queryMatchMesisProject();
    //查询作品赛非课程赛的比赛
    List<Match> queryMatchMesisProject_NoCor();
    //查询作品赛且是课程赛的比赛
    List<Match> queryMatchMesonTimeAndPro_Cor();
    int UpdateProjectByMid(Integer mid, Integer isProject);

    List<Match> queryMatchMesByName(String match_name);

    int UpdateCourseByMid(Integer mid, Integer isCourse);


    List<Map<String,Object>> queryMatchMesonTimeAndPro_CorByCid(int cid);
    //跟新竞赛是否已经出线
    int UpdateCodeByMid(Integer mid, int code);

    int deleteDistributeByMid(@Param("widList") List<Integer> widList);

    int deleteCXWorksByMid(Integer mid);

    int deletePrize_Model_LevelByMid(Integer mid);

    int UpdateIsSortByMid(Integer mid,Integer isSort);


}
