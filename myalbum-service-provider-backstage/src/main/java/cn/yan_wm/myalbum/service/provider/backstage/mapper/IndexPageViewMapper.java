package cn.yan_wm.myalbum.service.provider.backstage.mapper;

import cn.yan_wm.myalbum.commons.domain.IndexPageView;
import cn.yan_wm.myalbum.commons.domainExtend.backstage.BackstageIndexDto;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;

import java.util.List;
/**
 * @program: MyAlbum-Boot
 * @description: 后台首页Mapper
 * @author: yan_zt
 * @create: 2020-03-03 13:57
 */
public interface IndexPageViewMapper extends MyMapper<IndexPageView> {
    /**
     * 添加
     * @param
     * @return
     */
    int add();

    /**
     * 通过时间查询首页访问量
     * @param time
     * @return
     */
    List<IndexPageView> findByTime(@Param("time") String time);

    /**
     * 通过id添加访问量
     * @param id
     * @return
     */
    int addFrequencyById(@Param("id") int id);

    /**
     * 查询过去第n天的访问记录
     * @param n
     * @return
     */
    BackstageIndexDto getIndexPageViews(@Param("n") int n);

    /**
     * 查询过去第n月的访问记录
     * @param n
     * @return
     */
    BackstageIndexDto getIndexPageViewsMonth(@Param("n") int n);
}
