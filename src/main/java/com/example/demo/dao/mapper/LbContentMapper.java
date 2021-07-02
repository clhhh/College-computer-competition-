package com.example.demo.dao.mapper;

import com.example.demo.pojo.LBphoto;
import com.example.demo.pojo.LBvedio;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface LbContentMapper {
    //查询轮播图最大的序号
    int querylbPhotoMaxNum();
    //查询轮播图
    List<LBphoto> querylbPhoto();
    //根据ID查询轮播图
    List<LBphoto> querylbPhotobyid(int id);
    //查询视频
    List<LBvedio> querylbVedio();
    //根据ID查询视频
    List<LBvedio> querylbVediobyid(int id);


    //添加轮播图
    int insertlbPhoto(LBphoto lBphoto);
    //删除轮播图
    int deletelbPhoto(int id);
    //更新轮播图
    int updatelbPhoto(LBphoto lBphoto);

    //添加视频
    int insertlbVedio(LBvedio lBvedio);
    //删除视频
    int deletelbVedio(int id);
    //更新视频
    int updatelbVedio(LBvedio lBvedio);

    //调整轮播图的顺序
    int updatelbPhotoNum(List<Map<String,Object>>list );
}
