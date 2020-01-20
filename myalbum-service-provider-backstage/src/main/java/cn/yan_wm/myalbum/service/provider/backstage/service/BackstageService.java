package cn.yan_wm.myalbum.service.provider.backstage.service;

import cn.yan_wm.myalbum.commons.domain.IndexPageView;
import cn.yan_wm.myalbum.commons.domainExtend.backstage.BackstageIndexDto;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

/**
 * @program: MyAlbum-Boot
 * @description: 后台首页Service
 * @author: yan_zt
 * @create: 2020-01-10 11:23
 */
public interface BackstageService<list> {
    /**
     * 查询过去近七天的新增用户量
     * @param
     * @return
     */
    List<BackstageIndexDto> getNewUsersAWeek();

    /**
     * 获取前n个月的新增用户数
     * @param n
     * @return
     */
    List<BackstageIndexDto> getNewUsersMonth(int n);

    /**
     * 获取vip等级分布
     *
     * @param
     * @exception
     * @return
     * @author Yzn_zt
     * @date 2020/1/10 14:01
     */
    List<BackstageIndexDto> getVipDistribution();

    /**
     * 当没有当天记录时添加一天记录
     *
     * @param
     * @exception
     * @return
     * @author Yzn_zt
     * @date 2020/1/10 15:56
     */
    int addFrequency();

    /**
     * 通过id修改当天的浏览记录
     * @param id
     * @return
     */
    int updaFrequencyById(int id);

    /**
     * 将滔添加访问量的那条记录发送到rabiitMq
     * @param indexPageViewId
     */
    @Async
    void sendIndexPageViewIdToMPQ(Long indexPageViewId);
    /**
     * 通过时间查询当前时间是否有记录
     * @param time
     * @return
     */
    List<IndexPageView> getFrequencyByTime(String time);

    /**
     * 查询近七天的首页访问量
     *
     * @param
     * @exception
     * @return
     * @author Yzn_zt
     * @date 2020/1/10 17:07
     */
    List<BackstageIndexDto> getIndexPageViewsAWeek();

    /**
     * 查询近n个月的首页访问量
     * @param n
     * @return
     */
    List<BackstageIndexDto> getIndexPageViewsMonth(int n);
}
