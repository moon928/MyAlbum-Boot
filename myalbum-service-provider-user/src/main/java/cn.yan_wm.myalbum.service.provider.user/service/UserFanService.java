package cn.yan_wm.myalbum.service.provider.user.service;

import cn.yan_wm.myalbum.commons.domainExtend.user.UserAttentionExtend;
import cn.yan_wm.myalbum.commons.domainExtend.user.UserFanExtend;
import cn.yan_wm.myalbum.commons.model.DataSet;
import tk.mybatis.page.Page;


import java.util.List;
/**
 * @program: MyAlbum-Boot
 * @description: 用户粉丝管理Service
 * @author: yan_zt
 * @create: 2020-03-03 13:57
 */
public interface UserFanService{
    /**
     * 添加粉丝
     * @param userId
     * @param fanId
     * @return
     */
    int addFan(Long userId, Long fanId);

    /**
     * 删除粉丝
     * @param userId
     * @param fanId
     * @return
     */
    int deleteFan(Long userId, Long fanId);

    /**
     * 修改粉丝备注
     * @param userId
     * @param fanId
     * @param note
     * @return
     */
    int updateFanNote(Long userId, Long fanId, String note);

    /**
     * 查询粉丝信息
     * @param userId
     * @param fanId
     * @return
     */
    UserFanExtend getByFanId(Long userId, Long fanId);

    /**
     * 通过用户id 查询所有的粉丝
     * @param userId
     * @return
     */
    List<UserFanExtend> getByUserId(Long userId);

    /**
     * 分页查询粉丝信息
     * @param userId
     * @param page
     * @return
     */
    DataSet<UserFanExtend> page(Long userId, Page page);
}
